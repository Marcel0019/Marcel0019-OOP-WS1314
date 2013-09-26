/**
 * ControllerMVC fuer Start-/Stop-Button
 */
package kap46;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author clecon
 * @version 1.0, 09/2011
 * @author solymosi Vereinfachung
 * @version 04/09/12
 *
 */

public class Controller implements ActionListener {

	IModel model;
	IView view;
	public Controller(IModel model, IView view) {
		this.model = model;
		this.view = view;
	}
	
	public void actionPerformed(ActionEvent e) {
		String kommando = e.getActionCommand(); 
		System.out.println(kommando);
		view.geändert(kommando);
		model.änderung(kommando);
	}
}
