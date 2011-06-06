/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package messageGateway;

import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class JMSChannelGateway implements IChannelGateway
{
    private Context jndiContext; // the context
    private ConnectionFactory connectionFactory; // this will be ither TopicConnectionFactory of QueueConnectionFactory*/

    private Connection connection;
    protected Session session;
    private Destination destination;

    public JMSChannelGateway(String connectionName, String queueName) throws NamingException, JMSException
    {
        // connecting to the JMS and "queueConnectionFactory"
        jndiContext = new InitialContext();
        connectionFactory = (ConnectionFactory) jndiContext.lookup(connectionName);
        // create the connection, session and DestinationequestQueu
        connection = connectionFactory.createConnection();
        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        destination = (Destination) jndiContext.lookup(queueName);
    }

    public TextMessage createMessage(String body)
    {
        TextMessage result = null;
        try
        {
            result = session.createTextMessage(body);
        }
        catch(JMSException ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            return result;
        }
    }

    public Destination getDestination()
    {
        return destination;
    }

    public void openConnection()
    {
        try
        {
            connection.start();
        }
        catch (JMSException ex)
        {
            ex.printStackTrace();
        }
    }

    public void closeConnection()
    {
        try
        {
            connection.close();
        }
        catch (JMSException ex)
        {
            ex.printStackTrace();
        }
    }
}
