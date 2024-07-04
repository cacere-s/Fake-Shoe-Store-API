package com.fakepi.ShoeStore.Dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ShoeUpdateRequest {
  private String title;
  private String description;
  private Integer price;
}

