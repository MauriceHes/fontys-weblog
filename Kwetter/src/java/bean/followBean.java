/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bean;

import domain.User;
import java.util.Collection;
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
public class followBean {

    @Inject
    private IUserService service;

    /** Creates a new instance of followBean */
    public followBean() {
    }

    public Collection<User> getFollowing() {
        FacesContext context = FacesContext.getCurrentInstance();
        String paramUser = (String)context.getExternalContext().getRequestParameterMap().get("user");
         if(paramUser != null)
        {
            return service.findUserByName(paramUser).getFollowing();
        }
        return service.findUserByName("default").getFollowing();
    }

    public Collection<User> getFollowers() {
        FacesContext context = FacesContext.getCurrentInstance();
        String paramUser = (String)context.getExternalContext().getRequestParameterMap().get("user");
         if(paramUser != null)
        {
            return service.getFollowers(paramUser);
        }
        return service.getFollowers("default");
    }
}
