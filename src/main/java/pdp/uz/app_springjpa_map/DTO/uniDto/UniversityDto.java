package pdp.uz.app_springjpa_map.DTO.uniDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UniversityDto {
    private String uniName;
    private String street;
    private int homeNum;
    private int districtId;
}
