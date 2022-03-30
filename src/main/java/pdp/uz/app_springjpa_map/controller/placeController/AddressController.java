package pdp.uz.app_springjpa_map.controller.placeController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pdp.uz.app_springjpa_map.DTO.placeDTO.AddressDto;
import pdp.uz.app_springjpa_map.entity.place.Address;
import pdp.uz.app_springjpa_map.entity.place.District;
import pdp.uz.app_springjpa_map.repository.placeRepository.AddressRepository;
import pdp.uz.app_springjpa_map.repository.placeRepository.DistrictRepository;

import java.util.List;
import java.util.Optional;

@RestController
public class AddressController {

    @Autowired
    AddressRepository addressRepository;
    DistrictRepository districtRepository;

    //Get All Address
    @RequestMapping(value = "/address", method = RequestMethod.GET)
    public List getAddresses() {
        List<Address> addressList = addressRepository.findAll();
        return addressList;
    }

    //Get Address
    @RequestMapping(value = "/address/{id}", method = RequestMethod.GET)
    public Address getAddress(@PathVariable Integer id) {

        Optional<Address> optionalAddress = addressRepository.findById(id);
        if (optionalAddress.isPresent()) return optionalAddress.get();
        else return new Address();
    }

    //Delete Address
    @RequestMapping(value = "/address/{id}", method = RequestMethod.DELETE)
    public String deleteAddress(@PathVariable Integer id) {
        addressRepository.deleteById(id);
        return "Deleted Successfully";
    }

    //Add Address
    @RequestMapping(value = "/address", method = RequestMethod.PUT)
    public String addAddresses(@RequestBody AddressDto addressDto) {
        Address address = new Address();
        address.setStreet(addressDto.getStreet());
        address.setHomeNum(addressDto.getHomeNum());

        Optional<District> optionalDistrict = districtRepository.findById(addressDto.getDistrictId());
        if(optionalDistrict.isPresent()){
            address.setDistrict(optionalDistrict.get());
            addressRepository.save(address);
            return "Saved Successfully";
        }else return "District not found by id";
    }

    //Edit Address
    @RequestMapping(value = "/address/{id}", method = RequestMethod.PUT)
    public String editAddresses(@RequestBody AddressDto addressDto,@PathVariable Integer id) {
        Address address = new Address();
        address.setId(id);
        address.setStreet(addressDto.getStreet());
        address.setHomeNum(addressDto.getHomeNum());

        Optional<District> optionalDistrict = districtRepository.findById(addressDto.getDistrictId());
        if(optionalDistrict.isPresent()){
            address.setDistrict(optionalDistrict.get());
            addressRepository.save(address);
            return "Successfully updated";
        }else return "District not found by id";
    }
}