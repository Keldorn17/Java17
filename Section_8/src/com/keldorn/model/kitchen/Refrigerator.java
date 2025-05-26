package com.keldorn.model.kitchen;

public class Refrigerator extends KitchenItem{
    public Refrigerator(boolean hasWorkToDo) {
        super(hasWorkToDo);
    }

    public void orderFood() {
        if (isHasWorkToDo()) {
            System.out.println("Order food");
            setHasWorkToDo(false);
        }
    }
}
