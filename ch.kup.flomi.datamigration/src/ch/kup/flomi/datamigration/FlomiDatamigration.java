package ch.kup.flomi.datamigration;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.transaction.TransactionManager;

import aQute.bnd.annotation.component.Component;
import aQute.bnd.annotation.component.Reference;
import ch.kup.flomi.domain.Address;
import ch.kup.flomi.domain.Flomi;
import ch.kup.flomi.integration.AddressRepository;
import ch.kup.flomi.integration.FlomiRepository;
import ch.kup.flomi.odb.driver.OdbJdbcDriver;

@Component
public class FlomiDatamigration {
	private FlomiRepository flomiRepository;
	private AddressRepository addressRepository;
	private TransactionManager txManager;

	public void activate() throws Exception {
		System.out.println("DATA MIGRATION");

		txManager.begin();
		try {
			migrate();
			txManager.commit();
			System.out.println("Migration successful");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error in migration");
			txManager.rollback();
		}
	}

	private void migrate() throws SQLException {
		OdbJdbcDriver driver = new OdbJdbcDriver();
		Connection connection = driver.connect(
				":jdbc:odb:file:///Users/kup/Desktop/Flomi.odb", null);
		try {
			migrateFlomis(connection);
			migrateAddresses(connection);
		} finally {
			connection.close();
		}
	}

	private void migrateAddresses(Connection connection) throws SQLException {
		if (addressRepository.findAll().size() > 0)
			return;
		Statement stmt = connection.createStatement();
		ResultSet resultSet = stmt.executeQuery("select * from \"Adressen\"");
		while (resultSet.next()) {
			System.out.println("Creating Address...");
			createAddress(resultSet);
		}
		stmt.close();
	}

	private void migrateFlomis(Connection connection) throws SQLException {
		if (flomiRepository.findAll().size() > 0)
			return;
		Statement stmt = connection.createStatement();
		ResultSet resultSet = stmt.executeQuery("select * from \"Flomis\"");
		while (resultSet.next()) {
			System.out.println("Creating Flomi...");
			createFlomi(resultSet);
		}
		stmt.close();
	}

	private void createAddress(ResultSet rs) throws SQLException {
		Address adr = new Address();
		adr.setId(rs.getLong("ID"));
		adr.setAddress(rs.getString("Adresse"));
		adr.setAddress2(rs.getString("Adresse2"));
		adr.setBirthday(rs.getString("Geburtsdatum"));
		adr.setCarNumber(rs.getString("KFZ"));
		adr.setCity(rs.getString("Ort"));
		adr.setCountry(rs.getString("Land"));
		adr.setEmail(rs.getString("email"));
		adr.setFax(rs.getString("Faxnummer"));
		adr.setFirstName(rs.getString("Vorname"));
		adr.setLastName(rs.getString("Nachname"));
		adr.setMobilePhone(rs.getString("MobilesTelefon"));
		adr.setPhone(rs.getString("TelefonPrivat"));
		adr.setSalutation(rs.getString("Anrede"));
		adr.setTitle(rs.getString("Titel"));
		adr.setZip(rs.getString("Postleitzahl"));
		addressRepository.save(adr);
	}

	private void createFlomi(ResultSet rs) throws SQLException {
		Flomi flomi = new Flomi();
		flomi.setName(rs.getString("ID"));
		flomi.setDate(rs.getDate("Datum"));
		flomiRepository.save(flomi);
	}

	@Reference
	public void setAddressRepository(AddressRepository addressRepository) {
		this.addressRepository = addressRepository;
	}

	@Reference
	public void setFlomiRepository(FlomiRepository flomiRepository) {
		this.flomiRepository = flomiRepository;
	}

	@Reference
	public void setTxManager(TransactionManager txManager) {
		this.txManager = txManager;
	}

}
