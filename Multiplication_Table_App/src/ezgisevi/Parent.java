package ezgisevi;


import java.util.ArrayList;
import java.util.Random;


public class Parent implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String email;
	private String password;
	private String name;
	private ArrayList<Child> parentsChildren;
	private ArrayList<Exercise> exercises;
	
	
	
	public Parent(String email, String password, String name) {
		this.email = email;
		this.password = password;
		this.name = name;
		parentsChildren = new ArrayList<>();
		exercises = new ArrayList<>();
	}
	
	public ArrayList<Child> getParentsChildren() {
		return parentsChildren;
	}
	public void setParentsChildren(ArrayList<Child> parentsChildren) {
		this.parentsChildren = parentsChildren;
	}
	
	public ArrayList<Exercise> getExercises() {
		return exercises;
	}


	public void setExercises(ArrayList<Exercise> exercises) {
		this.exercises = exercises;
	}
	
	public void addExercise(Exercise exc) {
		this.exercises.add(exc);
		
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
	
	
}
