package in.original.incidentapp;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ComponentScan(basePackages = "in.original.incidentapp")
@ImportResource("classpath:applicationContext.xml")
public class AppConfig {
}
