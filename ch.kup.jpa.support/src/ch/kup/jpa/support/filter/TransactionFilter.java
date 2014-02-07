package ch.kup.jpa.support.filter;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import aQute.bnd.annotation.component.Component;
import aQute.bnd.annotation.component.Reference;
import ch.kup.jpa.support.filter.TransactionFilter.Config;

@Component(properties = "pattern=.*", designate = Config.class)
public class TransactionFilter implements Filter {
	interface Config {
		String pattern();
	}

	Logger logger = LoggerFactory.getLogger(TransactionFilter.class);

	private EntityManager em;

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain next) throws IOException, ServletException {
		logger.trace("TransactionFilter begin transaction");
		em.getTransaction().begin();
		try {
			logger.trace("TransactionFilter about to do some work");
			next.doFilter(req, resp);
			logger.trace("TransactionFilter work done. About to commit the transaction");
			em.getTransaction().commit();
			logger.trace("TransactionFilter Transaction successfully committed");
		} catch (Exception e) {
			logger.trace(
					"TransactionFiler Exception encountered, going to roll back tx",
					e);
			em.getTransaction().rollback();
			throw new RuntimeException(e);
		}
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		logger.info("TransactionFilter initialized");
	}

	@Reference
	public void setEm(EntityManager em) {
		this.em = em;
	}

}
