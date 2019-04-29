package org.q10viking;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Alien
 */
@XmlRootElement
public class Alien {

    private String name;
    private int points;
    
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


}