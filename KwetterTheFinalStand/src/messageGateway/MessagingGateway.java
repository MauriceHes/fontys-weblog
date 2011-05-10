/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package messageGateway;

import javax.jms.*;
import javax.naming.NamingException;
import JMS.JMSSettings;

/**
 *
 * @author Jurgen
 */
public abstract class MessagingGateway
{
    private MessageSendGateway sendgateway;
    private MessageReceiveGateway receivegateway;
    private String sendQueue, receiveQueue;

    public MessagingGateway(String connection, String sendQueue, String receiveQueue)
    {
        this.sendQueue = sendQueue;
        this.receiveQueue = receiveQueue;

        final String debugreceivequeue = receiveQueue;
        
        try
        {
            sendgateway = new MessageSendGateway(connection, sendQueue);
            receivegateway = new MessageReceiveGateway(connection, receiveQueue);
            receivegateway.addListener(new IMessageReceiverListener()
            {
                public void onMessage(Message msg)
                {
                    if(JMSSettings.DEBUG)
                    {
                        System.out.println("---------------------------------------------------------------------");
                        System.out.println("DEBUG: GATEWAY: received on ["+debugreceivequeue+"] message: " + msg);
                        System.out.println("---------------------------------------------------------------------");
                    }
                    
                    receivedMessage(msg);
                }
            });
        }
        catch(JMSException ex)
        {
            ex.printStackTrace();
        }
        catch(NamingException exn)
        {
            exn.printStackTrace();
        }
    }

    protected void openConnection()
    {
        sendgateway.openConnection();
        receivegateway.openConnection();
    }

    public void sendMessage(Message msg)
    {
        if(JMSSettings.DEBUG)
        {
            System.out.println("---------------------------------------------------------------------");
            System.out.println("DEBUG: GATEWAY: send on ["+sendQueue+"] message: " + msg);
            System.out.println("---------------------------------------------------------------------");
        }
        sendgateway.send(msg);
    }

    protected abstract void receivedMessage(Message msg);
    // implementation in specific MessageGateway (e.g.: BankGateway)

    public Message createMessage(String body)
    {
        return sendgateway.createMessage(body);
    }

    public Destination getDestination()
    {
        return receivegateway.getDestination();
    }
}