
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.logging.Logger;

@ServerEndpoint(value = "/game")
public class MyWebSocket {
    private Logger logger = Logger.getLogger(this.getClass().getName());

    @OnOpen
    public void onOpen(Session session) {
        logger.info("Connected .." + session.getId());
    }

    @OnMessage
    public String onMessage(String message, Session session) {
        switch (message) {
            case "quit":
                try {
                    session.close(new CloseReason(CloseReason.CloseCodes.GOING_AWAY, "Ta-ta"));
                } catch (IOException e) {
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }
                break;
        }
        return message;
    }

    @OnClose
    public void onClose(Session session, CloseReason reason) {
        logger.info(String.format("Session %s closed due to %s", session.getId(), reason));
    }
}
