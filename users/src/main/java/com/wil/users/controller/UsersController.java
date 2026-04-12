
package com.wil.users.controller;

import com.wil.users.entity.Users;
import com.wil.users.models.Bikes;
import com.wil.users.models.Cars;
import com.wil.users.service.UsersService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UsersController {
    
    @Autowired
    private UsersService userService;
    
    @GetMapping()
    public ResponseEntity<List<Users>> getListUsers(){
        List<Users> users=userService.getAll();
        if(users.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(users);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Users> getUser(@PathVariable("id") int id ){
        Users user=userService.getUserById(id);
        if(user==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }
    
    @PostMapping()
    public ResponseEntity<Users> saveUsers(@RequestBody Users user){
        Users newUser=userService.save(user);
        return ResponseEntity.ok(newUser);
    }
        
    @GetMapping("/car/{userId}")
    public ResponseEntity<List<Cars>> listCars(@PathVariable("userId") int userId){
        Users user=userService.getUserById(userId);
        if(user==null){
            return ResponseEntity.notFound().build();
        }
        
        List<Cars> cars=userService.getCars(userId);
        return ResponseEntity.ok(cars);
    }
    
    @GetMapping("/bike/{userId}")
    public ResponseEntity<List<Bikes>> listBikes(@PathVariable("userId") int userId){
        Users user=userService.getUserById(userId);
        if(user==null){
            return ResponseEntity.notFound().build();
        }
        
        List<Bikes> bikes=userService.getBikes(userId);
        return ResponseEntity.ok(bikes);
    }
    
    @PostMapping("/car/{userId}")
    public ResponseEntity<Cars> saveCar(@PathVariable("userId") int userId, @RequestBody Cars car){
        Cars newCar=userService.saveCar(userId, car);
        return ResponseEntity.ok(newCar);
    }
    
    @PostMapping("/bike/{userId}")
    public ResponseEntity<Bikes> saveBike(@PathVariable("userId") int userId, @RequestBody Bikes bike){
        Bikes newBike=userService.saveBike(userId, bike);
        return ResponseEntity.ok(newBike);
    }
    
    @GetMapping("/all/{userId}")
    public ResponseEntity<Map<String, Object>> listAllVehicles(@PathVariable("userId") int userId){
        Map<String,Object> result=userService.getUserAndVehicles(userId);
        return ResponseEntity.ok(result);
    }
}
