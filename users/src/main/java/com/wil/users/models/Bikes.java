
package com.wil.users.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Bikes {
    
    private String brand;
    private String model;
    private int userId;
}
