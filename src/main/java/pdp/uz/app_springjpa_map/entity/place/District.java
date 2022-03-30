package pdp.uz.app_springjpa_map.entity.place;

import lombok.Data;

import javax.persistence.*;
@Data
@Entity
public class District {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;

    @ManyToOne(cascade = CascadeType.ALL)
    private Region region;

}
