package fr.ecole.avaj.simulator;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Logger {
    private static Logger logger = null;
    private BufferedWriter bw = null;
    private FileWriter fw = null;

    private Logger() {
        try {
            fw = new FileWriter("simulation.txt");
            bw = new BufferedWriter(fw);
        }
        catch (IOException e) {
            System.err.format("IOException: %s%n", e);
            System.exit(1);
        }
    }

    public static Logger getLogger() {
        if (logger == null) {
            logger = new Logger();
        }
        return logger;
    }

    public void writeLine(String string) {
        try {
            bw.write(string);
            bw.newLine();
        }
        catch (IOException e) {
            System.err.format("IOException: %s%n", e);
            System.exit(2);
        }
    }

    public void close() {
        try {
            if (bw != null)
                bw.close();
            if (fw != null)
                fw.close();
        }
        catch (IOException ex) {
            System.err.format("IOException: %s%n", ex);
            System.exit(3);
        }
    }
}