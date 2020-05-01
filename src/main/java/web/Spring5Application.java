package web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class Spring5Application {

    private static PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    public static void main(String[] args) {
        System.out.println(passwordEncoder.encode("admin"));
        SpringApplication.run(Spring5Application.class, args);

    }
}
