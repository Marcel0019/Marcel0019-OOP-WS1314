/**
 * Schnittstelle fuer Models.
 */
package kap46;

import java.util.Observable;

/**
 * @author clecon
 * @version 1.0, 09/2011
 * @author solymosi Vereinfachung
 * @version 04/09/12
 *
 */
public abstract class IModel extends Observable {
	public abstract void änderung(String kommando);
}
