package ezgisevi;


import java.util.ArrayList;
//import java.util.Random;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

public class Exercise implements java.io.Serializable{
	
	private static final long serialVersionUID = 1L;
	private ArrayList<Question> questions;
	private int qNumber;
	private String name;
	private int number;
	private int beginTime;
	private String duration;
	private float speedScore;
	private float accuracyRate;
	private static int currExcNum = 1;
	private LocalDateTime Date;
	

	public Exercise(ArrayList<Question> questions, int qNumber, String name, int number) {
		this.questions = questions;
		this.qNumber = qNumber;
		this.name = name;
		this.number = number;
		accuracyRate = 0;
	}
	
	public Exercise() {
		
	}
	
	

	public LocalDateTime getDate() {
		return Date;
	}



	public void setDate(LocalDateTime date) {
		Date = date;
	}



	public String getDuration() {
		return duration;
	}



	public void setDuration(long milliseconds) {
		
        // DateFormat oluşturma
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss.SSS");
        dateFormat.setTimeZone(java.util.TimeZone.getTimeZone("UTC"));

        // Tarih nesnesini oluşturma ve formatlama
        Date date = new Date(milliseconds);
        String formattedTime = dateFormat.format(date);
        this.duration = formattedTime;
	}



	public ArrayList<Question> getQuestions() {
		return questions;
	}
	public void setQuestions(ArrayList<Question> questions) {
		this.questions = questions;
	}
	public int getqNumber() {
		return qNumber;
	}
	public void setqNumber(int qNumber) {
		this.qNumber = qNumber;
	}
	public float getAccuracyRate() {
		return accuracyRate;
	}

	public void setAccuracyRate() {   // soruların yuzde kacini dogru cevaplamis onu belirler
		int i = 0;
		for (Question qu : questions) {
			if(qu.isCorrect()) {
				i++;
			}
		}
		this.accuracyRate = (float)i*100 / questions.size();
		
	}
	
	public static int getCurrExcNum() {
		return currExcNum;
	}

	public static void setCurrExcNum(int currExcNum) {
		Exercise.currExcNum = currExcNum;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(int beginTime) {
		this.beginTime = beginTime;
	}

	public float getSpeedScore() {
		return speedScore;
	}

	public void setSpeedScore(long milliSecond) {  // egzersizdeki soru sayisina ve geçirilen süreye gore bir formul olusturulmustur
		float second = (float) milliSecond/1000;
		this.speedScore = 1*100*getqNumber()/second;
	}

	@Override
	public String toString() {
		return name;
	}

	public void setAccuracyRate(int i) {
		this.accuracyRate = i;
		
	}
	
	
	
	
}
