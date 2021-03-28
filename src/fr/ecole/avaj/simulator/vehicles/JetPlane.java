package fr.ecole.avaj.simulator.vehicles;

import	fr.ecole.avaj.simulator.*;
import	fr.ecole.avaj.weather.*;

public class JetPlane extends Aircraft implements Flyable {
    private WeatherTower weatherTower;

    public JetPlane(String name, Coordinates coordinates) {
        super(name, coordinates);
    }

    @Override
    public void updateConditions() {
        String weather = WeatherProvider.getProvider().getCurrentWeather(this.coordinates);
        switch(weather) {
            case "SUN":
                this.coordinates.move(0, 10, 2);
                break;
            case "RAIN":
                this.coordinates.move(0, 5, 0);
                break;
            case "FOG":
                this.coordinates.move(0, 1, 0);
                break;
            case "SNOW":
                this.coordinates.move(0, 0, -7);
                break;
        }
        // System.out.println(weather);
    }

    @Override
    public void registerTower(WeatherTower weatherTower) {
    }
}
