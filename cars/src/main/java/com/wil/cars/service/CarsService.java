
package com.wil.cars.service;

import com.wil.cars.entity.Cars;
import com.wil.cars.repository.CarsRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarsService {
    
    @Autowired
    private CarsRepository carRepository;
    
    public List<Cars> getAll(){
        return carRepository.findAll();
    }
    
    public Cars getCarById(int id){
        return carRepository.findById(id).orElse(null);
    }
    
    
    public Cars save(Cars car){
        Cars newCar=carRepository.save(car);
        return newCar;
    }        
    
    
    public List<Cars> byUserId(int userId){
        return carRepository.findByUserId(userId);
    }
    
}
