package by.rakushev.util;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class HibernateUtil {
	private static final Logger LOGGER = Logger.getLogger(HibernateUtil.class
			.getName());

	private static final SessionFactory sessionFactory;
	private static final ThreadLocal<Session> session = new ThreadLocal<Session>();

	static {
		try {
			Configuration configuration = new Configuration();
			configuration.configure();
			//configuration.configure("hibernate.cfg.xml");
			ServiceRegistry serviceRegistry = new ServiceRegistryBuilder()
					.applySettings(configuration.getProperties())
					.buildServiceRegistry();
			sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		} catch (Throwable ex) {
			LOGGER.log(Level.SEVERE, "Failed to create sessionFactory.", ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	/**
	 * Get current Hibernate session or initialize it if it's null
	 * 
	 * @return Hibernate session instance
	 */
	public static Session currentSession() {
		Session s = (Session) session.get();
		// Open a new Session, if this Thread has none yet
		try {
			if (s == null) {
				s = sessionFactory.openSession();
				session.set(s);
			}
		} catch (HibernateException e) {
			LOGGER.log(Level.SEVERE,
					"Get current session error: " + e.getMessage(), e);
		}
		return s;
	}

	/**
	 * Close current Hibernate session
	 */
	public static void closeSession() {
		Session s = (Session) session.get();
		session.set(null);
		try {
			if (s != null)
				s.close();
		} catch (HibernateException e) {
			LOGGER.log(Level.SEVERE,
					"Close current session error: " + e.getMessage(), e);
		}
	}
}

