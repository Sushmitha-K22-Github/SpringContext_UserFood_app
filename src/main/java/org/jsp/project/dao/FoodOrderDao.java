package org.jsp.project.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.jsp.project.dto.FoodOrder;
import org.jsp.project.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class FoodOrderDao {
	@Autowired
	EntityManager manager;
		@SuppressWarnings("unchecked")
		@Transactional
		public FoodOrder saveFoodOrder(FoodOrder food,int user_id) {
			User u=manager.find(User.class, user_id);
			if(u!=null) {
				u.getFoods().add(food);
				food.setUsers((List<User>) u);
			manager.persist(food);
			EntityTransaction transaction =manager.getTransaction();
			transaction.begin();
			transaction.commit();
			}
			return food;
		}
		@Transactional
		public FoodOrder updateFoodOrder(FoodOrder food,int user_id) {
			User u=manager.find(User.class, user_id);
			if(u!=null) {
				u.getFoods().add(food);
				food.setUsers((List<User>) u);
			EntityTransaction transaction=manager.getTransaction();
			manager.merge(food);
			transaction.begin();
			transaction.commit();
			return food;
		}
			return null;
		}
		
		public FoodOrder findFoodById(int id) {
			return manager.find(FoodOrder.class, id);
		}
		
		public List<FoodOrder> findFoodByName(String name) {
			Query q=manager.createQuery("select FoodOrder from f where f.name=?1");
			q.setParameter(1, name);
			return q.getResultList();
		}
		
		public boolean deleteFoodOrder(int id) {
			FoodOrder food=findFoodById(id);
			if(food!=null) {
				manager.remove(food);
				EntityTransaction transaction=manager.getTransaction();
				transaction.begin();
				transaction.commit();
				return true;
			}
			return false;
}
		public List<FoodOrder> findFoodByUserId(int User_id){
			Query q=manager.createQuery("select FoodOrder from f where f.users.id=?1");
			q.setParameter(1,User_id);
			return q.getResultList();
		}
}
