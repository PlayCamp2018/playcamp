package controllers;

import play.mvc.Controller;
import akka.actor.ActorSystem;
import akka.stream.Materializer;
import play.mvc.Result;
import play.mvc.WebSocket;
import javax.inject.Inject;

import akka.NotUsed;
import akka.event.LoggingAdapter;
import akka.japi.Pair;
import akka.japi.pf.PFBuilder;
import akka.stream.javadsl.*;
import play.libs.F;
import play.mvc.*;
import akka.event.Logging;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

/**
 * Based on https://github.com/playframework/play-java-chatroom-example
 *
 * Other: https://www.programcreek.com/java-api-examples/index.php?api=play.mvc.WebSocket
 *        https://www.programcreek.com/java-api-examples/?code=ugent-cros/cros-core/cros-core-master/app/controllers/Application.java#
 */

public class ChatController extends Controller {

    private final Flow<String, String, NotUsed> userFlow;
    private final ActorSystem actorSystem;
    private final Materializer materializer;
    private final org.slf4j.Logger logger;

    // static List<WebSocket> chatSockets = new ArrayList<>();

    @Inject
    public ChatController(ActorSystem actorSystem, Materializer materializer) {
        this.actorSystem = actorSystem;
        this.materializer = materializer;

        logger = org.slf4j.LoggerFactory.getLogger(this.getClass());
        LoggingAdapter logging = Logging.getLogger(actorSystem.eventStream(), logger.getName());

        //noinspection unchecked
        Source<String, Sink<String, NotUsed>> source = MergeHub.of(String.class)
                .log("source", logging)
                .recoverWithRetries(-1, new PFBuilder().match(Throwable.class, e -> Source.empty()).build());
        Sink<String, Source<String, NotUsed>> sink = BroadcastHub.of(String.class);

        Pair<Sink<String, NotUsed>, Source<String, NotUsed>> sinkSourcePair = source.toMat(sink, Keep.both()).run(materializer);
        Sink<String, NotUsed> chatSink = sinkSourcePair.first();
        Source<String, NotUsed> chatSource = sinkSourcePair.second();
        this.userFlow = Flow.fromSinkAndSource(chatSink, chatSource).log("userFlow", logging);

    }

    public Result index() {
        return ok(views.html.Chat.index.render());
    }

    public WebSocket ws() {

//        WebSocket socket = WebSocket.Text.accept(request ->
//                ActorFlow.actorRef(ChatWebSocketActor::props,
//                        actorSystem, materializer
//                )
//        );
//
//        chatSockets.add(socket);
//
//        //TODO: List aufraeumen
//
//        return socket;
        return WebSocket.Text.acceptOrResult(request -> {
            if (sameOriginCheck(request)) {
                return CompletableFuture.completedFuture(F.Either.Right(userFlow));
            } else {
                return CompletableFuture.completedFuture(F.Either.Left(forbidden()));
            }
        });
    }

    /**
     * Checks that the WebSocket comes from the same origin.  This is necessary to protect
     * against Cross-Site WebSocket Hijacking as WebSocket does not implement Same Origin Policy.
     * <p>
     * See https://tools.ietf.org/html/rfc6455#section-1.3 and
     * http://blog.dewhurstsecurity.com/2013/08/30/security-testing-html5-websockets.html
     */
    private boolean sameOriginCheck(Http.RequestHeader rh) {
        final Optional<String> origin = rh.header("Origin");

        if (! origin.isPresent()) {
            logger.error("originCheck: rejecting request because no Origin header found");
            return false;
        } else if (originMatches(origin.get())) {
            logger.debug("originCheck: originValue = " + origin);
            return true;
        } else {
            logger.error("originCheck: rejecting request because Origin header value " + origin + " is not in the same origin");
            return false;
        }
    }

    private boolean originMatches(String origin) {
        return origin.contains("localhost:9000") || origin.contains("localhost:19001");
    }
}
