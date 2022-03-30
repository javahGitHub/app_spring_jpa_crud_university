package pdp.uz.app_springjpa_map.entity.uni;


import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity(name = "groups")
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private  String name;

    @ManyToOne
    private Faculty faculty;

}
