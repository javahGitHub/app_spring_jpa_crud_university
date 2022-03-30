package pdp.uz.app_springjpa_map.DTO.uniDto;

import lombok.Data;
import pdp.uz.app_springjpa_map.entity.uni.Subject;
import pdp.uz.app_springjpa_map.entity.uni.University;

import javax.persistence.Column;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.util.List;
@Data
public class FacultyDto {

    private String name;

    private List<Integer> subjectsId;

}
