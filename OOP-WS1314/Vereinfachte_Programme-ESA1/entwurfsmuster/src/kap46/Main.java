/**
 * Beispiel Observer-Muster: Hauptprogramm.
 */
package kap46;

/**
 * @author clecon
 * @version 1.0, 08/2011
 * @author solymosi Vereinfachung
 * @version 04/09/12
 *
 */
public class Main {
	public static void main(String[] args) {
		Model model = new Model();
		
		// Beobachter:
		new DigitalView(model);
		new KreisView(model);
		
		// Uhr starten:
		model.zeitmessung();
	}
}
