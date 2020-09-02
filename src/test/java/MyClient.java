import javax.websocket.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Logger;

@ClientEndpoint
public class MyClient {
    private Logger logger = Logger.getLogger(this.getClass().getName());

    @OnOpen
    public void onOpen(Session session) {
        try {
            session.getBasicRemote().sendText("Start");

        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @OnMessage
    public String onMessage(String message, Session session) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            logger.info("Received ... " + message);
            String userInput = br.readLine();
            return userInput;
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @OnClose
    public void onClose(Session session, CloseReason reason) {
        logger.info(String.format("Session %s closed due to %s", session.getId(), reason));
    }
}
