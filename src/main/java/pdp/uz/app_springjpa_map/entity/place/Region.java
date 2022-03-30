package pdp.uz.app_springjpa_map.entity.place;

import lombok.Data;

import javax.persistence.*;
@Data
@Entity
public class Region {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private double population;

    @ManyToOne(cascade = CascadeType.ALL)
    private Country country;

}
