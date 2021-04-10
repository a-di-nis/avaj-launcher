package fr.ecole.avaj.simulator;

import	fr.ecole.avaj.simulator.vehicles.*;

import java.io.*;
import java.util.*;

public class Simulator {
    private static WeatherTower weatherTower;
	private static List<Flyable> flyables = new ArrayList<Flyable>();

	public static void main(String[] arg) throws InterruptedException {
		try {
            if (arg.length != 1) {
                System.out.println("Need to input one argument");
                System.exit(1);
            }

            BufferedReader reader = new BufferedReader(new FileReader(arg[0]));

            String line = reader.readLine();
            if (line != null) {
                weatherTower = new WeatherTower();
                int simulations = Integer.parseInt(line.split(" ")[0]);

                if (simulations < 0) {
                    System.out.println("Invalid simulations count " + simulations);
                    System.exit(3);
                }
                while ((line = reader.readLine()) != null) {
                    Flyable flyable = AircraftFactory.newAircraft(line.split(" ")[0], line.split(" ")[1],
                        Integer.parseInt(line.split(" ")[2]), Integer.parseInt(line.split(" ")[3]),
                        Integer.parseInt(line.split(" ")[4]));
                    if (flyable != null)
                        flyables.add(flyable);
                }

                for (Flyable flyable : flyables) {
                    flyable.registerTower(weatherTower);
                }

                for (int i = 1; i <= simulations; i++) {
                    weatherTower.conditionsChanged();
                }
            }
            else {
                System.out.println("File is empty");
                System.exit(2);
            }
		}
        catch (FileNotFoundException e) {
			System.out.println("Couldn't find file " + arg[0]);
            System.exit(4);
		}
        catch (IOException e) {
			System.out.println("There was an error while reading the file " + arg[0]);
            System.exit(5);
		}
        catch (ArrayIndexOutOfBoundsException e){
            System.out.println("The index is out of bounds");
            System.exit(6);
        }
        catch (NullPointerException e) {
			System.out.println("The value is null");
            System.exit(7);
        }
        catch (NumberFormatException e) {
            System.out.println("First line of the file " + arg[0] + " must be a positive integer");
            System.exit(8);
        }
        catch (IllegalArgumentException e) {
            System.out.println("A method has been passed an illegal or inappropriate argument.");
            System.exit(9);
        }
        finally {
            Logger.getLogger().close();
		}
	}
}