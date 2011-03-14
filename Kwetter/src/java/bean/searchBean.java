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
import service.UserService;

/**
 *
 * @author Jurgen
 */
@Named
@RequestScoped
public class searchBean {

    @EJB
    private UserService service;
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

}
