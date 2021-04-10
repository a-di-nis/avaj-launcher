package fr.ecole.avaj.simulator;

import	fr.ecole.avaj.simulator.vehicles.*;
import  java.util.List;
import  java.util.ArrayList;

public class Tower {
    private List<Flyable> observer = new ArrayList<Flyable>();

    public void register(Flyable flyable) {
        observer.add(flyable);
    };

    public void unregister(Flyable flyable) {
        observer.remove(flyable);
    };

    protected void conditionsChanged() {
        int i = 0;
        while (i < observer.size()) {
            observer.get(i).updateConditions();
            if (observer.get(i).hasLanded()) {
                this.unregister(observer.get(i));
            }
            else {
                i++;
            }
        }
    }
}