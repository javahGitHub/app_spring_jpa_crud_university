package pdp.uz.app_springjpa_map.entity.uni;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cache;
import pdp.uz.app_springjpa_map.entity.place.Address;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String firsName;

    @Column(nullable = false)
    private String secondName;

    @ManyToOne
    private Group group;

    @OneToOne
    private Address address;

}
