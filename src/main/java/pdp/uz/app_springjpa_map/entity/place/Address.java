package pdp.uz.app_springjpa_map.entity.place;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String street;

    private int homeNum;

    @ManyToOne(cascade = CascadeType.ALL)
    private District district;
}
