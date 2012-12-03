/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ineatconseil.antwerp;

import javax.ws.rs.ApplicationPath;
import org.glassfish.jersey.moxy.json.MoxyJsonBinder;
import org.glassfish.jersey.server.ResourceConfig;

/**
 *
 * @author nicolasger
 */
@ApplicationPath("api")
public class JaxrsApplication extends ResourceConfig{
    public JaxrsApplication() {
        super(PlayersResource.class, GamesResource.class);
        addBinders(new MoxyJsonBinder());
    }
}
