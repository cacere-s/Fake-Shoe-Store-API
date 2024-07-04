package com.fakepi.ShoeStore;

import com.fakepi.ShoeStore.Models.ShoeModel;
import com.fakepi.ShoeStore.Repository.CategoryRepository;
import com.fakepi.ShoeStore.Repository.ShoeRepository;
import com.fakepi.ShoeStore.Repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class ShoeStoreApplication {

  public static void main(String[] args) {
    SpringApplication.run(ShoeStoreApplication.class, args);
  }

  @Bean
  CommandLineRunner init (
      UserRepository userRepository,
      CategoryRepository categoryRepository,
      ShoeRepository shoeRepository
      ) {
    return args -> {
      ShoeModel shoe1 = ShoeModel.builder()
          .title("Prevail Premium")
          .description("Beige, blanco, gamuza, paneles de malla, puntera redonda, cierre con agujetas en la parte delantera, logo bordado en la lengüeta")
          .price(203).image("https://res.cloudinary.com/dgaj83nvk/image/upload/v1720067783/Shoe%20Store/Puma/aiffemdcdasvakaedhf9.png")
          .category(categoryRepository.findById(5L).orElseThrow())
          .user(userRepository.findById(2L).orElseThrow())
          .build();
      ShoeModel shoe2 = ShoeModel.builder()
          .title("MA Runner")
          .description("blanco, diseño a paneles de mallas, esquinas con paneles de cuero, aplique del logo en la lengüeta")
          .price(762).image("https://res.cloudinary.com/dgaj83nvk/image/upload/v1720068099/Shoe%20Store/Amiri/cq8olg2dpve7rpwanngl.png")
          .category(categoryRepository.findById(3L).orElseThrow())
          .user(userRepository.findById(3L).orElseThrow())
          .build();
      ShoeModel shoe3 = ShoeModel.builder()
          .title("Air Max Plus")
          .description("Negro, gris, blanco, detalle del logo Swoosh característico, puntera redonda, cierre con agujetas en la parte delantera")
          .price(340).image("https://res.cloudinary.com/dgaj83nvk/image/upload/v1720068396/Shoe%20Store/Nike/gglf1teurxnwvgpffun8.png")
          .category(categoryRepository.findById(1L).orElseThrow())
          .user(userRepository.findById(3L).orElseThrow())
          .build();

      List<ShoeModel> shoes = new ArrayList<>();
      shoes.add(shoe1);
      shoes.add(shoe2);
      shoes.add(shoe3);

      shoeRepository.saveAll(shoes);
    };
  }

}
