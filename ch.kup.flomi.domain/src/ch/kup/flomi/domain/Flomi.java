package ch.kup.flomi.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Flomi extends TrackedEntity<Long> {
	@Id
	@GeneratedValue
	private Long id;

	@Column(unique = true)
	private String name;

	@Temporal(TemporalType.DATE)
	private Date date;

	public Date getDate() {
		return date;
	}

	@Override
	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Flomi[" + getId() + ", name=" + name + "]";
	}

}
