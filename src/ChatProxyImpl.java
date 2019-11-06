import java.rmi.RemoteException;


public class ChatProxyImpl implements ChatProxy{
    ChatServer server;
    public ChatProxyImpl(ChatServerImpl server){
        this.server=server;
    }


    @Override
    public void sendMessage(String username, String message) throws RemoteException {
        server.sendMessage(username,message);
    }
}
