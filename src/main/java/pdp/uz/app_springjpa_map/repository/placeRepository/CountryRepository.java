package pdp.uz.app_springjpa_map.repository.placeRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pdp.uz.app_springjpa_map.entity.place.Country;
@Repository
public interface CountryRepository extends JpaRepository<Country,Integer> {
}
