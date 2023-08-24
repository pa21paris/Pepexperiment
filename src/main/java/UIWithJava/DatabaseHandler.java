/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UIWithJava;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author papar
 */
public class DatabaseHandler {
    
    private Connection con;
    private static DatabaseHandler instance=null;
    
    public static void intitializeInstance(Connection con){
        if(instance==null){
            instance=new DatabaseHandler(con);
        }
    }
    
    public static DatabaseHandler getInstance(){
        return instance;
    }
    
    private DatabaseHandler(Connection con){
        this.con=con;
    }
    
    public void insertIntoPepe(String name, int age) throws SQLException{
        Statement stm=this.con.createStatement();
        stm.executeUpdate("INSERT INTO pepe(name, age) VALUES ('"+name+"', "+age+");");
    }
    
    public void insertIntoPepe(Pepe pepe) throws SQLException{
        Statement stm=this.con.createStatement();
        stm.executeUpdate("INSERT INTO pepe(name, age) VALUES ('"+pepe.name()+"', "+pepe.age()+");");
    }
    
    public ArrayList<Pepe> getPepes() throws SQLException{
        Statement stm=this.con.createStatement();
        ResultSet res=stm.executeQuery("SELECT * FROM pepe;");
        var pepesArray=new ArrayList<Pepe>();
        while(res.next()){
            int id=res.getInt(1);
            String name=res.getString(2);
            int age=res.getInt(3);
            pepesArray.add(new Pepe(id, name, age));
        }
        return pepesArray;
    }
}
