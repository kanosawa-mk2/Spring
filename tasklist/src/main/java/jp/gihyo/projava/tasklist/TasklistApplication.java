package jp.gihyo.projava.tasklist;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class TasklistApplication  extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(TasklistApplication.class, args);
	}

//	@GetMapping("/hello")
//	public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
//		return String.format("Hello %s!", name);
//	}

}
