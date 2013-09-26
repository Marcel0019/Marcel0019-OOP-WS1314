/**
 * Verbund-Struktur, um die Uhrzeitkomponenten
 * (Stumde, Minute, Sekunde) zu speichern.
 */
package kap35;

/**
 * @author clecon
 * @version 08/2011
 * @author solymosi Vereinfachung
 * @version 04/09/12
 *
 */
public class Daten {
	private int stunde;
	private int minute;
	private int sekunde;	
	public Daten(int stunde, int minute, int sekunde) {
		this.stunde = stunde;
		this.minute = minute;
		this.sekunde = sekunde;
	}	
	public int getHours() {
		return stunde;
	}	
	public int getMinutes() {
		return minute;
	}	
	public int getSeconds() {
		return sekunde;
	}
}
