import java.io.Serializable;
import java.rmi.RemoteException;


public class ChatProxyImpl implements ChatProxy, Serializable {
    ChatServer server;
    public ChatProxyImpl(ChatServerImpl server){
        this.server=server;
    }


    @Override
    public void sendMessage(String username, String message) throws RemoteException {
        server.sendMessage(username,message);
    }
}
