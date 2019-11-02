import java.rmi.RemoteException;

public interface ClientProxy {
    public void receiveMessage(String username, String message) throws RemoteException;
}
