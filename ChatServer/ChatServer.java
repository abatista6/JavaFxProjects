package sample; //comment out for submission

//import required java classes
import java.lang.*;
import java.io.*;
import java.util.*;
import java.net.*;
import static java.lang.System.out;

/** Basic JavaFXChat Server Program
 * @author Adrianna Batista
 * @version 1.0
 * file name ChatServer.java
 */

public class ChatServer {
    Vector<String> users = new Vector<String>(); //vector list of user strings as defined by clients
    Vector<HandleClient> clients = new Vector<HandleClient>(); //vector list of clients connected to server
    private Socket socket;

    public void process() throws Exception {
        ServerSocket server = new ServerSocket(9999, 10);
        out.println("Server Started...Awaiting Client Connection...");
        while (true) {
            socket = server.accept();
            HandleClient c = new HandleClient(socket);
            clients.add(c);
        }
    }

    //Main class
    public static void main(String[] args) throws Exception {
        try {
            new ChatServer().process();
        } catch (Exception ex) {

        }
    }

    public void broadcast(String user, String message) {
        for (HandleClient c : clients)
            if (!c.getUserName().equals(user)) {
                c.sendMessage(user, message);
                //this.sendMessage(user,message);
            }

    }

    class HandleClient extends Thread {
        String name = "";
        BufferedReader input;
        PrintWriter output;

        public HandleClient(Socket socket) throws Exception {
            InetAddress addr1 = socket.getInetAddress();
            out.println("Client has connected@" + addr1); //command line output
            input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            output = new PrintWriter(socket.getOutputStream(), true);
            name = input.readLine();
            users.add(name);
            output.println("Server: Hello User"+addr1); //chat box output
            output.println("Server: Connect Multiple Clients to Chat");
            start();
        }

        public void sendMessage(String uname, String msg) {
            output.println(uname + " : " + msg);
            out.println(uname + " : " + msg);
        }

        public String getUserName() {
            return name;
        }

        public void run() {
            String txt;
            try {
                while (true) {
                    txt = input.readLine();
                    if (txt.equals("exit")) { //if user enters exit then
                        clients.remove(this); //remove this client from vector list
                        output.println("Server: Client " + name +" terminated connection");
                        out.println("Client " + name + " terminated connection");
                        users.remove(name); //remove this user from vector list
                        socket.close(); //close client connection
                        break;
                    }
                    broadcast(name, txt);
                }
            } catch (Exception ex) {
                out.println(ex.getMessage());
            } finally {
                out.println("Client is down");
            }
        }
    }

}
