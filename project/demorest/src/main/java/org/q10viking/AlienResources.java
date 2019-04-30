package org.q10viking;

import java.util.Arrays;
import java.util.List;

import javax.sound.sampled.SourceDataLine;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * AlienResources
 */
@Path("aliens")
public class AlienResources {

    //避免每次请求都重新创建AlienRepository
    static final AlienRepository alienRepo = new AlienRepository();
    
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public List<Alien> getAlien(){
        
        return alienRepo.getAliens();
    }


    @POST
    @Path("addAlien")
    public Alien addAlien(Alien alien){
        System.out.println(alien);
        alienRepo.createAlien(alien);
        return alien;
    }
}