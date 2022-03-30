package pdp.uz.app_springjpa_map.repository.placeRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import pdp.uz.app_springjpa_map.entity.place.Region;

import javax.jnlp.IntegrationService;


public interface RegionRepository extends JpaRepository<Region, Integer> {
}
