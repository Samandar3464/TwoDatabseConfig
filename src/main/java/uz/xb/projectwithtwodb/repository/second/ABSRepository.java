package uz.xb.projectwithtwodb.repository.second;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.xb.projectwithtwodb.entity.second.ABS;

@Repository
public interface ABSRepository extends JpaRepository<ABS, Integer> {
}
