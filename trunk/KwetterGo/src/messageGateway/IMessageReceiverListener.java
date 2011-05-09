/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package messageGateway;

import javax.jms.*;

/**
 *
 * @author Sebastiaan
 */
public interface IMessageReceiverListener extends MessageListener
{
    public void onMessage(Message msg);
}
