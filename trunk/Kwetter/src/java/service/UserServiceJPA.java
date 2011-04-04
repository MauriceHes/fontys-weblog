/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package service;

import domain.Tweet;
import domain.User;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.inject.Alternative;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
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
       // initUsers();
    }

    @Override
    public void create(User user) {
        System.out.println("create: " + user.getName());
        EntityManager em = emf.createEntityManager();

        User found = findUserByName(user.getName());
        if(found == null) {
            em.getTransaction().begin();
            em.persist(user);
            em.getTransaction().commit();
        }       
        
        em.close();
    }

    @Override
    public void edit(User user) {
        //TODO?
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
        EntityManager em = emf.createEntityManager();

        TypedQuery<User> query = em.createQuery("SELECT t from Tweeter", User.class);
        List<User> users = query.getResultList();

        em.close();
        return users;
    }

    @Override
    public User findUserByName(String name) {
        System.out.println("findUserByName: " + name);
        EntityManager em = emf.createEntityManager();

        TypedQuery<User> query = em.createQuery("SELECT t FROM Tweeter t WHERE t.name = '"+ name +"'", User.class);
        
        User user = null;
        try {
            user = query.getSingleResult();
        }
        catch(NoResultException e) {
            //user blijft null
            System.out.println("findUserByName catch gedaan");
        }       
        
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
        return findUserByName(name).getFollowers();
    }

    @Override
    public Collection<User> getFollowing(String name) {
        return findUserByName(name).getFollowing();
    }

    @Override
    public Collection<Tweet> getSearchedTweets(String filter) {
        Collection<Tweet> temp = new ArrayList();
        if(filter != null) {      

            EntityManager em = emf.createEntityManager();

            TypedQuery<Tweet> query = em.createQuery("SELECT t FROM Tweet t", Tweet.class);
            Collection<Tweet> tweets = query.getResultList();

            em.close();

            System.out.println(tweets.toString());
            //doorloop de tweets
            for(Tweet t : tweets) {

                if(t.getTweet().toLowerCase().contains(filter)) {
                    temp.add(t);
                }
                //als filter in de tweet staat, voeg hem toe aan de temp lijst

            }
        }
        return temp;
    }

    @Override
    public void addTweetToUser(String userName, String tweet) {
        EntityManager em = emf.createEntityManager();
        User user = findUserByName(userName);
        
        if(user != null){
            em.getTransaction().begin();
            
            User merged = em.merge(user);
            Tweet t = new Tweet(tweet, new Date(), "PC");
            em.persist(t);
            
            merged.addTweet(t);

            em.getTransaction().commit();
        }
        
        em.close();
    }

    public void addFollower(User user, User follower) {
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        User merged = em.merge(user);
        User followerMerg = em.merge(follower);
        merged.addFollower(followerMerg);

        em.getTransaction().commit();
        em.close();
    }

    public void addFollower(String userName, String followerName) {
        EntityManager em = emf.createEntityManager();
        User user = findUserByName(userName);
        User follower = findUserByName(followerName);

        if(user != null && follower != null) {
            em.getTransaction().begin();
            User merged = em.merge(user);
            User followerMerg = em.merge(follower);
            merged.addFollower(followerMerg);

            em.getTransaction().commit();
        }
        em.close();
    }

    @Override
    public List<String> getTrends() {
        //TODO uit database ophalen
        HashMap trendMap = new HashMap<String, Long>();
        trendMap.put("#hallo", 3L);
        trendMap.put("#you", 2L);
        trendMap.put("#again", 1L);
        trendMap.put("#where", 1L);

        List<String> temp = new ArrayList();
        //sorteer de hashmap op value en doorloop hem
        Iterator i = sortByValue(trendMap).iterator();
        int teller = 0;
        while(teller < 5) //haal de bovenste vijf trends op
        {
            if(i.hasNext()) {
                temp.add((String) i.next());
                teller ++;
            }
            else {
                break;
            }
        }
        return temp;
    }

    @Override
    public void initUsers(){
        User u1 = new User("hans","http://www.google.nl","geboren 1");
        User u2 = new User("frank","httpF","geboren 2");
        User u3 = new User("tom","httpT","geboren 3");
        User u4 = new User("sjaak","httpS","geboren 4");
        User u5 = new User("default", "httpD", "geboren 5");
        
        create(u1);
        create(u2);
        create(u3);
        create(u4);
        create(u5);

        addFollower(u2.getName(), u1.getName());
        addFollower(u3.getName(), u1.getName());
        addFollower(u4.getName(), u1.getName());
        addFollower(u1.getName(), u2.getName());
        addFollower(u1.getName(), u5.getName());

        addTweetToUser(u1.getName(), "#hallo");
        addTweetToUser(u1.getName(), "#hallo #again #you");
        addTweetToUser(u1.getName(), "#hallo #where are #you");
    }

    public List<String> sortByValue(final HashMap m) {
        List<String> keys = new ArrayList();
        keys.addAll(m.keySet());
        Collections.sort(keys, new Comparator() {

            @Override
            public int compare(Object o1, Object o2) {
                Object v1 = m.get(o1);
                Object v2 = m.get(o2);
                if (v1 == null) {
                    return (v2 == null) ? 0 : 1;
                }
                else if (v1 instanceof Comparable) {
                    return ((Comparable) v2).compareTo(v1);
                }
                else {
                    return 0;
                }

            }
        });
        return keys;
    }
}
