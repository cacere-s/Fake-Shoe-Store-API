package com.fakepi.ShoeStore.Dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ShoeRequest {
  private String title;
  private String description;
  private Integer price;
  private Long category_id;
  private String image;

}
