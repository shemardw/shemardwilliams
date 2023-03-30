package project.classes;


import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
public class SessionFactoryBuilder {
	private static SessionFactory sf = null;
	private static final Logger LOGGER = LogManager.getLogger(SessionFactoryBuilder.class);
	public static SessionFactory getSessionFactory() {
		if(sf == null) {
			try {
				sf = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Complaint.class).buildSessionFactory();
			} catch (RuntimeException e) {
				e.printStackTrace();
				LOGGER.error("Something went Wrong.");
			}
		}
		return sf;
	}
	
	public static void closeSessionFactory() {
		if(sf != null) {
			sf.close();
		}
	}
}
