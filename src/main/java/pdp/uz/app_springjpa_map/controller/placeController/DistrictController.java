package pdp.uz.app_springjpa_map.controller.placeController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pdp.uz.app_springjpa_map.DTO.placeDTO.DistrictDto;
import pdp.uz.app_springjpa_map.entity.place.District;
import pdp.uz.app_springjpa_map.entity.place.Region;
import pdp.uz.app_springjpa_map.repository.placeRepository.DistrictRepository;
import pdp.uz.app_springjpa_map.repository.placeRepository.RegionRepository;

import java.util.List;
import java.util.Optional;

@RestController
public class DistrictController {

    @Autowired
    DistrictRepository districtRepository;
    RegionRepository regionRepository;


    //Get Districts
    @RequestMapping(value = "/district",method = RequestMethod.GET)
    public List getDistricts(){
        List<District> districtList = districtRepository.findAll();
        return districtList;
    }

    //Get District
    @RequestMapping(value = "/district/{id}",method = RequestMethod.GET)
    public District getDistrict(@PathVariable Integer id){
        Optional<District> optionalDistrict = districtRepository.findById(id);
        if(optionalDistrict.isPresent()) return optionalDistrict.get();
        else return new District();

    }

    //Delete District
    @RequestMapping(value = "/district/{id}",method = RequestMethod.DELETE)
    public String deleteDistrict(@PathVariable Integer id){
       districtRepository.deleteById(id);
       return "Successfully Deleted";
    }


    //Add District
    @RequestMapping(value = "/district",method = RequestMethod.PUT)
    public String addDistricts(@RequestBody DistrictDto districtDto){
        District district=new District();
        district.setName(districtDto.getName());
        Optional<Region> optionalRegion = regionRepository.findById(districtDto.getRegionId());
        if(optionalRegion.isPresent()) {
            district.setRegion(regionRepository.findById(districtDto.getRegionId()).get());
            districtRepository.save(district);
            return "Added successfully";
        }
        else return  "Can't add";

    }

    //Edit District
    @RequestMapping(value = "/district/{id}",method = RequestMethod.PUT)
    public String editDistricts(@RequestBody DistrictDto districtDto,@PathVariable Integer id){
        District district=new District();
        district.setId(id);
        district.setName(districtDto.getName());
        Optional<Region> optionalRegion = regionRepository.findById(districtDto.getRegionId());
        if(optionalRegion.isPresent()) {
            district.setRegion(regionRepository.findById(districtDto.getRegionId()).get());
            districtRepository.save(district);
            return "Successfully Edited";
        }
        else return  "Couldn't edit";
    }

}
