package cz.zcu.kiv.eegdatabase.logic.controller.group;

import cz.zcu.kiv.eegdatabase.data.dao.GenericDao;
import cz.zcu.kiv.eegdatabase.data.dao.PersonDao;
import cz.zcu.kiv.eegdatabase.data.dao.ResearchGroupDao;
import cz.zcu.kiv.eegdatabase.data.pojo.GroupPermissionRequest;
import cz.zcu.kiv.eegdatabase.data.pojo.Person;
import cz.zcu.kiv.eegdatabase.data.pojo.ResearchGroup;
import cz.zcu.kiv.eegdatabase.data.service.MailService;
import cz.zcu.kiv.eegdatabase.logic.util.ControllerUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.HierarchicalMessageSource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EditGroupRoleController extends SimpleFormController {
    private Log log = LogFactory.getLog(getClass());
    private PersonDao personDao;
    private ResearchGroupDao researchGroupDao;
    private GenericDao<GroupPermissionRequest, Integer> groupPermissionRequestDao;
    private MailService mailService;

    /**
     * Source of localized messages defined in persistence.xml
     */
    private HierarchicalMessageSource messageSource;

    public EditGroupRoleController() {
        setCommandClass(EditGroupRoleCommand.class);
        setCommandName("editGroupRoleCommand");
    }

    @Override
    protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException bindException) throws Exception {
        EditGroupRoleCommand groupRoleCommand = (EditGroupRoleCommand) command;

        Person user = personDao.getPerson(ControllerUtils.getLoggedUserName());
        GroupPermissionRequest gpr = new GroupPermissionRequest();
        gpr.setPerson(user);
        String requestRole = groupRoleCommand.getRole();
        gpr.setRequestedPermission(requestRole);
        gpr.setResearchGroup(researchGroupDao.read(groupRoleCommand.getEditedGroup()));
        groupPermissionRequestDao.create(gpr);

        String researchGroupTitle = researchGroupDao.read(groupRoleCommand.getEditedGroup()).getTitle();
        String emailAdmin = researchGroupDao.read(groupRoleCommand.getEditedGroup()).getPerson().getEmail();

        try {
            mailService.sendRequestForGroupRoleMail(emailAdmin, gpr.getRequestId(), user.getUsername(),
                    researchGroupTitle, RequestContextUtils.getLocale(request));
        } catch (MailException e) {
            log.debug("E-mail was NOT sent");
            e.printStackTrace();
        }

        log.debug("Returning MAV");
        ModelAndView mav = new ModelAndView("groups/requestSent");
        GroupMultiController.setPermissionToRequestGroupRole(mav, personDao.getLoggedPerson());
        return mav;
    }

    @Override
    protected Map referenceData(HttpServletRequest request) throws Exception {
        Map map = new HashMap<String, Object>();
        List<ResearchGroup> researchGroupList =
                researchGroupDao.getResearchGroupsWhereMember(personDao.getLoggedPerson());
        ResearchGroup defaultGroup = personDao.getLoggedPerson().getDefaultGroup();
        int defaultGroupId = (defaultGroup != null) ? defaultGroup.getResearchGroupId() : 0;
        map.put("defaultGroupId", defaultGroupId);
        map.put("researchGroupList", researchGroupList);
        GroupMultiController.setPermissionToRequestGroupRole(map, personDao.getLoggedPerson());
        return map;
    }

    public PersonDao getPersonDao() {
        return personDao;
    }

    public void setPersonDao(PersonDao personDao) {
        this.personDao = personDao;
    }

    public ResearchGroupDao getResearchGroupDao() {
        return researchGroupDao;
    }

    public void setResearchGroupDao(ResearchGroupDao researchGroupDao) {
        this.researchGroupDao = researchGroupDao;
    }

    public GenericDao<GroupPermissionRequest, Integer> getGroupPermissionRequestDao() {
        return groupPermissionRequestDao;
    }

    public void setGroupPermissionRequestDao(GenericDao<GroupPermissionRequest, Integer> groupPermissionRequestDao) {
        this.groupPermissionRequestDao = groupPermissionRequestDao;
    }

    /**
     * @return the messageSource
     */
    public HierarchicalMessageSource getMessageSource() {
        return messageSource;
    }

    /**
     * @param messageSource the messageSource to set
     */
    public void setMessageSource(HierarchicalMessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public MailService getMailService() {
        return mailService;
    }

    public void setMailService(MailService mailService) {
        this.mailService = mailService;
    }

}
