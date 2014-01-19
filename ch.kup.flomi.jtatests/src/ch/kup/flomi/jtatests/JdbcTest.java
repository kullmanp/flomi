package ch.kup.flomi.jtatests;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.sql.XAConnection;
import javax.sql.XADataSource;

import aQute.bnd.annotation.component.Component;
import aQute.bnd.annotation.component.Reference;

@Component
public class JdbcTest {

	private XADataSource ds;

	public XADataSource getDs() {
		return ds;
	}

	public void activate()  {
		try { 
			System.out.println("XA Datasource: " + ds);
			XAConnection xaConnection = ds.getXAConnection();
			System.out.println(xaConnection);
			Connection connection = xaConnection.getConnection();
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("select * from flomi.FLOMI");
			while (resultSet.next()) {
				System.out.println("RESULT: " + resultSet.toString());
			}
			System.out.println("RESULTS DONE");
			statement.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Reference
	public void setDs(XADataSource ds) {
		this.ds = ds;
	}
} 
