package ezgisevi;


import java.util.ArrayList;

public class Child implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String email;
	private String password;
	private String name;
	private ArrayList<Exercise> exercises;
	private int excNumber = 0;
	
	public Child(String email, String pasword, String name) {
		this.email = email;
		this.password = pasword;
		this.name = name;
		exercises = new ArrayList<>();
	}
	
	public Child() {
		
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ArrayList<Exercise> getExercises() {
		return this.exercises;
	}

	public void setExercises(ArrayList<Exercise> exercises) {
		this.exercises = exercises;
	}

	public int getExcNumber() {
		return excNumber;
	}

	public void setExcNumber(int excNumber) {
		this.excNumber = excNumber;
	}

	@Override
	public String toString() {
		return email + " - " + name ;
	}
	
	
	
	
	
}
