package fr.ecole.avaj.simulator.vehicles;

import	fr.ecole.avaj.simulator.*;
import	fr.ecole.avaj.weather.*;

public class AircraftFactory {
    public static Flyable newAircraft(String type, String name, int longitude, int latitude, int height) {
        
        if (type == null) {
            return null;
        }

        if (type.equalsIgnoreCase("BALOON")) {
            return new Baloon(name, new Coordinates(longitude, latitude, height));
        }
        else if (type.equalsIgnoreCase(("JETPLANE"))) {
            return new JetPlane(name, new Coordinates(longitude, latitude, height));
        }
        else if (type.equalsIgnoreCase("HELICOPTER")) {
            return new Helicopter(name, new Coordinates(longitude, latitude, height));
        }
        return null;
    }
}
