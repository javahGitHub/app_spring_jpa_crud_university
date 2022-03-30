package pdp.uz.app_springjpa_map.repository.uniRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pdp.uz.app_springjpa_map.entity.uni.Group;

import java.util.List;

@Repository
public interface GroupRepository extends JpaRepository<Group,Integer> {
    List findAllByFaculty_University_Id(Integer uniId);
    List findAllByFaculty_Id(Integer facId);

    @Query("select (count(g) > 0) from groups g where g.name = ?1 and g.faculty.id = ?2")
    boolean existsByNameAndFaculty_Id(String name,Integer facId);
}
