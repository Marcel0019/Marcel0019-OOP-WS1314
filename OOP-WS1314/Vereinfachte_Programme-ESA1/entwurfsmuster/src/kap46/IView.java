/**
 * Schnittstelle fuer Views
 */
package kap46;

/**
 * @author clecon
 * @version 1.0, 09/2011
 * @author solymosi merceron Vereinfachung
 * @version 04/09/12 06/09/13
 *
 */
public interface IView extends Observer{	
	public void geändert(String s);
}
