package com.keldorn.model.kitchen;

public class CoffeeMaker extends KitchenItem{
    public CoffeeMaker(boolean hasWorkToDo) {
        super(hasWorkToDo);
    }

    public void brewCoffee() {
        if (isHasWorkToDo()) {
            System.out.println("Brew Coffee");
            setHasWorkToDo(false);
        }
    }
}
