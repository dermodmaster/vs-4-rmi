import java.io.Serializable;
import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class ChatServerImpl extends UnicastRemoteObject implements ChatServer{
    List<ClientProxy> clients;

    protected ChatServerImpl() throws RemoteException {
        this.clients = new ArrayList<ClientProxy>();
    }

    @Override
    public ChatProxy subscribeUser(ClientProxy handle) throws RemoteException {

        if(clients.contains(handle))return null;

        clients.add(handle);

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
