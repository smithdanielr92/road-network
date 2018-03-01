/**
 *  Project 01, RoadNetwork
 * @author Daniel Smith | CSE 274
 * 
 * All methods work properly with the exception of
 * areConnected() and degreesOfSeparation(). I was
 * unable to find a working algorithm to make them work.
 */

public class RoadNetwork {
	
	// instance variables
	private City[] cities;
	private int size;
	public static int DEFAULT_CAPACITY = 500;
	
	// constructor methods
	public RoadNetwork() {
		cities = new City[DEFAULT_CAPACITY];
		size = 0;
	}
	
	public RoadNetwork(int maxSize) {
		cities = new City[maxSize];
		size = 0;
	}
	
	// Adds a city to the state, i.e.
	public boolean addCity(String name) {
		if(containsCity(name)) {
			return false;
		}
		cities[size] = new City(name);
		size++;
		return true;
	}
	
	//Determines whether two cities are connected directly or through a chain of roads or not.
	public boolean areConnected(String city1, String city2) {
		if(containsCity(city1) && containsCity(city2)) {
			if(areDirectlyConnected(city1, city2)) {
				return true;
			}
		}
		return false;
	}
	
	//Determines whether two cities are connected directly via a road or not.
	public boolean areDirectlyConnected(String city1, String city2) {
		if(containsCity(city1) && containsCity(city2)) {
			return cities[cityIndex(city1)].isConnected(city2);
		}
		return false;
	}
	
	// If city1 and city2 are in the network, then connect them via a road.
	public boolean connect(String city1, String city2) {
		if(!areDirectlyConnected(city1, city2) && containsCity(city1) && containsCity(city2)) {
			cities[cityIndex(city1)].addConnectedCity(city2);
			cities[cityIndex(city2)].addConnectedCity(city1);
			return true;
		}
		return false;
	}
	
	// Returns true if there exists a city in the network with the given name, and false otherwise.
	public boolean containsCity(String name) {
		for(int i = 0; i < size; i++) {
			if(cities[i].getName().equals(name)) {
				return true;
			}
		}
		return false;
	}
	
	// currently inactive method.
	public int degreesOfSeparation(String city1, String city2) { 
		return -1;
	}
	
	// Removes city1 from city2's directly connected city list, and removes city2 from city1's directly connected list.
	public void disconnect(String city1, String city2) {
		if(containsCity(city1) && containsCity(city2)) {
			cities[cityIndex(city1)].disconnect(city2);
			cities[cityIndex(city2)].disconnect(city1);
		}
	}
	
	//Returns an (possibly empty) array of all cities in the network, sorted by name.
	public String[] getCities() {
		sortCities();
		String[] result = new String[size];
		for(int i = 0; i < size; i++) {
			result[i] = cities[i].getName();
		}
		return result;
	}
	
	// Gets a count of the number of cities in the network.
	public int getCityCount() {
		return size;
	}
	
	// Returns the directly connected cities of a particular city, sorted by name.
	public String[] getDirectlyConnectedCities(String name) {
		if(containsCity(name)) {
			return cities[cityIndex(name)].getDirectlyConnectedCities();
		}
		return new String[0];
	}
	
	// Removes city from the road network.
	public void removeCity(String name) {
		if(containsCity(name)) {
			for(int i = 0; i < size; i++) { //removed the city from all of the disconnected cities list.
				disconnect(cities[i].getName(), name);
			}
			cities[cityIndex(name)] = cities[size - 1];
			cities[size - 1]  = null;
			size--;
		}
	}
	
	// Returns a newline-separated list of cities, including a comma-separated list of each city's directly connected cities.
	public String toString() {
		String result = "";
		sortCities();
		for(int i = 0; i < size; i++) {
			result += (cities[i].getName() + "\n");
			cities[i].sortConnectedCities();
			String[] connectedCities = cities[i].getDirectlyConnectedCities();
			result += "(";
			for(int j = 0; j < connectedCities.length; j++) {
				result += connectedCities[j];
				if(j != connectedCities.length - 1) {
					result += ", ";
				}
			}
			result += ")\n";
		}
		
		return result;
	}
	
	// helper method that returns the index of a specified city.
	private int cityIndex(String name) {
		for(int i = 0; i < size; i++) {
			if(cities[i].getName().equals(name)) {
				return i;
			}
		}
		return -1;
	}
	
	// helper method that sorts the cities in the road network.
	private void sortCities() {
		for(int i = 0; i < size - 1; i++) {
			int minIndex = i;
			for(int j = i + 1; j < size; j++) {
				if(cities[i].getName().compareTo(cities[j].getName()) > 0) {
					minIndex = j;
				}
			}
			City temp = cities[i];
			cities[i] = cities[minIndex];
			cities[minIndex] = temp;
		}
	}
}
