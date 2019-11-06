import java.io.Serializable;
import java.rmi.RemoteException;

public class ClientProxyImpl implements ClientProxy, Serializable {
    ChatClient client;
    String username;

    public ClientProxyImpl(ChatClient client) throws RemoteException {
        this.client = client;
        this.username=client.getUsername();
    }

    public void receiveMessage(String username, String message) throws RemoteException{
        this.client.receiveMessage(username, message);
    }

    public String getUsername(){
        return this.username;
    }
}
