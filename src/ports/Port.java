
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE

package ports;
import java.util.*;
import containers.*;
import ships.*;
import interfaces.IPort;
/**
 * Implementation of Port class.Ports are the places where ships anchored.
 * Every port has an identity number (ID) and X,Y coordinates of its position.
 * Every ship has 7 arraylist which keep track of current (all type of) containers,basic containers,heavy containers
 * refrigerated containers, liquid containers, curent ships and history(has visited before) ships .
 * Port class implements IPort and Comparable interfaces.
 * 
 * @author Huzeyf
 *
 */
public class Port implements IPort,Comparable<Port>  {
	/**
	 * Identity number of port.
	 */
	final int ID;
	/**
	 * X position of port.
	 */
	final double X; 
	/**
	 * Y position of port.
	 */
	final double Y;
	/**
	 * Keeps track of current (all type of) containers.
	 */
	private ArrayList<Container> containers=new ArrayList<Container>();
	/**
	 * Keeps track of history (has visited before) of ships in the port.
	 */
	private ArrayList<Ship> history=new ArrayList<Ship>();
	/**
	 * Keeps track of current ships in the port.
	 */
	private ArrayList<Ship> current=new ArrayList<Ship>();
	/**
	 * Keeps track of BasicContainers in the port.
	 */
	private ArrayList<Container> bcont = new ArrayList<Container>();
	/**
	 * Keeps track of HeavyContainers in the port.
	 */
	private ArrayList<Container> hcont = new ArrayList<Container>();
	/**
	 * Keeps track of RefrigeratedContainers in the port.
	 */
	private ArrayList<Container> rcont = new ArrayList<Container>();
	/**
	 * Keeps track of LiquidContainers in the port.
	 */
	private ArrayList<Container> lcont = new ArrayList<Container>();
	/**
	 * Constructs a port with ID and position information.
	 * @param ID Identity number of port.
	 * @param X X position of port.
	 * @param Y Y position of port
	 */
	public Port(int ID,double X,double Y) {
		this.ID=ID;
		this.X=X;
		this.Y=Y;
	}
	/**
	 * Measures the distance between two ports.
	 * @param other the port that will be measured distance between of.
	 * @return distance Distance in km.
	 */
	public double getDistance(Port other) {		
		double distanceX=Math.abs(this.X-other.X);
		double distanceY=Math.abs(this.Y-other.Y);
		double distance=Math.hypot(distanceX,distanceY);
		return distance;
	}
	/**
	 * Adds ship to current ships of port.
	 * @param s incoming ship.
	 */
	
	public void incomingShip(Ship s) {
		if (this.current.contains(s) == false) {
			this.current.add(s);
			}	
	}
	/**
	 * Removes ship from current ships of port and adds it to the history.
	 * @param s outgoing ship.
	 */
	public void outgoingShip(Ship s) {
		if (this.current.contains(s)) {
			this.current.remove(s);
			}
		if (this.history.contains(s) == false) {
			this.history.add(s);
			}
	}
	/**
	 * Adds container to current containers of port.(Also adds container to relative type of arraylists.)
	 * @param cont The container which will be added to the port's inventory.
	 */
	public void addContainers(Container cont) {
		this.containers.add(cont);
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
	 * Removes container from current containers of port.(Also removes container from relative type of arraylists.)
	 * @param cont The container which will be removed from the port's inventory.
	 */
	public void removeContainers(Container cont) {
		this.containers.remove(cont);
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
			bcont.remove(cont);
		}
	}
	/**
	 * Getter method for identity number of port.
	 * @return this.ID Identity number of port.
	 */
	public int getID() {
		return this.ID;
	}
	/**
	 * Getter method for current ships of port.
	 * @return this.current Arraylist of current ships.
	 */
	public ArrayList<Ship> getCurrent(){
		return this.current;
	}
	/**
	 * Overrided toString method of Port class.
	 * Calls Collections.sort method to sort the arraylists by the identity number(ID) of the elements.
	 * Makes instances of Port class able to print as string.
	 * 
	 * @return str String format of instances of Port class.
	 */
	public String toString() {
		Collections.sort(bcont);
		Collections.sort(hcont);
		Collections.sort(rcont);
		Collections.sort(lcont);
		Collections.sort(this.current);
		String str=String.format("Port %d: (%.2f, %.2f)", this.ID,this.X,this.Y);
		if (bcont.size()!=0){
			str+="\n  BasicContainer: "+ bcont.toString().replaceAll("[\\[\\]]", "").replace(", ", " ") ;
		}
		if (hcont.size()!=0) {
			str+="\n  HeavyContainer: "+hcont.toString().replaceAll("[\\[\\]]", "").replace(", ", " ")  ;
			}
		if (rcont.size()!=0) {
			str+="\n  RefrigeratedContainer: "+rcont.toString().replaceAll("[\\[\\]]", "").replace(", ", " ")  ;
			}
		if (lcont.size()!=0) {
			str+="\n  LiquidContainer: "+lcont.toString().replaceAll("[\\[\\]]", "").replace(", ", " ") ;
			}
		str+="\n";
		for (Ship ship:this.current) {		
			str+=ship;
		}
		return str;
	}
	/**
	 * Overrides compareTo method for port class.
	 * Compares two ports with respect to their ID.
	 * @param port other port to compare.
	 * Returns negative value if ID of the port is smaller than other's ID;
	 * positive value if ID of the port is smaller than other's ID; 0 if it is equal. 
	 * @return result of this.ID-port.ID .
	 */
	@Override
    public int compareTo(Port port) {
        int compareID=((Port)port).ID;
        /* For Ascending order*/
        return this.ID-compareID;

    }
	/**
	 * Getter method for containers arraylist of port.
	 * @return this.containers
	 */
	public ArrayList<Container> getContainers(){
		return this.containers;
	}
	 
}



//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE

