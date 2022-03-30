package pdp.uz.app_springjpa_map.DTO.uniDto;

import lombok.Data;
import pdp.uz.app_springjpa_map.entity.place.Address;
import pdp.uz.app_springjpa_map.entity.uni.Group;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Data
public class StudentDto {

    private String firsName;

    private String secondName;

    private Integer groupId;

    private Integer addressId;

}
