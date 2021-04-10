package fr.ecole.avaj.simulator;

import fr.ecole.avaj.Exceptions.InvalidArgumentCountException;
import	fr.ecole.avaj.simulator.vehicles.*;

import java.io.*;
import java.util.*;

public class Simulator {
	public static void main(String[] arg) throws InterruptedException {
        WeatherTower weatherTower;
        List<Flyable> flyables = new ArrayList<Flyable>();
        BufferedReader reader = null;

		try {
            if (arg.length != 1) {
                System.out.println("Need to input one argument");
                System.exit(1);
            }

            reader = new BufferedReader(new FileReader(arg[0]));

            String line = reader.readLine();
            System.out.println(line);
            if (line != null) {
                weatherTower = new WeatherTower();
                int simulations = -1;
                try {
                    simulations = Integer.parseInt(line.split(" ")[0]);
                }
                catch (NumberFormatException e) {
                    System.out.println("First line of the file " + arg[0] + " must be a positive integer");
                    System.exit(9);
                }

                if (simulations < 0) {
                    System.out.println("Invalid simulations count " + simulations);
                    System.exit(2);
                }
                while ((line = reader.readLine()) != null) {
                    String[] lineElements = line.split(" ");
                    if (lineElements.length != 5) {
                        throw new InvalidArgumentCountException("There should be 5 argument per aircraft");
                    }
                    Flyable flyable = AircraftFactory.newAircraft(lineElements[0], lineElements[1],
                        Integer.parseInt(lineElements[2]), Integer.parseInt(lineElements[3]),
                        Integer.parseInt(lineElements[4]));
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
                System.exit(3);
            }
		}
        catch (InvalidArgumentCountException e) {
            System.out.println(e.getMessage());
        }
        catch (FileNotFoundException e) {
			System.out.println("Couldn't find file " + arg[0]);
		}
        catch (IOException e) {
			System.out.println("There was an error while reading the file " + arg[0]);
		}
        catch (ArrayIndexOutOfBoundsException e){
            System.out.println("The index is out of bounds");
        }
        catch (NumberFormatException e) {
            System.out.println("Not a number when number was expected.");
        }
        catch (Exception e) {
            System.out.println("An unexpected error happened.");
            e.printStackTrace();
        }
        finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            }
            catch (Exception e) {
                System.out.println("An error occurred when trying to close the reader file");
                e.printStackTrace();
            }
            Logger.getLogger().close();
		}
	}
}