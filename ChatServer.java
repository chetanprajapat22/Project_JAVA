import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

public class ChatServer {

    private static final int PORT = 12345; // Server port
    private static List<ClientHandler> clients = new ArrayList<>();

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server started. Waiting for clients to connect...");

            // Keep listening for new client connections
            while (true) {
                Socket clientSocket = serverSocket.accept(); // Accept client connection
                System.out.println("Client connected!");

                // Create a new client handler for each new client
                ClientHandler clientHandler = new ClientHandler(clientSocket);
                clients.add(clientHandler);

                // Start the client handler in a new thread
                new Thread(clientHandler).start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to broadcast message to all clients
    public static void broadcastMessage(String message, ClientHandler sender) {
        for (ClientHandler client : clients) {
            if (client != sender) {
                client.sendMessage(message); // Send the message to all other clients
            }
        }
    }

    // Remove client from the list when it disconnects
    public static void removeClient(ClientHandler client) {
        clients.remove(client);
    }

    // Inner class to handle individual client communication
    private static class ClientHandler implements Runnable {
        private Socket socket;
        private PrintWriter out;
        private BufferedReader in;

        public ClientHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                // Set up input and output streams
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(), true);

                // Read messages from the client and broadcast them
                String message;
                while ((message = in.readLine()) != null) {
                    System.out.println("Received: " + message);
                    broadcastMessage(message, this);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    // Close the socket when client disconnects
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                removeClient(this); // Remove client from the list
                System.out.println("Client disconnected.");
            }
        }

        // Method to send a message to the client
        public void sendMessage(String message) {
            out.println(message);
        }
    }
}

