package ezgisevi;


import java.awt.EventQueue;
import java.time.LocalDateTime;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

import javax.swing.JPasswordField;
import java.awt.Font;
//import javax.swing.JList;
//import javax.swing.JSpinner;
import javax.swing.JTable;
//import javax.swing.JScrollBar;
//import javax.swing.JTextArea;
import javax.swing.JComboBox;
//import javax.swing.JToggleButton;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;


public class App {
	static int check;
	static int excNo;
	JPanel solvePage;
	static Parent parent;
	static int childIndex;
	static Child theChild;
	static ArrayList<Child>child = new ArrayList<>();
	static Exercise currentExcersize = new Exercise();
	static Exercise currentChExcersize = new Exercise();
	static int childNumber = 0;
	private static long questionTime;
	private static long totalTime;
	private static int done = 0; 
	static DefaultTableModel model;
	JButton seeResultsButton;
	JButton addButton ;
	JPanel parentHomePage;
	private JFrame frame;
	private JPanel parentLoginPage;
	private JTextField emailTextField;
	private JPasswordField passwordField;
	private JPanel pickUserPage;
	private JButton btnNewButton_1;
	private JButton btnNewButton_2;
	private JTextField c_emailTextField;
	private JPanel setExcersizePage;
	private JLabel lblNewLabel_8;
	private JLabel lblNewLabel_9;
	private JLabel lblNewLabel_12;
	private JLabel lblNewLabel_13;
	private JLabel lblNewLabel_14;
	private JTextField g_question1_begin;
	private JTextField g_question1_end;
	private static long elapsedTime;
	private JTextField g_question2_begin;
	private JTextField g_question2_end;
	private JTextField g_questionNumber;
	private JLabel pWelcome;
	private JButton setQuestion;
	private JPanel childrenHomePage;
	private JButton exitParent;
	private JLabel number1;
	private JLabel number2;
	private JTextField resultG;
	private JButton getResultButton;
	private JLabel lblNewLabel_16;
	private JLabel lblNewLabel_17;
	private JLabel result;
	private JLabel true_false;
	private JButton btnNewButton_5;
	private static JLabel secondLabel;
	private static JLabel hourLabel;
	private static JLabel minuteLabel;
	private JLabel lblNewLabel_20;
	private static JLabel millisecondLabel;
	private static boolean isStart = false;
	private static int hours;
	private static int minutes;
	private static int seconds;
	private static int milliseconds;
	private JPasswordField c_passwordField;
	private JTable table;
	private JTextField g_excName;
	private JButton btnNewButton_8;
	private JLabel childNameLabel;
	JPanel seeResultsPage = new JPanel();
	DefaultComboBoxModel<Exercise> excersizeBox= new DefaultComboBoxModel<>();
	DefaultComboBoxModel<Exercise> selectBox= new DefaultComboBoxModel<>();
	DefaultComboBoxModel<Child> childrenBox= new DefaultComboBoxModel<>();
	
	static LocalDateTime currentDateTime;
	private JButton childResultsButton;
	


	public static void main(String[] args) {
		dosyadanOku();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					App window = new App();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public static  void dosyadanOku() {  // dosyadan verileri okuma fonksiyonu
		try {
			ObjectInputStream reader = new ObjectInputStream(new FileInputStream("veri.dat"));
			
			Integer parentCount = (Integer) reader.readObject();
			ArrayList<Parent> parents = new ArrayList<>();
			for (int i = 0; i < parentCount; i++) {
				parents.add((Parent) reader.readObject());
			}
			parent = parents.get(0);
			
			Integer childCount = (Integer) reader.readObject();
			ArrayList<Child> children = new ArrayList<>();
			for (int i = 0; i < childCount; i++) {
				children.add((Child) reader.readObject());
			}
			for (Child aChild : children) {
				child.add(aChild);
			}
			reader.close();
		} 
		catch (IOException  e) {
			System.out.println("An exception has occured during file reading.");
			e.printStackTrace();
		}
		
		catch (ClassNotFoundException  e) {
			System.out.println("An exception has occured while processing read records.");
			e.printStackTrace();
		}
	}
	
	public static void fileUpdate() {  // dosyayı güncelleme fonksiyonu
		try {
			ObjectInputStream reader = new ObjectInputStream(new FileInputStream("veri.dat"));
			
			Integer parentCount = (Integer) reader.readObject();
			ArrayList<Parent> parents = new ArrayList<>();
			
			for (int i = 0; i < parentCount; i++) {
				Parent p = (Parent) reader.readObject();
				p.setExercises(parent.getExercises());
				parents.add(p);
			}
			
			Integer childCount = (Integer) reader.readObject();
			ArrayList<Child> children = new ArrayList<>();
			for (int i = 0; i < childCount; i++) {
				Child c = (Child) reader.readObject();
				c.setExercises(child.get(i).getExercises());
				children.add(c);
			}
			
			reader.close();
			
			ObjectOutputStream yazici = new ObjectOutputStream(new FileOutputStream("veri.dat"));
			
			System.out.println("The information you have entered has been successfully saved in file " +"veri.dat");
			
			
			
			yazici.writeObject(parents.size());
			for (Parent aParent : parents) {
				yazici.writeObject(aParent);
			}
			
			yazici.writeObject(children.size());
			for (Child aChild : children) {
				yazici.writeObject(aChild);
			}
			
			
			
			yazici.close();
			System.out.println("The information you have entered has been successfully saved in file " +"veri.dat");
			
		} 
		catch (IOException  e) {
			System.out.println("An exception has occured during file reading.");
			e.printStackTrace();
		}
		
		catch (ClassNotFoundException  e) {
			System.out.println("An exception has occured while processing read records.");
			e.printStackTrace();
		}
	}
	
	public static ArrayList<Child> getOrder(ArrayList<Child> ch3,int index){  // hız ve doğruluk skorlarina gore cocuklari siralama fonksiyonu.
		ArrayList<Child> ch4 = new ArrayList<>(ch3);
		
		Collections.sort(ch4, new Comparator<Child>() {
            public int compare(Child child1, Child child2) {
            	if(child1.getExercises().get(index).getAccuracyRate()==child2.getExercises().get(index).getAccuracyRate()) {
            		return Float.compare(child2.getExercises().get(index).getSpeedScore(), child1.getExercises().get(index).getSpeedScore());
            	}else {
            		return Float.compare(child2.getExercises().get(index).getAccuracyRate(), child1.getExercises().get(index).getAccuracyRate());
            	}
                
            }
        });
		
		return ch4;
	}
	
	public static int findTheChild(ArrayList<Child> ch, String email, String password) {  //login işlemlerinde girilen e posta ve sifrede cocuk arama fonksiyonu
		
		int index = -1;
		for (Child aChild : ch) {
 			if (!email.isEmpty() && !password.isEmpty()) {
 				if (email.equals( aChild.getEmail()) && password.equals( aChild.getPassword())  ) {
 					index = ch.indexOf(aChild);
 					
 				}
 			
 			}
 			
 			
 		}
 		
		return index;
	}
	
	public static ArrayList<Question> getRandomNumber(int start, int start2, int end, int end2, int n) {  // Random bir şekilde belirtilen sayı kadar belirtilen aralıklarda soru içeren alıştırma ureten fonksiyon
		ArrayList<Question> questionss = new ArrayList<>();
		Random random = new Random();
		int num, num2;
		for(int i=0;i<n;i++) {
			num = random.nextInt(end-start + 1) + start;
			num2 = random.nextInt(end2-start2 + 1) + start2;
			questionss.add(new Question(num, num2));
		}
		return questionss;
	}
	/**
	 * Create the application.
	 */
	public App() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frame = new JFrame();
		frame.setBounds(100, 100, 732, 582);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		 JPanel childrenLoginPage = new JPanel();
		 
		 childrenHomePage = new JPanel();
		 childrenHomePage.setBackground(new Color(97, 120, 154));
		 childrenHomePage.setBounds(0, 0, 718, 545);
		 childrenHomePage.setVisible(false);
		 JPanel seeGraphicsPage = new JPanel();
		 
		 
		 seeGraphicsPage.setBackground(new Color(214, 174, 171));
		 seeGraphicsPage.setBounds(0, 0, 718, 545);
		 frame.getContentPane().add(seeGraphicsPage);
		 seeGraphicsPage.setLayout(null);
		 seeGraphicsPage.setVisible(false);
		 
		 JButton showGraphicsButton = new JButton("Görüntüle");
		 showGraphicsButton.setBackground(new Color(227, 227, 227));
		 showGraphicsButton.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 		Child currentChild = (Child) childrenBox.getSelectedItem();  //grafikte hangi cocugun gosterilecegini kullanıcıdan alma
		 		
		 		String graphicTitle =  "" + currentChild.getName() + "İlerleme Tablosu";
		 		LineChartDemo1.getGraphic(graphicTitle, currentChild);
		 		
		 	}
		 });
		 showGraphicsButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		 showGraphicsButton.setBounds(521, 369, 126, 61);
		 seeGraphicsPage.add(showGraphicsButton);
		 
		 JButton btnNewButton_10 = new JButton("Çıkış");
		 btnNewButton_10.setBackground(new Color(227, 227, 227));
		 btnNewButton_10.setFont(new Font("Tahoma", Font.PLAIN, 15));
		 btnNewButton_10.setBounds(549, 441, 98, 33);
		 btnNewButton_10.addActionListener(new ActionListener() {
			  public void actionPerformed(ActionEvent e) {
				  
				  parentHomePage.setVisible(true);   //sayfa gecisleri
				  seeGraphicsPage.setVisible(false);
			  	
			  }
		  }); 	
		 seeGraphicsPage.add(btnNewButton_10);
		 
		 JLabel lblNewLabel_10 = new JLabel("Hangi Çocuğu ilerleme Grafiğini Görmek İstersiniz?");
		 lblNewLabel_10.setForeground(new Color(0, 0, 0));
		 lblNewLabel_10.setFont(new Font("Tahoma", Font.PLAIN, 18));
		 lblNewLabel_10.setBounds(146, 134, 425, 33);
		 seeGraphicsPage.add(lblNewLabel_10);
		 
		 
		 JComboBox<Child> comboBox_2 = new JComboBox<>(childrenBox);
		 comboBox_2.setBounds(169, 178, 347, 33);
		 seeGraphicsPage.add(comboBox_2);
		 
		 
		 seeResultsPage.setBackground(new Color(97, 120, 154));
		 seeResultsPage.setBounds(0, 0, 718, 545);
		 frame.getContentPane().add(seeResultsPage);
		 seeResultsPage.setLayout(null);
		 
		 JScrollPane scrollPane = new JScrollPane();
		 scrollPane.setBounds(144, 74, 429, 266);
		 seeResultsPage.add(scrollPane);
		 
		 table = new JTable();
		 table.setBackground(new Color(250, 243, 243));
		 table.setModel(new DefaultTableModel(
		 	new Object[][] {
		 	},
		 	new String[] {
		 		"\u00C7ocuk", "Do\u011Fruluk Skoru(%)", "Hız Skoru(1x(n)x100/sn)"  // skor tablosunun başlıklarını olusturma
		 	}
		 ) {
		 	Class[] columnTypes = new Class[] {      //skor seklini tibini belirleme
		 		String.class, Float.class, Long.class
		 	};
		 	public Class getColumnClass(int columnIndex) {
		 		return columnTypes[columnIndex];
		 	}
		 });
		 
		 scrollPane.setViewportView(table);
		 
		 JComboBox<Exercise> comboBox_1 = new JComboBox<>(excersizeBox);  
		 
		 JButton listScoreButton = new JButton("Listele");
		 listScoreButton.addActionListener(new ActionListener() {  
			
		 	public void actionPerformed(ActionEvent e) {
		 		currentExcersize=	(Exercise) excersizeBox.getSelectedItem();  // skor tablosu icin secilen egzersizi skor tablosuna yazdırma
		 		model = (DefaultTableModel)table.getModel();
		 		if(check == 1) {
		 			for(int i=0; i<parent.getParentsChildren().size(); i++) {
			 			model.removeRow(0);
			 		}
		 		}
		 		
		 		ArrayList<Child> orderedChildren = new ArrayList<>();
		 		orderedChildren = getOrder(child, currentExcersize.getNumber()-1);    // cocuklari öncelikli olarak doğruluk skoruna göre eşit olan dogruluk skorlari icin de hız skoruna gore siralama
		 		
		 		for (Child ch2 : orderedChildren) {
		 			model.addRow(new Object [] {ch2.getName(), ch2.getExercises().get(currentExcersize.getNumber()-1).getAccuracyRate(),ch2.getExercises().get(currentExcersize.getNumber()-1).getSpeedScore() });
				}
		 		check = 1;   // sayfaya daha once ulasılmıs mı bilgisini tutar. bu sayede null islem yapılmaz
		 	}
		 });
		 listScoreButton.setBackground(new Color(241, 239, 237));
		 listScoreButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
		 listScoreButton.setBounds(526, 363, 123, 40);
		 seeResultsPage.add(listScoreButton);
		 
		 
		 comboBox_1.setBounds(40, 363, 352, 22);
		 seeResultsPage.add(comboBox_1);
		 
		 JButton btnNewButton_7 = new JButton("Çıkış");
		 btnNewButton_7.addActionListener(new ActionListener() {
			 	public void actionPerformed(ActionEvent e) {
			 		
			 		if(check == 1) {  // sayfaya daha once erisilmisse tablodaki verileri yeniden set etmeden once temizler.
			 			for(int i=0; i<parent.getParentsChildren().size(); i++) {
				 			model.removeRow(0);
				 		}
			 		}
			 		
			 		seeResultsPage.setVisible(false); // sayfa gecisi
			 		parentHomePage.setVisible(true);
			 	}
			 });
		 
		 btnNewButton_7.setBackground(new Color(214, 174, 171));
		 btnNewButton_7.setFont(new Font("Tahoma", Font.PLAIN, 15));
		 btnNewButton_7.setBounds(536, 414, 104, 33);
		 seeResultsPage.add(btnNewButton_7);
		 seeResultsPage.setVisible(false);
		 
		 
		 parentHomePage = new JPanel();
		 parentHomePage.setBackground(new Color(97, 120, 154));
		 parentHomePage.setBounds(0, 0, 718, 545);
		 frame.getContentPane().add(parentHomePage);
		 parentHomePage.setLayout(null);
		 parentHomePage.setVisible(false);
		 
		  seeResultsButton = new JButton("Skorlar");
		  seeResultsButton.setBackground(new Color(241, 239, 237));
		  seeResultsButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		  seeResultsButton.addActionListener(new ActionListener() {
			  public void actionPerformed(ActionEvent e) {
				  
				  excersizeBox.removeAllElements();
				  ArrayList<Exercise> allExercises = new ArrayList<Exercise>(parent.getExercises());    // skor tablosunda hangi egzersizin listesi olacagini belirlemek için liste seklinde sunma
					
					for (Exercise exercise : allExercises) {
						excersizeBox.addElement(exercise);
					}
					
					check = 0;
				  
			  		parentHomePage.setVisible(false);
			  		seeResultsPage.setVisible(true);
			  		
			  	}
		  }); 	
		  
		  seeResultsButton.setBounds(268, 231, 156, 65);
		  parentHomePage.add(seeResultsButton);
		  addButton = new JButton("alıştırma ekle");
		  addButton.setBackground(new Color(241, 239, 237));
		  addButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		  addButton.addActionListener(new ActionListener() {
		  	public void actionPerformed(ActionEvent e) {
		  		g_excName.setText("");
		  		parentHomePage.setVisible(false); //sayfa gecisleri
		  		setExcersizePage.setVisible(true);
		  		
		  	}
		  });
		  
		  	addButton.setBounds(268, 117, 156, 72);
		  	parentHomePage.add(addButton);
		  	
		  	pWelcome = new JLabel("New label");
		  	pWelcome.setForeground(new Color(255, 255, 255));
		  	pWelcome.setFont(new Font("Tahoma", Font.PLAIN, 20));
		  	pWelcome.setBounds(53, 54, 270, 52);
		  	parentHomePage.add(pWelcome);
		  	
		  	exitParent = new JButton("Çıkış");
		  	exitParent.setBackground(new Color(214, 174, 171));
		  	exitParent.setFont(new Font("Tahoma", Font.PLAIN, 17));
		  	exitParent.setBounds(545, 406, 112, 57);
		  	exitParent.addActionListener(new ActionListener() {
			 	public void actionPerformed(ActionEvent e) {
			 		parentHomePage.setVisible(false); // sayfa gecisleri
			 		pickUserPage.setVisible(true);
			 		
			 	}
			 });
		  	parentHomePage.add(exitParent);
		  	childResultsButton = new JButton("Öğrenci Takip");
		  	childResultsButton.addActionListener(new ActionListener() {
		  		public void actionPerformed(ActionEvent e) {   // hangi ogrencinin grafigi gorulmak isteniyor bilgisini alabilmek için ogrencileri gorunur selikde ebeveyne listeleme
		  			parentHomePage.setVisible(false);
		  			seeGraphicsPage.setVisible(true);
		  			childrenBox.removeAllElements();
		  			ArrayList<Child> allChildren = new ArrayList<Child>(parent.getParentsChildren());
					
						for (Child ch : allChildren) {
							childrenBox.addElement(ch);
						}
		  		}
		  	});
		  	childResultsButton.setFont(new Font("Tahoma", Font.PLAIN, 19));
		  	childResultsButton.setBackground(new Color(241, 239, 237));
		  	childResultsButton.setBounds(268, 340, 156, 65);
		  	parentHomePage.add(childResultsButton);
		  	parentHomePage.setVisible(false);
		 frame.getContentPane().add(childrenHomePage);
		 childrenHomePage.setLayout(null);
		 
		 JLabel lblNewLabel_15 = new JLabel("Alıştırmalarınız");
		 lblNewLabel_15.setForeground(new Color(255, 255, 255));
		 lblNewLabel_15.setFont(new Font("Tahoma", Font.PLAIN, 25));
		 lblNewLabel_15.setBounds(265, 152, 209, 32);
		 childrenHomePage.add(lblNewLabel_15);
		 JComboBox<Exercise> comboBox_ch_1 = new JComboBox<>(selectBox);
		 comboBox_ch_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		 
		 
		 comboBox_ch_1.setBounds(200, 195, 301, 32);
		 childrenHomePage.add(comboBox_ch_1);
		 
		 JButton btnNewButton_4 = new JButton("Seç");
		 btnNewButton_4.setBackground(new Color(214, 174, 171));
		 btnNewButton_4.setFont(new Font("Tahoma", Font.PLAIN, 20));
		 btnNewButton_4.setBounds(559, 357, 118, 52);
		 btnNewButton_4.addActionListener(new ActionListener() {
			 	public void actionPerformed(ActionEvent e) {  //Hangi alistirmayi sececegini belirleme 
			 		
			 		result.setText("");
			 		try {
			 			startTimer();
			 			
			 			done = 0;
			 			currentChExcersize=	(Exercise) selectBox.getSelectedItem();   // listelenen egzersizlerden secileni getirme.
			 			
		                excNo = currentChExcersize.getNumber();
		                excNo--;
		     
		                int x = child.get(childIndex).getExercises().get(excNo).getQuestions().get(done).getNum2(); // ilk soruyu ekrana yazdırma
		   
			    	    String deger2 = String.valueOf(x);
			    	    number2.setText(deger2);

			    	    int y = child.get(childIndex).getExercises().get(excNo).getQuestions().get(done).getNum();
			    	    String deger1 = String.valueOf(y);
			    	    number1.setText(deger1);
			    	    childrenHomePage.setVisible(false);  // sayfa gecisi
		                solvePage.setVisible(true);
		                
		                totalTime = 0;
		                currentDateTime = LocalDateTime.now();  //egzersizin yapıldığı tarihin bilgisini bulma ve cocugun egzersizlerinde ilgili yere kaydetme.
		                System.out.println(currentDateTime);
		                child.get(childIndex).getExercises().get(excNo).setDate(currentDateTime);
		                
		                }
		            catch (Exception ex) {
		                JOptionPane.showMessageDialog(null, "Lütfen geçerli sayı giriniz!");
		            }
			 		
			 	}
			 });
		 childrenHomePage.add(btnNewButton_4);
		 
		 JButton btnNewButton_6 = new JButton("Çıkış");
		 btnNewButton_6.setBackground(new Color(241, 239, 237));
		 btnNewButton_6.setFont(new Font("Tahoma", Font.PLAIN, 15));
		 btnNewButton_6.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 		childrenHomePage.setVisible(false);   // sayfa gecisi
		 		pickUserPage.setVisible(true);
		 		selectBox.removeAllElements();  //listenin üzerine aynı elemanlar tekrar eklenmasin diye
		 		
		 	}
		 });
		 btnNewButton_6.setBounds(584, 420, 93, 32);
		 childrenHomePage.add(btnNewButton_6);
		 
		 childNameLabel = new JLabel("New label");
		 childNameLabel.setForeground(new Color(255, 255, 255));
		 childNameLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		 childNameLabel.setBounds(53, 54, 270, 52);
		 childrenHomePage.add(childNameLabel);
		 
		 setExcersizePage = new JPanel();
		 setExcersizePage.setBackground(new Color(241, 239, 237));
		 setExcersizePage.setBounds(0, 0, 718, 545);
		 frame.getContentPane().add(setExcersizePage);
		 setExcersizePage.setLayout(null);
		 setExcersizePage.setVisible(false);
		 
		 lblNewLabel_8 = new JLabel("Aralık Başlangıcı:");
		 lblNewLabel_8.setFont(new Font("Tahoma", Font.PLAIN, 20));
		 lblNewLabel_8.setBounds(58, 188, 167, 43);
		 setExcersizePage.add(lblNewLabel_8);
		 
		 lblNewLabel_9 = new JLabel("Aralık Bitişi:");
		 lblNewLabel_9.setFont(new Font("Tahoma", Font.PLAIN, 20));
		 lblNewLabel_9.setBounds(58, 259, 116, 43);
		 setExcersizePage.add(lblNewLabel_9);
		 
		 lblNewLabel_12 = new JLabel("Sayı 1");
		 lblNewLabel_12.setFont(new Font("Tahoma", Font.PLAIN, 25));
		 lblNewLabel_12.setBounds(283, 121, 140, 67);
		 setExcersizePage.add(lblNewLabel_12);
		 
		 lblNewLabel_13 = new JLabel("Sayı 2");
		 lblNewLabel_13.setFont(new Font("Tahoma", Font.PLAIN, 25));
		 lblNewLabel_13.setBounds(468, 121, 129, 67);
		 setExcersizePage.add(lblNewLabel_13);
		 
		 lblNewLabel_14 = new JLabel("Soru Sayısı:");
		 lblNewLabel_14.setFont(new Font("Tahoma", Font.PLAIN, 20));
		 lblNewLabel_14.setBounds(175, 371, 129, 44);
		 setExcersizePage.add(lblNewLabel_14);
		 
		 g_question1_begin = new JTextField();
		 g_question1_begin.setBounds(258, 188, 140, 43);
		 setExcersizePage.add(g_question1_begin);
		 g_question1_begin.setColumns(10);
		 
		 g_question1_end = new JTextField();
		 g_question1_end.setBounds(258, 263, 140, 43);
		 setExcersizePage.add(g_question1_end);
		 g_question1_end.setColumns(10);
		 
		 g_question2_begin = new JTextField();
		 g_question2_begin.setBounds(439, 188, 140, 43);
		 setExcersizePage.add(g_question2_begin);
		 g_question2_begin.setColumns(10);
		 
		 g_question2_end = new JTextField();
		 g_question2_end.setBounds(439, 263, 140, 43);
		 setExcersizePage.add(g_question2_end);
		 g_question2_end.setColumns(10);
		 
		 g_questionNumber = new JTextField();
		 g_questionNumber.setBounds(348, 375, 140, 44);
		 setExcersizePage.add(g_questionNumber);
		 g_questionNumber.setColumns(10);
		 
		 setQuestion = new JButton("Tamam");
		 setQuestion.setBackground(new Color(97, 120, 154));
		 setQuestion.setFont(new Font("Tahoma", Font.PLAIN, 20));
		 setQuestion.setBounds(539, 371, 122, 44);
		 setQuestion.addActionListener(new ActionListener() {
		  	public void actionPerformed(ActionEvent e) {
		  		
		  		try {
		  			ArrayList<Question> questions = new ArrayList<>();
		  			//Excersize.setCurrExcNum(Excersize.getCurrExcNum() + 1);
		  			int no = parent.getExercises().size() + 1; // kacinci egzersizde olundugunu bulmak icin
		  			questions = getRandomNumber(Integer.parseInt(g_question1_begin.getText()), Integer.parseInt(g_question2_begin.getText()), Integer.parseInt(g_question1_end.getText()), Integer.parseInt(g_question2_end.getText()), Integer.parseInt(g_questionNumber.getText()));
		  			Exercise exc = new Exercise(questions, Integer.parseInt(g_questionNumber.getText()), no + ")" + g_excName.getText(), no);
		  			// egzersizdeki soruları belirlenen sayı araliklarında ve belirlenen sayıda rastgele olusturma
		 	 		parent.getExercises().add(exc); // parente egzersizi eklme
		 	 		
		 	 		for (Child aChild : child) {
		 	 			Exercise exc1 = new Exercise(questions, Integer.parseInt(g_questionNumber.getText()), no + ")" + g_excName.getText(), no);
		 	 			aChild.getExercises().add(exc1);
		 	 			
		 	 			//teker teker cocuklara egzersizi ekleme
					}
		 	 		
		 	 		fileUpdate();  // nesnelerin yeni hallerini dosyada güncelleme
		  			setExcersizePage.setVisible(false);
		 	 		parentHomePage.setVisible(true);
		 	 		
	             	}
	            catch (Exception ex) {
	                JOptionPane.showMessageDialog(null, "Lütfen sayı giriniz!");
	            }
		  		
		  		g_question1_begin.setText(""); // sayfada ilgili text bloklarını bir dahaki erisim icin temizleme
		  		g_question2_begin.setText("");
		  		g_question1_end.setText("");
		  		g_question2_end.setText("");
		  		g_questionNumber.setText("");
		  	}
		  });
		 setExcersizePage.add(setQuestion);
		 
		 g_excName = new JTextField();
		 g_excName.setBounds(258, 67, 363, 43);
		 setExcersizePage.add(g_excName);
		 g_excName.setColumns(10);
		 
		 JLabel lblNewLabel = new JLabel("Egzersiz Adı:");
		 lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		 lblNewLabel.setBounds(58, 67, 167, 43);
		 setExcersizePage.add(lblNewLabel);
		 
		 btnNewButton_8 = new JButton("İptal");
		 btnNewButton_8.addActionListener(new ActionListener() {
			 	public void actionPerformed(ActionEvent e) {
			 		setExcersizePage.setVisible(false);  // sayfa gecisi
			 		parentHomePage.setVisible(true);
			 	}
			 });
		 btnNewButton_8.setBackground(new Color(214, 174, 171));
		 btnNewButton_8.setFont(new Font("Tahoma", Font.PLAIN, 15));
		 btnNewButton_8.setBounds(549, 426, 99, 37);
		 setExcersizePage.add(btnNewButton_8);
		 
		 	ArrayList<Exercise> allExercises = new ArrayList<Exercise>(parent.getExercises());
			//ArrayList<Excersize> allExercises = parent.getExcersizes();
			for (Exercise exercise : allExercises) {
				excersizeBox.addElement(exercise);
			}
		
		 
		
		 solvePage = new JPanel();
		 solvePage.setBackground(new Color(241, 239, 237));
		 solvePage.setBounds(0, 0, 718, 545);
		 frame.getContentPane().add(solvePage);
		 solvePage.setLayout(null);
		 
		 number1 = new JLabel("0");
		 number1.setFont(new Font("Tahoma", Font.PLAIN, 25));
		 number1.setBounds(162, 240, 49, 39);
		 solvePage.add(number1);
		 
		 number2 = new JLabel("0");
		 number2.setFont(new Font("Tahoma", Font.PLAIN, 25));
		 number2.setBounds(314, 240, 49, 39);
		 solvePage.add(number2);
		 
		 resultG = new JTextField();
		 resultG.setBounds(457, 240, 151, 39);
		 solvePage.add(resultG);
		 resultG.setColumns(10);
		 
		 getResultButton = new JButton("Tamam");
		 getResultButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		 getResultButton.setBackground(new Color(214, 174, 171));
		 getResultButton.addActionListener(new ActionListener() {
			 @SuppressWarnings("static-access")
				public void actionPerformed(ActionEvent e) {  // cocuğun sorulari cozdugu kısım
				 	int sonucIntG = -1;
				 	try {
					 	sonucIntG = Integer.parseInt(resultG.getText());  // sayi girdiginden emin olunur.
					}
					catch(Exception ex){
						JOptionPane.showMessageDialog(null, "Lütfen sayı giriniz!");
					}
				 
		            try {
		            	if(sonucIntG != -1) {
		            		if(done+1 != child.get(childIndex).getExercises().get(excNo).getqNumber()) { // son soruda mı degil mi bunun kontrolunu yapar
				        		int x = child.get(childIndex).getExercises().get(excNo).getQuestions().get(done+1).getNum2(); //her isaretlemeden sonra sorulari değistime islemi
				        		//int x = Parent.getQuestions().get(done+1).getNum();
					    	    String deger2 = String.valueOf(x);
					    	    number2.setText(deger2);

					    	    int y = child.get(childIndex).getExercises().get(excNo).getQuestions().get(done+1).getNum();
					    	    String deger1 = String.valueOf(y);
					    	    number1.setText(deger1);
					    	    
					    	    //String sonucString = String.valueOf(Parent.getQuestions().get(done-1).getResult());
					    	    //sonuc.setText(sonucString);
				        	}
			            	done++; // soru takibi
			                
			                
			                String sonucString = String.valueOf(child.get(childIndex).getExercises().get(excNo).getQuestions().get(done-1).getResult());
			                result.setText(sonucString);
			                
			                child.get(childIndex).getExercises().get(excNo).getQuestions().get(done-1).setAnswer(sonucIntG);
			                
			                questionTime = elapsedTime - totalTime;  // her soru icin sure bilgisi ayarlanır
			                child.get(childIndex).getExercises().get(excNo).getQuestions().get(done-1).setTime(questionTime);
		                    totalTime += questionTime;
		             
			                // dogru yanlis bilgisi set etme
		                    if (sonucIntG != child.get(childIndex).getExercises().get(excNo).getQuestions().get(done-1).getResult()) {
			                    true_false.setText("YANLIŞ");
			                    child.get(childIndex).getExercises().get(excNo).getQuestions().get(done-1).setCorrect(false);
			                    
			                } else {
			                    true_false.setText("DOĞRU");
			                    child.get(childIndex).getExercises().get(excNo).getQuestions().get(done-1).setCorrect(true);
			                }
			                
			                if(done == child.get(childIndex).getExercises().get(excNo).getqNumber()) { // sonuncu soruyu isarelemis ise alttaki islemler yapilir.
			                	isStart = false;
			                	Long time = elapsedTime;
			                	child.get(childIndex).getExercises().get(excNo).setDuration(time); // sur bilgisi set edilir.
			                	
			                	child.get(childIndex).getExercises().get(excNo).setSpeedScore(time); // hız bilgisi ayarlanir.
			                	elapsedTime = 0;
			                	JOptionPane panel = new JOptionPane(); // tuşa basınca kullanıcıya mesaj penceresi açıyo
			                	child.get(childIndex).getExercises().get(excNo).setAccuracyRate();
			                	Report.reportExercise(child.get(childIndex));
			                	
			                	fileUpdate();
			    				panel.showMessageDialog(null, "Soru Sayısı bitmiştir!\n" + "Doğruluk oranı: " + child.get(childIndex).getExercises().get(excNo).getAccuracyRate() + "\n hız skoru: " + child.get(childIndex).getExercises().get(excNo).getSpeedScore());
			                }
		            	}
		            	
		            } catch (Exception ex) {
		                JOptionPane.showMessageDialog(null, "Soru sayısı bitmiştir!");
		                
		            }
		            
		            resultG.setText("");
		            
		        }
		 });
		 getResultButton.setBounds(512, 290, 96, 31);
		 solvePage.add(getResultButton);
		 
		 lblNewLabel_16 = new JLabel("x");
		 lblNewLabel_16.setFont(new Font("Tahoma", Font.PLAIN, 25));
		 lblNewLabel_16.setBounds(241, 240, 63, 39);
		 solvePage.add(lblNewLabel_16);
		 
		 lblNewLabel_17 = new JLabel("=");
		 lblNewLabel_17.setFont(new Font("Tahoma", Font.PLAIN, 25));
		 lblNewLabel_17.setBounds(385, 240, 49, 39);
		 solvePage.add(lblNewLabel_17);
		 
		 result = new JLabel("Sonuç");
		 result.setForeground(new Color(97, 120, 154));
		 result.setBackground(new Color(97, 120, 154));
		 result.setFont(new Font("Tahoma", Font.PLAIN, 25));
		 result.setEnabled(false);
		 result.setBounds(227, 335, 136, 53);
		 solvePage.add(result);
		 
		 true_false = new JLabel("doğru-yanlış");
		 true_false.setFont(new Font("Tahoma", Font.PLAIN, 20));
		 true_false.setBounds(227, 387, 112, 31);
		 solvePage.add(true_false);
		 
		 btnNewButton_5 = new JButton("Çıkış");
		 btnNewButton_5.setFont(new Font("Tahoma", Font.PLAIN, 20));
		 btnNewButton_5.setBackground(new Color(97, 120, 154));
		 btnNewButton_5.setBounds(476, 421, 132, 53);
		 btnNewButton_5.addActionListener(new ActionListener() {
			 	public void actionPerformed(ActionEvent e) {
			 		
			 		solvePage.setVisible(false);   // sayfa gecisi
			 		childrenHomePage.setVisible(true);
			 		
			 	}
			 });
		 solvePage.add(btnNewButton_5);
		 
		  hourLabel = new JLabel("0");
		  hourLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		  hourLabel.setBounds(54, 71, 67, 31);
		  solvePage.add(hourLabel);
		  
		  JLabel lblNewLabel_1 = new JLabel(":");
		  lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		  lblNewLabel_1.setBounds(92, 71, 58, 31);
		  solvePage.add(lblNewLabel_1);
		  
		   minuteLabel = new JLabel("0");
		   minuteLabel.setFont(new Font("Tahoma", Font.PLAIN, 19));
		   minuteLabel.setBounds(131, 71, 49, 31);
		   solvePage.add(minuteLabel);
		   
		   JLabel lblNewLabel_18 = new JLabel(":");
		   lblNewLabel_18.setFont(new Font("Tahoma", Font.PLAIN, 17));
		   lblNewLabel_18.setBounds(249, 71, 49, 31);
		   solvePage.add(lblNewLabel_18);
		   
		   secondLabel = new JLabel("0");
		   secondLabel.setFont(new Font("Tahoma", Font.PLAIN, 19));
		   secondLabel.setBounds(209, 71, 58, 31);
		   solvePage.add(secondLabel);
		   
		   lblNewLabel_20 = new JLabel(":");
		   lblNewLabel_20.setFont(new Font("Tahoma", Font.PLAIN, 17));
		   lblNewLabel_20.setBounds(173, 71, 58, 31);
		   solvePage.add(lblNewLabel_20);
		   
		   millisecondLabel = new JLabel("0");
		   millisecondLabel.setFont(new Font("Tahoma", Font.PLAIN, 19));
		   millisecondLabel.setBounds(287, 71, 58, 31);
		   solvePage.add(millisecondLabel);
		   solvePage.setVisible(false);
		 
		 
		 childrenLoginPage.setBackground(new Color(241, 239, 237));
		 childrenLoginPage.setBounds(0, 0, 718, 545);
		 frame.getContentPane().add(childrenLoginPage);
		 childrenLoginPage.setLayout(null);
		 childrenLoginPage.setVisible(false);
		 
		 c_emailTextField = new JTextField();
		 c_emailTextField.setBounds(277, 218, 195, 31);
		 childrenLoginPage.add(c_emailTextField);
		 c_emailTextField.setColumns(10);
		 
		 JButton btnNewButton_3 = new JButton("Giriş Yap");
		 btnNewButton_3.setBackground(new Color(97, 120, 154));
		 btnNewButton_3.setFont(new Font("Tahoma", Font.PLAIN, 20));
		 btnNewButton_3.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 		
		 		
		 		childIndex = findTheChild(child,c_emailTextField.getText(),c_passwordField.getText() );   // giris islemlerini yapan fonksiyonu cagırma
		 		
		 		if (childIndex == -1) {
		 			JOptionPane.showMessageDialog(null, "Hatalı");
		 		}else {
		 			childrenHomePage.setVisible(true);
 					childrenLoginPage.setVisible(false);
 					
 					ArrayList<Exercise> ch_Exercises = new ArrayList<>(child.get(childIndex).getExercises());
 
 					for (Exercise exCh : ch_Exercises) {
 						selectBox.addElement(exCh);
 					}
 					childNameLabel.setText("Hoşgeldin " + child.get(childIndex).getName());
 					
		 		}
		 		
		 		
		 		
		 	}
		 });
		 btnNewButton_3.setBounds(454, 373, 157, 64);
		 childrenLoginPage.add(btnNewButton_3);
		 
		 JLabel lblNewLabel_4 = new JLabel("Çocuk Girişi");
		 lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 25));
		 lblNewLabel_4.setBounds(303, 113, 195, 64);
		 childrenLoginPage.add(lblNewLabel_4);
		 
		 JLabel lblNewLabel_5 = new JLabel("email:");
		 lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 20));
		 lblNewLabel_5.setBounds(142, 218, 89, 31);
		 childrenLoginPage.add(lblNewLabel_5);
		 
		 JLabel lblNewLabel_6 = new JLabel("şifre:");
		 lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 20));
		 lblNewLabel_6.setBounds(142, 283, 101, 31);
		 childrenLoginPage.add(lblNewLabel_6);
		 
		 c_passwordField = new JPasswordField();
		 c_passwordField.setBounds(277, 283, 195, 31);
		 childrenLoginPage.add(c_passwordField);
		 
		 parentLoginPage = new JPanel();
		 parentLoginPage.setBackground(new Color(241, 239, 237));
		 parentLoginPage.setBounds(0, 0, 718, 545);
		 frame.getContentPane().add(parentLoginPage);
		 parentLoginPage.setLayout(null);
		 parentLoginPage.setVisible(false);
		 
		 emailTextField = new JTextField();
		 emailTextField.setBounds(277, 218, 195, 31);
		 parentLoginPage.add(emailTextField);
		 emailTextField.setColumns(10);
		 
		 passwordField = new JPasswordField();
		 passwordField.setBounds(277, 283, 195, 31);
		 parentLoginPage.add(passwordField);
		 
		 JLabel lblNewLabel_2 = new JLabel("email:");
		 lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		 lblNewLabel_2.setBounds(144, 218, 109, 31);
		 parentLoginPage.add(lblNewLabel_2);
		 
		 JLabel lblNewLabel_3 = new JLabel("şifre:");
		 lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 20));
		 lblNewLabel_3.setBounds(144, 283, 98, 31);
		 parentLoginPage.add(lblNewLabel_3);
		 
		 JButton btnNewButton = new JButton("giriş yap");
		 btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		 btnNewButton.setBackground(new Color(97, 120, 154));
		 
		 btnNewButton.setBounds(454, 373, 157, 64);
		 parentLoginPage.add(btnNewButton);
		 
		 JLabel lblNewLabel_7 = new JLabel("Veli Girişi");
		 lblNewLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 25));
		 lblNewLabel_7.setBounds(319, 113, 195, 64);
		 parentLoginPage.add(lblNewLabel_7);
		 
		 btnNewButton.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 		if (!emailTextField.getText().isEmpty() && !passwordField.getText().isEmpty()) {  // ebeveynin giris islemlerini yapma
		 			if (emailTextField.getText().equals( parent.getEmail()) && passwordField.getText().equals( parent.getPassword())  ) {
		 				pWelcome.setText("Hoşgeldin " + parent.getName());
		 				parentHomePage.setVisible(true);
		 				parentLoginPage.setVisible(false);
		 			}
		 			else {
		 				JOptionPane.showMessageDialog(null, "Hatalı");
		 			}
		 		
		 		}
		 		else {
		 			JOptionPane.showMessageDialog(null, "Hatalı");
		 		}
		 		
		 	}
		 });
		 
		 pickUserPage = new JPanel();
		 pickUserPage.setBackground(new Color(97, 120, 154));
		 pickUserPage.setBounds(0, 0, 718, 545);
		 frame.getContentPane().add(pickUserPage);
		 pickUserPage.setLayout(null);
		 
		 btnNewButton_2 = new JButton("veli girisi");
		 btnNewButton_2.setBackground(new Color(241, 239, 237));
		 btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		 btnNewButton_2.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 		emailTextField.setText("");
		 		passwordField.setText("");
		 		pickUserPage.setVisible(false);   // sayfa gecisi
		 		parentLoginPage.setVisible(true);
		 	}
		 });
		 btnNewButton_2.setBounds(276, 314, 144, 77);
		 pickUserPage.add(btnNewButton_2);
		 //solveButton.addActionListener(menu);
		 
		 //addButton.addActionListener(menu);
		 
		 
		 btnNewButton_1 = new JButton("cocuk girisi");
		 btnNewButton_1.setBackground(new Color(241, 239, 237));
		 btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		 btnNewButton_1.setBounds(276, 152, 144, 77);
		 btnNewButton_1.addActionListener(new ActionListener() {
		 	  	public void actionPerformed(ActionEvent e) {
		 	  		c_emailTextField.setText("");
		 	  		c_passwordField.setText("");
		 	  		pickUserPage.setVisible(false);
		 	  		childrenLoginPage.setVisible(true);
		 	  	}
		 	  });
		 pickUserPage.add(btnNewButton_1);

	}
	private static void startTimer() {   // süre islemlerinin yapılmasını saglayan fonksiyon.
		isStart = true;
		Thread thread = new Thread() {
			public void run() {
				long startTime = System.currentTimeMillis();
				
				while (isStart) {
					try {
						TimeUnit.MILLISECONDS.sleep(1);
						long currentTime = System.currentTimeMillis();
						elapsedTime = currentTime - startTime;

						milliseconds = (int) (elapsedTime % 1000);
						seconds = (int) (elapsedTime / 1000) % 60;
						minutes = (int) (elapsedTime / (1000 * 60)) % 60;
						hours = (int) (elapsedTime / (1000 * 60 * 60));

						hourLabel.setText(String.format("%02d", hours));
						minuteLabel.setText(String.format("%02d", minutes));
						secondLabel.setText(String.format("%02d", seconds));
						millisecondLabel.setText(String.format("%02d", milliseconds));
						Thread.sleep(0);
					} catch (Exception e2) {
						System.out.println("something is wrong");
					}
				}
			}
		};
		thread.start();
	}
}
	
