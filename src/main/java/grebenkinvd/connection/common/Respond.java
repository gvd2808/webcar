package grebenkinvd.connection.common;

import java.io.Serial;
import java.io.Serializable;

public class Respond implements Serializable {
    @Serial
    private static final long serialVersionUID = 2319L;
    // TODO: temporary respond
    private String message;

    public Respond(){
        this.message = "Hello from RC car";
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
