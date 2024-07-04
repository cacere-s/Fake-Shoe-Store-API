package com.fakepi.ShoeStore.Models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "shoes")
public class ShoeModel {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String title;
  private Integer price;
  private String description;
  private String image;
  @JsonManagedReference
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "user_id")
  private UserModel user;
  @JsonManagedReference
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(nullable = false, name = "category_id")
  private ShoeCategoryModel category;
}
