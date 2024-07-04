package com.fakepi.ShoeStore.Controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
public class UserController {

  @GetMapping("/")
  String shoeStore() {
    return "Fake Shoe Store API";
  }


}
