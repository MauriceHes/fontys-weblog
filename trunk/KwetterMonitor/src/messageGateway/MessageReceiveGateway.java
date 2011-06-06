/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package messageGateway;

import javax.jms.*;
import javax.naming.NamingException;

public class MessageReceiveGateway extends JMSChannelGateway implements IMessageReceiver
{
    private MessageConsumer consumer;

    public MessageReceiveGateway(String connectionName, String queueName) throws NamingException, JMSException
    {
        super(connectionName, queueName);
        consumer = session.createConsumer(getDestination());
    }

    public void addListener(IMessageReceiverListener listener)
    {
        try
        {
            consumer.setMessageListener(listener);
        }
        catch(JMSException ex)
        {
            ex.printStackTrace();
        }
    }

    public Session getSession()
    {
        return session;
    }
}
