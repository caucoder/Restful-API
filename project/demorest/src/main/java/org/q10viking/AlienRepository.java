package org.q10viking;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * AlienRepository
 */
public class AlienRepository {

    Connection con = null;
    public AlienRepository() {
        String url = "jdbc:mysql://localhost:3306/restdb";
        String username = "root";
        String password = "root";
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, username, password);
        }catch(Exception e){
            System.out.println(e);
        }
    }


    public List<Alien> getAliens(){
        List<Alien> aliens = new ArrayList<>();
        String sql = "select * from alien";
        try {
            Statement st = con.createStatement();
            ResultSet rt = st.executeQuery(sql);
            while(rt.next()){
                Alien a = new Alien();
                a.setId(rt.getInt(1));
                a.setName(rt.getString(2));
                a.setPoints(rt.getInt(3));
                aliens.add(a);
            }
        } catch (Exception e) {
            //TODO: handle exception
        }
        System.out.println("查询所有信息");
        return aliens;
    }

    public Alien getAlienById(int id){
        Alien alien = new Alien();
        String sql = "select * from alien where id = "+id;
        try {
            Statement st = con.createStatement();
            ResultSet rt = st.executeQuery(sql);
            if(rt.next()){
                alien.setId(rt.getInt(1));
                alien.setName(rt.getString(2));
                alien.setPoints(rt.getInt(3)); 
            }
        } catch (Exception e) {
            //TODO: handle exception
        }
        System.out.println("根据id查询");
        return alien;
    }


	public void createAlien(Alien alien) {
        String sql = "insert into alien value(?,?,?)";
        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, alien.getId());
            st.setString(2, alien.getName());
            st.setInt(3, alien.getPoints());
            st.executeUpdate();
        } catch (Exception e) {
            //TODO: handle exception
        }
        System.out.println("添加成功");

	}


	public void updateAlien(Alien alien) {
        String sql = "update alien set name=?,points=? where id=?";
        try {
            PreparedStatement pt = con.prepareStatement(sql);
            pt.setString(1, alien.getName());
            pt.setInt(2, alien.getPoints());
            pt.setInt(3, alien.getId());
            pt.executeUpdate();
        } catch (Exception e) {
            //TODO: handle exception
        }
       
	}


	public void deleteAlienById(int id) {
        String sql = "delete from alien where id=?";
        try {
            PreparedStatement pt = con.prepareStatement(sql);
            pt.setInt(1, id);
            pt.executeUpdate();
        } catch (Exception e) {
            //TODO: handle exception
        }
	}
    
}