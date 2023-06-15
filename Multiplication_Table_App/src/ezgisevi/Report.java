package ezgisevi;


import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class Report {
    public static void reportExercise(Child ch) {
        String filePath = "data.csv";
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        ArrayList<Question> Qs = new ArrayList<>(); 
        
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePath, true), "UTF-8"))) {
			
			writer.write(ch.getEmail());
			writer.write(";");
			
			writer.write(ch.getName());
			writer.write(";");
			
			writer.write(ch.getExercises().get(App.excNo).getName());
			writer.write(";");
			
			String info = decimalFormat.format(ch.getExercises().get(App.excNo).getAccuracyRate());
			writer.write(info);
			writer.write(";");
			
			info = decimalFormat.format(ch.getExercises().get(App.excNo).getSpeedScore());
			writer.write(info);
			writer.write(";");
			
			info = "";
			info = info + ch.getExercises().get(App.excNo).getDate();
			writer.write(info);
			writer.write(";");
			
			writer.write(ch.getExercises().get(App.excNo).getDuration());
			writer.write(";");
			
			info = "";
			info = info + ch.getExercises().get(App.excNo).getqNumber();
			writer.write(info);
			writer.write(";");
			
			writer.write(" ");
			writer.write(";");
			
			Qs = ch.getExercises().get(App.excNo).getQuestions();
			
			for (Question qu : Qs) {
				info = "";
				info = info + qu.getNum() + "x" + qu.getNum2();
				writer.write(info);
				writer.write(";");
			}
			writer.newLine();
			
			for(int i=0; i<9; i++) {
				writer.write(" ");
				writer.write(";");
			}
			
			for (Question qu : Qs) {
				if(qu.isCorrect()) {
					writer.write("Dogru");
					writer.write(";");
				}else {
					writer.write("Yanlis");
					writer.write(";");
				}
			}
			
			writer.newLine();
			
			for(int i=0; i<9; i++) {
				writer.write(" ");
				writer.write(";");
			}
			
			for (Question qu : Qs) {
				writer.write(qu.getTime());
				writer.write(";");
			}
			writer.newLine();
			
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
