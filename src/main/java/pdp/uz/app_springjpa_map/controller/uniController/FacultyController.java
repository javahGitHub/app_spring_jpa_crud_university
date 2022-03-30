package pdp.uz.app_springjpa_map.controller.uniController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pdp.uz.app_springjpa_map.DTO.uniDto.FacultyDto;
import pdp.uz.app_springjpa_map.entity.uni.Faculty;
import pdp.uz.app_springjpa_map.entity.uni.Subject;
import pdp.uz.app_springjpa_map.entity.uni.University;
import pdp.uz.app_springjpa_map.repository.uniRepository.FacultyRepository;
import pdp.uz.app_springjpa_map.repository.uniRepository.SubjectRepository;
import pdp.uz.app_springjpa_map.repository.uniRepository.UniversityRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/faculty")
public class FacultyController {
    @Autowired
    FacultyRepository facultyRepository;
    @Autowired
    UniversityRepository universityRepository;
    @Autowired
    SubjectRepository subjectRepository;


    //1-Ministry of Higher Education (MHE)
    //2-University Director

    //Read by MHE
    @GetMapping
    public List getFaculties(){
        List<Faculty> allFacultyList = facultyRepository.findAll();
        return allFacultyList;
    }

    //Read by University id
    @GetMapping("/ReadByUniId/{uniId}")
    public List getFacultiesByUniId(@PathVariable Integer uniId){
        Optional<University> optionalUniversity = universityRepository.findById(uniId);

        if(!optionalUniversity.isPresent())
            return Arrays.asList("University not found by id="+uniId);

        List allByUniversityId = facultyRepository.findAllByUniversityId(uniId);
        return allByUniversityId;
    }

    //Add by University id
    @PostMapping("/addByUniId/{uniId}")
    public String addFacultiesByUniId(@PathVariable Integer uniId, @RequestBody FacultyDto facultyDto){

        //Check name of Faculty in University
        boolean exists = facultyRepository.existsByNameAndUniversityId(facultyDto.getName(), uniId);
        if (exists)
            return "This university such faculty exist";


        //Check University id
        Optional<University> optionalUniversity = universityRepository.findById(uniId);
        if(!optionalUniversity.isPresent())
            return "University id not found";
        Faculty faculty=new Faculty();
        faculty.setName(facultyDto.getName());
        faculty.setUniversity(optionalUniversity.get());
        List list=new ArrayList<>();
        for (Integer integer : facultyDto.getSubjectsId()) {
            //Check Subject id
            Optional<Subject> optionalSubject = subjectRepository.findById(integer);
            if(!optionalSubject.isPresent())
                return "Among ides of Subject not found by id="+integer;
            list.add(optionalSubject.get());
        }
        faculty.setSubjects(list);
        Faculty saved = facultyRepository.save(faculty);
        return "Faculty saved successfully by id="+saved.getId();
    }

    //Delete Faculty by University id
    @DeleteMapping("/{id}/deleteByUniId/{uniId}")
    public String deleteFacultyByUniId(@PathVariable Integer uniId,@PathVariable Integer id){
        try{
        Optional<University> optionalUniversity = universityRepository.findById(uniId);

        if(!optionalUniversity.isPresent())
            return "University not found by id="+uniId;
        facultyRepository.deleteById(id);
        return "Faculty deleted successfully";
        }catch (Exception e){
            return "Error in deleting action";
        }
    }

    //Update by University id
    @PostMapping("/{id}/updateByUniId/{uniId}")
    public String updateFacultiesByUniId(@PathVariable Integer uniId,@PathVariable Integer id, @RequestBody FacultyDto facultyDto){

        //Check name of Faculty in University
        boolean exists = facultyRepository.existsByNameAndUniversityId(facultyDto.getName(), uniId);
        if (exists)
            return "This university such faculty exist";


        //Check University id
        Optional<University> optionalUniversity = universityRepository.findById(uniId);
        if(!optionalUniversity.isPresent())
            return "University id not found";

        //Check Faculty id
        Optional<Faculty> optionalFaculty = facultyRepository.findById(id);
        if(!optionalFaculty.isPresent())
            return "Faculty not found";


        Faculty faculty=optionalFaculty.get();
        faculty.setName(facultyDto.getName());
        faculty.setUniversity(optionalUniversity.get());
        List list=new ArrayList<>();
        for (Integer integer : facultyDto.getSubjectsId()) {
            //Check Subject id
            Optional<Subject> optionalSubject = subjectRepository.findById(integer);
            if(!optionalSubject.isPresent())
                return "Among ides of Subject not found by id="+integer;
            list.add(optionalSubject.get());
        }
        faculty.setSubjects(list);
        Faculty saved = facultyRepository.save(faculty);
        return "Faculty saved successfully by id="+saved.getId();
    }


}