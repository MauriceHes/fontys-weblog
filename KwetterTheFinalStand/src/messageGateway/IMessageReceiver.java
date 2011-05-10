/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package messageGateway;

/**
 *
 * @author Jurgen
 */
public interface IMessageReceiver
{
   public void addListener(IMessageReceiverListener listener);
}