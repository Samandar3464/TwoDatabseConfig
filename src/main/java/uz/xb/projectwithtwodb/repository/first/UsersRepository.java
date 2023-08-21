package uz.xb.projectwithtwodb.repository.first;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.xb.projectwithtwodb.entity.first.Users;

public interface UsersRepository extends JpaRepository<Users, Integer> {
}
