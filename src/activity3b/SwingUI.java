/*
 * Initial Author
 *      Michael J. Lutz
 *
 * Other Contributers
 *
 * Acknowledgements
 */

/*
 * Swing UI class used for displaying the information from the
 * associated weather station object.
 *
 * This is an extension of JFrame, the outermost container in
 * a Swing application.
 *
 * Also implements Observer so we can receive update notifications
 * from the attached WeatherStation.
 */

import java.awt.Font ;
import java.awt.GridLayout ;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame ;
import javax.swing.JLabel ;
import javax.swing.JPanel ;

//import java.text.DecimalFormat ;

public class SwingUI extends JFrame implements Observer {
   private JLabel celsiusField ;   // put current celsius reading here
   private JLabel kelvinField ;    // put current kelvin reading here
   private JLabel inchesField ;
   private JLabel millibarsField ;    
   private JLabel fahrenheitField ;


   private final WeatherStation station ;

   /*
    * A Font object contains information on the font to be used to
    * render text.
    */
   private static Font labelFont =
      new Font(Font.SERIF, Font.PLAIN, 45) ;

   /*
    * Create and populate the SwingUI JFrame with panels and labels to
    * show the temperatures.
    */
   public SwingUI(WeatherStation station) {
      super("Weather Station") ;
   
      /*
       * Remember the station we're attached to and
       * add ourselves as an observer.
       */
      this.station = station;
      this.station.addObserver(this);
   
      /*
       * WeatherStation frame is a grid of 1 row by an indefinite
       * number of columns.
       */
      this.setLayout(new GridLayout(1,0)) ;
   
      /*
       * There are two panels, one each for Kelvin and Celsius, added to the
       * frame. Each Panel is a 2 row by 1 column grid, with the temperature
       * name in the first row and the temperature itself in the second row.
       */
      JPanel panel ;
   
      /*
       * Set up Kelvin display.
       */
      panel = new JPanel(new GridLayout(2,1)) ;
      this.add(panel) ;
      createLabel(" Kelvin ", panel) ;
      kelvinField = createLabel("", panel) ;
   
      /*
       * Set up Celsius display.
       */
      panel = new JPanel(new GridLayout(2,1)) ;
      this.add(panel) ;
      createLabel(" Celsius ", panel) ;
      celsiusField = createLabel("", panel) ;
   
      panel = new JPanel(new GridLayout(2,1)) ;
      this.add(panel) ;
      createLabel(" Fahrenheit ", panel) ;
      fahrenheitField = createLabel("", panel) ;
   
      panel = new JPanel(new GridLayout(2,1)) ;
      this.add(panel) ;
      createLabel(" Inches ", panel) ;
      inchesField = createLabel("", panel) ;
   
      panel = new JPanel(new GridLayout(2,1)) ;
      this.add(panel) ;
      createLabel(" Millibars ", panel) ;
      millibarsField = createLabel("", panel) ;
   
       /*
       * Set up the frame's default close operation pack its elements,
       * and make the frame visible.
       */
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE) ;
      this.pack() ;
      this.setVisible(true) ;
   }

    /*
     * Set the label holding the Kelvin temperature.
     * Now private as it is set from inside the Observer.
     */
   private void setKelvinJLabel(double temperature) {
      kelvinField.setText(String.format("%6.2f", temperature)) ;
   }

   private void setCelsiusJLabel(double temperature) {
      celsiusField.setText(String.format("%6.2f", temperature)) ;
   }

   private void setFahrenheitJLabel(double temperature) {
      fahrenheitField.setText(String.format("%6.2f", temperature)) ;
   }

   private void setInchesJLabel(double pressure) {
      inchesField.setText(String.format("%6.2f", pressure)) ;
   }

   private void setMillibarsJLabel(double pressure) {
      millibarsField.setText(String.format("%6.2f", pressure)) ;
   }

   /*
    * Create a Label with the initial value <title>, place it in
    * the specified <panel>, and return a reference to the Label
    * in case the caller wants to remember it.
    */
   private JLabel createLabel(String title, JPanel panel) {
      JLabel label = new JLabel(title) ;
   
      label.setHorizontalAlignment(JLabel.CENTER) ;
      label.setVerticalAlignment(JLabel.TOP) ;
      label.setFont(labelFont) ;
      panel.add(label) ;
   
      return label ;
   }
   
   /*
    * Called when WeatherStation gets another reading.
    * The Observable should be the station; the Object
    * argument is ignored.
    */
   public void update(Observable obs, Object ignore) {
      
      /*
       * Check for spurious updates from unrelated objects.
       */
      if(station != obs) {
         return ;
      }
      
      setKelvinJLabel(station.getKelvin());
      setCelsiusJLabel(station.getCelsius());
      setInchesJLabel(station.getInches());
      setMillibarsJLabel(station.getMillibars());     
      setFahrenheitJLabel(station.getFahrenheit());

   }   

   /*
    * Start the application.
    */
   public static void main(String[] args) {
      WeatherStation ws = new WeatherStation() ;
      Thread thread = new Thread(ws) ;
      SwingUI ui = new SwingUI(ws) ;
      thread.start() ;
   }


}
