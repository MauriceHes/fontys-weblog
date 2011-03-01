/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bean;

import domain.TimeFormat;
import domain.Tweet;
import java.util.Collection;
import java.util.List;
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
public class tweetBean {

    @EJB
    private UserService service;


    /** Creates a new instance of tweetBean */
    public tweetBean() {
    }

    public Collection<Tweet> getTweets() {
        return service.findAll().get(0).getTweets();
    }

}
