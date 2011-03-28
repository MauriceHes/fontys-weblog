package domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity (name="Tweeter")
public class User implements Serializable  {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private int id;
    private String name;
    private String web;
    private String bio;

    @ManyToMany
    private Collection<User> following = new ArrayList();

    @ManyToMany
    private Collection<User> followers = new ArrayList();
    
    @OneToMany
    private Collection<Tweet> tweets = new ArrayList();    

    public User() {
    }

    public User(String naam) {
        this.name = naam;
    }

    public User(String naam, String web, String bio) {
        this.name = naam;
        this.web = web;
        this.bio = bio;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    public Collection<User> getFollowing() {
        return following;
    }

    public void setFollowing(Collection<User> following) {
        this.following = following;
    }

    public Collection<User> getFollowers() {
        return followers;
    }

    public void setFollowers(Collection<User> followers) {
        this.followers = followers;
    }

    public Collection<Tweet> getTweets() {
        return tweets;
    }

    public void setTweets(Collection<Tweet> tweets) {
        this.tweets = tweets;
    }

    public Boolean addFollowing(User following) {
        return this.following.add(following);
    }

    public Boolean addFollower(User follower) {
        return this.followers.add(follower);
    }
  
    public Boolean addTweet(Tweet tweet){
        return this.tweets.add(tweet);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (name != null ? name.hashCode() + bio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the name fields are not set
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        return this.hashCode()==other.hashCode();
    }

    @Override
    public String toString() {
        return "twitter.domain.User[naam=" + name + "]";
    }

}
