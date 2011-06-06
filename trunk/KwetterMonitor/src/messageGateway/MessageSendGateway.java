/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package messageGateway;

import javax.jms.*;
import javax.naming.NamingException;

public class MessageSendGateway extends JMSChannelGateway implements IMessageSender
{
    private MessageProducer producer;

    public MessageSendGateway(String connectionName, String queueName) throws NamingException, JMSException
    {
        super(connectionName, queueName);
        producer = session.createProducer(getDestination());
    }
    
    public void send(Message msg)
    {
        try
        {
            producer.send(msg);
        }
        catch(JMSException ex)
        {
            ex.printStackTrace();
        }
    }
}
