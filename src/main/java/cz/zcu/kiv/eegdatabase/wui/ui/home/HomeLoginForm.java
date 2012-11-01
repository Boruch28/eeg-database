package cz.zcu.kiv.eegdatabase.wui.ui.home;

import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.validation.validator.StringValidator;

import cz.zcu.kiv.eegdatabase.wui.components.utils.ResourceUtils;
import cz.zcu.kiv.eegdatabase.wui.core.dto.UserDTO;
import cz.zcu.kiv.eegdatabase.wui.core.facade.security.SecurityFacade;
import cz.zcu.kiv.eegdatabase.wui.ui.articles.ArticlesPage;

public class HomeLoginForm extends Form<UserDTO> {

    private static final long serialVersionUID = -5196364867691352802L;

    @SpringBean
    SecurityFacade secFacade;

    public HomeLoginForm(String id) {
        super(id, new CompoundPropertyModel<UserDTO>(new UserDTO()));

        TextField<String> userName = new TextField<String>("userName");
        userName.add(new StringValidator(5, 25));
        userName.setRequired(true);

        add(userName);

        PasswordTextField password = new PasswordTextField("password");
        userName.add(new StringValidator(5, 25));
        userName.setRequired(true);

        add(password);

        Button submit = new Button("submit", ResourceUtils.getModel("action.login")) {

            private static final long serialVersionUID = 1L;

            @Override
            public void onSubmit() {
                UserDTO object = HomeLoginForm.this.getModelObject();
                if (secFacade.authorization(object.getUserName(), object.getPassword())) {
                    continueToOriginalDestination();
                    setResponsePage(ArticlesPage.class);

                } else {
                    error("fail");
                }
            }
        };
        add(submit);
    }

}