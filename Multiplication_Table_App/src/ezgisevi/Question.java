package ezgisevi;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Question implements java.io.Serializable{
	private int num;
	private int num2;
	private int result;
	private int answer;
	private boolean correct;
	private String time;

	

	public Question(int num, int num2) {
		this.num = num;
		this.num2 = num2;
		this.result = num * num2;
		correct = false;
	}
	
	public String getTime() {
		return time;
	}

	public void setTime(Long time) {
		// DateFormat oluşturma
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss.SSS");
        dateFormat.setTimeZone(java.util.TimeZone.getTimeZone("UTC"));

        // Tarih nesnesini oluşturma ve formatlama
        Date date = new Date(time);
        String formattedTime = dateFormat.format(date);
		this.time = formattedTime;
	}
	
	public int getAnswer() {
		return answer;
	}

	public void setAnswer(int answer) {
		this.answer = answer;
	}

	public boolean isCorrect() {
		return correct;
	}

	public void setCorrect(boolean correct) {
		this.correct = correct;
	}

	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public int getNum2() {
		return num2;
	}
	public void setNum2(int num2) {
		this.num2 = num2;
	}
	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}
	
	
	
}
