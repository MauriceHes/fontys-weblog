package JMS;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;
import messageGateway.MessagingGateway;

public class TweetGateway extends MessagingGateway
{
    
    public TweetGateway()
    {
        super(JMSSettings.CONNECTION, JMSSettings.POST_REQUEST, JMSSettings.POST_REPLY);
    }

    public void start()
    {
        this.openConnection();
    }

    public void postTweet(String tweet, String user, String via) throws JMSException
    {
        Message msg = this.createMessage("Tweet");
        // set values
        msg.setStringProperty("tweet", tweet);
        msg.setStringProperty("user", user);
        msg.setStringProperty("via", via);
        // send it
        this.sendMessage(msg);
    }

    @Override
    protected void receivedMessage(Message msg) {
        try {
            System.out.println(((TextMessage) msg).getText());
        } catch (JMSException ex) {
            Logger.getLogger(TweetGateway.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}