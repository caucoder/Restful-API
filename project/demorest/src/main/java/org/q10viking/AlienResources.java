package org.q10viking;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
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
    @Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
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

    //update data
    @PUT
    @Path("updateAlien")
    @Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    public void updateAlien(Alien alien){
        System.out.println(alien);
        //如果不存在，则添加
        if(alienRepo.getAlienById(alien.getId()).getId()==0){
            System.out.println("该对象不能更新，添加"+alien);
            alienRepo.createAlien(alien);
        }else{
            alienRepo.updateAlien(alien);
            System.out.println("更新成功。");
        }
    }




    @DELETE
    @Path("alien/{id}")
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    @Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    public Alien deleteAlien(@PathParam("id") int id){
        
        Alien a = alienRepo.getAlienById(id);
        //如果id=0，表示是空值
        if(a.getId() != 0)  
            alienRepo.deleteAlienById(id);
        return a;
    }
}