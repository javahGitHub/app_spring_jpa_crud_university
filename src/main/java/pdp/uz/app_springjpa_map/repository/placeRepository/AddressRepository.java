package pdp.uz.app_springjpa_map.repository.placeRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pdp.uz.app_springjpa_map.entity.place.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address,Integer> {
}
