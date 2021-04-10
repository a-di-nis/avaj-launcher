package fr.ecole.avaj.weather;

public class WeatherProvider {
    private static WeatherProvider weatherProvider = null;
    private String[] weather = {"SUN", "RAIN", "FOG", "SNOW"};

    private WeatherProvider() {
    }

    public static WeatherProvider getProvider() {
        if (weatherProvider == null) {
            weatherProvider = new WeatherProvider(); 
        }
        return weatherProvider;
    }

    public String getCurrentWeather(Coordinates coordinates) {
        int int_random = (coordinates.getLatitude() * 5 + coordinates.getLongitude() * 3 + coordinates.getHeight() * 7) % 4;
        return (weather[int_random]);
    }
}