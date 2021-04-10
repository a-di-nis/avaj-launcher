package fr.ecole.avaj.simulator.vehicles;

import	fr.ecole.avaj.simulator.*;

public interface Flyable {
    public void updateConditions();

    public void registerTower(WeatherTower WeatherTower);

    public boolean hasLanded();
}
