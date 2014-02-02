package ch.kup.flomi.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Tisch extends TrackedEntity<Long> {
	@Id
	@GeneratedValue
	private Long id;

	@Column(unique = true)
	private String name;

	@Column(scale = 2, precision = 10)
	private BigDecimal preis;

	@Override
	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public BigDecimal getPreis() {
		return preis;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPreis(BigDecimal preis) {
		this.preis = preis;
	}

}
