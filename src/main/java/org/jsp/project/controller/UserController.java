package org.jsp.project.controller;

import java.util.List;
import java.util.Scanner;

import org.jsp.project.config.FoodOrderConfig;
import org.jsp.project.config.UserConfig;
import org.jsp.project.dao.FoodOrderDao;
import org.jsp.project.dao.UserDao;
import org.jsp.project.dto.FoodOrder;
import org.jsp.project.dto.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {
	static Scanner sc=new Scanner(System.in);
	static UserDao udao;
	static FoodOrderDao fdao;

	static {
		ApplicationContext context=new AnnotationConfigApplicationContext(UserConfig.class);
		udao=context.getBean(UserDao.class);
	}
	static {
		ApplicationContext context=new AnnotationConfigApplicationContext(FoodOrderConfig.class);
		fdao=context.getBean(FoodOrderDao.class);
	}
	public static void main(String[] args) {
		System.out.println("1.Save User");
		System.out.println("2.Update User");
		System.out.println("3.Delete User");
		System.out.println("4.fetch User by Phone and Password");
		//System.out.println("5.verify User by Email and Password");
		System.out.println("6.fetch user by id");
		System.out.println("7.fetch user by name");
		System.out.println("8.save foodorder by userID");
		System.out.println("9.save food");
		int choice=sc.nextInt();
		switch(choice) {
		case 1:{
			save();
			break;
		}
		case 2:{
			update();
			break;
		}
		case 3:{
			delete();
			break;
		}
		case 4:{
			fetchUserByPhoneandPassword();
			break;
		}
		case 6:{
			findUserById();
			break;
		}
		case 7:{
			findUserByName();
			break;
		}
		case 8:{
			 saveFoodOrder();
			break;
		}
		case 9:{
			 saveFood();
			break;
		}
		}
	}

	public static void save() {
		System.out.println("enter name,phone,email and password");
		String name=sc.next();
		long phone=sc.nextLong();
		String email=sc.next();
		String password=sc.next();
		User u=new User();
		u.setName(name);
		u.setPhone(phone);
		u.setEmail(email);
		u.setPassword(password);
		u=udao.saveUser(u);
		System.out.println("user saved with Id:"+u.getId());
		
	}
	public static void update() {
		System.out.println("enter ur Id to update the record");
		int id=sc.nextInt();
		System.out.println("enter ur name, phone, email and password to update the record");
		String name=sc.next();
		long phone=sc.nextLong();
		String email=sc.next();
		String password=sc.next();
		User u=new User();
		u.setId(id);
		u.setName(name);
		u.setPhone(phone);
		u.setEmail(email);
		u.setPassword(password);
		u=udao.updateUser(u);
		System.out.println("user updated with Id:"+u.getId());
	}
	public static void delete() {
		System.out.println("enter ur Id to delete the record");
		int id=sc.nextInt();
		User u=new User();
		u.setId(id);
		u=udao.updateUser(u);
		if(u!=null) {
			System.out.println("user deleted with Id:"+u.getId());
		}else {
			System.out.println("entered id is inavlid");
		}	
	  }
  public static void findUserById() {	
	System.out.println("enter user id to find the user");
	int uid=sc.nextInt();
	User u=udao.findUserById(uid);
	System.out.println("id:"+u.getId());
	System.out.println("name:"+u.getName());
	System.out.println("phone:"+u.getPhone());
	System.out.println("email:"+u.getEmail());
	System.out.println("password:"+u.getPassword());
  }
  public static void findUserByName() {	
		System.out.println("enter user name to find the user");
		String uname=sc.next();
		List<User> u=udao.findUserByName(uname);
		System.out.println("id:"+((User) u).getId());
		System.out.println("name:"+((User) u).getName());
		System.out.println("phone:"+((User) u).getPhone());
		System.out.println("email:"+((User) u).getEmail());
		System.out.println("password:"+((User) u).getPassword());
	  }
	public static void fetchUserByPhoneandPassword() {
		System.out.println("enter ur phone and password fetch the user details");
		long phone=sc.nextLong();
		String password=sc.next();
	  User	u=udao.verifyUser(phone,password);
		if(u!=null) {
			System.out.println("user fetched succefully");
		}else {
			System.out.println("entered phone or password invalid");
		}
	}
	public static void saveFoodOrder() {
		System.out.println("enter the user id to add the foodorder");
		int uid=sc.nextInt();
		User u=udao.findUserById(uid);
		if(u!=null) {
			System.out.println("enter the location to save the food order");
			String location=sc.next();
			FoodOrder food=new FoodOrder();
			food.setLocation(location);
			System.out.println("food is added to the User:"+food.getId());
		}
		
	}
	public static void saveFood() {
		System.out.println("enter location to save food");
		String name=sc.next();
		FoodOrder food=new FoodOrder();
		
		food.setLocation(name);
		food=fdao.saveFoodOrder(food, 1);
	
		System.out.println("user saved with Id:"+food.getId());
		
	}
}
