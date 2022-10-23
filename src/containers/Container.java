
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE

package containers;
/**
 * 
 * Implementation of Container class.Every contianer object has its own ID,weight.
 * Container class has two subclasses which are BasicContainer and HeavyContainer.
 * HeavyContainer class has two subclasses which are LiquidContainer and RefrigeratedContainer.
 * These are special type of HeavyContainers , the rule of weight greater than 3000kg is ignored for these.
 * Container class impleements Comparable interface in order to override compareTo method. 
 * 
 * @author Huzeyf
 * 
 */
public abstract class Container implements Comparable<Container> {
	/**
	 * Identification (ID) of containers.
	 */
	final int ID;
	/**
	 * Weight of container. (kg)
	 */
	final int weight;
//	/**
//	 * The identity number of port where container is in currently. 
//	 */
//	private int portID;
	/**
	 * The identity number of container's types.
	 */
	private int typeID;
	/**
	 * Every container has a charge per km according to their types.
	 */
	public double chargePerKM;
	/**
	 * Construct a container object with ID and weight
	 *  but the reason that it is a abstract class,Container 
	 *  constructor can't be called directly.
	 *  @param ID ID of the container.
	 *  @param weight Weight of the container.
	 */
	public Container(int ID,int weight) {
		this.ID=ID;
		this.weight=weight;
	}
	/**
	 * This method returns consumption of containers.
	 * @return (chargePerKM *weight) of the cont
	 */
	public double consumption() {
		return ((this.chargePerKM)*(this.weight));
	}
	/**
	 * Checks if two containers is identical or not.
	 * @param other The other container to compare.
	 * @return true if the containers are identical;false otherwise.
	 */
	public boolean equals(Container other) {
		if  ((this.typeID==other.typeID) && (this.ID==other.ID) && (this.weight==other.weight)){
			return true;
		}
		return false;
	}
	/**
	 * Returns typeID of containers.(ie.Basic>0,Heavy>1...)
	 * @return typeID 
	 */
	public int getTypeID() {
		return this.typeID;
	}
//	/**
//	 * Returns the identity number of port where container is in currently. 
//	 * @return portID 
//	 */
//	public int getPortID() {
//		return this.portID;
//	}
	/**
	 * Sets typeID of containers.
	 * @param typeID
	 */
	public void setTypeID(int typeID) {
		this.typeID=typeID;
	}
//	/**
//	 *  Sets portID of containers.
//	 * @param portID
//	 */
//	public void setPortID(int portID) {
//		this.portID=portID;
//	}
	/**
	 *Overrides toString method for container class.
	 *Returns ID of the container as string.
	 *@return Value of this.ID as a string.
	 */
	@Override
	public String toString() {
		return String.valueOf(this.ID);
	}
	/**
	 * Overrides compareTo method for container class.
	 * Compares two containers with respect to their ID.
	 * @param cont other container to compare.
	 * Returns negative value if ID of the container is smaller than other's ID;
	 * positive value if ID of the container is smaller than other's ID; 0 if it is equal. 
	 * @return result of this.ID-cont.ID.
	 */
	@Override
    public int compareTo(Container cont) {
        int compareID=((Container)cont).ID;
        /* For Ascending order*/
        return this.ID-compareID;

    }
	/**
	 * Getter method for containers' ID.
	 * @return this.ID
	 */
	public int getID() {
		return this.ID;
	}
	/**
	 * Getter method for containers' weight.
	 * @return this.weight
	 */
	public int getWeight() {
		return this.weight;
	}
}



//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE

