## HexGame

HexGame, iki kişilik bir strateji oyunudur. Oyun, mavi ve kırmızı renkli oyuncular arasında altıgen hücrelerden oluşan bir tahta üzerinde oynanır. Amaç, kendi renginizdeki hücreleri birleştirerek karşı tarafa ulaşmaktır.

## Özellikler
İki kişilik oyun: Mavi ve Kırmızı oyuncular sırayla oynar.
Farklı boyutlarda oyun tahtası seçenekleri: 5x5, 11x11, 17x17.
Grafiksel arayüz ve animasyonlar.
## Kurulum

## JavaFX Kurulumu:

Projeyi çalıştırmak için JavaFX'in kurulu olduğundan emin olun. JavaFX kütüphanelerini proje yapılandırmanıza ekleyin.
Projeyi Kopyalayın:

bash
Kodu kopyala
git clone https://github.com/elifep/Hex-Game.git
cd HexGame

## Projeyi IntelliJ IDEA ile Açın:

IntelliJ IDEA'yı açın ve File > Open menüsünden projeyi seçin.

## JavaFX Kütüphanelerini Ekleyin:

Proje ayarlarından Libraries sekmesine gidin ve JavaFX SDK'sını ekleyin.

## Nasıl Oynanır?
Oyunu Başlatın:
IntelliJ IDEA'da HexGame sınıfını çalıştırarak oyunu başlatın.

Tahta Boyutunu Seçin:
Ekranın altındaki radyo butonlarından birini seçerek tahta boyutunu ayarlayın (5x5, 11x11, 17x17).

Oyuna Başlayın:
Start butonuna basarak oyunu başlatın. İlk hamleyi mavi oyuncu yapar.

Hücreye Tıklayın:
Hücrelere tıklayarak sırayla hamle yapın. Her hücre tıklaması oyuncunun renginde boyanır.

Oyunun Durumunu Kontrol Edin:
Oyunculardan biri kazandığında ekranın üst kısmında kazanan oyuncu belirtilir. Yeni oyun başlatmak için Yeni Oyun butonuna basın veya oyunu sonlandırmak için Bitir butonunu kullanın.

## Kod Yapısı
HexGame: Uygulamanın ana sınıfı. JavaFX sahnesini ve bileşenlerini oluşturur.
HexBoard: Oyun tahtasını temsil eder. Hücrelerin yerleşimi, oyunun durumu ve kazanma kontrolleri burada yapılır.
Tile: Her bir altıgen hücreyi temsil eder. Hücrelerin tıklanabilir olmasını ve animasyonları sağlar.

## Katkıda Bulunma
Katkıda bulunmak isterseniz, lütfen önce bir konu açarak ne üzerinde çalışmak istediğinizi belirtin. Daha sonra bir pull request göndererek katkıda bulunabilirsiniz.

## Lisans
Bu proje MIT Lisansı ile lisanslanmıştır. Ayrıntılar için LICENSE dosyasına bakın.

HexGame oynarken iyi eğlenceler! Herhangi bir sorunla karşılaşırsanız, lütfen GitHub'da bir konu açın.
