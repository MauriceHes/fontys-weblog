package service;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import domain.User;
import domain.Tweet;
import java.io.Serializable;

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


    public int count() {
        return users.size();
    }

    private void initUsers(){
        User u1 = new User("Hans de Frikandel","http://www.google.nl","geboren 1");
        User u2 = new User("Frank","httpF","geboren 2");
        User u3 = new User("Tom","httpT","geboren 3");
        User u4 = new User("Sjaak","httpS","geboren 4");
        u1.addFollowing(u2);
        u1.addFollowing(u3);
        u1.addFollowing(u4);

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
    }
}
