package com.example.petstore;

public interface ICart {    // Repository Pattern Interface
    Pet add(Pet pet) throws Exception;
    Pet remove(Pet pet) throws Exception;
}
