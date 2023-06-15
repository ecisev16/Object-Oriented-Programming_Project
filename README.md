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

