/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bean;

import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import service.IUserService;

/**
 *
 * @author DeluxZ
 */
@Named
@RequestScoped
public class statsBean {

    @Inject
    private IUserService service;

    /** Creates a new instance of statsBean */
    public statsBean() {
    }

    public int getFollowingCount() {
        FacesContext context = FacesContext.getCurrentInstance();
        String paramUser = (String)context.getExternalContext().getRequestParameterMap().get("user");
        if(paramUser != null)
        {
            return service.getFollowing(paramUser).size();
        }
        return service.getFollowing("default").size();
    }

    public int getFollowerCount() {
        FacesContext context = FacesContext.getCurrentInstance();
        String paramUser = (String)context.getExternalContext().getRequestParameterMap().get("user");
         if(paramUser != null)
        {
            return service.getFollowers(paramUser).size();
        }
        return service.getFollowers("default").size();
    }

    public int getTweetCount() {
        FacesContext context = FacesContext.getCurrentInstance();
        String paramUser = (String)context.getExternalContext().getRequestParameterMap().get("user");
         if(paramUser != null)
        {
            return service.findUserByName(paramUser).getTweets().size();
        }
        return service.findUserByName("default").getTweets().size();
    }

}
