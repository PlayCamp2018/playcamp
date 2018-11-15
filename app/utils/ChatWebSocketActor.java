package utils;
import akka.actor.*;

import java.sql.Timestamp;

public class ChatWebSocketActor extends AbstractActor {

    public static Props props(ActorRef out) {
        return Props.create(ChatWebSocketActor.class, out);
    }

    private final ActorRef out;

    public ChatWebSocketActor(ActorRef out) {
        this.out = out;
    }

    @Override
    public Receive createReceive() {
        Timestamp time = new Timestamp(System.currentTimeMillis());
        return receiveBuilder().match(String.class, message -> out.tell(time + ": " + message, self())).build();
    }

    public void postStop() {
        System.out.println("WebSocket has closed");
    }
}