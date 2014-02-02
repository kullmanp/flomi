package ch.kup.flomi.datamigration;

import java.sql.Connection;

import javax.transaction.TransactionManager;

import aQute.bnd.annotation.component.Component;
import aQute.bnd.annotation.component.Reference;
import ch.kup.flomi.integration.AddressRepository;
import ch.kup.flomi.integration.FlomiBuchungRepository;
import ch.kup.flomi.integration.FlomiRepository;
import ch.kup.flomi.integration.TischRepository;
import ch.kup.flomi.odb.driver.OdbJdbcDriver;

@Component
public class FlomiDatamigration {
	private FlomiRepository flomiRepository;
	private AddressRepository addressRepository;
	private TransactionManager txManager;
	private TischRepository tischRepository;
	private FlomiBuchungRepository flomiBuchungRepository;

	public void activate() throws Exception {
		try {
			System.out.println("DATA MIGRATION");
			migrate();
			System.out.println("Migration successful");
		} catch (Exception e) {
			System.out.println("ERROR in Migration");
			e.printStackTrace();
		}
	}

	private void migrate() throws Exception {
		OdbJdbcDriver driver = new OdbJdbcDriver();
		Connection connection = driver.connect(
				":jdbc:odb:file:///Users/kup/Desktop/Flomi.odb", null);
		try {
			new FlomiMigrator(flomiRepository, "select * from \"Flomis\"")
					.migrate(connection, txManager);
			new AddressMigrator(addressRepository, "select * from \"Adressen\"")
					.migrate(connection, txManager);
			new TischMigrator(tischRepository, "select * from \"Tische\"")
					.migrate(connection, txManager);
			new FlomiBuchungMigrator(flomiBuchungRepository,
					"select * from \"Buchungen\"", addressRepository,
					flomiRepository, tischRepository).migrate(connection,
					txManager);
		} finally {
			connection.close();
		}
	}

	@Reference
	public void setAddressRepository(AddressRepository addressRepository) {
		this.addressRepository = addressRepository;
	}

	@Reference
	public void setFlomiBuchungRepository(
			FlomiBuchungRepository flomiBuchungRepository) {
		this.flomiBuchungRepository = flomiBuchungRepository;
	}

	@Reference
	public void setFlomiRepository(FlomiRepository flomiRepository) {
		this.flomiRepository = flomiRepository;
	}

	@Reference
	public void setTischRepository(TischRepository tischRepository) {
		this.tischRepository = tischRepository;
	}

	@Reference
	public void setTxManager(TransactionManager txManager) {
		this.txManager = txManager;
	}

}
