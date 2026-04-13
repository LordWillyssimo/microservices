
package com.wil.users.feignclients;

import com.wil.users.models.Cars;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name="cars",url="http://localhost:8082",path="/api/v1/car")
//@FeignClient(name="cars",path="/car")
//@RequestMapping("/api/v1/car")
public interface CarsFeignClient {
    
    @PostMapping()
    public Cars save(@RequestBody Cars car);
 
    @GetMapping("/user/{userId}")
    public List<Cars> getCars(@PathVariable("userId") int userId);
    
}
