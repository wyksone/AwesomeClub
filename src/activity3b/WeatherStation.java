/*
 * Initial Author
 *      Michael J. Lutz
 *
 * Other Contributers
 *
 * Acknowledgements
 */

/*
 * Class for a simple computer based weather station that reports the current
 * temperature (in Celsius) every second. The station is attached to a
 * sensor that reports the temperature as a 16-bit number (0 to 65535)
 * representing the Kelvin temperature to the nearest 1/100th of a degree.
 *
 * This class is implements Runnable so that it can be embedded in a Thread
 * which runs the periodic sensing.
 *
 * The class also extends Observable so that it can notify registered
 * objects whenever its state changes. Convenience functions are provided
 * to access the temperature in different schemes (Celsius, Kelvin, etc.)
 */
import java.util.Observable ;

public class WeatherStation extends Observable implements Runnable {

   private final KelvinTempSensor sensor ; // Temperature sensor.
   private final Barometer bar; // Pressure sensor

   private final long PERIOD = 1000 ;      // 1 sec = 1000 ms
   private final int KTOC = -27315 ;       // Kelvin to Celsius conversion.

   private int currentReading ;
   private double currentPressure ;

   /*
    * When a WeatherStation object is created, it in turn creates the sensor
    * object it will use.
    */
   public WeatherStation() {
      sensor = new KelvinTempSensor() ;
      bar = new Barometer() ;
      currentReading = sensor.reading() ;
      currentPressure = bar.pressure() ;
   }

   /*
    * Return the current reading in degrees celsius as a
    * double precision number.
    */
   public double getCelsius() {
      return (currentReading + KTOC) / 100.0 ;
   }

   public double getKelvin() {
      return currentReading / 100.0 ;
   }

   public double getFahrenheit() {
      return (getKelvin() - 273.15) * 1.8 + 32.00;
   }

   public double getInches() {
      return this.currentPressure;
   }

   public double getMillibars() {
      return this.currentPressure * 33.8637;
   }
   /*
    * The "run" method called by the enclosing Thread object when started.
    * Repeatedly sleeps a second, acquires the current temperature from its
    * sensor, and notifies registered Observers of the change.
    */
   public void run() {
      while( true ) {
         try {
            Thread.sleep(PERIOD) ;
         } catch (Exception ex) {ex.getMessage();}
         
         /*
          * Get next reading and notify any Observers.
          */
         synchronized(this) {
            currentReading = sensor.reading() ;
            currentPressure = bar.pressure() ;
         }
         setChanged() ;
         notifyObservers() ;
      }
   }

}
