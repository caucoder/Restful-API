package org.q10viking;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * AlienResources
 */
@Path("aliens")
public class AlienResources {

    @GET
    @Produces(MediaType.APPLICATION_XML)
    public Alien getAlien(){
        Alien alien  = new Alien();
        alien.setName("Q10Viking");
        alien.setPoints(95);
        return alien;
    }
    
}