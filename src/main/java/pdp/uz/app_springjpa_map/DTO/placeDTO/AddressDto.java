package pdp.uz.app_springjpa_map.DTO.placeDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDto {
    private String street;
    private int homeNum;
    private int districtId;
}
