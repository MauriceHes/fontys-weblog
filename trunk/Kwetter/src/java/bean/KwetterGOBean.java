/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import service.IUserService;

/**
 *
 * @author Jurgen
 */
@MessageDriven(mappedName = "PostTweetRequestQueue", activationConfig = {
    @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge"),
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
})
public class KwetterGOBean implements MessageListener {
    
    @Inject
    private IUserService service;
    
    public KwetterGOBean() {
    }
    
    @Override
    public void onMessage(Message message) {
        try {
            //TextMessage tm = (TextMessage)message;
            System.out.println("KwetterGOBean.java - onMessage: " + message.getStringProperty("user"));
            service.addTweetToUser(message.getStringProperty("user"), message.getStringProperty("tweet"));
        } catch (JMSException ex) {
            Logger.getLogger(KwetterGOBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
