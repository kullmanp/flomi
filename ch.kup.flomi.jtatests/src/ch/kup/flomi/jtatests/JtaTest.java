package ch.kup.flomi.jtatests;

import java.util.List;

import javax.transaction.TransactionManager;

import ch.kup.flomi.domain.Flomi;
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

	public void activate() throws Exception {
		System.out.println("JTA Tester Component is starting ...");
		tm.begin();
		try {
			Flomi newFlomi = new Flomi();
			newFlomi.setName("JTA FLOMI 2");
			flomiRepository.save(newFlomi);
			
			List<Flomi> findAll = flomiRepository.findAll();
			tm.commit();
			System.out.println("Transaction successfully committed");
			
			System.out.println("FOUND " + findAll.size() + " flomis");
			for (Flomi flomi : findAll) { 
				System.out.println("FLOMI: " + flomi);
			}
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
