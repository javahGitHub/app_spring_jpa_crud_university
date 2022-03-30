package pdp.uz.app_springjpa_map.entity.uni;


import lombok.Data;
import pdp.uz.app_springjpa_map.entity.place.Address;

import javax.persistence.*;
@Data
@Entity
public class University {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;

    @OneToOne(cascade ={ CascadeType.ALL})
    private Address address;
}
