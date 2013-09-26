/**
 * Uhr (Uhrwerk).
 */
package kap12;

/**
 * @author clecon
 * @version 1.0, 08/2011
 * @author solymosi Vereinfachung
 * @version 04/09/12
 *
 */

import java.util.Calendar;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JFrame;

@SuppressWarnings("serial")
public class UhrKreise extends JFrame {
	private static final int FREQUENZ = 1000;
	// Größe des Frames:
	private static final int BREITE = 800;
	private static final int HOEHE = 300;	
	
	// Groesse der Kreise:
	private static final int GROESSE_STUNDEN_KREIS = 200;
	private static final int SKALIERUNGSFAKTOR_STUNDEN = GROESSE_STUNDEN_KREIS/24;
	private static final int GROESSE_MINUTEN_KREIS = 150;
	private static final int SKALIERUNGSFAKTOR_MINUTEN = GROESSE_MINUTEN_KREIS/60;
	private static final int GROESSE_SEKUNDEN_KREIS = 100;
	private static final int SKALIERUNGSFAKTOR_SEKUNDEN = GROESSE_SEKUNDEN_KREIS/60;
	private static final int POS_X_STUNDEN = 210;
	private static final int POS_X_MINUTEN = 395;
	private static final int POS_X_SEKUNDEN = 530;
	private static final int POS_Y = 50+(GROESSE_STUNDEN_KREIS/2);
	private static final int POS_SCHRIFT = 30;
	private static final Color FARBE_STUNDE = Color.red;
	private static final Color FARBE_MINUTE = Color.green;
	private static final Color FARBE_SEKUNDE = Color.blue;

	public UhrKreise() {
		try {
			setTitle("Kreisuhr");
			setSize(BREITE, HOEHE);
			setDefaultCloseOperation(EXIT_ON_CLOSE);
			setVisible(true);
	
			while (true) {
				repaint();
				Thread.sleep(FREQUENZ);
			}
		} catch (InterruptedException e) {
			System.err.println("FEHLER in Warteschleife");
		}
	}
	private int getHours() {
		return Calendar.getInstance().get(Calendar.HOUR);
	}	
	private int getMinutes() {
		return Calendar.getInstance().get(Calendar.MINUTE);
	}
	private int getSeconds() {
		return Calendar.getInstance().get(Calendar.SECOND);
	}
	
	private void zeichneKreis(Graphics g, int x, int y, int radius, Color farbe) {
		g.setColor(farbe);
		g.fillOval(x-radius, y-radius, 2*radius, 2*radius);
	}	
	private void zeichneKreisLinie(Graphics g, int x, int y, int radius, Color farbe) {
		g.setColor(farbe);
		g.drawOval(x-radius, y-radius, 2*radius, 2*radius);
	}	
		
	public void paint(Graphics g) {
		g.clearRect(0, 0, BREITE, HÖHE);
		zeichneKreisLinie(g, POS_X_STUNDEN, POS_Y, GROESSE_STUNDEN_KREIS/2, FARBE_STUNDE);
		zeichneKreisLinie(g, POS_X_MINUTEN, POS_Y, GROESSE_MINUTEN_KREIS/2, FARBE_MINUTE);
		zeichneKreisLinie(g, POS_X_SEKUNDEN, POS_Y, GROESSE_SEKUNDEN_KREIS/2, FARBE_SEKUNDE);

        // Uhrzeit als Kreise:
        // zeichne Stunde:
		int kreisDurchmesser = getHours() * SKALIERUNGSFAKTOR_STUNDEN;
		zeichneKreis(g, POS_X_STUNDEN, POS_Y, kreisDurchmesser/2, FARBE_STUNDE);
		g.drawString("" + getHours(), POS_X_STUNDEN, POS_Y + (GROESSE_STUNDEN_KREIS/2) + POS_SCHRIFT);        
        // zeichne Minute:
		kreisDurchmesser = getMinutes() * SKALIERUNGSFAKTOR_MINUTEN;
		zeichneKreis(g, POS_X_MINUTEN, POS_Y, kreisDurchmesser/2, FARBE_MINUTE);
		g.drawString("" + getMinutes(), POS_X_MINUTEN, POS_Y + (GROESSE_STUNDEN_KREIS/2) + POS_SCHRIFT);		
        // zeichneSekunde:
		kreisDurchmesser = getSeconds() * SKALIERUNGSFAKTOR_SEKUNDEN;
		zeichneKreis(g, POS_X_SEKUNDEN, POS_Y, kreisDurchmesser/2, FARBE_SEKUNDE);
		g.drawString("" + getSeconds(), POS_X_SEKUNDEN, POS_Y + (GROESSE_STUNDEN_KREIS/2) + POS_SCHRIFT);
    }

	public static void main(String[] args) {
		new UhrKreise();
	}
}
