package assigment_2;

import java.util.ArrayList;
import java.util.List;

public class Queue extends Thread{
	private List<Client> clients;
	private int id;
	private static int clientss = 0;
	private int waiting = 0;
	private boolean running = true;

	public List<Client> getList() {
		return this.clients;
	}

	public int getWaiting() {
		return waiting;
	}

	public Queue(int id) {
		clients = new ArrayList<Client>();
		this.id = id;
	}
	@SuppressWarnings("deprecation")
	public void startQ() {
		if(running == true) {
			start();
		}else {
			resume();
		}
	}
	
	
	
	public void run() {
			try {
				while(true) {
					if(clients.size() > 0) {
						Client c = clients.get(0);
						int x = c.getService();
						sleep(x * 1000);													
						waiting -= x;
						clientss--;
						clients.remove(0);
						System.out.print("---->(" + c.getID() + ", " + c.getArrival() + ", " + c.getService() + ")" + " has left the shop!\n");
						
					}
					else {						
						sleep(1000);
					}
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	}
	
	
	
	
	public synchronized void addClient( Client c ) throws InterruptedException{
		clients.add(c);
		waiting += c.getService();
		notifyAll();
	} 
}
