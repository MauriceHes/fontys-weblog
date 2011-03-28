/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bean;

import domain.User;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import service.IUserService;

/**
 *
 * @author DeluxZ
 */
@Named
@RequestScoped
public class userBean {

    @Inject
    private IUserService service;
 
    /** Creates a new instance of userBean */
    public userBean() {        

    }

    public User getUser() {
        FacesContext context = FacesContext.getCurrentInstance();
        String paramUser = (String)context.getExternalContext().getRequestParameterMap().get("user");
        System.out.println(paramUser);
        if(paramUser != null)
        {
            return service.findUserByName(paramUser);
        }
        return service.findUserByName("default");
    }

}