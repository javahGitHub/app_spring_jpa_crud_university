package pdp.uz.app_springjpa_map.repository.uniRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pdp.uz.app_springjpa_map.entity.uni.Student;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student,Integer> {
    Page findAllByGroup_Faculty_UniversityId(Integer uniId, Pageable pageable);
    Page findAllByGroup_FacultyId(Integer facId,Pageable pageable);
    Page findAllByGroupId(Integer gId,Pageable pageable);
}
