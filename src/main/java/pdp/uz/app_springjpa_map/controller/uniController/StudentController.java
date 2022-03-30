package pdp.uz.app_springjpa_map.controller.uniController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import pdp.uz.app_springjpa_map.DTO.uniDto.StudentDto;
import pdp.uz.app_springjpa_map.entity.place.Address;
import pdp.uz.app_springjpa_map.entity.uni.Faculty;
import pdp.uz.app_springjpa_map.entity.uni.Group;
import pdp.uz.app_springjpa_map.entity.uni.Student;
import pdp.uz.app_springjpa_map.entity.uni.University;
import pdp.uz.app_springjpa_map.repository.placeRepository.AddressRepository;
import pdp.uz.app_springjpa_map.repository.uniRepository.FacultyRepository;
import pdp.uz.app_springjpa_map.repository.uniRepository.GroupRepository;
import pdp.uz.app_springjpa_map.repository.uniRepository.StudentRepository;
import pdp.uz.app_springjpa_map.repository.uniRepository.UniversityRepository;

import javax.xml.ws.FaultAction;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentRepository studentRepository;
    @Autowired
    AddressRepository addressRepository;
    @Autowired
    UniversityRepository universityRepository;
    @Autowired
    FacultyRepository facultyRepository;
    @Autowired
    GroupRepository groupRepository;

    //1-Ministry of Higher Education (MHE)
    //2-University Director
    //3-Faculty manager
    //4-Group tutor

    //C---CREATE Student by Group id
    @PostMapping("/addStudentByGroupId/{gId}")
    public String addStudent(@PathVariable Integer gId, @RequestBody StudentDto studentDto) {

        //Check Group in repository
        Optional<Group> optionalGroup = groupRepository.findById(gId);
        if (!optionalGroup.isPresent())
            return "Group not found by id=" + gId;
        Student student = new Student();
        student.setFirsName(studentDto.getFirsName());
        student.setSecondName(studentDto.getSecondName());
        student.setGroup(optionalGroup.get());

        //Check Address in repository
        Optional<Address> optionalAddress = addressRepository.findById(studentDto.getAddressId());
        if (!optionalAddress.isPresent())
            return "Address not found by id=" + studentDto.getAddressId();
        student.setAddress(optionalAddress.get());
        studentRepository.save(student);
        return "Student saved successfully";
    }

    //R---READ Students by MHE
    @GetMapping
    public Page<Student> getStudents(@RequestParam int page) {
        Pageable pageable= PageRequest.of(page,10);
        Page<Student> studentPage = studentRepository.findAll(pageable);
        return studentPage;
    }

    //R---READ Students by University Director
    @GetMapping("/getByUniId/{uniId}")
    public Page getStudentsByUniId(@PathVariable Integer uniId,@RequestParam int page) {
        Pageable pageable=PageRequest.of(page,10);
        //Check University in repository
        Optional<University> optionalUniversity = universityRepository.findById(uniId);
        if (!optionalUniversity.isPresent())
            return (Page) Arrays.asList("University not found by id=" + uniId);
        Page byGroupFacultyUniversityId = studentRepository.findAllByGroup_Faculty_UniversityId(uniId, pageable);
        return byGroupFacultyUniversityId;
    }

    //R---READ Students by Faculty manager
    @GetMapping("/getByFacId/{facId}")
    public Page getStudentsByFacId(@PathVariable Integer facId,@RequestParam  int page) {
        Pageable pageable=PageRequest.of(page,10);
        //Check Faculty in repository
        Optional<Faculty> optionalFaculty = facultyRepository.findById(facId);
        if (!optionalFaculty.isPresent())
            return (Page) Arrays.asList("Faculty not found by id=" + facId);
        Page allByGroupFacultyIdPage = studentRepository.findAllByGroup_FacultyId(facId, pageable);
        return allByGroupFacultyIdPage;
    }

    //R---READ Students by Group tutor
    @GetMapping("/getByGroupId/{gId}")
    public Page getStudentsByGroupId(@PathVariable Integer gId,@RequestParam  int page) {
        Pageable pageable=PageRequest.of(page,10);
        //Check Group in repository
        Optional<Group> optionalFaculty = groupRepository.findById(gId);
        if (!optionalFaculty.isPresent())
            return (Page) Arrays.asList("Group not found by id=" + gId);
        Page allByGroupId = studentRepository.findAllByGroupId(gId, pageable);
        return allByGroupId;
    }

    //U---UPDATE Students by Group tut
    @PostMapping("/{id}/updateByGroupId/{gId}")
    public String updateStudentsByGroupId(@PathVariable Integer gId,@PathVariable Integer id,@RequestBody StudentDto studentDto) {
        //Check Group in repository
        Optional<Group> optionalGroup = groupRepository.findById(gId);
        if (!optionalGroup.isPresent())
            return "Group not found by id=" + gId;
        //Check Student in repository
        Optional<Student> optionalStudent = studentRepository.findById(id);
        if(!optionalStudent.isPresent())
            return "Student not found by id="+id;
        Student student=optionalStudent.get();
        student.setFirsName(studentDto.getFirsName());
        student.setSecondName(studentDto.getSecondName());
        student.setGroup(optionalGroup.get());

        //Check Address in repository
        Optional<Address> optionalAddress = addressRepository.findById(studentDto.getAddressId());
        if (!optionalAddress.isPresent())
            return "Address not found by id=" + studentDto.getAddressId();
        student.setAddress(optionalAddress.get());
        studentRepository.save(student);
        return "Student saved successfully";
    }


    //D---DELETE Student by Group tutor
    @GetMapping("/{id}/deleteByGroupId/{gId}")
    public String deleteStudentByGroupId(@PathVariable Integer gId,@PathVariable Integer id) {
        //Check Group in repository
        Optional<Group> optionalFaculty = groupRepository.findById(gId);
        if (!optionalFaculty.isPresent())
            return "Group not found by id=" + gId;
        //Check Student in repository
        Optional<Student> optionalStudent = studentRepository.findById(id);
        if(!optionalStudent.isPresent())
            return "Student not found by id="+id;
        studentRepository.deleteById(id);
        return "Student deleted successfully";
    }



}
