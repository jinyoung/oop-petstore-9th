package com.example.petstore;

public class Dog extends Pet implements Runnable {

    @Override
    public void speak() {
        System.out.println("멍멍");
    }

    @Override
    public void run() {
        System.out.println(" Dog Run!!!");
        
    }

    @Override
    public void eat() {
        
        super.eat();
        super.eat();

    }

    
    
    
}
