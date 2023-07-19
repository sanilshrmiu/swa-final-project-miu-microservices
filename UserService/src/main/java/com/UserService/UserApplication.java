package com.UserService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.UserService.daos.IUserDao;
import com.UserService.models.User;
import com.UserService.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import com.UserService.daos.IRoleDao;
import com.UserService.models.Roles;
import com.UserService.models.enums.ERoles;



@SpringBootApplication
@EnableDiscoveryClient
public class UserApplication implements CommandLineRunner {

	@Autowired
	private IRoleDao roleRepository;
	@Autowired
	private IUserDao userRepository;

	@Autowired
	private UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(UserApplication.class, args);
	}


	@Override
	public void run(String...args) throws Exception {

		Set<Roles> roles = new HashSet<>();
		Roles adminRole = new Roles();
		adminRole.setRole(ERoles.ADMIN);
		Roles teacherRole = new Roles();
		teacherRole.setRole(ERoles.TEACHER);
		Roles studentRole = new Roles();
		studentRole.setRole(ERoles.STUDENT);

		roles.add(adminRole);
		roles.add(teacherRole);
		roles.add(studentRole);

		List < Roles > role = roleRepository.findAll();
		if (role.size() == 0) {
			roleRepository.saveAll(roles);
		}

		List<User> user =userRepository.findAll();
		if(user.size() ==0){

			User adminUser = new User();
			adminUser.setEmail("admin@miu.edu");
			adminUser.setPassword("admin");
			adminUser.setUsername("admin");

			List<Roles> adminUserRoles = new ArrayList<>();

			adminUserRoles.add(adminRole);
			adminUserRoles.add(teacherRole);
			adminUserRoles.add(studentRole);
			adminUser.setRoles(adminUserRoles);

			userService.saveUser(adminUser);

			User teacherUser = new User();
			teacherUser.setEmail("teacher@miu.edu");
			teacherUser.setPassword("teacher");
			teacherUser.setUsername("teacher");

			List<Roles> teacherRoles = new ArrayList<>();

			teacherRoles.add(teacherRole);
			teacherUser.setRoles(teacherRoles);
			userService.saveUser(teacherUser);



			// First creating Student User
			User studentUser = new User();
			studentUser.setEmail("student@miu.edu");
			studentUser.setPassword("student");
			studentUser.setUsername("student");

			// List of roles assigned to student
			List<Roles> studentRoles = new ArrayList<>();
			studentRoles.add(studentRole);

			// Setting roles of student to student object now
			studentUser.setRoles(studentRoles);

			// Adding student to database
			userService.saveUser(studentUser);

		}
		System.out.println("Server is running!!!!");
	}

}