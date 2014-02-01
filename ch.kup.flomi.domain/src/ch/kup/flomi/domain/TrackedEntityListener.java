package ch.kup.flomi.domain;

import java.sql.Timestamp;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

public class TrackedEntityListener {

	@PrePersist
	public void prePersist(TrackedEntity<?> entity) {
		entity.setCreatedBy("kup");
		entity.setCreatedOn(new Timestamp(System.currentTimeMillis()));
	}

	@PreUpdate
	public void preUpdate(TrackedEntity<?> entity) {
		entity.setChangedBy("kup");
		entity.setChangedOn(new Timestamp(System.currentTimeMillis()));
	}

}
