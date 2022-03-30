package pdp.uz.app_springjpa_map.DTO.uniDto;

import lombok.Data;

import javax.persistence.Column;

@Data
public class GroupDto {
    private  String name;
    private int facultyId;
}
