import java.rmi.*;
public interface ChatProxy extends Remote{
    public void sendMessage(String username, String message) throws RemoteException;
}
