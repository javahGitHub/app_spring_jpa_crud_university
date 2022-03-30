package pdp.uz.app_springjpa_map.entity.uni;


import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Faculty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;

    @ManyToMany
    private List<Subject> subjects;

    @ManyToOne
    private University university;
}