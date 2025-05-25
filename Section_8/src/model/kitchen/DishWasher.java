package model.kitchen;

public class DishWasher extends KitchenItem{
    public DishWasher(boolean hasWorkToDo) {
        super(hasWorkToDo);
    }

    public void doDishes() {
        if (isHasWorkToDo()) {
            System.out.println("Starts doing the dishes");
            setHasWorkToDo(false);
        }
    }
}
