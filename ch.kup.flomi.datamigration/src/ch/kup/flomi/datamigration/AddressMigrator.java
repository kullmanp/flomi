package ch.kup.flomi.datamigration;

import java.sql.ResultSet;
import java.sql.SQLException;

import ch.kup.flomi.domain.Address;
import ch.kup.flomi.integration.AddressRepository;

public class AddressMigrator extends
		SimpleTableMigrator<AddressRepository, Address, Long> {

	public AddressMigrator(AddressRepository repository, String sql) {
		super(repository, sql);
	}

	@Override
	public void createEntity(ResultSet rs) throws SQLException {
		Address adr = new Address();
		adr.setAddressNumber(rs.getLong("ID"));
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
		getRepository().save(adr);
	}

}
