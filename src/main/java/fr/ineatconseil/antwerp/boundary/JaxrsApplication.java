package fr.ineatconseil.antwerp.boundary;

import javax.ws.rs.ApplicationPath;
import org.glassfish.jersey.moxy.json.MoxyJsonBinder;
import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("api")
public class JaxrsApplication extends ResourceConfig{
    public JaxrsApplication() {
        super(PlayersResource.class, GamesResource.class);
        addBinders(new MoxyJsonBinder());
    }
}
