package service;


import domain.Trend;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import domain.User;
import domain.Tweet;
import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;

@Stateless
public class UserService implements Serializable  {

    private List<User> users;
    private Collection<Trend> trends;
    private HashMap<String, Long> trendMap;

    public UserService() {
        users = new ArrayList();
        trends = new ArrayList();
        initUsers();
    }

    public void create(User user) {
        users.add(user);
    }

    public void edit(User user) {
        //nothing has to be done now
    }

    public void remove(User user) {
        users.remove(user);
    }

    public List<User> findAll() {
        return users;
    }

    public User find(Object id) {
       throw new UnsupportedOperationException("Not supported yet.");
    }

    public User findUserByName(String name) {
        for(User u: users) {
            if(u.getName().equals(name)) {
                return u;
            }
        }
        return null;
    }


    public int count() {
        return users.size();
    }

    public Collection<User> getFollowers(String name) {
        Collection<User> temp = new ArrayList<User>();
        //doorloop alle users
        for(User u: users) {
            //haal Users op die User u volgt
            Collection<User> following = u.getFollowing();
            //doorloop die Users
            for(User follower: following) {
                //als de naam gelijk is aan degene die wij zoeken, voeg User u toe aan de lijst
                if(follower.getName().equals(name)) {
                    temp.add(u);
                }
            }
        }
        return temp;
    }   
    
    public Collection<Tweet> getSearchedTweets(String filter) {
        Collection<Tweet> temp = new ArrayList();
        if(filter != null) {            
            //doorloop alle users
            for(User u:users)
            {
                //haal de tweets op bij User u
                Collection<Tweet> tweets = u.getTweets();
                //doorloop de tweets
                for(Tweet t: tweets) {
                    //als filter in de tweet staat, voeg hem toe aan de temp lijst
                    if(t.getTweet().toLowerCase().contains(filter)) {
                        temp.add(t);
                    }
                }
            }
        }
        return temp;
    }

    public void addTweetToUser(String user, String tweet) {
        User u = findUserByName(user);
        u.addTweet(new Tweet(tweet, new Date(), "PC"));

        String[] words = tweet.split(" ");
        for(String s: words) {
            if(s.startsWith("#")) {
                if(trendMap.containsKey(s)) {
                    trendMap.put(s, trendMap.get(s) + 1);
                }
                else {
                    trendMap.put(s, 1L);
                }
            }
        }
    }
    
    public List<String> getTrends() {
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
                    return ((Comparable) v1).compareTo(v2);
                }
                else {
                    return 0;
                }

            }
        });
        return keys;
    }

    private void initUsers(){
        User u1 = new User("hans","http://www.google.nl","geboren 1");
        User u2 = new User("frank","httpF","geboren 2");
        User u3 = new User("tom","httpT","geboren 3");
        User u4 = new User("sjaak","httpS","geboren 4");
        User u5 = new User("default", "httpD", "geboren 5");
        u1.addFollowing(u2);
        u1.addFollowing(u3);
        u1.addFollowing(u4);

        u2.addFollowing(u1);

        u5.addFollowing(u1);

        Tweet t1 = new Tweet("#hallo", new Date(), "PC");
        Tweet t2 = new Tweet("#hallo #again #you", new Date(), "PC");
        Tweet t3 = new Tweet("#hallo #where are #you", new Date(), "PC");
        u1.addTweet(t1);
        u1.addTweet(t2);
        u1.addTweet(t3);

        users.add(u1);
        users.add(u2);
        users.add(u3);
        users.add(u4);
        users.add(u5);
    }
}
