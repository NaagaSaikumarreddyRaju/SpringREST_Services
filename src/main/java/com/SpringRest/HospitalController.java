package com.SpringRest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class HospitalController {
    @Autowired
    HospitalService hospitalService;
    @GetMapping("/getHospitals")
    public List<Hospital> getHospitals(){
        return hospitalService.getHospitalList();
    }

    @GetMapping("/getHospitals/{id}")
    public Hospital getHospitalById(@PathVariable("id") int id){
        return hospitalService.getHospitalById(id);
    }

    @GetMapping("/getHospitals/{location}/{rating}")
    public List<Hospital> getHospitalByNameAndRating(@PathVariable("location") String location,@PathVariable("rating") double rating){
        return hospitalService.getHospitalByNameAndRating(location,rating);
    }

    @PostMapping("/saveHospital")
    //@ResponseBody
    public String saveHospital(@RequestBody Hospital hospital){
        System.out.println(hospital.toString());
        return hospitalService.saveHospital(hospital) ? "Data inserted successfully":"Data not inserted successfully";
    }

    @PostMapping("/saveHospitals")
    public String saveHospitals(@RequestBody List<Hospital> hospital){
        return hospitalService.saveHospitals(hospital) ? "Data inserted successfully":"Data not inserted successfully";
    }

    @DeleteMapping("/delHospital/{id}")
    public String delHospitalById(@PathVariable("id") int id){
        return hospitalService.delHospitalById(id) ? "Data Deleted Successfully" : "Data Not Deleted";
    }

    @DeleteMapping("/delHospital/{location}/{rating}")
    public String delHospitalByIdAndRating(@PathVariable("location") String location,@PathVariable("rating") double rating){
        return hospitalService.delHospitalByIdAndRating(location,rating) ? "Data Deleted Successfully" : "Data Not Deleted";
    }


    @PutMapping("/updateHospital/{id}")
    public Hospital updateHospital(@RequestBody Hospital newHospital,@PathVariable("id") int id){
        return hospitalService.updateHospital(newHospital,id);
    }

    @PutMapping("/updateHospitalByName/{name}")
    public Hospital updateHospitalByName(@RequestBody Hospital newHospital,@PathVariable("name") String name){
        return hospitalService.updateHospitalByName(newHospital,name);
    }
}
