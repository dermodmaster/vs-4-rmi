import javax.swing.*;
import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.awt.*;
import java.awt.event.*;

public class ChatClient extends JFrame{
    ClientProxy handle;
    ChatProxy proxy;
    String username;
    JTextField textField;
    JTextArea textArea;

    /**
     * Konstruktor welcher per swing eine Gui erstellt in der Nachrichten gelesen und eingegeben werden können,
     * stellt außerdem die Verbindung zum Server her.
     * @param username
     * @throws Exception
     */
    public ChatClient(String username) throws Exception {
        setTitle("RMI Chat Client");
        this.username=username;
        Registry registry = LocateRegistry.getRegistry();
        ChatServer chatServer = (ChatServer)registry.lookup("ChatServer");
        handle = new ClientProxyImpl(this);
        proxy=chatServer.subscribeUser(handle);
        if(proxy==null){
            JOptionPane.showMessageDialog(null, "Username bereits Vergeben");
            System.exit(0);
        }

        textArea = new JTextArea();
        textArea.setEditable(false);
        textField = new JTextField();

        JScrollPane scroller = new JScrollPane();
        scroller.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroller.getViewport().setView(textArea);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(scroller, BorderLayout.CENTER);
        getContentPane().add(textField, BorderLayout.NORTH);
        //Nachricht schreiben
        textField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    sendMessage(textField.getText(),username);
                    textField.setText("");
                } catch(RemoteException ex) {
                    ex.printStackTrace();
                }
            }
        });

        addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent e)
            {
                try {
                    chatServer.unsubscribeUser(handle);
                }
                catch(RemoteException re){
                    re.printStackTrace();
                }
                dispose();
                System.exit(0);
            }
        });
        setSize(1000, 600);
    }

    public static void main (String[] args) throws RemoteException{
        try {
            String name = JOptionPane.showInputDialog(null, "Eingabe des Namens");
            if (name != null && name.trim().length() > 0) {
                ChatClient client = new ChatClient(name);
                client.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Ein leerer Nickname ist nicht gestattet.");
                System.exit(0);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Bekommen einer Nachricht
     * @param username Von welchem benutzer
     * @param message Die bekommene Nachricht
     */
    public void showMessage(String username, String message) {
        textArea.append(username +": "+message+"\n");
        textArea.setCaretPosition(textArea.getText().length()-1);
    }

    /**
     * Versenden einer Nachricht
     * @param message Die zu versendene Nachricht
     * @param username Der benutzername
     * @throws RemoteException
     */
    public void sendMessage(String message,String username) throws RemoteException{
        proxy.sendMessage(username,message);
    }
    public String getUsername(){
        return username;
    }
}
