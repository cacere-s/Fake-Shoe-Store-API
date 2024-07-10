package com.fakepi.ShoeStore.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "categories")
public class ShoeCategoryModel {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;
  private String image;
  @JsonBackReference
  @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
  private List<ShoeModel> shoe;
}
