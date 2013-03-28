package cz.zcu.kiv.eegdatabase.wui.components.page;

import javax.servlet.http.HttpServletResponse;

import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.request.http.WebResponse;

import cz.zcu.kiv.eegdatabase.wui.components.utils.ResourceUtils;
import cz.zcu.kiv.eegdatabase.wui.ui.home.HomePage;

/**
 * Access Denied page for EEGDatabase, with header, footer and menu section.
 * 
 * @author Jakub Rinkes
 * 
 */
public class AccessDeniedPage extends MenuPage {

    private static final long serialVersionUID = 1L;

    public AccessDeniedPage() {
        super();

        setPageTitle(ResourceUtils.getModel("title.page.access.denied"));
        add(new BookmarkablePageLink<MenuPage>("goToHomeLink", HomePage.class));
    }

    @Override
    protected void setHeaders(final WebResponse response) {

        super.setHeaders(response);
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
    }

    @Override
    public boolean isErrorPage() {

        return true;
    }
}
