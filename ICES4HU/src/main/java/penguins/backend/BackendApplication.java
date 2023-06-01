package penguins.backend;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.context.annotation.Bean;
import penguins.backend.Admin.AdminRepository;
import penguins.backend.Auth.AuthenticationService;
import penguins.backend.Auth.RegisterRequest;


import static penguins.backend.User.UserType.ADMIN;

@SpringBootApplication
public class BackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(
			AdminRepository adminRepository,
			AuthenticationService service
	) {
		return args -> {
			var admin = RegisterRequest.builder()
					.firstName("AdFirst")
					.lastName("AdLast")
					.username("Admin")
					.password("password")
					.userType(ADMIN)
					.build();
			System.out.println("Admin token: " + service.register(admin).getAccessToken());

		};
	}
}
