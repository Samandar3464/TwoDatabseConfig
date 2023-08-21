package uz.xb.projectwithtwodb.entity.first;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "info")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Info {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Integer userId;
    private String name;
    private String carName;
}
