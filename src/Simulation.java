package assigment_2;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class Simulation extends Thread {
	private List<Queue> queues = new ArrayList<Queue>();
	private List<Client> clients = new ArrayList<Client>();
	private List<Thread> serversThread = new ArrayList<Thread>();
	private boolean running = true;
	private int maxQueues;
	private int maxClients;
	private int time;
    private int minArrival;
    private int maxArrival;
    private int minWait;
    private int maxWait;   
    private int media;

    public  Simulation(int maxC) {		
		this.maxQueues = maxC; 
		for(int i = 1; i <= maxC; i++) {
			Queue c = new Queue(i);
			Thread serverThread = new Thread(c);
			serversThread.add(serverThread);
			queues.add(c);
		}		
	}
    
	public List<Queue> getQueues(){
		return this.queues;
	}
	
	public int getNrClients(){
		return this.maxClients;
	}
	
	public int getNrQueues() {
		return this.maxQueues;
	}
	
	public List<Client> getListClients(){
		return this.clients;
	}
    
    public void setMaxQueues(int m) {
    	this.maxQueues= m;
    }
    
    public void setMaxClients(int m) {
    	this.maxClients = m;
    }
	
    public void setTime(int m) {
    	this.time = m;
    }
    
    public void setArrivals(int i, int m) {
    	this.maxArrival = m;
    	this.minArrival = i;
    }
    
    public void setWaits(int i, int m) {
    	this.maxWait = m;
    	this.minWait = i;
    }
       
	public void setTasksTimer(int minTarr, int maxTarr, int minTwait, int maxTwait) {	
		this.minArrival = minTarr;
		this.maxArrival = maxTarr;
		this.minWait = minTwait;
		this.maxWait = maxTwait;
	}
    
	public void afisClients() {
		for(Client item : this.clients) {
			 System.out.print("(" + item.getID() +", "+item.getArrival() + ", " + item.getService() + ") ");
		}
	}
	
	public int strategy() {
		int aux = 0;
		int minWtime = queues.get(0).getWaiting(); 		
		for(int i = 0; i < maxQueues; i++)			
			if(queues.get(i).getWaiting() <= minWtime){
				minWtime = queues.get(i).getWaiting();
				aux = i;
			}		
		return aux;
	}
    
	
	public void generateClients() {
		Random random = new Random();	
		for(int i=0; i < maxClients; i++) {			
			int timeBetweenArrivals = random.nextInt((maxArrival - minArrival) + 1) + minArrival;
			int ServingTime = random.nextInt((maxWait - minWait) + 1) + minWait;			
			Client c = new Client(i + 1, timeBetweenArrivals, ServingTime);	
			media += ServingTime;
			clients.add(c);			
		}
		media = media / maxClients;
		System.out.print("\n" + "The clients participating in the simulation are:\n");
		for(Client item : clients) {
			 System.out.print("(" + item.getID() +", "+item.getArrival() + ", " + item.getService() + ") ");
		}
		System.out.print("\n\n");
		clients.sort(Comparator.comparing(Client::getArrival));
	}
	
	public void printQueues() {	
		int i = 0;
		for(Queue q : queues) {	
			i++;
			System.out.print("  Queue no. " + i + ": ");
			if(q.getWaiting() == 0)
				System.out.print(" is closed!");
			else
				for(Client item : q.getList())
		       	 	System.out.print("(" + item.getID() +", "+item.getArrival() + ", " + item.getService() + ") ");	
			System.out.print("\n");
		} 
		System.out.print("\n");
	}
	
	
	@SuppressWarnings("deprecation")
	public void startSim() {
		for(int i = 0 ; i < maxQueues; ++i) {
			queues.get(i).startQ();
		}
		if(running  == true) {
			start();
		}
		else
			resume();
	}
	
	
	
	
	public void run() {
		
		generateClients();							
				
		int sec = 0, poz = 0;
		while(sec <= time) {
			try {
					System.out.print("\n");
					System.out.print("Time: " + sec + "\n");
					
					if(poz < maxClients) {
						while(clients.get(poz).getArrival() == sec) {
							Client c = clients.get(poz);
							int queue = strategy();	
							int queue1 = queue + 1;
							System.out.print("---->(" + c.getID() + ", " + c.getArrival() + ", " + c.getService() + ")" + " added to the queue no. " + queue1 + "\n");
							queues.get(queue).addClient(c);	
							if(poz + 1 < maxClients)
								poz++;
							else
								break;
						}
					}
					printQueues();
					sec++;
					sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Average waiting time: " + media + " seconds!");
		
	}


}
