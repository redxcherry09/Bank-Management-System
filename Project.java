package connection;

import java.sql.Connection;
import java.sql.DriverManager;


public class Project {

    public static void main(String args[]){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection Conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/job_portal","root","root");
            System.out.println("connection build succesfully");
        }
        
        catch(Exception e){
            System.out.println("connection failed");
        }
    }
}

