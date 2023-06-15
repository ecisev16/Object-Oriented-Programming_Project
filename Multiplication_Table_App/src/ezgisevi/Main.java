package ezgisevi;



import java.io.FileOutputStream;
import java.io.IOException;
//import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;


public class Main {

	public static void main(String[] args) {
//		dosyayaYaz();
		App.main(args);
	}
	
	public static void dosyayaYaz() {
		try {
			String fileName = "veri.dat";
			ObjectOutputStream yazici = new ObjectOutputStream(new FileOutputStream(fileName));
			Integer parentCount = 1;
			Parent parent = new Parent("molly_weasley@gmail.com", "1234", "Molly");
			
			
			
			Question q1 = new Question(2, 6);
			Question q2 = new Question(3, 7);
			Question q3 = new Question(1, 8);
			ArrayList<Question> qA1 = new ArrayList<>();
			qA1.add(q1);
			qA1.add(q2);
			qA1.add(q3);
			
			ArrayList<Question> qA3 = new ArrayList<>();
			qA3.add(q1);
			qA3.add(q2);
			qA3.add(q3);
			
			ArrayList<Question> qA5 = new ArrayList<>();
			qA5.add(q1);
			qA5.add(q2);
			qA5.add(q3);
			
			ArrayList<Question> qA7 = new ArrayList<>();
			qA7.add(q1);
			qA7.add(q2);
			qA7.add(q3);
			
			ArrayList<Question> qA9 = new ArrayList<>();
			qA9.add(q1);
			qA9.add(q2);
			qA9.add(q3);
			
			ArrayList<Question> qA11 = new ArrayList<>();
			qA11.add(q1);
			qA11.add(q2);
			qA11.add(q3);
		
			

			Exercise exc1 = new Exercise(qA1, 3, "1)orta - 2 soru", 1);
			Exercise exc2 = new Exercise(qA3, 3, "1)orta - 2 soru", 1);
			Exercise exc3 = new Exercise(qA5, 3, "1)orta - 2 soru", 1);
			Exercise exc4 = new Exercise(qA7, 3, "1)orta - 2 soru", 1);
			Exercise exc5 = new Exercise(qA9, 3, "1)orta - 2 soru", 1);
			Exercise exc6 = new Exercise(qA11, 3, "1)orta - 2 soru", 1);
			
			
			ArrayList<Exercise> list = new ArrayList<>();
			list.add(exc1);
			
			ArrayList<Exercise> list2 = new ArrayList<>();
			list2.add(exc2);
			
			ArrayList<Exercise> list3 = new ArrayList<>();
			list3.add(exc3);
			
			ArrayList<Exercise> list4 = new ArrayList<>();
			list4.add(exc4);
			
			ArrayList<Exercise> list5 = new ArrayList<>();
			list5.add(exc5);
			
			ArrayList<Exercise> list6 = new ArrayList<>();
			list6.add(exc6);
			
			
			System.out.println("The information you have entered has been successfully saved in file " +fileName);
			
			Integer childNumber = 5;
			Child c1 = new Child("ron_weasley@gmail.com", "1234", "Ron");
			c1.setExercises(list2);
			Child c2 = new Child("fred_weasley@gmail.com", "5678", "Fred");
			c2.setExercises(list3);
			Child c3 = new Child("george_weasley@gmail.com", "9000", "George");
			c3.setExercises(list4);
			Child c4 = new Child("ginny_weasley@gmail.com", "0009", "Ginny");
			c4.setExercises(list5);
			Child c5 = new Child("charlie_weasley@gmail.com", "4470", "Charlie");
			c5.setExercises(list6);
			
			ArrayList<Child> cA10 = new ArrayList<>();
			cA10.add(c1);
			cA10.add(c2);
			cA10.add(c3);
			cA10.add(c4);
			cA10.add(c5);
			
			parent.setParentsChildren(cA10);
			parent.setExercises(list);
		
			yazici.writeObject(parentCount);
			yazici.writeObject(parent);
			yazici.writeObject(childNumber);
			yazici.writeObject(c1);
			yazici.writeObject(c2);
			yazici.writeObject(c3);
			yazici.writeObject(c4);
			yazici.writeObject(c5);
			
			
			
			yazici.close();
			System.out.println("The information you have entered has been successfully saved in file " +fileName);
			
		} 
		catch (IOException  e) {
			System.out.println("An exception has occured during writing to file.");
			e.printStackTrace();
		}
	}
	
	
}
