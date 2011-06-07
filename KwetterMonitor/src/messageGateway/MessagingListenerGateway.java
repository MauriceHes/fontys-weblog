/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package messageGateway;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.naming.NamingException;

/**
 *
 * @author DeluxZ
 */
public class MessagingListenerGateway extends JMSChannelGateway implements IMessageListener {

    private MessageConsumer consumer;
    
    public MessagingListenerGateway(String connectionName, String topicName) throws NamingException, JMSException
    {
        super(connectionName,topicName);
        consumer = session.createConsumer(getDestination());
    }
    
    public void onMessage(Message message) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
