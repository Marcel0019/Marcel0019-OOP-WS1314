/**
 * Beispiel Observer-Muster.
 * Konkretes Subjekt (Uhrwerk).
 */
package kap35;

import java.util.Calendar;
import java.util.Observable;

/**
 * @author clecon
 * @data 1.0, Aug 2011
 * @author solymosi Vereinfachung
 * @version 04/09/12
 *
 */

public class UhrObservable extends Observable {	
	private static final int FREQUENZ = 1000; // Frequenz der Uhrzeit-Aktualisierung (in Millisekunden)
	private Daten uhrzeit;
	
	public void zeitmessung() {
		try {
			while (true) {
				Thread.sleep(FREQUENZ);
				Calendar kalender = Calendar.getInstance(); 
				uhrzeit = new Daten(kalender.get(Calendar.HOUR), kalender.get(Calendar.MINUTE), kalender.get(Calendar.SECOND));
				super.setChanged();
				super.notifyObservers(uhrzeit);
			}
		} catch (InterruptedException e) {
			System.err.println("FEHLER bei der Uhr-Warteschleife.");
		}
	}
}
