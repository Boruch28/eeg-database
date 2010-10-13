package cz.zcu.kiv.eegdatabase.logic.controller.scenario;

import cz.zcu.kiv.eegdatabase.data.dao.AuthorizationManager;
import cz.zcu.kiv.eegdatabase.data.dao.PersonDao;
import cz.zcu.kiv.eegdatabase.data.dao.ResearchGroupDao;
import cz.zcu.kiv.eegdatabase.data.dao.ScenarioDao;
import cz.zcu.kiv.eegdatabase.data.pojo.ResearchGroup;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Hibernate;
import org.springframework.validation.BindException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import cz.zcu.kiv.eegdatabase.data.pojo.Scenario;
import java.util.HashMap;
import java.util.List;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Controller which processes form for adding a measuration
 *
 * @author Jindra
 */
public class AddScenarioController
        extends SimpleFormController
        implements Validator {

  private Log log = LogFactory.getLog(getClass());
  private AuthorizationManager auth;
  private ScenarioDao scenarioDao;
  private PersonDao personDao;
  private ResearchGroupDao researchGroupDao;

  public AddScenarioController() {
    setCommandClass(AddScenarioCommand.class);
    setCommandName("addScenario");
  }

  @Override
  protected ModelAndView showForm(HttpServletRequest request, HttpServletResponse response, BindException errors) throws Exception {
    ModelAndView mav = super.showForm(request, response, errors);

    mav.addObject("userIsExperimenter", auth.userIsExperimenter());

    if (!auth.userIsExperimenter()) {
      mav.setViewName("scenario/userNotExperimenter");
    }

    return mav;
  }

  @Override
  protected Map referenceData(HttpServletRequest request) throws Exception {
    Map map = new HashMap<String, Object>();

    List<ResearchGroup> groups = researchGroupDao.getResearchGroupsWhereAbleToWriteInto(personDao.getLoggedPerson());
    map.put("researchGroupList", groups);

    ResearchGroup defaultGroup = personDao.getLoggedPerson().getDefaultGroup();
    int defaultGroupId = (defaultGroup != null) ? defaultGroup.getResearchGroupId() : 0;
    map.put("defaultGroupId",defaultGroupId);


    return map;
  }

  @Override
  protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException bindException) throws Exception {
    ModelAndView mav = new ModelAndView(getSuccessView());

    log.debug("Processing form data");
    AddScenarioCommand data = (AddScenarioCommand) command;

    if (!auth.userIsExperimenter()) {
      mav.setViewName("scenario/userNotExperimenter");
      return mav;
    }

    MultipartFile file = data.getDataFile();

    if (file == null) {
      log.error("No data file was uploaded!");
    } else {
      log.debug("Creating new scenario object");
      Scenario scenario = new Scenario();

      log.debug("Setting owner to the logged user.");
      scenario.setPerson(personDao.getLoggedPerson());

      log.debug("Setting research group.");
      ResearchGroup group = new ResearchGroup();
      group.setResearchGroupId(data.getResearchGroup());
      scenario.setResearchGroup(group);

      log.debug("Setting scenario title: " + data.getTitle());
      scenario.setTitle(data.getTitle());

      log.debug("Setting scenario description: " + data.getDescription()); 
      scenario.setDescription(data.getDescription());

      log.debug("Setting scenario length: " + data.getLength());
      scenario.setScenarioLength(Integer.parseInt(data.getLength()));

      log.debug("Setting XML data file");
      scenario.setScenarioXml(Hibernate.createClob(new String(file.getBytes())));

      log.debug("Setting private/public access"); 
      scenario.setPrivateScenario(data.getPrivateNote()); 

      log.debug("Saving scenario object");
      scenarioDao.create(scenario);

      log.debug("Scenario saved to database");
    }

    log.debug("Returning MAV");
    return mav;
  }

  public boolean supports(Class clazz) {
    return clazz.equals(AddScenarioCommand.class);
  }

  public void validate(Object command, Errors errors) {
    log.debug("Validating scenario form");
    AddScenarioCommand data = (AddScenarioCommand) command;

    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "required.field");
    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "length", "required.field");
    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "required.field");

    if (data.getResearchGroup() == -1) {
      // research group not chosen
      errors.rejectValue("researchGroup", "required.researchGroup");
    } else if (!auth.personAbleToWriteIntoGroup(data.getResearchGroup())) {
      errors.rejectValue("researchGroup", "invalid.notAbleToAddExperimentInGroup");
    }

    try {
      Integer.parseInt(data.getLength());
    } catch (NumberFormatException ex) {
      errors.rejectValue("length", "invalid.scenarioLength");
      log.debug("Scenario length is not in parseable format!");
    }

    if (data.getDataFile().isEmpty()) {
      errors.rejectValue("dataFile", "required.dataFile");
      log.debug("No data file was inserted!");
    }
  }

  public ScenarioDao getScenarioDao() {
    return scenarioDao;
  }

  public void setScenarioDao(ScenarioDao scenarioDao) {
    this.scenarioDao = scenarioDao;
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

  public AuthorizationManager getAuth() {
    return auth;
  }

  public void setAuth(AuthorizationManager auth) {
    this.auth = auth;
  }
}