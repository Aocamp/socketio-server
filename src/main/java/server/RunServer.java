package server;

public class RunServer {
    public static void main(String[] args) {
        Server server1 = new Server();
        Thread thread = new Thread(server1);
        thread.start();

    }
}
