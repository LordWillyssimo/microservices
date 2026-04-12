
package com.wil.bikes.repository;

import com.wil.bikes.entity.Bikes;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BikesRepository extends JpaRepository<Bikes, Integer>{
    
    List<Bikes> findByUserId(int userId);
    
}
