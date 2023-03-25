package grebenkinvd.connection;

import grebenkinvd.Token;

import java.net.InetAddress;
import java.net.InetSocketAddress;

/**
 * contains information about hosting controller-server
 */
public class ServerData {
    private final InetSocketAddress serverAddress;
    private static final String defaultHostname = "grebenkin.pro";
    private static final int defaultPort = 8081;
    private final Token token = new Token();

    // constructors
    public ServerData(String hostname, int serverPort){
        this.serverAddress = new InetSocketAddress(hostname, serverPort);
    }
    public ServerData(int serverPort){
        this.serverAddress = new InetSocketAddress(defaultHostname, serverPort);
    }
    public ServerData(){
        this.serverAddress = new InetSocketAddress(defaultHostname, defaultPort);
    }

    public InetSocketAddress getServerAddress() {
        return serverAddress;
    }

    @Override
    public String toString() {
        return "server ip address: " + this.serverAddress.getHostName() +
                " on port " + this.serverAddress.getPort();
    }
}
