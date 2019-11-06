import java.io.Serializable;
import java.rmi.RemoteException;

public class ClientProxyImpl implements ClientProxy, Serializable {
    ChatClient client;

    public ClientProxyImpl(ChatClient client) throws RemoteException {
        this.client = client;
    }

    public void receiveMessage(String username, String message) throws RemoteException{
        this.client.receiveMessage(username, message);
    }
}
