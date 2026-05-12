package connection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;


public class java2 {
    public static void main(String args[]) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/the","root", "root");

            Statement st = conn.createStatement();

            String query = "create table th(id int, name varchar(20))";
            st.executeUpdate(query);
           
            st.executeUpdate("insert into th values(1,'ruby singh')");
            st.executeUpdate("insert into th values(2,'muskan')");
            st.executeUpdate("insert into th values(3,'nikshay')");
            st.executeUpdate("insert into th values(4,'naman')");
            st.executeUpdate("insert into th values(5,'roshni')");
            System.out.println("Connection built successfully");
            
            st.close();
            conn.close();
        } catch (Exception e) {
            System.out.println("Connection failed");
            System.out.println(e);
        }
    }
}
