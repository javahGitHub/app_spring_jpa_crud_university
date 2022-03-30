package pdp.uz.app_springjpa_map.controller.uniController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pdp.uz.app_springjpa_map.DTO.uniDto.GroupDto;
import pdp.uz.app_springjpa_map.entity.uni.Faculty;
import pdp.uz.app_springjpa_map.entity.uni.Group;
import pdp.uz.app_springjpa_map.repository.uniRepository.FacultyRepository;
import pdp.uz.app_springjpa_map.repository.uniRepository.GroupRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/group")
public class GroupController {

    @Autowired
    GroupRepository groupRepository;
    @Autowired
    FacultyRepository facultyRepository;

    //1-Ministry of Higher Education (MHE)
    //2-University Director
    //3-Faculty manager


//C  //CREATE Group by Faculty id
    @PostMapping("/add")
    public String addGroup( @RequestBody GroupDto groupDto){
        //Check Faculty in repository
        Optional<Faculty> optionalFaculty = facultyRepository.findById(groupDto.getFacultyId());
        if(!optionalFaculty.isPresent())
            return "Faculty not found by id="+groupDto.getFacultyId();
        //Check Group in Faculty
        boolean existsByFaculty = groupRepository.existsByNameAndFaculty_Id(groupDto.getName(),optionalFaculty.get().getId());
        if(existsByFaculty)
            return "Group name already exist in Faculty by id="+optionalFaculty.get().getId();

        Group group=new Group();
        group.setFaculty(optionalFaculty.get());
        group.setName(groupDto.getName());
        groupRepository.save(group);
        return "Group saved added successfully";
    }

//R  //READ Groups by MHE
    @GetMapping
    public List getGroups(){
        List<Group> groupList = groupRepository.findAll();
        return groupList;
    }

//R  //READ Groups by University Director
    @GetMapping("/getGroupsByUniId/{uniId}")
    public List getGroupsByUniId(@PathVariable Integer uniId){
        List<Group> groupList = groupRepository.findAllByFaculty_University_Id(uniId);
        return groupList;
    }

//R  //READ Groups by Faculty Manager
    @GetMapping("/getGroupsByUniId/{facId}")
    public List getGroupsByFacId(@PathVariable Integer facId){
        List<Group> groupList = groupRepository.findAllByFaculty_Id(facId);
        return groupList;
    }


//U  //UPDATE Group
    @PostMapping("/update/{id}")
    public String updateGroup(@PathVariable Integer id, @RequestBody GroupDto groupDto){

        //Check Faculty in repository
        Optional<Faculty> optionalFaculty = facultyRepository.findById(groupDto.getFacultyId());
        if(!optionalFaculty.isPresent())
            return "Faculty not found by id="+groupDto.getFacultyId();

        //Check Group in repository
        Optional<Group> optionalGroup = groupRepository.findById(id);
        if(!optionalGroup.isPresent())
           return "Group not found by id="+id;

        //Check Group in Faculty
        boolean existsByFaculty = groupRepository.existsByNameAndFaculty_Id(groupDto.getName(),optionalFaculty.get().getId());
        if(existsByFaculty)
            return "Group name already exist in Faculty by id="+optionalFaculty.get().getId();

        Group group=optionalGroup.get();
        group.setFaculty(optionalFaculty.get());
        group.setName(groupDto.getName());
        groupRepository.save(group);
        return "Group updated successfully";
    }

//D  //DELETE Group by Faculty id
    @DeleteMapping("/{id}/deleteByFacId/{facId}")
    public String deleteGroupByFacId(@PathVariable Integer facId,@PathVariable Integer id){

        //Check Faculty in repository
        Optional<Faculty> optionalFaculty = facultyRepository.findById(facId);
        if(!optionalFaculty.isPresent())
            return "Faculty not found by id="+facId;

        //Check Group in repository
        Optional<Group> optionalGroup = groupRepository.findById(id);
        if(!optionalGroup.isPresent())
            return "Group not found by id="+id;
        groupRepository.deleteById(id);

        return "Group deleted successfully";
    }

}
