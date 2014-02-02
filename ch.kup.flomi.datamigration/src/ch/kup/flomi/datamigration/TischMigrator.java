package ch.kup.flomi.datamigration;

import java.sql.ResultSet;
import java.sql.SQLException;

import ch.kup.flomi.domain.Tisch;
import ch.kup.flomi.integration.TischRepository;

public class TischMigrator extends
		SimpleTableMigrator<TischRepository, Tisch, Long> {

	public TischMigrator(TischRepository repository, String sql) {
		super(repository, sql);
	}

	@Override
	public void createEntity(ResultSet rs) throws SQLException {
		Tisch tisch = new Tisch();
		tisch.setName(rs.getString("ID"));
		tisch.setPreis(rs.getBigDecimal("Preis"));
		getRepository().save(tisch);
	}

}
