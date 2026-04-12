package com.wil.users.service;

import com.wil.users.entity.Users;
import com.wil.users.feignclients.BikesFeignClient;
import com.wil.users.feignclients.CarsFeignClient;
import com.wil.users.models.Bikes;
import com.wil.users.models.Cars;
import com.wil.users.repository.UsersRepository;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UsersService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private UsersRepository usersRepository;
    
    @Autowired
    private CarsFeignClient carFeignClient;
    
    @Autowired
    private BikesFeignClient bikeFeignClient;
    
    public List<Users> getAll() {
        return usersRepository.findAll();
    }

    public Users getUserById(int id) {
        return usersRepository.findById(id).orElse(null);
    }

    public Users save(Users user) {
        Users newUser = usersRepository.save(user);
        return newUser;
    }
    
    
    public List<Cars> getCars(int userId) {
        List<Cars> cars = restTemplate.getForObject("http://localhost:8082/api/v1/car/user/" + userId, List.class);
        return cars;
    }

    public List<Bikes> getBikes(int userId) {
        List<Bikes> bikes = restTemplate.getForObject("http://localhost:8083/api/v1/bike/user/" + userId, List.class);
        return bikes;
    }
    
    public Cars saveCar(int usuarioId, Cars car){
        car.setUserId(usuarioId);
        Cars newCar=carFeignClient.save(car);
        return newCar;
    }
    
    public Bikes saveBike(int usuarioId, Bikes bike){
        bike.setUserId(usuarioId);
        Bikes newbike=bikeFeignClient.save(bike);
        return newbike;
    }
    
    public Map<String, Object> getUserAndVehicles(int userId){
        Map<String,Object> result=new HashMap<>();
        Users user=usersRepository.findById(userId).orElse(null);
        if(user==null){
            result.put("Message", "User not found");
            return result;
        }
        
        result.put("User", user);
        List<Cars> cars=carFeignClient.getCars(userId);
        if(cars.isEmpty()){
            result.put("Cars", "User haven't cars");
        }else{
            result.put("Cars", cars);
        }
        List<Bikes> bikes=bikeFeignClient.getBikes(userId);
        if(bikes.isEmpty()){
            result.put("Bikes", "User haven'bikes");
        }else{
            result.put("Bikes", bikes);
        }
        return result;
    }



}
