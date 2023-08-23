package uz.xb.projectwithtwodb.cron;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import uz.xb.projectwithtwodb.entity.mysql.ABS;
import uz.xb.projectwithtwodb.repository.mysql.ABSRepository;
import uz.xb.projectwithtwodb.service.UserService;

import java.util.List;

@Component
public class UserCron {

    private final ABSRepository aBSRepository;
    private final UserService userService;
    private final JdbcTemplate postgresTemplate;
    private final JdbcTemplate mysqlTemplate;


    public UserCron(
            @Qualifier("postgres") JdbcTemplate postgresTemplate,
            @Qualifier("mySql") JdbcTemplate mysqlTemplate,
            ABSRepository aBSRepository,
            UserService userService
            ) {
        this.postgresTemplate = postgresTemplate;
        this.mysqlTemplate = mysqlTemplate;
        this.aBSRepository=aBSRepository;
        this.userService=userService;
    }


//    @Scheduled(cron = "0 22 * * 1-6")
    @Scheduled(cron = "1 * * * * *")
    public void cron() {
        System.out.println("started");
        List<ABS> absList = userService.fromPtoS();
        mysqlTemplate.update("CREATE TABLE IF NOT EXISTS abs(" +
                "id serial primary key ," +
                "user_id int ," +
                " name varchar(50) , " +
                "car_name varchar(50))");


        for (ABS abs : absList) {
            ABS abs1 = new ABS(abs.getId(), abs.getUserId(), abs.getName(), abs.getCarName());
            String query= "insert into abs(user_id , name , car_name) values ( ?,?,?)";
            mysqlTemplate.update(query,abs1.getUserId(),abs1.getName(),abs1.getCarName());
        }
        System.out.println("end");
    }
}
