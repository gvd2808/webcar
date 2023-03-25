package grebenkinvd.connection;

import grebenkinvd.connection.common.Respond;
import grebenkinvd.connection.common.Serializer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class UDPConnector implements Runnable {
    private P2PStatus status = P2PStatus.NOT_CONNECTED;
    private final ServerData serverData;
    private static final InetSocketAddress LocalhostAddr = new InetSocketAddress("localhost", 8082);
    private DatagramChannel channel;

    public UDPConnector() throws IOException {
        this.serverData = new ServerData();
        this.initChannel();
    }

    @Override
    public void run() {
        this.connect();
        // Here loop
        while (this.status == P2PStatus.CONNECTED){
            sendRespond(new Respond());
        }
        this.disconnect();
        this.close();
    }

    private void initChannel() throws IOException {
        this.channel = DatagramChannel.open();
        channel.bind(LocalhostAddr);
        channel.configureBlocking(false);
        System.out.println("connection established on " + LocalhostAddr.getPort() + " port");
    }
    private void connect() {
        try {
            this.channel.connect(serverData.getServerAddress());
            this.status = P2PStatus.CONNECTED;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void disconnect() {
        try {
            channel.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void close() {
        try {
            this.channel.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public void sendRespond(Respond respond){
        ByteArrayOutputStream serialized = Serializer.serialize(respond);
        try {
            channel.send(ByteBuffer.wrap(serialized.toByteArray()), this.serverData.getServerAddress());
        } catch (IOException e) {
            e.printStackTrace();
            disconnect();
        }

    }




}
