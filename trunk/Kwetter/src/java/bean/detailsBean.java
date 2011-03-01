/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bean;

import domain.User;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import service.UserService;

/**
 *
 * @author DeluxZ
 */
@Named
@RequestScoped
public class detailsBean {

    @EJB
    private UserService service;

    /** Creates a new instance of detailsBean */
    public detailsBean() {
    }

}
