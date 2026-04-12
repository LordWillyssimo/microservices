
package com.wil.bikes.controller;

import com.wil.bikes.entity.Bikes;
import com.wil.bikes.service.BikesService;
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
@RequestMapping("/api/v1/bike")
public class BikesController {
    
    @Autowired
    private BikesService bikeService;
    
    @GetMapping
    public ResponseEntity<List<Bikes>> listBikes(){
        List<Bikes> bikes=bikeService.getAll();
        if(bikes.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(bikes);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Bikes> getBike(@PathVariable("id") int id){
        Bikes bike=bikeService.getBikeById(id);
        if(bike==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(bike);
    }
    
    @PostMapping
    public ResponseEntity<Bikes> saveCar(@RequestBody Bikes bike){
        Bikes newBike=bikeService.save(bike);
        return ResponseEntity.ok(newBike);
    }
    
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Bikes>> listCarsByUserId(@PathVariable("userId") int userId){
        List<Bikes> bikes=bikeService.byUserId(userId);
        if(bikes.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(bikes);
    }

}
