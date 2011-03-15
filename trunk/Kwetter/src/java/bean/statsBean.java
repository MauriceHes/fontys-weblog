/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bean;

import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import service.IUserService;

/**
 *
 * @author DeluxZ
 */
@Named
@RequestScoped
public class statsBean {

    @EJB
    private IUserService service;

    /** Creates a new instance of statsBean */
    public statsBean() {
    }

    public int getFollowingCount() {
        FacesContext context = FacesContext.getCurrentInstance();
        String paramUser = (String)context.getExternalContext().getRequestParameterMap().get("user");
        if(service.findUserByName(paramUser) != null)
        {
            return service.findUserByName(paramUser).getFollowing().size();
        }
        return service.findUserByName("default").getFollowing().size();
    }

    public int getFollowerCount() {
        FacesContext context = FacesContext.getCurrentInstance();
        String paramUser = (String)context.getExternalContext().getRequestParameterMap().get("user");
        if(service.findUserByName(paramUser) != null)
        {
            return service.getFollowers(service.findUserByName(paramUser).getName()).size();
        }
        return service.getFollowers(service.findUserByName("default").getName()).size();
    }

    public int getTweetCount() {
        FacesContext context = FacesContext.getCurrentInstance();
        String paramUser = (String)context.getExternalContext().getRequestParameterMap().get("user");
        if(service.findUserByName(paramUser) != null)
        {
            return service.findUserByName(paramUser).getTweets().size();
        }
        return service.findUserByName("default").getTweets().size();
    }

}
