package org.q10viking;

import java.util.Arrays;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * AlienResources
 */
@Path("aliens")
public class AlienResources {

    AlienRepository alienRepo = new AlienRepository();

    @GET
    @Produces(MediaType.APPLICATION_XML)
    public List<Alien> getAlien(){
    
        return alienRepo.getAliens();
    }

}