package grebenkinvd.connection;

import java.net.DatagramSocket;

public class UDPConnector implements Runnable{
    private final P2PStatus status = P2PStatus.FREE;
    private ServerData serverData;
    private DatagramSocket socket;

    public UDPConnector(){
        
    }

    @Override
    public void run() {
        while (this.status != P2PStatus.CONNECTED){
            // TODO: connecting loop
        }
    }
}
