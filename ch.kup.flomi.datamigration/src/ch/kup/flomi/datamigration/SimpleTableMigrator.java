package ch.kup.flomi.datamigration;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.persistence.EntityManager;

import ch.kup.flomi.integration.Repository;

public abstract class SimpleTableMigrator<R extends Repository<E, K>, E, K> {
	private final R repository;
	private final String sql;

	public SimpleTableMigrator(R repository, String sql) {
		super();
		this.repository = repository;
		this.sql = sql;
	}

	/**
	 * inside the transaction
	 */
	protected void beforeMigration() {
	}

	public abstract void createEntity(ResultSet rs) throws SQLException;

	public R getRepository() {
		return repository;
	}

	public void migrate(Connection connection, EntityManager em)
			throws Exception {
		try {
			em.getTransaction().begin();
			beforeMigration();
			migrateInternal(connection);
			em.getTransaction().commit();
		} catch (Exception e) {
			try {
				em.getTransaction().rollback();
			} catch (Exception e2) {
				// ignore this
			}
			throw new RuntimeException("Error in migration", e);
		}
	}

	private void migrateInternal(Connection connection) throws SQLException {
		if (repository.findAll().size() > 0)
			return;
		Statement stmt = connection.createStatement();
		ResultSet resultSet = stmt.executeQuery(sql);
		while (resultSet.next()) {
			createEntity(resultSet);
		}
		stmt.close();
	}

}
