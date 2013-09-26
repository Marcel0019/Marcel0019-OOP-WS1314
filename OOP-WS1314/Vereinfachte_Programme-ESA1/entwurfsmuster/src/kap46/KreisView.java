/**
 * Beispiel fuer Observer-Muster: Beobachter2 (Kreisuhr).
 */
package kap46;

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

import kap35.Daten;

@SuppressWarnings("serial")
public class KreisView extends JFrame implements Observer {
	// Größe des Frames:
	private static final int BREITE = 800;
	private static final int HÖHE = 300;	

	// Groesse der Kreise:
	private static final int GROESSE_STUNDEN_KREIS = 200;
	private static final int SKALIERUNGSFAKTOR_STUNDEN = GROESSE_STUNDEN_KREIS/24;
	private static final int GROESSE_MINUTEN_KREIS = 150;
	private static final int SKALIERUNGSFAKTOR_MINUTEN = GROESSE_MINUTEN_KREIS/60;
	private static final int GROESSE_SEKUNDEN_KREIS = 100; // 50;
	private static final int SKALIERUNGSFAKTOR_SEKUNDEN = GROESSE_SEKUNDEN_KREIS/60;
	private static final int POS_X_STUNDEN = 210;
	private static final int POS_X_MINUTEN = 395;
	private static final int POS_X_SEKUNDEN = 530;
	private static final int POS_Y = 50+(GROESSE_STUNDEN_KREIS/2);
	private static final int POS_SCHRIFT = 30;
	private static final Color FARBE_STUNDE = Color.red;
	private static final Color FARBE_MINUTE = Color.green;
	private static final Color FARBE_SEKUNDE = Color.blue;

	private Daten daten;
	
	public KreisView(Observable subjekt) {
		subjekt.addObserver(this);
		
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
	private void zeichneKreis(Graphics g, int skalierung, int position, Color farbe, int zeit) {
		g.setColor(farbe);
		int durchmesser = zeit * skalierung, radius = durchmesser / 2;
		g.fillOval(position - radius, POS_Y - radius, durchmesser, durchmesser);
		g.drawString("" + zeit, position, POS_Y + (GROESSE_STUNDEN_KREIS/2) + POS_SCHRIFT);		
	}

	@Override // Frame
	public void paint(Graphics g) {
		if (daten == null) {
			return;
		}
		g.clearRect(0, 0, BREITE, HÖHE);
        zeichneKreisLinie(g, POS_X_STUNDEN, POS_Y, GROESSE_STUNDEN_KREIS/2, FARBE_STUNDE);
        zeichneKreisLinie(g, POS_X_MINUTEN, POS_Y, GROESSE_MINUTEN_KREIS/2, FARBE_MINUTE);
        zeichneKreisLinie(g, POS_X_SEKUNDEN, POS_Y, GROESSE_SEKUNDEN_KREIS/2, FARBE_SEKUNDE);
        // Uhrzeit als Kreise:
		zeichneKreis(g, SKALIERUNGSFAKTOR_STUNDEN, POS_X_STUNDEN, FARBE_STUNDE, daten.getHours());
		zeichneKreis(g, SKALIERUNGSFAKTOR_MINUTEN, POS_X_MINUTEN, FARBE_MINUTE, daten.getMinutes());
		zeichneKreis(g, SKALIERUNGSFAKTOR_SEKUNDEN, POS_X_SEKUNDEN, FARBE_SEKUNDE, daten.getSeconds());
	}
	@Override // Observer
	public void update(Observable subjekt, Object daten) {
		this.daten = (Daten)daten;
		repaint();
	}
	public static void main(String[] args) {
		Model subjekt = new Model();
		new KreisView(subjekt);
		subjekt.zeitmessung();
	}	
}
