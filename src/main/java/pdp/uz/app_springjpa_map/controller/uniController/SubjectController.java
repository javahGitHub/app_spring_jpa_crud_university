package pdp.uz.app_springjpa_map.controller.uniController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pdp.uz.app_springjpa_map.entity.uni.Faculty;
import pdp.uz.app_springjpa_map.entity.uni.Subject;
import pdp.uz.app_springjpa_map.entity.uni.University;
import pdp.uz.app_springjpa_map.repository.uniRepository.FacultyRepository;
import pdp.uz.app_springjpa_map.repository.uniRepository.SubjectRepository;
import pdp.uz.app_springjpa_map.repository.uniRepository.UniversityRepository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.swing.*;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/subject")
public class SubjectController  {

    @Autowired
    SubjectRepository subjectRepository;
    @Autowired
    UniversityRepository universityRepository;
    @Autowired
    FacultyRepository facultyRepository;
    //1-Ministry of Higher Education (MHE)
    //2-University Director
    //3-Faculty manager or others
    //4-Group tutor or others


    //C---CREAT subject
    @PostMapping
    public String addSubject(@RequestBody Subject subject) {
        boolean existsByName = subjectRepository.existsByName(subject.getName());
        if (existsByName)
            return "This subject already exist";
        subjectRepository.save(subject);
        return "Subject added";
    }

    //R---READ all subjects
    @GetMapping
    public List<Subject> getSubjects() {
        List<Subject> subjectList = subjectRepository.findAll();
        return subjectList;
    }

    //R---READ all subjects by University id
    @GetMapping("/getAllByUniversityId/{uniId}")
    public List getSubjectsByUniId(@PathVariable Integer uniId) {
        Optional<University> optionalFaculty = universityRepository.findById(uniId);
        if(!optionalFaculty.isPresent())
            return Arrays.asList("University not found by id"+uniId);
        List<Subject> subjectList = subjectRepository.getAllByUniId(uniId);
        return subjectList;
    }

    //R---READ all subjects by Faculty id
    @GetMapping("/getAllByFacultyId/{facId}")
    public List getSubjectsByFacId(@PathVariable Integer facId) {
        Optional<Faculty> optionalFaculty = facultyRepository.findById(facId);
        if(!optionalFaculty.isPresent())
            return Arrays.asList("Faculty not found by id"+facId);
        List<Subject> subjectList = subjectRepository.getAllByFacultyId(facId);
        return subjectList;
    }

    //U---UPDATE subject
    @PostMapping("/{id}")
    public String updateSubject(@PathVariable Integer id,@RequestBody Subject subject){
        //Check name of Subject
        boolean existsByName = subjectRepository.existsByName(subject.getName());
        if (existsByName)
            return "This subject already exist";
        //Check id of Subject
        boolean exists = subjectRepository.existsById(id);
        if(!exists)
            return "Subject not found by id="+id;

        subject.setId(id);
        subjectRepository.save(subject);
        return "Subject updated successfully";
    }

    //D---DELETE subject
    @DeleteMapping("/{id}")
    public String deleteSubject(@PathVariable Integer id) {
        boolean exists = subjectRepository.existsById(id);
        if(!exists)
            return "Subject not found by id="+id;
        subjectRepository.deleteById(id);
        return "Subject deleted successfully by id="+id;
    }


}
