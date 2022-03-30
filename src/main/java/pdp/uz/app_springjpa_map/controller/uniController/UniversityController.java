package pdp.uz.app_springjpa_map.controller.uniController;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pdp.uz.app_springjpa_map.DTO.uniDto.UniversityDto;
import pdp.uz.app_springjpa_map.entity.place.Address;
import pdp.uz.app_springjpa_map.entity.place.District;
import pdp.uz.app_springjpa_map.entity.uni.University;
import pdp.uz.app_springjpa_map.repository.placeRepository.AddressRepository;
import pdp.uz.app_springjpa_map.repository.placeRepository.DistrictRepository;
import pdp.uz.app_springjpa_map.repository.uniRepository.UniversityRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/university")
public class UniversityController {

    @Autowired
    UniversityRepository universityRepository;
    @Autowired
    AddressRepository addressRepository;
    @Autowired
    DistrictRepository districtRepository;

    //1-Ministry of Higher Education (MHE)
    //2-University Director

    //Get Universities by MHE
    @GetMapping
    private List getUniversities() {
        List<University> universityList = universityRepository.findAll();
        return universityList;
    }

    //Get University by Director
    @GetMapping("/{id}")
    private University getUniversity(@PathVariable Integer id) {
        Optional<University> optionalUniversity = universityRepository.findById(id);

        if (optionalUniversity.isPresent())
            return optionalUniversity.get();
        else return new University();
    }

    //Delete University
    @DeleteMapping("/{id}")
    private String deleteUniversity(@PathVariable Integer id) {
        universityRepository.deleteById(id);
        return "Successfully deleted";
    }


    //Put University
    @PostMapping
    private String addUniversity(@RequestBody UniversityDto universityDto) {
        University university = new University();
        Address address = new Address();
        address.setStreet(universityDto.getStreet());
        address.setHomeNum(universityDto.getHomeNum());
        Optional<District> optionalDistrict = districtRepository.findById(universityDto.getDistrictId());
        if (optionalDistrict.isPresent()) {
            address.setDistrict(optionalDistrict.get());
            university.setAddress(address);
            university.setName(universityDto.getUniName());
            return "Successfully saved";
        }
        else return "Can't find by  District id";


    }

    //Update University
    @PostMapping("/{id}")
    private String editUniversity(@RequestBody UniversityDto universityDto, @PathVariable Integer id) {
        University university = new University();
        Address address = new Address();
        address.setStreet(universityDto.getStreet());
        address.setHomeNum(universityDto.getHomeNum());
        Optional<District> optionalDistrict = districtRepository.findById(universityDto.getDistrictId());
        if (optionalDistrict.isPresent()) {
            address.setDistrict(optionalDistrict.get());
            university.setId(id);
            university.setAddress(address);
            university.setName(universityDto.getUniName());
            return "Successfully edited";
        }
        else return "Can't find by  District id";

    }

}
