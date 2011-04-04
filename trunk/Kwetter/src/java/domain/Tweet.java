package domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;

@Entity
public class Tweet implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private int id;
    @Column(name="tweets",length=140)
    private String tweet;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date postDate;
    private String postedFrom;

    @ManyToOne
    private User user;

    public Tweet() {
    }
    
    public Tweet(String tweet) {
        this.tweet = tweet;
    }

    public Tweet(String tweet, Date datum, String vanaf) {
        this.tweet = tweet;
        this.postDate = datum;
        this.postedFrom = vanaf;
    }

    public String getTweet() {
        return tweet;
    }

    public void setTweet(String tweet) {
        this.tweet = tweet;
    }
    
    public String getDatum() {
        //return postDate;
        return TimeFormat.stringDifferenceFromNow(postDate);
    }

    public void setDatum(Date datum) {
        this.postDate = datum;
    }

    public String getVanaf() {
        return postedFrom;
    }

    public void setVanaf(String vanaf) {
        this.postedFrom = vanaf;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return this.user;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tweet != null ? tweet.hashCode()+ postDate.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Tweet)) {
            return false;
        }
        Tweet other = (Tweet) object;
        return this.hashCode() == other.hashCode();
    }

    @Override
    public String toString() {
        return "twitter.domain.Tweet[id=" + postDate.toString() + ";tweet=" + tweet + "]";
    }

}
