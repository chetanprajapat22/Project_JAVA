import java.io.*;
import java.net.Socket;

public class ChatClient {
    private static final String SERVER_IP = "localhost";
    private static final int SERVER_PORT = 12345;

    public static void main(String[] args) {
        try (Socket socket = new Socket(SERVER_IP, SERVER_PORT);
             BufferedReader keyboardInput = new BufferedReader(new InputStreamReader(System.in));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            System.out.println("Connected to the chat server.");

            // Create a new thread to listen for messages from the server
            new Thread(() -> {
                String serverMessage;
                try {
                    while ((serverMessage = in.readLine()) != null) {
                        System.out.println("Message from server: " + serverMessage);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();

            // Read messages from the keyboard and send to the server
            String userMessage;
            while ((userMessage = keyboardInput.readLine()) != null) {
                out.println(userMessage); // Send message to the server
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

