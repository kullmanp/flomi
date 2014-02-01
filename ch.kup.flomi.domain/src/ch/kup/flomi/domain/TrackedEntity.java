package ch.kup.flomi.domain;

import java.sql.Timestamp;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

/**
 * A representation of the model object '<em><b>TrackedEntity</b></em>'. <!--
 * begin-user-doc --> <!-- end-user-doc -->
 */
@MappedSuperclass()
// @EntityListeners(value = { TrackedEntityListener.class })
public abstract class TrackedEntity<PK> implements Identifiable<PK> {

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 */
	@Basic()
	@Column(name = "anid")
	private String createdBy = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 */
	@Basic()
	@Column(name = "andatime")
	private Timestamp createdOn = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 */
	@Basic()
	@Column(name = "mutid")
	private String changedBy = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 */
	@Basic()
	@Column(name = "mutdatime")
	@Version
	private Timestamp changedOn = null;

	/**
	 * Returns the value of '<em><b>changedBy</b></em>' feature.
	 * 
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the value of '<em><b>changedBy</b></em>' feature
	 */
	public String getChangedBy() {
		return changedBy;
	}

	/**
	 * Returns the value of '<em><b>changedOn</b></em>' feature.
	 * 
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the value of '<em><b>changedOn</b></em>' feature
	 */
	public Timestamp getChangedOn() {
		return changedOn;
	}

	/**
	 * Returns the value of '<em><b>createdBy</b></em>' feature.
	 * 
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the value of '<em><b>createdBy</b></em>' feature
	 */
	public String getCreatedBy() {
		return createdBy;
	}

	/**
	 * Returns the value of '<em><b>createdOn</b></em>' feature.
	 * 
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the value of '<em><b>createdOn</b></em>' feature
	 */
	public Timestamp getCreatedOn() {
		return createdOn;
	}

	/**
	 * Sets the '{@link TrackedEntity#getChangedBy() <em>changedBy</em>}'
	 * feature.
	 * 
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param newChangedBy
	 *            the new value of the '{@link TrackedEntity#getChangedBy()
	 *            changedBy}' feature.
	 */
	public void setChangedBy(String newChangedBy) {
		changedBy = newChangedBy;
	}

	/**
	 * Sets the '{@link TrackedEntity#getChangedOn() <em>changedOn</em>}'
	 * feature.
	 * 
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param newChangedOn
	 *            the new value of the '{@link TrackedEntity#getChangedOn()
	 *            changedOn}' feature.
	 */
	public void setChangedOn(Timestamp newChangedOn) {
		changedOn = newChangedOn;
	}

	/**
	 * Sets the '{@link TrackedEntity#getCreatedBy() <em>createdBy</em>}'
	 * feature.
	 * 
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param newCreatedBy
	 *            the new value of the '{@link TrackedEntity#getCreatedBy()
	 *            createdBy}' feature.
	 */
	public void setCreatedBy(String newCreatedBy) {
		createdBy = newCreatedBy;
	}

	/**
	 * Sets the '{@link TrackedEntity#getCreatedOn() <em>createdOn</em>}'
	 * feature.
	 * 
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param newCreatedOn
	 *            the new value of the '{@link TrackedEntity#getCreatedOn()
	 *            createdOn}' feature.
	 */
	public void setCreatedOn(Timestamp newCreatedOn) {
		createdOn = newCreatedOn;
	}

	/**
	 * A toString method which prints the values of all EAttributes of this
	 * instance. <!-- begin-user-doc --> <!-- end-user-doc -->
	 */
	@Override
	public String toString() {
		return "TrackedEntity " + " [createdBy: " + getCreatedBy() + "]"
				+ " [createdOn: " + getCreatedOn() + "]" + " [changedBy: "
				+ getChangedBy() + "]" + " [changedOn: " + getChangedOn() + "]";
	}
}
