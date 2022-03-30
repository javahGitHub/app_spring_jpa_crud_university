package pdp.uz.app_springjpa_map.repository.uniRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pdp.uz.app_springjpa_map.entity.uni.Faculty;

import java.util.List;

@Repository
public interface FacultyRepository extends JpaRepository<Faculty,Integer> {
    List findAllByUniversityId(Integer uniId);
    @Query("select (count(f) > 0) from Faculty f where f.name = ?1 and f.university.id = ?2")
    boolean existsByNameAndUniversityId(String name, Integer id);
}
