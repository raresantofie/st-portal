package ro.studentportal.stportal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import ro.studentportal.stportal.model.Email;
import ro.studentportal.stportal.model.Name;
import ro.studentportal.stportal.model.Role;
import ro.studentportal.stportal.model.User;
import ro.studentportal.stportal.repository.RoleRepository;
import ro.studentportal.stportal.repository.UserRepository;
import ro.studentportal.stportal.service.UserService;

import javax.sql.DataSource;

@SpringBootApplication
@EnableTransactionManagement
public class StPortalApplication implements CommandLineRunner {

	@Autowired
	UserService userService;

	@Autowired
	RoleRepository roleRepository;

	public static void main(String[] args) {
		SpringApplication.run(StPortalApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Role adminRole =new Role();
		adminRole.setRole("ADMIN");
		try{
			roleRepository.save(adminRole);
		} catch (Exception e){

		}

		Role studentRole = new Role();
		studentRole.setRole("STUDENT");
		try{
			roleRepository.save(studentRole);
		} catch (Exception e){

		}

		Role librarianRole = new Role();
		librarianRole.setRole("LIBRARIAN");
		try{
			roleRepository.save(librarianRole);
		} catch (Exception e){

		}
	}


	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		return bCryptPasswordEncoder;
	}

}
