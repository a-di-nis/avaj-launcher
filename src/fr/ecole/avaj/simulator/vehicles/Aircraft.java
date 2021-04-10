package fr.ecole.avaj.simulator.vehicles;

import	fr.ecole.avaj.weather.*;

public abstract class Aircraft {
    protected long id;
    protected String name;
    protected Coordinates coordinates;
    static private long idCounter = 0;

    protected Aircraft(String name, Coordinates coordinates) {
        this.name = name;
        this.id = nextId();
        this.coordinates = coordinates;
    }

    private long nextId() {
        idCounter++;
        this.id = idCounter;
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public long getId() {
        return this.id;
    }
}