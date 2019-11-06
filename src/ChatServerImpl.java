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

    public void sendMessage(String username, String message) throws RemoteException{
        for(ClientProxy client : clients){
            client.receiveMessage(username, message);
        }
    }

    @Override
    public ChatProxy subscribeUser(ClientProxy handle) throws RemoteException {
        for(int i=0;i<clients.size();i++){
            if(clients.get(i).getUsername().equals(handle.getUsername())){
                return null;
            }
        }
        clients.add(handle);
        return new ChatProxyImpl(this);
    }

    @Override
    public boolean unsubscribeUser(ClientProxy handle) throws RemoteException {
        for(int i=0;i<clients.size();i++){
            if(clients.get(i).getUsername().equals(handle.getUsername())){
                clients.remove(i);
                System.out.println(clients.size());
                return true;
            }
        }
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
