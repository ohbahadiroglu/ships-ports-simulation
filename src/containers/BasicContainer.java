
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE

package containers;
/**
 * One of the type of containers.Includes containers which is lighter than 3000kg.Extends Container class.
 *
 * @author Huzeyf
 *
 */
public class BasicContainer extends Container {
	/**
	 * Constructs an object of BasicContainer.(Calls constructor of superclass)
	 * @param ID Identity number of the container
	 * @param weight weight of the container
	 */
	public BasicContainer(int ID,int weight) {
		super(ID,weight);
		this.chargePerKM=2.50;
	}
}


//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE

