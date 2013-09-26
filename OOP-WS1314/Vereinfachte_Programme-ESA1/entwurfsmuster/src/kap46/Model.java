/**
 * Beispiel Observer-Muster.
 * Konkretes Subjekt (Uhrwerk).
 */
package kap46;

import java.util.Calendar;

import kap35.Daten;
/**
 * @author clecon
 * @data 1.0, Aug 2011
 * @author solymosi Vereinfachung
 * @version 04/09/12
 *
 */

public class Model extends IModel {	
	private static final int FREQUENZ = 1000; // Frequenz der Uhrzeit-Aktualisierung (in Millisekunden)
	private Daten uhrzeit;
	private boolean stopp = false;
	private int frequenz = FREQUENZ;
	
	// wird von main aufgerufen, nach dem Konstruktor 
	public void zeitmessung() {
		try {
			while (true) {
				Thread.sleep(frequenz);
				if (!stopp) {
					Calendar kalender = Calendar.getInstance(); 
					uhrzeit = new Daten(kalender.get(Calendar.HOUR), kalender.get(Calendar.MINUTE), kalender.get(Calendar.SECOND));
					super.setChanged();
					super.notifyObservers(uhrzeit);	
				}
			}
		} catch (InterruptedException e) {
			System.err.println("FEHLER bei der Uhr-Warteschleife.");
		}
	}

	@Override // IModelMVC
	// wird vom UhrControler aufgerufen
	public void änderung(String kommando) {
		switch (kommando) {
			case "Start" : stopp = false; break; // Stoppen der Uhrzeit aufheben
			case "Stopp" : stopp = true; break; // Uhrzeit stoppen
			case "Magic" : frequenz = (frequenz == 1000) 
					? 5000 // 5 Sekunden 
					: 1000; // 1 Sekunde
			super.setChanged();
			super.notifyObservers(uhrzeit);	
		}
	}
}
