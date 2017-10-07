import java.awt.*;
import javax.swing.*;
import java.sql.*;

public class Nikhil
{
	public static void main(String[] args) throws Exception
	{
		Class.forName("sun.jdbc.odbc.JdbcOdbc");
		Connection c=DriverManager.getConnection("jdbc:odbc:ExampleDSN2");
		PreparedStatement ps=c.prepareStatement("insert into ExampleTable values (?)");
		ps.setString(1,"abcdef");
		ps.executeUpdate();
		ps=c.prepareStatement("select * from ExampleTable");
		ResultSet r=ps.executeQuery();
		while(r.next())
		{
			System.out.println(r.getString("ID"));
		}
	}
}