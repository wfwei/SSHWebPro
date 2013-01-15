package cn.edu.zju.plex.wp.util;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

	private static final ThreadLocal<Session> threadLocal = new ThreadLocal<Session>();
	public static SessionFactory sessionFactory = null;

	static {
		buildSession();
	}

	public static Session getSession() throws HibernateException {
		Session session = threadLocal.get();
		if (session == null || !session.isOpen()) {
			if (sessionFactory == null) {
				rebuildSessionFactory();
			}
			session = (sessionFactory != null) ? sessionFactory.openSession()
					: null;
			threadLocal.set(session);
		}
		return session;
	}

	public static void rebuildSessionFactory() {
		buildSession();
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public static void closeConnection() {
		Session session = threadLocal.get();
		threadLocal.set(null);
		if (session != null) {
			session.close();
		}
	}

	private static void buildSession() {
		try {
			Configuration config = new Configuration()
					.configure("cn/edu/zju/plex/wp/config/hibernate.cfg.xml");
			sessionFactory = config.buildSessionFactory();
		} catch (Exception e) {
			System.err.println("创建会话工厂失败");
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		System.out.println("Ok");
	}
}
