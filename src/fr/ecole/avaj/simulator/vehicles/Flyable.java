package fr.ecole.avaj.simulator.vehicles;

import	fr.ecole.avaj.simulator.*;
import	fr.ecole.avaj.weather.*;

public interface Flyable {
    public void updateConditions();

    public void registerTower(WeatherTower WeatherTower);
}
