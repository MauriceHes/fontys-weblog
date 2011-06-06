/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package messageGateway;

import javax.jms.*;

public interface IChannelGateway
{
   public TextMessage createMessage(String body);
   public Destination getDestination();
   public void openConnection();
   public void closeConnection();
}
