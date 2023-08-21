package uz.xb.projectwithtwodb.service;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import uz.xb.projectwithtwodb.entity.second.ABS;
import uz.xb.projectwithtwodb.repository.second.ABSRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserService {

    @Qualifier("jdbcUser1")
    private final JdbcTemplate jdbcTemplate1;

    @Qualifier("jdbcUser2")
    private final JdbcTemplate jdbcTemplate2;

    private final ABSRepository absRepository;

    public List<ABS> fromPtoS() {
        String query = "select u.id , u.name, s.car_name from users u  join car s on u.id = s.user_id";
        List<Map<String, Object>> maps = jdbcTemplate1.queryForList(query);
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

}
