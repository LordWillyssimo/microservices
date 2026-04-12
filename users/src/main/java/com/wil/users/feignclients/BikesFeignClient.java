
package com.wil.users.feignclients;

import com.wil.users.models.Bikes;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name="bikes",url="http://localhost:8083",path="/api/v1/bike")

public interface BikesFeignClient {
    
    @PostMapping()
    public Bikes save(@RequestBody Bikes bike);
    
    @GetMapping("/user/{userId}")
    public List<Bikes> getBikes(@PathVariable("userId") int userId);
}
