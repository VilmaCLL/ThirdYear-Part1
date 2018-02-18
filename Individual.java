import java.util.Date;

/**
 * 
 * @author Catalina Hernández Morales - 2015295
 * @author Vilma López Lemoine - 2015210
 * 
 * This class creates the object that will represent each applicant, with its attributes and getter/setter methods.
 * Each node of the list will store one individual, with these attributes.
 */



public class Individual {
	
	//Attributes for this object.
	public int id;
	public String first_name;
	public String last_name;
	public Date date;
	public String passport;
	public int priority;
	public static int counter =0; //Counter to auto-generate the ID.
	public Individual nextInLine;//References the next person on the queue.
	public Individual prevInLine;//References the person ahead on the queue.
	
	

	public Individual (String first_name, String last_name, String passport, int priority){
		
		//When initializing the object, first start with the counter, so it assigns the ID and auto-increments.
		counter ++;
		this.id = counter;
		this.date = new Date();
		this.first_name = first_name;
		this.last_name = last_name;
		this.passport = passport;
		this.priority = priority;
		this.nextInLine = null;
		this.prevInLine = null;
		
		
	}



			public int getId() {
				return id;
			}
		
		
		
			public void setId(int id) {
				this.id = id;
			}
		
		
		
			public String getFirst_name() {
				return first_name;
			}
		
		
		
			public void setFirst_name(String first_name) {
				this.first_name = first_name;
			}
		
		
		
			public String getLast_name() {
				return last_name;
			}
		
		
		
			public void setLast_name(String last_name) {
				this.last_name = last_name;
			}
		
		
		
			public Date getDate() {
				return date;
			}
		
		
		
			public void setDate(Date date) {
				this.date = date;
			}
		
		
		
			public String getPassport() {
				return passport;
			}
		
		
		
			public void setPassport(String passport) {
				this.passport = passport;
			}
		
		
		
			public int getPriority() {
				return priority;
			}
		
		
		
			public void setPriority(int priority) {
				this.priority = priority;
			}
}
		
		
	