/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bean;

import domain.Tweet;
import java.util.Collection;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import service.IUserService;

/**
 *
 * @author Jurgen
 */
@Named
@RequestScoped
public class searchBean {


    @Inject
    private IUserService service;
    private String filter;

    /** Creates a new instance of searchBean */
    public searchBean() {
    }

    public void setFilter(String filter) {
        this.filter = filter.toLowerCase();
    }

    public String getFilter() {
        return this.filter;
    }

    public Collection<Tweet> getSearchedTweets() {
        return service.getSearchedTweets(filter);
    }

    public Collection<Tweet> getMentions() {
        FacesContext context = FacesContext.getCurrentInstance();
        String paramUser = (String)context.getExternalContext().getRequestParameterMap().get("user");
        if(paramUser != null)
        {
            return service.getSearchedTweets("@" + paramUser);
        }
        return service.getSearchedTweets("@default");

    }

}
