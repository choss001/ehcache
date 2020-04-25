package com.example.demo;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Member implements Serializable {
  private long idx;
  
  private String email;
  
  private String name;

}
