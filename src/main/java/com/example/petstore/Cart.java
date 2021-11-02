package com.example.petstore;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;
//import org.springframework.stereotype.Service;

//@Component
public class Cart implements ICart{

    List<Pet> pets = new ArrayList<Pet>();

    public Cart(){
    }

    @Override
    public Pet add(Pet pet) throws Exception {
        pets.add(pet);

        return pet;
    }

    @Override
    public Pet remove(Pet pet) throws Exception {
        pets.remove(pet);

        return pet;
    }

    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("입양목록: <br>");
        pets.stream().forEach(pet -> buffer.append("<li>"+pet.toString()));

        return buffer.toString();
    }

    

}
