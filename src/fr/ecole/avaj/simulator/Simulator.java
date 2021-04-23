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
            // System.out.println(line);
            if (line != null) {
                weatherTower = new WeatherTower();
                int simulations = -1;
                try {
                    simulations = Integer.parseInt(line.split(" ")[0]);
                }
                catch (NumberFormatException e) {
                    System.out.println("First line of the file " + arg[0] + " must be a positive integer");
                    System.out.println(e.getMessage());
                    System.exit(9);
                }

                if (simulations < 0) {
                    System.out.println("Invalid simulations count " + simulations);
                    System.exit(2);
                }
                while ((line = reader.readLine()) != null) {
                    String lineSpaces = line.trim().replaceAll(" +", " ");
                    String[] lineElements = lineSpaces.split(" ");
                    if (lineElements.length != 5) {
                        throw new InvalidArgumentCountException("There should be 5 arguments per aircraft");
                    }
                    Flyable flyable = AircraftFactory.newAircraft(lineElements[0], lineElements[1],
                        Integer.parseInt(lineElements[2]), Integer.parseInt(lineElements[3]),
                        Integer.parseInt(lineElements[4]));
                    if (Integer.parseInt(lineElements[2]) < 0 || Integer.parseInt(lineElements[3]) < 0 || Integer.parseInt(lineElements[4]) < 0) {
                        throw new InvalidArgumentCountException("All the values of the coordinates must be >= 0");
                    }
                    if (!(lineElements[0].equals("Baloon")) && !(lineElements[0].equals("JetPlane")) && !(lineElements[0].equals("Helicopter"))) {		
                        throw new InvalidArgumentCountException("The type of aircarft must be either Baloon, JetPlane or Helicopter");		
                    }
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
			// System.out.println("Couldn't find file " + arg[0]);
            System.out.println("Couldn't open the file.");
            System.out.println(e.getMessage());
		}
        catch (IOException e) {
			System.out.println("There was an error while reading the file " + arg[0]);
            System.out.println(e.getMessage());
		}
        catch (ArrayIndexOutOfBoundsException e){
            System.out.println("The index is out of bounds");
            System.out.println(e.getMessage());
        }
        catch (NumberFormatException e) {
            System.out.println("Not a number when number was expected.");
            System.out.println(e.getMessage());
        }
        catch (Exception e) {
            System.out.println("An unexpected error happened.");
            System.out.println(e.getMessage());
            // e.printStackTrace();
        }
        finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            }
            catch (Exception e) {
                System.out.println("An error occurred when trying to close the reader file");
                System.out.println(e.getMessage());
                // e.printStackTrace();
            }
            Logger.getLogger().close();
		}
	}
}