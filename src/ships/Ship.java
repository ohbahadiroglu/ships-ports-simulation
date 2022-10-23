
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE

package ships;
import interfaces.IShip;
import ports.*;
import containers.*;
import java.util.*;
/**
 * Implementation of ship class.
 * Every ship has an ID,fuel,info of fuel consumption per km,current anchored port info and lots of restrictions.
 * These are max number of all containers,max number heavy containers
 * max num of refrigerated containers and max num of liquid containers and total weight capacity.
 * Every ship has 5 arraylist which keep track of current (all type of) containers,basic containers heavycontainers
 * refrigerated containers and liquid containers.
 * Ship class implements IShip and Comparable interfaces.
 * 
 * @author Huzeyf
 *
 */
public class Ship implements IShip,Comparable<Ship>  {
	/**
	 * Identity number of ships.
	 */
	final int ID;
	/**
	 * Fuel amount of ships.
	 */
	private double fuel = 0;
	/**
	 * Port of ship currently anchored.
	 */
	private Port currentPort;
	/**
	 * Total weight amount (kg) that ship can carry.
	 */
	final int totalWeightCapacity;
	/**
	 * Maximum number of all containers ship can carry.
	 */
	final int maxNumberOfAllContainers;
	/**
	 * Maximum number of HeavyContainers ship can carry.
	 */
	final int maxNumberOfHeavyContainers;
	/**
	 * Maximum number of RefrigeratedContainers ship can carry.
	 */
	final int maxNumberOfRefrigeratedContainers;
	/**
	 * Maximum number of LiquidContainers ship can carry.
	 */
	final int maxNumberOfLiquidContainers;
	/**
	 *	Fuel consumption of ship per kilometers.
	 */
	final double fuelConsumptionPerKM;
	/**
	 * The total weight of ship's current containers.
	 */
	private int totalContWeight = 0;
	/**
	 * Stores all containers in the ship as an arraylist.
	 */
	private ArrayList<Container> currentContainers = new ArrayList<Container>();
	/**
	 * Stores BasicContainers in the ship as an arraylist.
	 */
	private ArrayList<Container> bcont = new ArrayList<Container>();
	/**
	 * Stores HeavyContainers in the ship as an arraylist.
	 */
	private ArrayList<Container> hcont = new ArrayList<Container>();
	/**
	 * Stores RefrigeratedContainers in the ship as an arraylist.
	 */
	private ArrayList<Container> rcont = new ArrayList<Container>();
	/**
	 * Stores LiquidContainers in the ship as an arraylist.
	 */
	private ArrayList<Container> lcont = new ArrayList<Container>();
	/**
	 * Constructs a Ship instance.Ships ID,port .etc informations given as parameters.
	 * @param ID ID of the ship
	 * @param p Port of ship.
	 * @param totalWeightCapacity
	 * @param maxNumberOfAllContainers
	 * @param maxNumberOfHeavyContainers
	 * @param maxNumberOfRefrigeratedContainers
	 * @param maxNumberOfLiquidContainers
	 * @param fuelConsumptionPerKM
	 */
	
	
	public Ship(int ID, Port p, int totalWeightCapacity, 
			int maxNumberOfAllContainers, 
			int maxNumberOfHeavyContainers, 
			int maxNumberOfRefrigeratedContainers, 
			int maxNumberOfLiquidContainers,
			double fuelConsumptionPerKM)  {
		this.ID=ID;
		this.currentPort=p;
		this.totalWeightCapacity=totalWeightCapacity;
		this.maxNumberOfAllContainers=maxNumberOfAllContainers;
		this.maxNumberOfHeavyContainers=maxNumberOfHeavyContainers;
		this.maxNumberOfLiquidContainers=maxNumberOfLiquidContainers;
		this.maxNumberOfRefrigeratedContainers=maxNumberOfRefrigeratedContainers;		
		this.fuelConsumptionPerKM=(fuelConsumptionPerKM);
	}
	/**
	 * Getter method of Ship.currentContainers .
	 * @return currentContainers of ship
	 */
	public ArrayList<Container> getCurrentContainers(){
		return this.currentContainers;
	}
	/**
	 * Adder method of Ship.currentContainers . 
	 * Also adds the containers to the arraylists appropriate type of containers .
	 * BasicContainerd to bcont,HeavyContainers to hcont...
	 * @param cont Container will be added .
	 */
	public void addCurrentContainers(Container cont) {
		this.currentContainers.add(cont);
		this.totalContWeight+=cont.getWeight();
		if (cont instanceof containers.HeavyContainer) { 		
			if (cont instanceof containers.RefrigeratedContainer) { 
				this.rcont.add(cont);
				}
			else if (cont instanceof containers.LiquidContainer) {
				this.lcont.add(cont);
				}
			else {
				this.hcont.add(cont);
				}
			}
		else {
			this.bcont.add(cont);
		}
	}
	/**
	 * Remover method of Ship.currentContainers . 
	 * Also removes the containers from the arraylists appropriate type of containers .
	 * BasicContainerd from bcont,HeavyContainers from hcont...
	 * @param cont Container will be removed.
	 */
	public void removeCurrentContainers(Container cont) {
		this.currentContainers.remove(cont);
		if (cont instanceof containers.HeavyContainer) { 		
			if (cont instanceof containers.RefrigeratedContainer) { 
				this.rcont.remove(cont);
				}
			else if (cont instanceof containers.LiquidContainer) {
				this.lcont.remove(cont);
				}
			else {
				this.hcont.remove(cont);
				}
			}
		else {
			this.bcont.remove(cont);
		}
	}
	/**
	 * Checks if the ship is able to sail to the port given as p parameter of method. 
	 * Call getDistance method from port class.
	 * @see getDistance 
	 * @return true if the ship has enough amount of fuel to sail to the other port;false otherwise.
	 */
	public boolean sailTo(Port p) {
		double contFuelNeed=0;
		for(Container cont:this.currentContainers) {
			contFuelNeed+=cont.consumption();
			}
		double distance = this.currentPort.getDistance(p);	
		double totalFuelNeed= ((this.fuelConsumptionPerKM+contFuelNeed)*distance);
		if (totalFuelNeed<=this.fuel) {
			this.fuel-=totalFuelNeed;
			return true;
		}else {
			return false;
		}
	}
	/**
	 * Adds fuel of the ship current fuel.
	 */
	public void reFuel(double newFuel) {
		this.fuel+=newFuel;
		
		
	}
	/**
	 * Checks if the ship is able to be loaded with new container.
	 * Decides type of the container and checks if it is able to load according to ship's restrictions.
	 * @return true if is able to load,false otherwise. 
	 * 
	 * @param cont Container to load to ship.
	 */
	public boolean load(Container cont) {
		if(this.getCurrentPort().getContainers().contains(cont)) {
			if (this.currentContainers.size()<this.maxNumberOfAllContainers) {
				if (this.totalContWeight+cont.getWeight()<=this.totalWeightCapacity){
					if (cont instanceof containers.HeavyContainer) {
						if ( (this.hcont.size()+this.rcont.size()+this.lcont.size() ) < this.maxNumberOfHeavyContainers) {
							if (cont instanceof containers.RefrigeratedContainer) {
								if (this.rcont.size()<this.maxNumberOfRefrigeratedContainers) {
									return true;
								}else {
									return false;
								}
							}
							if (cont instanceof containers.LiquidContainer) {
								if (this.lcont.size()<this.maxNumberOfLiquidContainers) {
									return true;
								}else {
									return false;
								}
							}
						}else {
							return false;
						}
						return true;
					}
					return true;
				}else {
					return false;
				}
			}
			return false;
		}
		return false;
	}
	/**
	 * Checks if the container is able to unload.
	 * @param cont Container to unload from ship.
	 * @return true if the container is in the ship;false otherwise.
	 */
	public boolean unLoad(Container cont) {
		if (this.currentContainers.contains(cont))   {
			this.removeCurrentContainers(cont);
			this.totalContWeight-=cont.getWeight();
			return true;			
		}
		return false;
	}
	/**
	 * Getter method for Ship.currentPort.
	 * @return this.currentPort
	 */
	public Port getCurrentPort() {
		return currentPort;
	}
	/**
	 * Setter method for Ship.currentPort.
	 * 
	 */
	public void setCurrentPort(Port currentPort) {
		this.currentPort = currentPort;
	}
	/**
	 * Overrided toString method of Ship class.
	 * Calls Collections.sort method to sort the arraylists by the identity number(ID) of the elements.
	 * Makes instances of Ship class able to print as string.
	 * 
	 * @return str String format of instances of Ship class.
	 */
	@Override
	public String toString() {
		Collections.sort(bcont);
		Collections.sort(hcont);
		Collections.sort(rcont);
		Collections.sort(lcont);
		
		String str=String.format("  Ship %s: %.2f", this.ID,this.fuel);
		if (bcont.size()!=0){
			str+="\n    BasicContainer: "+ bcont.toString().replaceAll("[\\[\\]]", "").replace(", ", " ") ;
		}
		if (hcont.size()!=0) {
			str+="\n    HeavyContainer: "+hcont.toString().replaceAll("[\\[\\]]", "").replace(", ", " ")  ;
			}
		if (rcont.size()!=0) {
			str+="\n    RefrigeratedContainer: "+rcont.toString().replaceAll("[\\[\\]]", "").replace(", ", " ")  ;
			}
		if (lcont.size()!=0) {
			str+="\n    LiquidContainer: "+lcont.toString().replaceAll("[\\[\\]]", "").replace(", ", " ") ;
			}
		str+="\n";

		return str;
	}
	/**
	 * Overrides compareTo method for Ship class.
	 * Compares two ships with respect to their ID.
	 * @param ship other ship to compare.
	 * Returns negative value if ID of the ship is smaller than other's ID;
	 * positive value if ID of the ship is smaller than other's ID; 0 if it is equal. 
	 * @return result of this.ID-ship.ID .
	 */
	@Override
    public int compareTo(Ship ship) {
        int compareID=((Ship)ship).ID;
        /* For Ascending order*/
        return this.ID-compareID;

    }
	/**
	 * Getter method for ships' ID.
	 * @return this.ID
	 */
	public int getID() {
		return this.ID;
	}
	
}



//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE

