/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UIWithJava;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author papar
 */
public class Main {
    
    static private Connection con;
    
    public static void main(String[] args){
        EventQueue.invokeLater(()->{
            try {
                Class.forName("org.sqlite.JDBC");
                con=DriverManager.getConnection("jdbc:sqlite:test.db");
                System.out.println("Connected successfully");
                Statement stm=con.createStatement();
                createDatabase(stm);
                DatabaseHandler.intitializeInstance(con);
                var frame=new GUIBuilderFrame();
                frame.setVisible(true);
            } catch (ClassNotFoundException | SQLException e) {
                System.out.println(e.getMessage());
            }

        });
    }
    
    private static void createDatabase(Statement stm) throws SQLException{
        String pepeTablesql="""
                            CREATE TABLE IF NOT EXISTS pepe (
                                id INTEGER PRIMARY KEY AUTOINCREMENT,
                                name VARCHAR(50) NOT NULL,
                                age INTEGER NOT NULL CHECK(age BETWEEN 0 AND 100)
                            );
                            """;
        stm.execute(pepeTablesql);
    }
    
}