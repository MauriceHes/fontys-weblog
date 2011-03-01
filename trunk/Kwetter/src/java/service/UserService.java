package service;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import domain.User;
import domain.Tweet;
import java.io.Serializable;
import java.util.Collection;

@Stateless
public class UserService implements Serializable  {

    private List<User> users;


    public UserService() {
        users = new ArrayList();
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

        Tweet t1 = new Tweet("Hallo", new Date(), "PC");
        Tweet t2 = new Tweet("Hallo again", new Date(), "PC");
        Tweet t3 = new Tweet("Hallo where are you", new Date(), "PC");
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
