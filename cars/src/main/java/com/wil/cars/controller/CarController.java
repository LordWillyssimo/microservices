
package com.wil.cars.controller;

import com.wil.cars.entity.Cars;
import com.wil.cars.service.CarsService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/car")
public class CarController {
    
    @Autowired
    private CarsService carService;
    
    @GetMapping
    public ResponseEntity<List<Cars>> listCars(){
        List<Cars> cars=carService.getAll();
        if(cars.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(cars);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Cars> getCar(@PathVariable("id") int id){
        Cars car=carService.getCarById(id);
        if(car==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(car);
    }
    
    @PostMapping
    public ResponseEntity<Cars> saveCar(@RequestBody Cars car){
        Cars newCar=carService.save(car);
        return ResponseEntity.ok(newCar);
    }
    
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Cars>> listCarsByUserId(@PathVariable("userId") int userId){
        List<Cars> cars=carService.byUserId(userId);
        if(cars.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cars);
    }
}
