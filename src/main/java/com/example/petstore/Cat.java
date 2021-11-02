package com.example.petstore;


import java.io.Serializable;

public class Cat extends Pet implements Groomable, Runnable{

    @Override
    public void speak() {

        if(getEnergy() < 5){            
            System.out.println("I'm hungry");
        }else     
            System.out.println("Hi");
    }

    @Override
    public String grooming() {
        //answer must be obtained by UI

        return "야옹. 행복하다 집사야.";
    }

    @Override
    public void run() {
        
        System.out.println("I'm running!!!");
        
    }

    
}
