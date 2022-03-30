package pdp.uz.app_springjpa_map.controller.placeController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pdp.uz.app_springjpa_map.DTO.placeDTO.CountryDto;
import pdp.uz.app_springjpa_map.entity.place.Continent;
import pdp.uz.app_springjpa_map.entity.place.Country;
import pdp.uz.app_springjpa_map.repository.placeRepository.ContinentRepository;
import pdp.uz.app_springjpa_map.repository.placeRepository.CountryRepository;

import java.util.List;
import java.util.Optional;

@RestController
public class CountryController {

    @Autowired
    CountryRepository countryRepository;
    @Autowired
    ContinentRepository continentRepository;


    //Get Country
    @RequestMapping(value = "/country", method = RequestMethod.GET)
    public List getContinents() {
        List<Country> countryList = countryRepository.findAll();
        return countryList;
    }

    //Get Country
    @RequestMapping(value = "/country/{id}", method = RequestMethod.GET)
    public Country getContinent(@PathVariable Integer id) {
        Optional<Country> optionalCountry = countryRepository.findById(id);
        if (optionalCountry.isPresent()) return optionalCountry.get();
        else return new Country();
    }

    //Delete Country
    @RequestMapping(value = "/country/{id}", method = RequestMethod.DELETE)
    public String deleteContinent(@PathVariable Integer id) {
        countryRepository.deleteById(id);
        return "Deleted successfully";
    }

    //Add Country
    @RequestMapping(value = "/country", method = RequestMethod.PUT)
    public String addContinent(@RequestBody CountryDto countryDto) {
        Country country = new Country();
        Continent continent = new Continent();
        country.setName(countryDto.getName());
        country.setCurrency(countryDto.getCurrency());
        country.setPopulation(countryDto.getPopulation());
        country.setStateLanguage(countryDto.getStateLanguage());
        System.out.println("before find");
        Optional<Continent> optionalContinent = continentRepository.findById(1);
        System.out.println("after find");
        System.out.println(optionalContinent.get());
        if (optionalContinent.isPresent()) {
            continent.setCountriesNum(optionalContinent.get().getCountriesNum());
            continent.setName(optionalContinent.get().getName());
            country.setContinent(continent);
            countryRepository.save(country);
            return "Saved Successfully";
        } else return "Can't save :Continent do not exist";
    }

    //Update Country
    @RequestMapping(value = "/country/{id}", method = RequestMethod.PUT)
    public String editContinent(@RequestBody CountryDto countryDto, @PathVariable Integer id) {
        Country country = new Country();
        country.setId(id);
        country.setName(countryDto.getName());
        country.setCurrency(countryDto.getCurrency());
        country.setPopulation(countryDto.getPopulation());
        country.setStateLanguage(countryDto.getStateLanguage());
        country.setContinent(continentRepository.findById(countryDto.getContinentId()).get());
        countryRepository.save(country);
        return "Edited Successfully";
    }

}