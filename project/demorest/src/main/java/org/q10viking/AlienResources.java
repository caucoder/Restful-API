package org.q10viking;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
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
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    public List<Alien> getAlien(){
        
        return alienRepo.getAliens();
    }


    @GET
    @Path("alien/{id}")
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    public Alien getAlien(@PathParam("id") int id){
        
        return alienRepo.getAlienById(id);
    }


    @POST
    @Path("addAlien")
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    public Alien addAlien(Alien alien){
        System.out.println(alien);
        alienRepo.createAlien(alien);
        return alien;
    }
}