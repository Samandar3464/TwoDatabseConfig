package uz.xb.projectwithtwodb.repository.postgres;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.xb.projectwithtwodb.entity.postgres.Users;

public interface UsersRepository extends JpaRepository<Users, Integer> {
}
