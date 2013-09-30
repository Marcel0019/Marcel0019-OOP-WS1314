/**
 * Uhr (Uhrwerk).
 */
package entwurfsmuster;

/**
 * @author clecon
 * @version 1.0, 08/2011
 *
 */

import java.util.Calendar;
import java.util.Date;
import java.awt.*;
import java.awt.event.*;
import java.text.*; // SimpleDateFormat
import java.util.*; // TimeZone

public class Uhr extends Frame {

	private Calendar kalender;
	private TextField textField;
	private TextField label; 
	private Date date = new Date();
	 
	public Uhr() {
		// Anzeige:
		setTitle("Digitaluhr");
		setSize(400,300);
		setVisible(true);
		erstelleOberflaeche();
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent ev) {
				dispose();
				System.exit(0);
			}
		});

		// Uhr:
		kalender = Calendar.getInstance();
	} // Konstruktor
	
	public void erstelleOberflaeche() {
		setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
		label = new TextField("Uhrzeit: ");
		label.setEditable(false);
		add(label);
		textField = new TextField(10);
		textField.setEditable(false);
		add(textField);
		pack();
	} // erstelleOberflaeche
	
	public int getStunde() {
		return kalender.get(Calendar.HOUR);
	} // getStunde
	
	public int getMinute() {
		return kalender.get(Calendar.MINUTE);
	} // getMinute
	
	public int getSekunde() {
		return kalender.get(Calendar.SECOND);
	} // getSekunde
	
	public int getHours() {
		date = new Date();
		return date.getHours();
	} // getHours
	
	public int getMinutes() {
		date = new Date();
		return date.getMinutes();
	} // getMinutes

	public int getSeconds() {
		date = new Date();
		return date.getSeconds();
	} // getSeconds
	
	public void anzeige() {
		String s;
		while (true) {
			// HH: 24-Stunden-Darstellung
			SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
			df.setTimeZone(TimeZone.getDefault()); // nicht mehr unbedingt
													// notwendig seit JDK 1.2
			// Formatierung zu String:
			s = df.format(date);
			// BEGIN TEST
			s = new String();
			s += new Integer(getHours()).toString();
			s += ":";
			s += new Integer(getMinutes()).toString();
			s += ":";
			s += new Integer(getSeconds()).toString();
			// ENDE TEST
			textField.setText(s);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				System.err.println("FEHLER in Warteschleife");
			}
		} // while
	} // anzeige
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Uhr uhr = new Uhr();
		uhr.anzeige();
	} // main

} // class Uhr
