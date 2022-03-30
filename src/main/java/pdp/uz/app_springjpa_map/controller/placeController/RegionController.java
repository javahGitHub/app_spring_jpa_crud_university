package pdp.uz.app_springjpa_map.controller.placeController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pdp.uz.app_springjpa_map.DTO.placeDTO.RegionDto;
import pdp.uz.app_springjpa_map.entity.place.Region;
import pdp.uz.app_springjpa_map.repository.placeRepository.CountryRepository;
import pdp.uz.app_springjpa_map.repository.placeRepository.RegionRepository;

import java.util.List;
import java.util.Optional;

@RestController
public class RegionController {

    @Autowired
    RegionRepository regionRepository;
    CountryRepository countryRepository;

    //Get Regions
    @RequestMapping(value = "/region",method = RequestMethod.GET)
    public List getRegions(){
        List<Region> regionList = regionRepository.findAll();
        return regionList;
    }

    //Get Region
    @RequestMapping(value = "/region/{id}",method = RequestMethod.GET)
    public Region getRegion(@PathVariable Integer id){
        Optional<Region> optionalRegion = regionRepository.findById(id);
        if(optionalRegion.isPresent())return optionalRegion.get();
        else return new Region();
    }

    //Delete Region
    @RequestMapping(value = "/region/{id}",method = RequestMethod.DELETE)
    public String deleteRegion(@PathVariable Integer id){
        regionRepository.deleteById(id);
        return "Deleted Successfully";
    }

    //Add Region
    @RequestMapping(value = "/region",method = RequestMethod.PUT)
    public String addRegion(@RequestBody RegionDto regionDto){

     Region region=new Region();
     region.setName(regionDto.getName());
     region.setPopulation(regionDto.getPopulation());
     region.setCountry(countryRepository.findById(regionDto.getCountryId()).get());
     regionRepository.save(region);
     return "Successfully Saved";
    }

    //Update Region
    @RequestMapping(value = "/region/{id}",method = RequestMethod.PUT)
    public String editRegion(@RequestBody RegionDto regionDto,@PathVariable Integer id){

        Region region=new Region();
        region.setId(id);
        region.setName(regionDto.getName());
        region.setPopulation(regionDto.getPopulation());
        region.setCountry(countryRepository.findById(regionDto.getCountryId()).get());
        regionRepository.save(region);
        return "Successfully Edited";
    }


}
