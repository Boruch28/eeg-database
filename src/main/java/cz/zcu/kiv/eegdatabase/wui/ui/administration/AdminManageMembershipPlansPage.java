package cz.zcu.kiv.eegdatabase.wui.ui.administration;

import cz.zcu.kiv.eegdatabase.data.pojo.MembershipPlan;
import cz.zcu.kiv.eegdatabase.data.pojo.Person;
import cz.zcu.kiv.eegdatabase.data.pojo.PromoCode;
import cz.zcu.kiv.eegdatabase.wui.app.session.EEGDataBaseSession;
import cz.zcu.kiv.eegdatabase.wui.components.form.input.AjaxConfirmLink;
import cz.zcu.kiv.eegdatabase.wui.components.menu.button.ButtonPageMenu;
import cz.zcu.kiv.eegdatabase.wui.components.page.MenuPage;
import cz.zcu.kiv.eegdatabase.wui.components.page.UnderConstructPage;
import cz.zcu.kiv.eegdatabase.wui.components.utils.PageParametersUtils;
import cz.zcu.kiv.eegdatabase.wui.components.utils.ResourceUtils;
import cz.zcu.kiv.eegdatabase.wui.core.membershipplan.MembershipPlanFacade;
import cz.zcu.kiv.eegdatabase.wui.core.membershipplan.PersonMembershipPlanFacade;
import cz.zcu.kiv.eegdatabase.wui.core.membershipplan.ResearchGroupMembershipPlanFacade;
import cz.zcu.kiv.eegdatabase.wui.core.person.PersonFacade;
import cz.zcu.kiv.eegdatabase.wui.core.promocode.PromoCodeFacade;
import cz.zcu.kiv.eegdatabase.wui.ui.account.MyAccountPageLeftMenu;
import cz.zcu.kiv.eegdatabase.wui.ui.administration.forms.MembershipPlanManageFormPage;
import cz.zcu.kiv.eegdatabase.wui.ui.administration.forms.PromoCodeManageFormPage;
import cz.zcu.kiv.eegdatabase.wui.ui.home.HomePage;
import cz.zcu.kiv.eegdatabase.wui.ui.memberships.MembershipPlansDetailPage;
import cz.zcu.kiv.eegdatabase.wui.ui.people.form.PersonFormPage;
import cz.zcu.kiv.eegdatabase.wui.ui.promoCodes.PromoCodeDetailPage;
import org.apache.wicket.RestartResponseAtInterceptPageException;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by Lichous on 6.4.15.
 */

@AuthorizeInstantiation(value = {"ROLE_ADMIN"})
public class AdminManageMembershipPlansPage extends MenuPage {

    private static final long serialVersionUID = -5514198024012232250L;

    private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

    @SpringBean
    MembershipPlanFacade membershipPlanFacade;

    @SpringBean
    PromoCodeFacade promoCodeFacade;

    @SpringBean
    ResearchGroupMembershipPlanFacade researchGroupMembershipPlanFacade;

    @SpringBean
    PersonMembershipPlanFacade personMembershipPlanFacade;

    public AdminManageMembershipPlansPage() {

        setPageTitle(ResourceUtils.getModel("pageTitle.membershipPlans"));
        add(new ButtonPageMenu("leftMenu", AdministrationPageLeftMenu.values()));
        Person user = EEGDataBaseSession.get().getLoggedUser();

        if (user == null)
            throw new RestartResponseAtInterceptPageException(HomePage.class);


        List<MembershipPlan> personMembershipPlanList = membershipPlanFacade.getAvailablePersonMembershipPlans();
        List<MembershipPlan> groupMembershipPlanList = membershipPlanFacade.getAvailableGroupMembershipPlans();



        ListView<MembershipPlan> personPlans = new ListView<MembershipPlan>("personPlans", personMembershipPlanList) {

            private static final long serialVersionUID = 1L;

            @Override
            protected void populateItem(final ListItem<MembershipPlan> item) {
                MembershipPlan modelObject = item.getModelObject();
                item.add(new Label("name", modelObject.getName()));
                item.add(new Label("price", modelObject.getPrice()));
                item.add(new Label("length",modelObject.getLength()));
                AjaxConfirmLink<Void> deleteLink = new AjaxConfirmLink<Void>("deleteLink", ResourceUtils.getString("text.delete.membershipplan")) {

                    private static final long serialVersionUID = 1L;

                    @Override
                    public void onClick(AjaxRequestTarget target) {
                        if(personMembershipPlanFacade.isPlanUsed(item.getModelObject().getMembershipId())) {
                            item.getModelObject().setValid(false);
                            membershipPlanFacade.update(item.getModelObject());
                        } else {
                            membershipPlanFacade.delete(item.getModelObject());
                        }

                        setResponsePage(AdminManageMembershipPlansPage.class);
                    }
                };
                deleteLink.setVisibilityAllowed(true);
                item.add(deleteLink);

                BookmarkablePageLink<Void> editLink = new BookmarkablePageLink<Void>("editLink", MembershipPlanManageFormPage.class,PageParametersUtils.getDefaultPageParameters(item.getModelObject().getMembershipId()));
                editLink.setVisibilityAllowed(true);
                item.add(editLink);

                BookmarkablePageLink<Void> detailLink = new BookmarkablePageLink<Void>("detailLink", MembershipPlansDetailPage.class,PageParametersUtils.getDefaultPageParameters(item.getModelObject().getMembershipId()));
                detailLink.setVisibilityAllowed(true);
                item.add(detailLink);
            }
        };

        ListView<MembershipPlan> groupPlans = new ListView<MembershipPlan>("groupPlans", groupMembershipPlanList) {

            private static final long serialVersionUID = 1L;

            @Override
            protected void populateItem(final ListItem<MembershipPlan> item) {
                MembershipPlan modelObject = item.getModelObject();
                item.add(new Label("name", modelObject.getName()));
                item.add(new Label("price", modelObject.getPrice()));
                item.add(new Label("length",modelObject.getLength()));
                AjaxConfirmLink<Void> deleteLink = new AjaxConfirmLink<Void>("deleteLink", ResourceUtils.getString("text.delete.membershipplan")) {

                    private static final long serialVersionUID = 1L;

                    @Override
                    public void onClick(AjaxRequestTarget target) {
                        if(researchGroupMembershipPlanFacade.isPlanUsed(item.getModelObject().getMembershipId()))  {
                            item.getModelObject().setValid(false);
                            membershipPlanFacade.update(item.getModelObject());
                        } else {
                            membershipPlanFacade.delete(item.getModelObject());
                        }

                        setResponsePage(AdminManageMembershipPlansPage.class);
                    }
                };
                deleteLink.setVisibilityAllowed(true);
                item.add(deleteLink);

                BookmarkablePageLink<Void> editLink = new BookmarkablePageLink<Void>("editLink", MembershipPlanManageFormPage.class,PageParametersUtils.getDefaultPageParameters(item.getModelObject().getMembershipId()));
                editLink.setVisibilityAllowed(true);
                item.add(editLink);

                BookmarkablePageLink<Void> detailLink = new BookmarkablePageLink<Void>("detailLink", MembershipPlansDetailPage.class,PageParametersUtils.getDefaultPageParameters(item.getModelObject().getMembershipId()));
                detailLink.setVisibilityAllowed(true);
                item.add(detailLink);
            }
        };

        BookmarkablePageLink<Void> addPlan = new BookmarkablePageLink<Void>("addPlan", MembershipPlanManageFormPage.class);

        add(personPlans,groupPlans,addPlan);

        List<PromoCode> personPromoCodeList = promoCodeFacade.getAvailablePersonPromoCodes();
        List<PromoCode> groupPromoCodeList = promoCodeFacade.getAvailableGroupPromoCodes();


        ListView<PromoCode> personPromoCodes = new ListView<PromoCode>("personPromoCodes",personPromoCodeList) {

            private static final long serialVersionUID = 1L;

            @Override
            protected void populateItem(final ListItem<PromoCode> item) {
                PromoCode modelObject = item.getModelObject();
                item.add(new Label("keyword", modelObject.getKeyword()));
                item.add(new Label("discount", modelObject.getDiscount()));
                item.add(new Label("dateFrom",dateFormat.format(modelObject.getFrom())));
                item.add(new Label("dateTo",dateFormat.format(modelObject.getTo())));
                AjaxConfirmLink<Void> deleteLink = new AjaxConfirmLink<Void>("deleteLink", ResourceUtils.getString("text.delete.promocode")) {

                    private static final long serialVersionUID = 1L;

                    @Override
                    public void onClick(AjaxRequestTarget target) {
                        promoCodeFacade.delete(item.getModelObject());
                        setResponsePage(AdminManageMembershipPlansPage.class);
                    }
                };
                deleteLink.setVisibilityAllowed(true);
                item.add(deleteLink);

                BookmarkablePageLink<Void> editLink = new BookmarkablePageLink<Void>("editLink", PromoCodeManageFormPage.class,PageParametersUtils.getDefaultPageParameters(item.getModelObject().getPromoCodeId()));
                editLink.setVisibilityAllowed(true);
                item.add(editLink);

                BookmarkablePageLink<Void> detailLink = new BookmarkablePageLink<Void>("detailLink", PromoCodeDetailPage.class,PageParametersUtils.getDefaultPageParameters(item.getModelObject().getPromoCodeId()));
                detailLink.setVisibilityAllowed(true);
                item.add(detailLink);
            }
        };

        ListView<PromoCode> groupPromoCodes = new ListView<PromoCode>("groupPromoCodes",groupPromoCodeList) {

            private static final long serialVersionUID = 1L;

            @Override
            protected void populateItem(final ListItem<PromoCode> item) {

                PromoCode modelObject = item.getModelObject();
                item.add(new Label("keyword", modelObject.getKeyword()));
                item.add(new Label("discount", modelObject.getDiscount()));
                item.add(new Label("dateFrom",dateFormat.format(modelObject.getFrom())));
                item.add(new Label("dateTo",dateFormat.format(modelObject.getTo())));
                AjaxConfirmLink<Void> deleteLink = new AjaxConfirmLink<Void>("deleteLink", ResourceUtils.getString("text.delete.promocode")) {

                    private static final long serialVersionUID = 1L;

                    @Override
                    public void onClick(AjaxRequestTarget target) {
                        promoCodeFacade.delete(item.getModelObject());
                        setResponsePage(AdminManageMembershipPlansPage.class);
                    }
                };
                deleteLink.setVisibilityAllowed(true);
                item.add(deleteLink);

                BookmarkablePageLink<Void> editLink = new BookmarkablePageLink<Void>("editLink", PromoCodeManageFormPage.class,PageParametersUtils.getDefaultPageParameters(item.getModelObject().getPromoCodeId()));
                editLink.setVisibilityAllowed(true);
                item.add(editLink);

                BookmarkablePageLink<Void> detailLink = new BookmarkablePageLink<Void>("detailLink", PromoCodeDetailPage.class,PageParametersUtils.getDefaultPageParameters(item.getModelObject().getPromoCodeId()));
                detailLink.setVisibilityAllowed(true);
                item.add(detailLink);
            }
        };

        BookmarkablePageLink<Void> addPromoCode = new BookmarkablePageLink<Void>("addPromoCode", PromoCodeManageFormPage.class);

        add(personPromoCodes,groupPromoCodes,addPromoCode);
        //throw new RestartResponseAtInterceptPageException(UnderConstructPage.class);
    }
}