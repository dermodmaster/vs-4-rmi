import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
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

    public static void main(String[] args){
        try {
            LocateRegistry.createRegistry(Registry.REGISTRY_PORT);
            Naming.rebind("ChatServer", new ChatServerImpl());
        }catch (Exception ex){

        }
    }

}
