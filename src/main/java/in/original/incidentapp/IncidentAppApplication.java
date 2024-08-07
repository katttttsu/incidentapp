package in.original.incidentapp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("in.original.incidentapp")
public class IncidentAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(IncidentAppApplication.class, args);
	}
}