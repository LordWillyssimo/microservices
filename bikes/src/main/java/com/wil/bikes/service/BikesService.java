
package com.wil.bikes.service;

import com.wil.bikes.entity.Bikes;
import com.wil.bikes.repository.BikesRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BikesService {
    
    @Autowired
    private BikesRepository bikeRepository;
    
    public List<Bikes> getAll(){
        return bikeRepository.findAll();
    }
    
    public Bikes getBikeById(int id){
        return bikeRepository.findById(id).orElse(null);
    }
    
    
    public Bikes save(Bikes bike){
        Bikes newBike=bikeRepository.save(bike);
        return newBike;
    }        
    
    
    public List<Bikes> byUserId(int userId){
        return bikeRepository.findByUserId(userId);
    }
    
    
}
