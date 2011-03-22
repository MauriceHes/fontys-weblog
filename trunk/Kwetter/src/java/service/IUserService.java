/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package service;

import domain.Tweet;
import domain.User;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author DeluxZ
 */
public interface IUserService {

    public void create(User user);

    public void edit(User user);

    public void remove(User user);

    public List<User> findAll();

    public User findUserByName(String name);

    public int count();

    public Collection<User> getFollowers(String name);

    public Collection<Tweet> getSearchedTweets(String filter);

    public void addTweetToUser(String user, String tweet);

    public List<String> getTrends();

    public void initUsers();

}
