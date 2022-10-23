
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE

package containers;
/**
 * One of the special type of  heavy containers.Extends HeavyContainer class.
 *
 * @author Huzeyf
 *
 */

public class RefrigeratedContainer extends HeavyContainer{
	/**
	 * Constructs an instance of RefrigeratedContainer.(Calls constructor of superclass)
	 * @param ID Identity number of the container
	 * @param weight weight of the container
	 */
	public RefrigeratedContainer(int ID,int weight) {
		super(ID,weight);
		this.chargePerKM=5.00;
	}
}



//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE

