/**
 * Uhr (Uhrwerk).
 */
package kap11;

/**
 * @author clecon
 * @version 1.0, 08/2011
 * @author solymosi Vereinfachung
 * @version 04/09/12
 *
 */

import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.FlowLayout;
import java.awt.TextField;

import javax.swing.JFrame;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class Uhr extends JFrame {
	private static final int FREQUENZ = 1000;
	// Größe des Frames:
	private static final int BREITE = 400;
	private static final int HÖHE = 300;	
	
	public Uhr() {
		try {
			setTitle("Digitaluhr");
			setSize(BREITE, HÖHE);
			setDefaultCloseOperation(EXIT_ON_CLOSE);
	
			setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
			JLabel beschriftung = new JLabel("Uhrzeit: ");
			add(beschriftung);
			TextField uhrzeit = new TextField(10);
			uhrzeit.setEditable(false);
			add(uhrzeit);
			pack();
			setVisible(true);

			while (true) {
				uhrzeit.setText(new SimpleDateFormat("hh:mm:ss").format(new Date()));
				Thread.sleep(FREQUENZ);
			}
		} catch (InterruptedException e) {
			System.err.println("FEHLER in Warteschleife");
		}
	}	
	public static void main(String[] args) {
		new Uhr();
	}
}
