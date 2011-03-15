/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bean;

import domain.User;
import javax.ejb.EJB;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import service.IUserService;

/**
 *
 * @author DeluxZ
 */
@Named
@RequestScoped
public class userBean {

    @EJB
    private IUserService service;
 
    /** Creates a new instance of userBean */
    public userBean() {
    }

    public User getUser() {
        FacesContext context = FacesContext.getCurrentInstance();
        String paramUser = (String)context.getExternalContext().getRequestParameterMap().get("user");
        if(service.findUserByName(paramUser) != null)
        {
            return service.findUserByName(paramUser);
        }
        return service.findUserByName("default");
    }

}