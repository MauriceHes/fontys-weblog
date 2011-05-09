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
public interface IMessageSender
{
    public void send(Message msg);
}
