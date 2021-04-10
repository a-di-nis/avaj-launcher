package fr.ecole.avaj.simulator.vehicles;

import	fr.ecole.avaj.simulator.*;
import	fr.ecole.avaj.weather.*;

public class Helicopter extends Aircraft implements Flyable {
    private WeatherTower weatherTower;
    private boolean landed = false;

    public Helicopter(String name, Coordinates coordinates) {
        super(name, coordinates);
	}

    @Override
    public void updateConditions() {
        String weather = WeatherProvider.getProvider().getCurrentWeather(this.coordinates);
        switch(weather) {
            case "SUN":
                this.coordinates.move(10, 0, -2);
                Logger.getLogger().writeLine("Helicopter#" + this.name + "(" + this.id + "): Here comes the sun");
                break;
            case "RAIN":
                this.coordinates.move(5, 0, 0);
                Logger.getLogger().writeLine("Helicopter#" + this.name + "(" + this.id + "): Just a little rain, oh ooh, yeah-yeah-yeah");
                break;
            case "FOG":
                this.coordinates.move(1, 0, 0);
                Logger.getLogger().writeLine("Helicopter#" + this.name + "(" + this.id + "): The fog's getting thicker, and the world's spinning fast");
                break;
            case "SNOW":
                this.coordinates.move(0, 0, -12);
                Logger.getLogger().writeLine("Helicopter#" + this.name + "(" + this.id + "): And the sky is a hazy shade of winter");
                break;
        }
        if (this.coordinates.getHeight() <= 0) {
            landed = true;
            Logger.getLogger().writeLine("Tower says: Helicopter#" + this.name + "(" + this.id + ") unregistered to weather tower.");
            Logger.getLogger().writeLine("Landing coordinates: " + this.coordinates.getLongitude() + " " + this.coordinates.getLatitude() + " " + this.coordinates.getHeight() + ".");
        }
    }

    @Override
    public void registerTower(WeatherTower weatherTower) {
        weatherTower.register(this);
        this.weatherTower = weatherTower;
        Logger.getLogger().writeLine("Tower says: Helicopter#" + this.name + "(" + this.id + ") registered to weather tower.");
    }

    @Override
    public boolean hasLanded() {
        return this.landed;
    }
}
