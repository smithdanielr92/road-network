import java.util.ArrayList;
/**
 * Project 01 RoadNetwork
 * @author Daniel Smith | CSE 274
 */

public class City {
	private ArrayList<String> connectedCities;
	private String name;
	
	// constructor method.
	public City(String name) {
		this.name = name;
		connectedCities = new ArrayList<String>();
	}
	
	// returns the name of the city.
	public String getName() {
		return name;
	}
	
	// sets the name of the city.
	public void setName(String name) {
		this.name = name;
	}
	
	// Adds a city to the list of cities connected to this city.
	public void addConnectedCity(String name) {
		for(int i = 0; i < connectedCities.size(); i++) {
			if(connectedCities.get(i).equals(name)) {
				return;
			}
		}
		connectedCities.add(name);
	}
	
	// Disconnects a city from this city.
	public boolean disconnect(String name) {
		for(int i = 0; i < connectedCities.size(); i++) {
			if(connectedCities.get(i).equals(name)) {
				connectedCities.remove(i);
				return true;
			}
		}
		return false;
	}
	
	// Returns a list of the cities directly connected to this one.
	public String[] getDirectlyConnectedCities() {
		sortConnectedCities();
		String[] result = new String[connectedCities.size()];
		for(int i = 0; i < connectedCities.size(); i++) {
			result[i] = connectedCities.get(i);
		}
		return result;
	}
	
	// checks whether a city is connected to this city.
	public boolean isConnected(String name) {
		for(int i = 0; i < connectedCities.size(); i++) {
			if(connectedCities.get(i).equals(name)) {
				return true;
			}
		}
		return false;
	}
	
	// sorts the connectedCities arraylist.
	public void sortConnectedCities() {
		for(int i = 0; i < connectedCities.size() - 1; i++) {
			int minIndex = i;
			for(int j = i + 1; j < connectedCities.size(); j++) {
				if(connectedCities.get(i).compareTo(connectedCities.get(j)) > 0) {
					minIndex = j;
				}
			}
			String temp = connectedCities.get(i);
			connectedCities.set(i, connectedCities.get(minIndex));
			connectedCities.set(minIndex, temp);
		}
	}
}
