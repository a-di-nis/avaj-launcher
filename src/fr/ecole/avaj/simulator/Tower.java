package fr.ecole.avaj.simulator;

import	fr.ecole.avaj.simulator.*;
import	fr.ecole.avaj.simulator.vehicles.*;
import	fr.ecole.avaj.weather.*;
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
        for (Flyable flyable : observer) {
            flyable.updateConditions();
        }
    }
}
