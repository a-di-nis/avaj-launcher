package fr.ecole.avaj.simulator;

import	fr.ecole.avaj.simulator.*;
import	fr.ecole.avaj.weather.*;

public class WeatherTower extends Tower {
    public String getWeather(Coordinates coordinates) {
        return (WeatherProvider.getProvider().getCurrentWeather(coordinates));
    }
}
