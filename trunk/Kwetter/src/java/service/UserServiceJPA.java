/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package service;

import domain.Tweet;
import domain.User;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.inject.Alternative;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author DeluxZ
 */

@Alternative
public class UserServiceJPA implements IUserService, Serializable {

    private EntityManagerFactory emf;

    public UserServiceJPA() {
        emf = Persistence.createEntityManagerFactory("KwetterPU");
    }

    @PostConstruct
    public void postConstruct() {
        initUsers();
    }

    @Override
    public void create(User user) {
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void edit(User user) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void remove(User user) {
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        em.remove(user);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public List<User> findAll() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public User findUserByName(String name) {
        EntityManager em = emf.createEntityManager();

        TypedQuery<User> query = em.createQuery("SELECT t FROM Tweeter t WHERE t.name = '"+ name +"'", User.class);
        User user = query.getSingleResult();
        
        em.close();
        return user;
    }

    @Override
    public int count() {
        EntityManager em = emf.createEntityManager();

        Query query = em.createQuery("SELECT COUNT(*) as c FROM Tweeter");

        int count = ((Long)query.getSingleResult()).intValue();
        //int count = (Integer)query.getSingleResult(); //voor als hij wel een int terug geeft

        em.close();
        return count;
    }

    @Override
    public Collection<User> getFollowers(String name) {
        EntityManager em = emf.createEntityManager();
        TypedQuery<User> query = em.createQuery("SELECT t FROM Tweeter t, Follower f WHERE f.follower = t.name AND f.following = '"+ name + "'", User.class);
        return query.getResultList();
    }

    @Override
    public Collection<User> getFollowing(String name) {
        EntityManager em = emf.createEntityManager();
        TypedQuery<User> query = em.createQuery("SELECT t FROM Tweeter t, Follower f WHERE f.following = t.name AND f.follower = '"+ name + "'", User.class);
        return query.getResultList();
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

    @Override
    public void initUsers(){
        User u1 = new User("hans","http://www.google.nl","geboren 1");
        User u2 = new User("frank","httpF","geboren 2");
        User u3 = new User("tom","httpT","geboren 3");
        User u4 = new User("sjaak","httpS","geboren 4");
        User u5 = new User("default", "httpD", "geboren 5");

        u1.addFollowing(u2);
        u2.addFollower(u1);

        u1.addFollowing(u3);
        u3.addFollower(u1);

        u1.addFollowing(u4);
        u4.addFollower(u1);

        u2.addFollowing(u1);
        u1.addFollower(u2);

        u5.addFollowing(u1);
        u1.addFollower(u5);

        Tweet t1 = new Tweet("#hallo", new Date(), "PC");
        Tweet t2 = new Tweet("#hallo #again #you", new Date(), "PC");
        Tweet t3 = new Tweet("#hallo #where are #you", new Date(), "PC");
        u1.addTweet(t1);
        u1.addTweet(t2);
        u1.addTweet(t3);

        create(u1);
        create(u2);
        create(u3);
        create(u4);
        create(u5);

        //TODO: trends toevoegen aan tabel
        //trendMap.put("#hallo", 3L);
        //trendMap.put("#you", 2L);
        //trendMap.put("#again", 1L);
        //trendMap.put("#where", 1L);
    }
}
