package messageGateway;

import javax.jms.*;
/**
 *
 * @author Jurgen
 */
public interface IChannelGateway
{
   public TextMessage createMessage(String body);
   public Destination getDestination();
   public void openConnection();
   public void closeConnection();
}