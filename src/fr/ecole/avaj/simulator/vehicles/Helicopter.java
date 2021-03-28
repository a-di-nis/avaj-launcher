package fr.ecole.avaj.simulator.vehicles;

import	fr.ecole.avaj.simulator.*;
import	fr.ecole.avaj.weather.*;

public class Helicopter extends Aircraft implements Flyable {
    private WeatherTower weatherTower;

    public Helicopter(String name, Coordinates coordinates) {
        super(name, coordinates);
	}

    @Override
    public void updateConditions() {
        String weather = WeatherProvider.getProvider().getCurrentWeather(this.coordinates);
        switch(weather) {
            case "SUN":
                this.coordinates.move(10, 0, -2);
                break;
            case "RAIN":
                this.coordinates.move(5, 0, 0);
                break;
            case "FOG":
                this.coordinates.move(1, 0, 0);
                break;
            case "SNOW":
                this.coordinates.move(0, 0, -12);
                break;
        }
    }

    @Override
    public void registerTower(WeatherTower weatherTower) {
    }
}
