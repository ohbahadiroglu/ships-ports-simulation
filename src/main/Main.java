
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE

package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

import containers.*;
import ports.Port;
import ships.Ship;

/**
 * Runnable main class.Reads input from a txt file,simulate the all actions
 *  and prints the neccesary output information to a txt file.
 * 
 * @author Huzeyf
 *
 */
public class Main {
	public static void main(String[] args) throws FileNotFoundException {
		
		//
		// Main receives two arguments: path to input file and path to output file.
		// You can assume that they will always be provided, so no need to check them.
		// Scanner and PrintStream are already defined for you.
		// Use them to read input and write output.
		// 
		// Good Luck!
		// 
		
		Scanner in = new Scanner(new File(args[0]));
		PrintStream out = new PrintStream(new File(args[1]));
		int N = in.nextInt();
		/**
		 * Stores instances of Containers in a arraylist.
		 */
		ArrayList<Container> conts=new ArrayList<Container>();
		/**
		 * Stores instances of Ships in a arraylist.
		 */
		ArrayList<Ship> ships=new ArrayList<Ship>();
		/**
		 * Stores instances of Ports in a arraylist.
		 */
		ArrayList<Port> ports=new ArrayList<Port>();
		/**
		 * ID counter of containers.
		 */
		int contIdCounter=0;
		/**
		 * ID counter of ports.
		 */
		int portIdCounter=0;
		/**
		 * ID counter of ships.
		 */
		int shipIdCounter=0;
		
		for ( int i=0 ; i<=N ; i++) {
			String line = in.nextLine();
			String[] linetokens= line.split(" ");
			
			if (linetokens[0].equals("1")){
							
				int portID=Integer.parseInt(linetokens[1]);
				int weight=Integer.parseInt(linetokens[2]);
			
				if (linetokens.length==3) {
					
					if (weight<=3000) {
						BasicContainer cont=new BasicContainer(contIdCounter,weight);
//						cont.setPortID(portID);
						cont.setTypeID(0);
						conts.add(cont);
						ports.get(portID).addContainers(cont);
						contIdCounter++;
						
						
					}
					else {
						HeavyContainer cont=new HeavyContainer(contIdCounter,weight);						
//						cont.setPortID(portID);
						cont.setTypeID(1);
						conts.add(cont);
						ports.get(portID).addContainers(cont);
						contIdCounter++;
					}
				}
				if (linetokens.length==4) {
					if (linetokens[3].equals("R")) {
						RefrigeratedContainer cont=new RefrigeratedContainer(contIdCounter,weight);
//						cont.setPortID(portID);
						cont.setTypeID(2);
						conts.add(cont);
						ports.get(portID).addContainers(cont);
						contIdCounter++;
					}
					if (linetokens[3].equals("L")) {
						LiquidContainer cont=new LiquidContainer(contIdCounter,weight);
//						cont.setPortID(portID);
						cont.setTypeID(3);
						conts.add(cont);
						ports.get(portID).addContainers(cont);
						contIdCounter++;
					}					
				}
			}
			if (linetokens[0].equals("2")) {
				int PortIdOfShip=Integer.parseInt(linetokens[1]);
				int maxWeightCapacity=Integer.parseInt(linetokens[2]);
				int maxNumAllConts=Integer.parseInt(linetokens[3]);
				int maxNumOfHeavyConts=Integer.parseInt(linetokens[4]);
				int maxNumOfRefConts=Integer.parseInt(linetokens[5]);
				int maxNumOfLiqConts=Integer.parseInt(linetokens[6]);
				double fuelConsPerKM=Double.parseDouble(linetokens[7]);
				Port portOfShip=ports.get(PortIdOfShip);
				Ship ship=new Ship(shipIdCounter,portOfShip,maxWeightCapacity,maxNumAllConts,maxNumOfHeavyConts,maxNumOfRefConts,maxNumOfLiqConts,fuelConsPerKM);
				ships.add(ship);
				portOfShip.incomingShip(ship);
				shipIdCounter++;
			}
			if (linetokens[0].equals("3")) {
				double X=Double.parseDouble(linetokens[1]);
				double Y=Double.parseDouble(linetokens[2]);
				Port port=new Port(portIdCounter, X, Y);
				ports.add(port);
				portIdCounter++;
			}
			if (linetokens[0].equals("4")) {
				int shipIDToBeLoaded=Integer.parseInt(linetokens[1]);
				int contIDToLoad=Integer.parseInt(linetokens[2]);
				Ship ship=ships.get(shipIDToBeLoaded);
				Container cont=conts.get(contIDToLoad);
				Port currentPort=ship.getCurrentPort();
				if (ship.load(cont)) {
					currentPort.removeContainers(cont);
					ship.addCurrentContainers(cont);
				}		
			}
			if (linetokens[0].equals("5")) {
				int shipIDToBeUnloaded=Integer.parseInt(linetokens[1]);
				int contIDToUnload=Integer.parseInt(linetokens[2]);
				Ship ship=ships.get(shipIDToBeUnloaded);
				Container cont=conts.get(contIDToUnload);
				Port currentPort=ship.getCurrentPort();
				if (ship.unLoad(cont)) {
					currentPort.addContainers(cont);
				}
			}
			if (linetokens[0].equals("6")) {
				int shipIDToTravel=Integer.parseInt(linetokens[1]);
				int destinationPortID=Integer.parseInt(linetokens[2]);
				Ship ship=ships.get(shipIDToTravel);
				Port currentPort=ship.getCurrentPort();
				Port destinationPort=ports.get(destinationPortID);
				if (ship.sailTo(destinationPort)) {
					currentPort.outgoingShip(ship);
					destinationPort.incomingShip(ship);
					ship.setCurrentPort(destinationPort);
				}
			}
			if (linetokens[0].equals("7")) {
				int shipIDToRefuel=Integer.parseInt(linetokens[1]);
				double newFuel=Double.parseDouble(linetokens[2]);
				Ship shipToRefuel=ships.get(shipIDToRefuel);
				shipToRefuel.reFuel(newFuel);
			}
			
		}
		
		for (Port port :ports) {out.print(port);}
	
		in.close();
		out.close();
		
	}
	
}



//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE

