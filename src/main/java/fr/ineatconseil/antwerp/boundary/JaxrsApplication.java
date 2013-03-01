package fr.ineatconseil.antwerp.boundary;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.media.sse.OutboundEventWriter;
import org.glassfish.jersey.moxy.json.MoxyJsonFeature;
import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("api")
public class JaxrsApplication extends ResourceConfig{
    public JaxrsApplication() {
        super(  PlayersResource.class, 
                MovesResource.class,
                GamesResource.class,
                OutboundEventWriter.class,
                MoxyJsonFeature.class
        );
    }
}
