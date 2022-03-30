package pdp.uz.app_springjpa_map.repository.placeRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import pdp.uz.app_springjpa_map.entity.place.District;

public interface DistrictRepository extends JpaRepository<District,Integer> {
}
