/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package service;

import domain.Tweet;
import domain.User;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author DeluxZ
 */
public class UserServiceJPA implements IUserService, Serializable {

    @Override
    public void create(User user) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void edit(User user) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void remove(User user) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<User> findAll() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public User findUserByName(String name) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int count() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Collection<User> getFollowers(String name) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Collection<Tweet> getSearchedTweets(String filter) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void addTweetToUser(String user, String tweet) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<String> getTrends() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    

}
