package standalone.model;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.TimerTask;


public class Subject  extends TimerTask {

	private static Subject  subject= null;
	
	private Subject(){
		
	}
	
	private static ArrayList<Observer> queue;
	public static Subject getSubject(){
		if(subject==null){
			subject = new Subject();
			 queue = new ArrayList<Observer>();				 
				try{
					Connection con = mySqlConnection.getConnection();
					String query = "Select * from stocks.companies";
					PreparedStatement pstmt = con.prepareStatement(query);
					ResultSet rs = pstmt.executeQuery();
					while(rs.next()){
						String company_code = rs.getString("company_code");
						String company_name = rs.getString("company_name");
						queue.add(new Observer(company_code,company_name));				
					}			
				}
				catch(Exception e){
					System.out.println(e.getMessage());
				}							
		}
		
		return subject;
	}
	
	/*
	 * Registers obj to Subject
	 */
	public void register(Observer obj){
		queue.add(obj);
	};
	
	/*
	 * Unregisters obj from Subject
	 */
	public void unregister(Observer obj){
		int i;
		for(i=0; i<queue.size(); i++){
			if(queue.get(i).getCompany_code().equals(obj.getCompany_code())){
				break;
			}
		}
		queue.remove(i);
	};
	
	/*
	 * notifies all observers
	 */
	public void notifyObservers(){
		for(Observer currentObserver: queue){
			currentObserver.addSingleStockToDB();
		}		
	};
	
	/*
	 * (non-Javadoc)
	 * @see java.util.TimerTask#run()
	 * overrides Timerrun run. and is called by timer after every five minutes
	 */
	public void run(){
		notifyObservers();
	}
		
}
