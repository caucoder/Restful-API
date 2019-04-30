package org.q10viking;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Alien
 */
@XmlRootElement
public class Alien {

    private String name;
    private int points;
    private int id;
    

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPoints() {
        return this.points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }


}