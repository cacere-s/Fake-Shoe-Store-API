package com.fakepi.ShoeStore.Excepcions;

public class ShoeNotFoundException extends RuntimeException {
  public ShoeNotFoundException(String message) {
    super(message);
  }
}
