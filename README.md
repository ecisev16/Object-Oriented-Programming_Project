# Multimlication_Table_App
Oyun açıldığında kullanıcının ilk olarak login olmasını istenir. Programın bir ebeveyn ve birden fazla
çocuk kullanıcısı vardır. Ebeveyn kullanıcısı aynı zamanda yönetici kullanıcı (admin) konumundadır ve her
türlü ayarlamaları yapabilir.

Varsayılan alıştırma modu axb (a:1..10, b:1..10 arası) şeklinde tüm olası çarpımların rastgele sorulacağı
N adet sorudan oluşacaktır. Ebeveyn a, b, N aralıklarını değiştirerek istediği kadar alıştırma
tanımlayabilir.

Bir çocuk bir alıştırmaya başladığında ekranda görünür biçimde zamanlama tutulur.
Hangi çocuğun hangi alıştırmaya ne zaman başladığı, o alıştırmayı ne kadar zamanda bitirdiği, o
alıştırmada hangi çarpım sorularının sorulduğu, hangi sorunun kaç sn’de cevaplandığı, hangi soruların
doğru hangi soruların yanlış cevaplandığı bilgilerinin güncesi "data.csv" dosyasında tutulmaktadır.
Tutulan güncelerin ebeveyne gösterildiği grafiksel bir raporlama ekranı bulunmaktadır.

Alıştırma sonuçlarından bir hız ve bir doğruluk skoru oluşturulup, alıştırma sonunda hem
günceye eklenmeli hem de çocuğa bildirilmektedir. Bu skorlar o alıştırmanın “yüksek skor”
tablosunda tutulur.

Programdaki tüm ayarlar, tanımlı kullanıcılar, tanımlı alıştırmalar, vb. serileştirme yöntemi kullanılarak
oluşturululmuş "veri.dat" dosyasında saklanıp güncellenmektedir.

##########################################################################################################


When the game is opened, the user is first prompted to log in. The program has one parent user and multiple child users. The parent user also serves as the administrator and has the ability to make any adjustments.

The default practice mode consists of multiplication exercises in the form of axb (a: 1..10, b: 1..10), where N questions are randomly selected. The parent can modify the ranges a, b, and the number of questions (N) to define custom exercises as desired.

When a child starts an exercise, the timing is recorded visibly on the screen. Information such as which child started which exercise, the duration of the exercise, the multiplication questions asked in the exercise, how long each question was answered, and which questions were answered correctly or incorrectly are all stored in a "data.csv" file. A graphical reporting screen displays these records to the parent.

From the exercise results, both a speed and accuracy score are generated. These scores are added to the log (data.csv) and communicated to the child. These scores are also maintained in a "high score" table for each exercise.

All settings, defined users, defined exercises, etc., are stored and updated in a serialized "veri.dat" file.

