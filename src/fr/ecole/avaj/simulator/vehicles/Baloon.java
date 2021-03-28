package fr.ecole.avaj.simulator.vehicles;

import	fr.ecole.avaj.simulator.*;
import	fr.ecole.avaj.weather.*;

public class Baloon extends Aircraft implements Flyable {
    private WeatherTower weatherTower;

    public Baloon(String name, Coordinates coordinates) {
        super(name, coordinates);
	}

    @Override
    public void updateConditions() {
        String weather = WeatherProvider.getProvider().getCurrentWeather(this.coordinates);        
        // this.coordinates = new Coordinates(this.coordinates.getLongitude() + 5,...)
        switch(weather) {
            case "SUN":
                this.coordinates.move(2, 0, 4);
                break;
            case "RAIN":
                this.coordinates.move(0, 0, -5);
                break;
            case "FOG":
                this.coordinates.move(0, 0, -3);
                break;
            case "SNOW":
                this.coordinates.move(0, 0, -15);
                break;
        }
    }

    @Override
    public void registerTower(WeatherTower weatherTower) {
    }
}
