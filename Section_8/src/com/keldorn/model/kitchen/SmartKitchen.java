package com.keldorn.model.kitchen;

public class SmartKitchen extends KitchenItem{
    private CoffeeMaker brewMaster;
    private DishWasher dishWasher;
    private Refrigerator iceBox;

    public SmartKitchen(boolean hasWorkToDo, CoffeeMaker brewMaster, DishWasher dishWasher, Refrigerator iceBox) {
        super(hasWorkToDo);
        this.brewMaster = brewMaster;
        this.dishWasher = dishWasher;
        this.iceBox = iceBox;
    }

    public void addWater() {
        brewMaster.setHasWorkToDo(true);
    }

    public void pourMilk() {
        iceBox.setHasWorkToDo(true);
    }

    public void loadDishwasher() {
        dishWasher.setHasWorkToDo(true);
    }

    public void doKitchenWork() {
        brewMaster.brewCoffee();
        iceBox.orderFood();
        dishWasher.doDishes();
    }
}
