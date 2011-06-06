/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package messageGateway;

import javax.jms.*;

public interface IMessageListener {
    public void onMessage(Message message); 
}
