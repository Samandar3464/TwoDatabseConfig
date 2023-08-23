package uz.xb.projectwithtwodb.service;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import uz.xb.projectwithtwodb.entity.mysql.ABS;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class UserService {


    private final JdbcTemplate postgresTemplate;
    private final JdbcTemplate mysqlTemplate;


    public UserService(
            @Qualifier("postgres") JdbcTemplate postgresTemplate,
            @Qualifier("mySql") JdbcTemplate mysqlTemplate) {
        this.postgresTemplate = postgresTemplate;
        this.mysqlTemplate = mysqlTemplate;
    }

//    private final ABSRepository absRepository;


    public List<ABS> fromPtoS() {
        String query = "select u.id , u.name, s.car_name from users u  join car s on u.id = s.user_id";
        List<Map<String, Object>> maps = postgresTemplate.queryForList(query);
        List<ABS> usersList = new ArrayList<>();
        for (Map<String, Object> map : maps) {
            ABS users = new ABS();
            users.setUserId((Integer) map.get("id"));
            users.setName((String) map.get("name"));
            users.setCarName((String) map.get("car_name"));
            usersList.add(users);
        }

        return usersList;
    }

    public List<ABS> fromStoP() {
        List<ABS> absList = fromPtoS();
        mysqlTemplate.update("CREATE TABLE IF NOT EXISTS abs(" +
                "id serial primary key ," +
                "user_id int ," +
                " name varchar(50) , " +
                "car_name varchar(50))");

        postgresTemplate.execute("CREATE TABLE IF NOT EXISTS call_info ("
                + "id BIGSERIAL PRIMARY KEY,"
                + "calls_id BIGINT NOT NULL UNIQUE,"
                + "caller_id VARCHAR(32),"
                + "loan_id VARCHAR(7),"
                + "created_at TIMESTAMP,"
                + "duration INTEGER,"
                + "result INTEGER,"
                + "transfered_at TIMESTAMP WITH TIME ZONE,"
                + "campaign_id BIGINT"
                + ")");

        for (ABS abs : absList) {
            ABS abs1 = new ABS(abs.getId(), abs.getUserId(), abs.getName(), abs.getCarName());
            String query= "insert into abs(user_id , name , car_name) values ( ?,?,?)";
            mysqlTemplate.update(query,abs1.getUserId(),abs1.getName(),abs1.getCarName());
        }
        return absList;
    }
}
