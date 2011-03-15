/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bean;

import domain.TimeFormat;
import domain.Tweet;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
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
    private String tweet;
    private String activeUser;


    /** Creates a new instance of tweetBean */
    public tweetBean() {
    }

    public Collection<Tweet> getTweets() {
        FacesContext context = FacesContext.getCurrentInstance();
        String paramUser = (String)context.getExternalContext().getRequestParameterMap().get("user");
        if(service.findUserByName(paramUser) != null)
        {
            return service.findUserByName(paramUser).getTweets();
        }
        return service.findUserByName("default").getTweets();
    }

    public void postNewTweet(String user) {
        if((tweet != null) && (tweet.length() > 0))
        {
            System.out.println(user);
            if(user != null)
            {
                service.addTweetToUser(user, tweet);
            }
        }
    }

    public List<String> getTrends() {
        return service.getTrends();
    }

    /*public String getActiveUser() {
        FacesContext context = FacesContext.getCurrentInstance();
        activeUser = (String)context.getExternalContext().getRequestParameterMap().get("user");
        System.out.println(context.getExternalContext().getRequestParameterMap().toString());
        System.out.println(activeUser);
        return activeUser;
    }*/

    public void setTweet(String tweet) {
        this.tweet = tweet;
    }

    public String getTweet() {
        return this.tweet;
    }
}
