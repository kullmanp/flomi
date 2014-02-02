package ch.kup.flomi.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class FlomiBuchung extends TrackedEntity<Long> {
	@Id
	@TableGenerator(name = "BUCHUNG", allocationSize = 100)
	@GeneratedValue(generator = "BUCHUNG", strategy = GenerationType.TABLE)
	private Long id;

	private Long oldId;

	@ManyToOne()
	@JoinColumn(name = "ADDRESS_ID")
	private Address address;

	@Temporal(TemporalType.DATE)
	private Date anmeldeDatum;

	@ManyToOne()
	@JoinColumn(name = "FLOMI_ID")
	private Flomi flomi;

	@ManyToOne()
	@JoinColumn(name = "TISCH_ID")
	private Tisch tisch;

	private String fakturaNr;

	public Address getAddress() {
		return address;
	}

	public Date getAnmeldeDatum() {
		return anmeldeDatum;
	}

	public String getFakturaNr() {
		return fakturaNr;
	}

	public Flomi getFlomi() {
		return flomi;
	}

	@Override
	public Long getId() {
		return id;
	}

	public Long getOldId() {
		return oldId;
	}

	public Tisch getTisch() {
		return tisch;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public void setAnmeldeDatum(Date anmeldeDatum) {
		this.anmeldeDatum = anmeldeDatum;
	}

	public void setFakturaNr(String fakturaNr) {
		this.fakturaNr = fakturaNr;
	}

	public void setFlomi(Flomi flomi) {
		this.flomi = flomi;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	public void setOldId(Long oldId) {
		this.oldId = oldId;
	}

	public void setTisch(Tisch tisch) {
		this.tisch = tisch;
	}

}
