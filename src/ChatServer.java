import java.rmi.*;

public interface ChatServer extends Remote {
    public ChatProxy subscribeUser(ClientProxy handle) throws RemoteException;

    public boolean unsubscribeUser(ClientProxy handle) throws RemoteException;

    void sendMessage(String username, String message);
}
