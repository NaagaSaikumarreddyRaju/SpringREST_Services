package com.SpringRest;

import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.util.ReflectionUtils.findField;

@Service
public class HospitalService {

    private List<Hospital> hospitalList = new ArrayList<>(Arrays.asList(new Hospital(1001,"Apollo Hospital","Chennai",3.8),
                                          new Hospital(1002,"Global Hospital","Chennai",3.5),
                                          new Hospital(1003,"VCare Hospital","Chennai",3.9),
                                          new Hospital(1004,"Medicare Hospital","Chennai",4.2),
                                          new Hospital(1005,"LVPrasadHospital","Bangalore",3)));

    public List<Hospital> getHospitalList(){
        return hospitalList;
    }

    public Hospital getHospitalById(int id) {
        return hospitalList.stream()
                .filter(hospitalId -> hospitalId.getId() == id)
                .findFirst()
                .get();
    }

    public List<Hospital> getHospitalByNameAndRating(String location,double rating) {
        return hospitalList.stream()
                .filter(hospital -> hospital.getLocation().equals(location) && hospital.getRating() >= rating)
                .collect(Collectors.toList());
    }

    public boolean saveHospital(Hospital hospital) {
        return hospitalList.add(hospital);
    }

    public boolean saveHospitals(List<Hospital> hospital) {
        return hospitalList.addAll(hospital);
    }

    public boolean delHospitalById(int id) {
        Hospital hosp = hospitalList.stream().filter(hospital -> hospital.getId() == id).findFirst().get();
        return hospitalList.remove(hosp);
    }

    public boolean delHospitalByIdAndRating(String location, double rating) {
       List<Hospital> list = hospitalList.stream()
                .filter(hospital -> hospital.getLocation().equals(location) && hospital.getRating() < rating)
                .collect(Collectors.toList());
       return hospitalList.removeAll(list);
    }

    public Hospital updateHospital(Hospital newHospital, int id) {

        Hospital oldHospital = hospitalList.stream().filter(h -> h.getId() == id).findFirst().get();
        oldHospital.setId(newHospital.getId());
        oldHospital.setName(newHospital.getName());
        oldHospital.setLocation(newHospital.getLocation());
        oldHospital.setRating(newHospital.getRating());
        return oldHospital;
    }

    public Hospital updateHospitalByName(Hospital newHospital, String name){
        Hospital oldHospital = hospitalList.stream().filter(h -> h.getName().equals(name)).findFirst().get();
        oldHospital.setRating(newHospital.getRating());
        return oldHospital;
    }

    public Hospital partialDataUpdate(Map<String, Object> fields, int id) {
        Hospital existingHospital = hospitalList.stream().filter(h -> h.getId()==id).findFirst().get();
         fields.forEach((key,value) ->{
            Field field = ReflectionUtils.findField(Hospital.class,key);
            field.setAccessible(true);
            ReflectionUtils.setField(field, existingHospital,value);
        });

        return existingHospital;
    }
}
