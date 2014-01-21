package ch.kup.flomi.jtatests;

import java.util.List;
import java.util.UUID;

import javax.transaction.TransactionManager;

import ch.kup.flomi.domain.Address;
import ch.kup.flomi.domain.Flomi;
import ch.kup.flomi.integration.AddressRepository;
import ch.kup.flomi.integration.FlomiRepository;
import aQute.bnd.annotation.component.Component;
import aQute.bnd.annotation.component.Reference;

@Component
public class JtaTest {
	private TransactionManager tm;

	private FlomiRepository flomiRepository;
	private AddressRepository addressRepository;

	@Reference
	public void setAddressRepository(AddressRepository addressRepository) {
		this.addressRepository = addressRepository;
	}

	@Reference
	void setTransactionManager(TransactionManager tm) {
		this.tm = tm;
	}

	public void activate() throws Exception {
		System.out.println("JTA Tester Component is starting ...");
		tm.begin();
		try {
			Flomi newFlomi = new Flomi();
			newFlomi.setName("JTA FLOMI 2");
			flomiRepository.persist(newFlomi);
			createTestAddresses();

			List<Flomi> findAll = flomiRepository.findAll();
			tm.commit();
			System.out.println("Transaction successfully committed");

			System.out.println("FOUND " + findAll.size() + " flomis");
			for (Flomi flomi : findAll) {
				System.out.println("FLOMI: " + flomi);
			}

		} catch (Throwable t) {
			t.printStackTrace();
			System.out.println("Rolling back...");
			tm.rollback();
		}
	}

	private void createTestAddresses() {
		Address address = new Address();
		address.setFirstName("Test"); 
		address.setLastName(UUID.randomUUID().toString());
		address.setZip("8000");
		addressRepository.persist(address);
	}

	public FlomiRepository getFlomiRepository() {
		return flomiRepository;
	}

	@Reference
	public void setFlomiRepository(FlomiRepository flomiRepository) {
		this.flomiRepository = flomiRepository;
	}
}
