import javax.swing.*;
import java.io.Serializable;
import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ChatClient extends JFrame{
    JTextArea output;
    JTextField input;

    ClientProxy handle;
    ChatProxy proxy;
    String username;

    /**
     * Konstruktor welcher per swing eine Gui erstellt in der Nachrichten gelesen und eingegeben werden können,
     * stellt außerdem die Verbindung zum Server her.
     * @param username
     * @throws Exception
     */
    public ChatClient(String username) throws Exception {
        this.username=username;
        Registry registry = LocateRegistry.getRegistry();
        ChatServer chatServer = (ChatServer)registry.lookup("ChatServer");
        handle = new ClientProxyImpl(this);
        proxy=chatServer.subscribeUser(handle);

        setTitle(username);
        getContentPane().setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        output = new JTextArea();
        output.setEditable(false);
        JScrollPane scroller = new JScrollPane();
        scroller.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroller.getViewport().setView(output);
        getContentPane().add(scroller, BorderLayout.CENTER);
        input = new JTextField();
        getContentPane().add(input, BorderLayout.NORTH);
        //Nachricht schreiben
        input.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    sendMessage(input.getText(),username);
                    input.setText("");
                } catch(RemoteException ex) {
                    ex.printStackTrace();
                }
            }
        });

        JButton close = new JButton("close");
        getContentPane().add(close, BorderLayout.SOUTH);
        //beenden
        close.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    chatServer.unsubscribeUser(handle);
                }
                catch(RemoteException re){
                    re.printStackTrace();
                }
                System.exit(0);
            }
        });
        setSize(400, 300);
    }

    public static void main (String[] args){
        try {
            String name = JOptionPane.showInputDialog(null, "Eingabe des Namens");
            if (name != null && name.trim().length() > 0) {
                ChatClient client = new ChatClient(name);
                client.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Bitte Geben sie einen Name mit mindestens einem Zeichen ein");
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
        output.append(username +": "+message+"\n");
        output.setCaretPosition(output.getText().length()-1);
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
