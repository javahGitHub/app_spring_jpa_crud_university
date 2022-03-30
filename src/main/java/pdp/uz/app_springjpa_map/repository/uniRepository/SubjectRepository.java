package pdp.uz.app_springjpa_map.repository.uniRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pdp.uz.app_springjpa_map.entity.uni.Subject;

import java.util.List;

@Repository
public interface SubjectRepository extends JpaRepository<Subject,Integer> {
    boolean existsByName(String name);

    @Query(value = "SELECT S.ID,\n" +
            "\tS.NAME,\n" +
            "\tF.NAME\n" +
            "FROM SUBJECT S\n" +
            "JOIN FACULTY_SUBJECTS SF ON SF.SUBJECTS_ID = S.ID\n" +
            "JOIN FACULTY F ON SF.FACULTY_ID = F.ID\n" +
            "WHERE F.ID=1 ",nativeQuery = true)
    List getAllByFacultyId(Integer fId);

    @Query(value = "SELECT S.ID,\n" +
            "\tS.NAME,\n" +
            "\tF.NAME\n" +
            "FROM SUBJECT S\n" +
            "JOIN FACULTY_SUBJECTS SF ON SF.SUBJECTS_ID = S.ID\n" +
            "JOIN FACULTY F ON SF.FACULTY_ID = F.ID\n" +
            "WHERE F.ID=(select id from faculty where university_id= ?1)",nativeQuery = true )
    List getAllByUniId(Integer uniId);
}
