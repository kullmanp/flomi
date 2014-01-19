package ch.kup.flomi.jtatests;

import javax.transaction.TransactionManager;

import ch.kup.flomi.integration.FlomiRepository;
import aQute.bnd.annotation.component.Component;
import aQute.bnd.annotation.component.Reference;

@Component
public class JtaTest {
	private TransactionManager tm;

	private FlomiRepository flomiRepository;
	 
	@Reference
	void setTransactionManager(TransactionManager tm) {
		this.tm = tm;
	}
	
	public void activate( ) throws Exception {
		System.out.println("JTA Tester Component is starting ...");
		tm.begin();
		try {
			flomiRepository.findAll();
			tm.commit();
			System.out.println("Transaction successfully committed");
		} catch (Throwable t) {
			System.out.println("Rolling back...");
			tm.rollback();
			t.printStackTrace();
		}
	}

	public FlomiRepository getFlomiRepository() {
		return flomiRepository;
	}

	@Reference
	public void setFlomiRepository(FlomiRepository flomiRepository) {
		this.flomiRepository = flomiRepository;
	}
}
