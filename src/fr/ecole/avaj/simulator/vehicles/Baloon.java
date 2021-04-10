package fr.ecole.avaj.simulator.vehicles;

import	fr.ecole.avaj.simulator.*;
import	fr.ecole.avaj.weather.*;

public class Baloon extends Aircraft implements Flyable {
    private WeatherTower weatherTower;
    private boolean landed = false;

    public Baloon(String name, Coordinates coordinates) {
        super(name, coordinates);
	}

    @Override
    public void updateConditions() {
        String weather = WeatherProvider.getProvider().getCurrentWeather(this.coordinates);        
        switch(weather) {
            case "SUN":
                this.coordinates.move(2, 0, 4);
                Logger.getLogger().writeLine("Baloon#" + this.name + "(" + this.id + "): Here comes the sun, and I say it's all right");
                break;
            case "RAIN":
                this.coordinates.move(0, 0, -5);
                Logger.getLogger().writeLine("Baloon#" + this.name + "(" + this.id + "): Just a little rain, oh ooh, yeah-yeah-yeah");
                break;
            case "FOG":
                this.coordinates.move(0, 0, -3);
                Logger.getLogger().writeLine("Baloon#" + this.name + "(" + this.id + "): The fog's getting thicker, and the world's spinning fast");
                break;
            case "SNOW":
                this.coordinates.move(0, 0, -15);
                Logger.getLogger().writeLine("Baloon#" + this.name + "(" + this.id + "): And the sky is a hazy shade of winter");
                break;
        }
        if (this.coordinates.getHeight() <= 0) {
            landed = true;
            Logger.getLogger().writeLine("Tower says: Baloon#" + this.name + "(" + this.id + ") unregistered to weather tower.");
            Logger.getLogger().writeLine("Landing coordinates: " + this.coordinates.getLongitude() + " " + this.coordinates.getLatitude() + " " + this.coordinates.getHeight() + ".");
        }
    }

    @Override
    public void registerTower(WeatherTower weatherTower) {
        this.weatherTower = weatherTower;
        this.weatherTower.register(this);
        Logger.getLogger().writeLine("Tower says: Baloon#" + this.name + "(" + this.id + ") registered to weather tower.");
    }

    @Override
    public boolean hasLanded() {
        return this.landed;
    }
}
