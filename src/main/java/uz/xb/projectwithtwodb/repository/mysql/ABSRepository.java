package uz.xb.projectwithtwodb.repository.mysql;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.xb.projectwithtwodb.entity.mysql.ABS;

@Repository
public interface ABSRepository extends JpaRepository<ABS, Integer> {
}
