/**
 * Beispiel Observer-Muster: Hauptprogramm.
 */
package kap35;

/**
 * @author clecon
 * @version 1.0, 08/2011
 * @author solymosi Vereinfachung
 * @version 04/09/12
 *
 */
public class Main {
	public static void main(String[] args) {
		UhrObservable subjekt = new UhrObservable();
		
		// Beobachter:
		new DigitalObserver(subjekt);
		new KreisObserver(subjekt);
		
		// Uhr starten:
		subjekt.zeitmessung();
	}
}
