package pdp.uz.app_springjpa_map.entity.place;

import lombok.Data;

import javax.persistence.*;
@Data
@Entity
public class Continent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private int countriesNum;
}
