package service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import domain.User;
import domain.Tweet;
import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import javax.inject.Inject;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

@Stateless
public class UserService implements IUserService, Serializable {

    private List<User> users;
    //private Collection<Trend> trends;
    private HashMap<String, Long> trendMap;
    // JMS zooi
    private Context jndiContext;
    private ConnectionFactory connectionFactory;
    private Session session;
    private Topic topic;
    private Connection connection;
    private MessageProducer producer;

    public UserService() throws NamingException, JMSException {
        users = new ArrayList();
        trendMap = new HashMap<String, Long>();
        //trends = new ArrayList();
        initUsers();

        //JMS Producer
        jndiContext = new InitialContext();
        connectionFactory = (ConnectionFactory) jndiContext.lookup("MonitorConnectionFactory");
        topic = (Topic) jndiContext.lookup("SearchTweetTopic");

        /*connection = connectionFactory.createConnection();
        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        System.out.println("voor producer aanmaken");
        producer = session.createProducer(topic);
        TextMessage message = session.createTextMessage("This is a textmessage");
        message.setStringProperty("hashtag", "sport");
        connection.start();
        System.out.println("connection gestart");
        producer.send(message);
        connection.close();*/
    }

    @Override
    public void create(User user) {
        users.add(user);
    }

    @Override
    public void edit(User user) {
        //nothing has to be done now
    }

    @Override
    public void remove(User user) {
        users.remove(user);
    }

    @Override
    public List<User> findAll() {
        return users;
    }

    @Override
    public User findUserByName(String name) {
        for (User u : users) {
            if (u.getName().equals(name)) {
                return u;
            }
        }
        return null;
    }

    @Override
    public int count() {
        return users.size();
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
        if (filter != null) {
            //doorloop alle users
            for (User u : users) {
                //haal de tweets op bij User u
                Collection<Tweet> tweets = u.getTweets();
                //doorloop de tweets
                for (Tweet t : tweets) {
                    //als filter in de tweet staat, voeg hem toe aan de temp lijst
                    if (t.getTweet().toLowerCase().contains(filter)) {
                        temp.add(t);
                    }
                }
            }
        }
        return temp;
    }

    @Override
    public void addTweetToUser(String user, String tweet) {
        User u = findUserByName(user);
        u.addTweet(new Tweet(tweet, new Date(), "PC"));

        String[] words = tweet.split(" ");
        for (String s : words) {
            if (s.startsWith("#")) {
                try {
                    connection = connectionFactory.createConnection();
                    session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

                    System.out.println("voor producer aanmaken");
                    producer = session.createProducer(topic);
                    
                    TextMessage message = session.createTextMessage(tweet);
                    message.setStringProperty("hashtag", s.replace("#", ""));
                    connection.start();
                    System.out.println("connection gestart");
                    producer.send(message);
                    connection.close();

                } catch (JMSException ex) {
                    Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
                }
                if (trendMap.containsKey(s)) {
                    trendMap.put(s, trendMap.get(s) + 1);
                } else {
                    trendMap.put(s, 1L);
                }

            }
        }
    }

    @Override
    public List<String> getTrends() {
        List<String> temp = new ArrayList();
        //sorteer de hashmap op value en doorloop hem
        Iterator i = sortByValue(trendMap).iterator();
        int teller = 0;
        while (teller < 5) //haal de bovenste vijf trends op
        {
            if (i.hasNext()) {
                temp.add((String) i.next());
                teller++;
            } else {
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
                } else if (v1 instanceof Comparable) {
                    return ((Comparable) v2).compareTo(v1);
                } else {
                    return 0;
                }

            }
        });
        return keys;
    }

    @Override
    public void initUsers() {
        User u1 = new User("hans", "http://www.google.nl", "geboren 1");
        User u2 = new User("frank", "httpF", "geboren 2");
        User u3 = new User("tom", "httpT", "geboren 3");
        User u4 = new User("sjaak", "httpS", "geboren 4");
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

        users.add(u1);
        users.add(u2);
        users.add(u3);
        users.add(u4);
        users.add(u5);

        trendMap.put("#hallo", 3L);
        trendMap.put("#you", 2L);
        trendMap.put("#again", 1L);
        trendMap.put("#where", 1L);
    }
}
