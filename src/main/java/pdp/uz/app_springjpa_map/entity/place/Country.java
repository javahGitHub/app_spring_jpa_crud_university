package pdp.uz.app_springjpa_map.entity.place;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private double population;

    @Column(nullable = false)
    private String currency;

    @Column(nullable = false)
    private String stateLanguage;

    @ManyToOne(cascade = CascadeType.ALL)
    private Continent continent;
}
