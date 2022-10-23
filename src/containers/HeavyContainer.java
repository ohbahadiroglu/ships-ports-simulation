
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE

package containers;
/**
 * One of the type of containers.Includes containers which is heavier than 3000kg 
 * and special type containers (Refrigerated and Liquid containers).
 * Extends Container class.
 *
 * @author Huzeyf
 *
 */

public class HeavyContainer extends Container {
	/**
	 * Constructs an instance of HeavyContainer.(Calls constructor of superclass)
	 * @param ID Identity number of the container
	 * @param weight weight of the container
	 */
	public HeavyContainer(int ID,int weight) {
		super(ID, weight);
		this.chargePerKM=3.00;

	}
}



//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE

