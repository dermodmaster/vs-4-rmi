import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;

public class ChatServerImpl extends UnicastRemoteObject implements ChatServer {
    protected ChatServerImpl() throws RemoteException {

    }

    @Override
    public ChatProxy subscribeUser(ClientProxy handle) throws RemoteException {
        return null;
    }

    @Override
    public boolean unsubscribeUser(ClientProxy handle) throws RemoteException {
        return false;
    }
}
