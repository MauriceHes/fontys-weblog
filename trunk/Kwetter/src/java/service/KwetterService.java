/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import domain.Tweet;
import java.util.Collection;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Jurgen
 */
@WebService
@Stateless()
public class KwetterService {
    
    @Inject
    private IUserService service;

    /**
     * Web service operation
     */
    @WebMethod(operationName = "GetTweetsFromUser")
    public Collection<Tweet> GetTweetsFromUser(String username) {
        System.out.println("--- KwetterService --- ontvangt username: " + username);
        System.out.println("--- KwetterService --- aantal tweets: " + service.findUserByName(username).getTweets().size());
        return service.findUserByName(username).getTweets();
    }
}
