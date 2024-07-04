package com.fakepi.ShoeStore.Excepcions;

public class CategoryNotFoundException extends RuntimeException {
  public CategoryNotFoundException(String message) {
    super(message);
  }
}
