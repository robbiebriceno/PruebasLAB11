package com.tecsup.petclinic.exceptions;

public class OwnerNotFoundException extends Exception {

  public OwnerNotFoundException() {
    super();
  }

  public OwnerNotFoundException(String message) {
    super(message);
  }
}