package com.example.petstore;

import java.util.ArrayList;
import java.util.List;

public abstract class Pet {     // Entity. Domain Class.

    abstract public void speak();


    List<Listener> listeners = new ArrayList<Listener>();
    public void addListener(Listener listener) {
        this.listeners.add(listener);
    }

    private int energy = 0;
        public int getEnergy() {
            return energy;
        }
        protected void setEnergy(int energy) {
            if(Math.abs(this.energy - energy) < 3 )
                this.energy = energy;
            else    
                throw new IllegalArgumentException("Energy change is too big");
        }

    public void eat(){
        energy++;

        if(listeners!=null){
            for(int i = 0; i<listeners.size(); i++){
                listeners.get(i).energyChanged(energy);
            }
        }
    }

    @Override
    public String toString() {
        
        return "<a href='./"+this.getClass().getSimpleName().toLowerCase()+"'" + ">" + this.getClass().getSimpleName() + "</a>";
    }
    
}
