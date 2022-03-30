package pdp.uz.app_springjpa_map.DTO.placeDTO;

import lombok.Data;

import javax.persistence.Column;
@Data
public class CountryDto {

    private int continentId;

    private String name;

    private double population;

    private String currency;

    private String stateLanguage;

}
