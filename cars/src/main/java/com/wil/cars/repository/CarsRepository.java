
package com.wil.cars.repository;

import com.wil.cars.entity.Cars;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarsRepository extends JpaRepository<Cars, Integer>{
    
    List<Cars> findByUserId(int userId);
    
}
