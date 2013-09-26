/**
 * Beispiel fuer Observer-Muster: Beobachter 1.
 */
package kap35;

/**
 * @author clecon
 * @version 1.0, 08/2011
 * @author solymosi Vereinfachung
 * @version 04/09/12
 *
 */
import java.util.Observable;
import java.util.Observer;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.FlowLayout;

@SuppressWarnings("serial")
public class DigitalObserver extends JFrame implements Observer {
	// Größe des Frames:
	private static final int BREITE = 400;
	private static final int HOEHE = 300;	

	private Observable subjekt;
	private Daten uhrzeit;
	private JTextField textField;
	
	public DigitalObserver(Observable subjekt) {
		this.subjekt = subjekt;
		this.subjekt.addObserver(this);
		
		// Anzeige:
		setTitle("Digitaluhr");
		setSize(BREITE ,HOEHE);

		setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
		add(new JLabel("Uhrzeit: "));
		textField = new JTextField(10);
		textField.setEditable(false);
		add(textField);
		pack();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	@Override
	public void update(Observable o, Object arg) {
		System.out.println("TICK");
		uhrzeit = (Daten)arg;
        textField.setText("" + uhrzeit.getHours()  + ":" + uhrzeit.getMinutes() + ":" + uhrzeit.getSeconds());
        }
	public static void main(String[] args) {
		UhrObservable subjekt = new UhrObservable();
		new DigitalObserver(subjekt);
		subjekt.zeitmessung();
	}
}
