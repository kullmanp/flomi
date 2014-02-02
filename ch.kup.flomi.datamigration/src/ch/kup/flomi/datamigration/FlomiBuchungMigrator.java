package ch.kup.flomi.datamigration;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import ch.kup.flomi.domain.Address;
import ch.kup.flomi.domain.Flomi;
import ch.kup.flomi.domain.FlomiBuchung;
import ch.kup.flomi.domain.Tisch;
import ch.kup.flomi.integration.AddressRepository;
import ch.kup.flomi.integration.FlomiBuchungRepository;
import ch.kup.flomi.integration.FlomiRepository;
import ch.kup.flomi.integration.TischRepository;

public class FlomiBuchungMigrator extends
		SimpleTableMigrator<FlomiBuchungRepository, FlomiBuchung, Long> {

	private final AddressRepository addressRepository;
	private final FlomiRepository flomiRepository;
	private final TischRepository tischRepository;

	private Map<Long, Address> addresses;
	private Map<String, Flomi> flomis;
	private Map<String, Tisch> tische;

	public FlomiBuchungMigrator(FlomiBuchungRepository repository, String sql,
			AddressRepository addressRepository,
			FlomiRepository flomiRepository, TischRepository tischRepository) {
		super(repository, sql);
		this.addressRepository = addressRepository;
		this.flomiRepository = flomiRepository;
		this.tischRepository = tischRepository;
	}

	@Override
	protected void beforeMigration() {
		addresses = new HashMap<Long, Address>();
		for (Address address : addressRepository.findAll()) {
			addresses.put(address.getAddressNumber(), address);
		}
		flomis = new HashMap<String, Flomi>();
		for (Flomi flomi : flomiRepository.findAll()) {
			flomis.put(flomi.getName().toLowerCase(), flomi);
		}
		tische = new HashMap<String, Tisch>();
		for (Tisch tisch : tischRepository.findAll()) {
			tische.put(tisch.getName().toLowerCase(), tisch);
		}
	}

	@Override
	public void createEntity(ResultSet rs) throws SQLException {
		FlomiBuchung fb = new FlomiBuchung();
		fb.setOldId(rs.getLong("ID"));
		fb.setAddress(addresses.get(rs.getLong("AdresseID")));
		fb.setAnmeldeDatum(rs.getDate("AnmeldeDatum"));
		String flomiName = rs.getString("Flomi");
		if (flomiName != null)
			fb.setFlomi(flomis.get(flomiName.toLowerCase()));
		String tischName = rs.getString("Tisch");
		if (tischName != null)
			fb.setTisch(tische.get(tischName.toLowerCase()));
		fb.setFakturaNr(rs.getString("FakturaNr"));
		getRepository().save(fb);
	}

}
