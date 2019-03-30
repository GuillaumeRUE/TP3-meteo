package fr.ul.miage.rue.TP3;

import fr.ul.miage.meteo.json.Example;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        MeteoClient cl = new MeteoClient();
        Example res = cl.getWeatherByCityName();
        if(res != null) {
        	String v = res.getName();
        	float f = res.getMain().getTemp();
        	System.out.printf("Il fait %.1f °C à %s%n", f-273.15f,v);
        }
        
    }
}
