package ch.kup.flomi.datamigration;

import java.sql.ResultSet;
import java.sql.SQLException;

import ch.kup.flomi.domain.Flomi;
import ch.kup.flomi.integration.FlomiRepository;

public class FlomiMigrator extends
		SimpleTableMigrator<FlomiRepository, Flomi, Long> {

	public FlomiMigrator(FlomiRepository repository, String sql) {
		super(repository, sql);
	}

	@Override
	public void createEntity(ResultSet rs) throws SQLException {
		Flomi flomi = new Flomi();
		flomi.setName(rs.getString("ID"));
		flomi.setDate(rs.getDate("Datum"));
		getRepository().save(flomi);
	}

}
