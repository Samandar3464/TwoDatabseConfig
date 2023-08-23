package uz.xb.projectwithtwodb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ProjectWithTwoDbApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProjectWithTwoDbApplication.class, args);
    }

}
