import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ClientProxyImpl extends UnicastRemoteObject implements ClientProxy, Serializable {
    ChatClient client;

    public ClientProxyImpl(ChatClient client) throws RemoteException {
        this.client = client;
    }

    public ClientProxyImpl() throws RemoteException {

    }

    public void receiveMessage(String username, String message) throws RemoteException{
        this.client.showMessage(username, message);
    }

    public String getUsername() throws RemoteException{
        return this.client.getUsername();
    }
}
