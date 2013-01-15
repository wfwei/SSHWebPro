package cn.edu.zju.plex.wp.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import cn.edu.zju.plex.wp.bean.UserBean;
import cn.edu.zju.plex.wp.util.HibernateUtil;

public class UserDAO {

	private UserDAO user;

	public UserDAO getUser() {
		return user;
	}

	public void setUser(UserDAO user) {
		this.user = user;
	}

	public static boolean registerValidate(String username) {
		boolean isConflict = true;
		Session session = null;
		try {
			session = HibernateUtil.getSession();
			String hql = "select u.username from UserBean u where u.username=?";
			Query query = session.createQuery(hql);
			query.setString(0, username);
			if (query.list().size() == 0) {
				isConflict = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			HibernateUtil.closeConnection();
		}
		return !isConflict;
	}

	/**
	 * 判断登录用户是否合法，合法返回完整的user对象，否则返回null。
	 */
	public static UserBean loginValidate(UserBean user) {
		Session session = null;
		UserBean validUser = null;
		try {
			session = HibernateUtil.getSession();
			String hql = "from UserBean user where user.username=? and user.password=?";
			Query qUser = session.createQuery(hql);
			qUser.setParameter(0, user.getUsername());
			qUser.setParameter(1, user.getPassword());
			List<UserBean> resList = qUser.list();
			System.out.println(resList);
			if (resList.size() == 1) {
				validUser = resList.get(0);
				System.out.println("Login Succeed:" + validUser);
			}
		} catch (Exception e) {
			System.out.println("添加用户失败");
			e.printStackTrace();
		} finally {
			HibernateUtil.closeConnection();
		}
		return validUser;
	}

	public static boolean addUser(UserBean user) {
		Session session = null;
		boolean success = false;
		try {
			session = HibernateUtil.getSession();
			session.beginTransaction();
			user.setTimeJoined(new Date());
			user.setLastLogin(new Date());
			session.save(user);
			session.getTransaction().commit();
			success = true;
		} catch (Exception e) {
			session.getTransaction().rollback();
			System.out.println("添加用户失败");
			e.printStackTrace();
		} finally {
			HibernateUtil.closeConnection();
		}
		return success;
	}

	public void rmUser(UserBean user) {

	}

	public void updateUser(UserBean user) {

	}

	/**
	 * 更新用户登录时间，并返回用户。
	 * 
	 * @param user
	 * @return
	 */
	public static UserBean loginUser(UserBean user) {
		Session session = null;
		try {
			session = HibernateUtil.getSession();
			user = (UserBean) session.get(UserBean.class, user.getId());
			user.setLastLogin(new Date());
			session.flush();
		} catch (Exception e) {
			System.err.println("登录用户失败");
			e.printStackTrace();
		} finally {
			HibernateUtil.closeConnection();
		}
		return user;
	}

}
