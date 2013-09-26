/**
 * Beispiel fuer Observer-Muster: Beobachter2 (Kreisuhr).
 */
package kap35;

/**
 * @author clecon
 * @version 1.0, 08/2011
 * @author solymosi Vereinfachung
 * @version 04/09/12
 *
 */

import java.awt.Color;
import java.awt.Graphics;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class KreisObserver extends JFrame implements Observer {
	// Größe des Frames:
	private static final int BREITE = 800;
	private static final int HÖHE = 300;	

	// Groesse der Kreise:
	private final int GROESSE_STUNDEN_KREIS = 200;
	private final int SKALIERUNGSFAKTOR_STUNDEN = GROESSE_STUNDEN_KREIS/24;
	private final int GROESSE_MINUTEN_KREIS = 150;
	private final int SKALIERUNGSFAKTOR_MINUTEN = GROESSE_MINUTEN_KREIS/60;
	private final int GROESSE_SEKUNDEN_KREIS = 100; // 50;
	private final int SKALIERUNGSFAKTOR_SEKUNDEN = GROESSE_SEKUNDEN_KREIS/60;
	private final int POS_X_STUNDEN = 210;
	private final int POS_X_MINUTEN = 395;
	private final int POS_X_SEKUNDEN = 530;
	private final int POS_Y = 50+(GROESSE_STUNDEN_KREIS/2);
	private final Color FARBE_STUNDE = Color.red;
	private final Color FARBE_MINUTE = Color.green;
	private final Color FARBE_SEKUNDE = Color.blue;

	private Observable subjekt;
	private Daten data;
	
	public KreisObserver(Observable subjekt) {
		this.subjekt = subjekt;
		this.subjekt.addObserver(this);
		
		// Anzeige:
		setTitle("Kreisuhr");
		setSize(BREITE, HÖHE);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	private void zeichneKreisLinie(Graphics g, int x, int y, int radius, Color farbe) {
		g.setColor(farbe);
		g.drawOval(x-radius, y-radius, 2*radius, 2*radius);
	}	
	private void zeichneKreis(Graphics g, int durchmesser, int position, Color farbe, int zeit) {
		g.setColor(farbe);
		int radius = durchmesser / 2;
		g.fillOval(position - radius, POS_Y - radius, durchmesser, durchmesser);
		g.drawString("" + zeit, position, POS_Y + (GROESSE_STUNDEN_KREIS/2)+30);		
	}

	@Override // Frame
	public void paint(Graphics g) {
		if (data == null) {
			return;
		}
		g.clearRect(0, 0, BREITE, HÖHE);
        zeichneKreisLinie(g, POS_X_STUNDEN, POS_Y, GROESSE_STUNDEN_KREIS/2, FARBE_STUNDE);
        zeichneKreisLinie(g, POS_X_MINUTEN, POS_Y, GROESSE_MINUTEN_KREIS/2, FARBE_MINUTE);
        zeichneKreisLinie(g, POS_X_SEKUNDEN, POS_Y, GROESSE_SEKUNDEN_KREIS/2, FARBE_SEKUNDE);
        // Uhrzeit als Kreise:
		zeichneKreis(g, data.getHours() * SKALIERUNGSFAKTOR_STUNDEN, POS_X_STUNDEN, FARBE_STUNDE, data.getHours());
		zeichneKreis(g, data.getMinutes() * SKALIERUNGSFAKTOR_MINUTEN, POS_X_MINUTEN, FARBE_MINUTE, data.getMinutes());
		zeichneKreis(g, data.getSeconds() * SKALIERUNGSFAKTOR_SEKUNDEN, POS_X_SEKUNDEN, FARBE_SEKUNDE, data.getSeconds());
	}
	@Override // Observer
	public void update(Observable subjekt, Object daten) {
		data = (Daten)daten;
		repaint();
	}
	public static void main(String[] args) {
		UhrObservable subjekt = new UhrObservable();
		new KreisObserver(subjekt);
		subjekt.zeitmessung();
	}	
}
