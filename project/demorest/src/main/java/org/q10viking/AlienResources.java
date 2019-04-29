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

    @GET
    @Produces(MediaType.APPLICATION_XML)
    public List<Alien> getAlien(){
        Alien alien1  = new Alien();
        alien1.setName("Q10Viking");
        alien1.setPoints(95);


        Alien alien2  = new Alien();
        alien2.setName("huangzhuangzhuang");
        alien2.setPoints(100);

        List<Alien> aliens = Arrays.asList(alien1,alien2);

        return aliens;
    }



    
}