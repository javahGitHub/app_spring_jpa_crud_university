package pdp.uz.app_springjpa_map.controller.placeController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pdp.uz.app_springjpa_map.DTO.placeDTO.ContinentDto;
import pdp.uz.app_springjpa_map.entity.place.Continent;
import pdp.uz.app_springjpa_map.repository.placeRepository.ContinentRepository;

import java.util.List;
import java.util.Optional;

@RestController
public class ContinentController {

    @Autowired
    ContinentRepository continentRepository;

    //Get Continents
    @RequestMapping(value = "/continent", method = RequestMethod.GET)
    public List getContinents() {
        List<Continent> continentList = continentRepository.findAll();
        return continentList;
    }

    //Get Continent
    @RequestMapping(value = "/continent/{id}", method = RequestMethod.GET)
    public Continent getContinent(@PathVariable Integer id) {
        Optional<Continent> optionalContinent = continentRepository.findById(id);
        if (optionalContinent.isPresent()) return optionalContinent.get();
        else return new Continent();
    }

    //Delete Continent
    @RequestMapping(value = "/continent/{id}", method = RequestMethod.DELETE)
    public String deleteContinent(@PathVariable Integer id) {
        continentRepository.deleteById(id);
        return "Deleted successfully";
    }

    //Add Continent
    @RequestMapping(value = "/continent", method = RequestMethod.PUT)
    public String addContinent(@RequestBody ContinentDto continentDto) {
        Continent continent = new Continent();
        continent.setName(continentDto.getName());
        continent.setCountriesNum(continentDto.getCountriesNum());
        continentRepository.save(continent);
        return "Saved Successfully";
    }

    //Update Continent
    @RequestMapping(value = "/continent/{id}", method = RequestMethod.PUT)
    public String editContinent(@RequestBody Continent continentDto,@PathVariable Integer id) {
        Continent continent = new Continent();
        continent.setId(id);
        continent.setName(continentDto.getName());
        continent.setCountriesNum(continentDto.getCountriesNum());
        continentRepository.save(continent);
        return "Updated Successfully";
    }

}
