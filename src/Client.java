package assigment_2;

public class Client {
	private int arrival;
	private int service;
	private int id;

	public int getArrival() {
		return arrival;
	}

	public void setArrival(int arrival) {
		this.arrival = arrival;
	}

	public int getService() {
		return service;
	}

	public void setService(int service) {
		this.service = service;
	}

	public int getID() {
		return id;
	}

	public void setID(int id) {
		this.id = id;
	}

	public Client(int id, int arrival, int service) {
		this.id = id;
		this.service = service;
		this.arrival = arrival;

	}
	

}
