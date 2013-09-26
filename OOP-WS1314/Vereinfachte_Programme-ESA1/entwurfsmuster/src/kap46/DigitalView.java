/**
 * Beispiel fuer Observer-Muster: Beobachter 1.
 */
package kap46;

/**
 * @author clecon
 * @version 1.0, 08/2011
 * @author solymosi merceron Vereinfachung
 * @version 04/09/12 06/09/13
 *
 */
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import kap35.Daten;

@SuppressWarnings("serial")
public class DigitalView extends JFrame implements IView {
	// Größe des Frames:
	private static final int BREITE = 400;
	private static final int HÖHE = 300;	

	private JTextField textField;
	private JButton buttonStart, buttonStopp, buttonMagic;
	
	public DigitalView(IModel subjekt) {
		subjekt.addObserver(this);		
		
		// Anzeige:
		setTitle("Digitaluhr");
		setSize(BREITE, HÖHE);

		setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
		super.add(new JLabel("Uhrzeit: "));
		textField = new JTextField(10);
		textField.setEditable(false);
		super.add(textField);

		buttonStopp = new JButton("Stopp");
		buttonStart = new JButton("Start");
		buttonMagic = new JButton("Magic");
		buttonStart.setEnabled(false);
		super.add(buttonStopp);
		super.add(buttonStart);
		super.add(buttonMagic);		

		ActionListener kontoller = new Controller(subjekt, this);
		buttonStart.addActionListener(kontoller);
		buttonStopp.addActionListener(kontoller);
		buttonMagic.addActionListener(kontoller);
		pack();
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	@Override // IViewMVC
	public void geändert(String s) {
		boolean stop = s.equals("Stopp"); 
		buttonStopp.setEnabled(!stop);
		buttonStart.setEnabled(stop);
	}
	@Override // Observer
	public void update(Observable o, Object arg) {
		System.out.println("TICK");
		Daten uhrzeit = (Daten)arg;
        textField.setText("" + uhrzeit.getHours()  + ":" + uhrzeit.getMinutes() + ":" + uhrzeit.getSeconds());
        }
	public static void main(String[] args) {
		Model subjekt = new Model();
		new DigitalView(subjekt);
		subjekt.zeitmessung();
	}
}
