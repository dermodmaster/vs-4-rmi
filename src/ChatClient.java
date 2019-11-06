import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ChatClient {
    public static void main (String[] args){
        try{
            Registry registry = LocateRegistry.getRegistry();
            ChatServer chatServer = (ChatServer) registry.lookup("ChatServer");

        }   catch (AccessException e){
            e.printStackTrace();
        }   catch (RemoteException e){
            e.printStackTrace();
        }   catch (NotBoundException e){
            e.printStackTrace();
        }
    }
}
