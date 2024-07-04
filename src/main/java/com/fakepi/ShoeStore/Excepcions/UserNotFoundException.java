package com.fakepi.ShoeStore.Excepcions;

public class UserNotFoundException extends RuntimeException {
  public UserNotFoundException(String message) {
    super(message);
  }
}
