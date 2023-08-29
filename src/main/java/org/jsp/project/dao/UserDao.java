package org.jsp.project.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.jsp.project.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao {
	@Autowired
	EntityManager manager;
		@Transactional
		public User saveUser(User user) {
			EntityTransaction transaction =manager.getTransaction();
			manager.persist(user);
			transaction.begin();
			transaction.commit();
			return user;
		}
		@Transactional
		public User updateUser(User user) {
			EntityTransaction transaction=manager.getTransaction();
			manager.merge(user);
			transaction.begin();
			transaction.commit();
			return user;
		}
		
		public User findUserById(int id) {
			return manager.find(User.class, id);
		}
		
		public List<User> findUserByName(String name) {
			Query q=manager.createQuery("select u from User u where u.name=?1");
			q.setParameter(1, name);
			return q.getResultList();
		}
		
		public User verifyUser(long phone,String password) {
			Query q=manager.createQuery("select u from User u where u.phone=?1 and password=?2");
			q.setParameter(1, phone);
			q.setParameter(2, password);
			try {
				return(User)q.getSingleResult();
			}catch(NoResultException e) {
				return null;
			}
		}
		
		public boolean deleteUser(int id) {
			User u=findUserById(id);
			if(u!=null) {
				manager.remove(u);
				EntityTransaction transaction=manager.getTransaction();
				transaction.begin();
				transaction.commit();
				return true;
			}
			return false;
}
}
