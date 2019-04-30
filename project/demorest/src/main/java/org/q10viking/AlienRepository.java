package org.q10viking;

import java.util.ArrayList;
import java.util.List;

/**
 * AlienRepository
 */
public class AlienRepository {

    List<Alien> aliens;
    public AlienRepository() {

        aliens = new ArrayList<>();
        Alien alien1  = new Alien();
        alien1.setId(1);
        alien1.setName("Q10Viking");
        alien1.setPoints(95);


        Alien alien2  = new Alien();
        alien2.setId(2);
        alien2.setName("huangzhuangzhuang");
        alien2.setPoints(100);

        aliens.add(alien1);
        aliens.add(alien2);
    }


    public List<Alien> getAliens(){
        return this.aliens;
    }

    public Alien getAlienById(int id){
        return aliens.stream()
                    .filter(x -> x.getId() == id)
                    .findAny()
                    .orElse(null);
    }
    
}