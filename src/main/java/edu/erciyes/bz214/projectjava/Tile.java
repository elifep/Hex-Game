package edu.erciyes.bz214.projectjava;

import javafx.animation.ScaleTransition;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.util.Duration;

public class Tile extends Polygon {//oyun tahtasında bir hücreyi temsil eder
    //Bu sınıf JavaFX'in Polygon sınıfını extend eder ve her bir hücrenin tıklanabilir bir altıgen olmasını sağlar
    private final int x, y;
    private final HexBoard board;

    public Tile(double xCoord, double yCoord, int x, int y, HexBoard board, double r, double n, double tileWidth) {
       //constructor method
        this.x = x;
        this.y = y;
        this.board = board;

        getPoints().addAll(//Altıgenin köşe noktaları belirlenip getPoints() metoduyla Polygon sınıfının points listesine ekleniyor.
                xCoord, yCoord,
                xCoord, yCoord + r,
                xCoord + n, yCoord + 1.5 * r,
                xCoord + tileWidth, yCoord + r,
                xCoord + tileWidth, yCoord,
                xCoord + n, yCoord - 0.5 * r
        );

        //Hücrenin başlangıç rengi beyaz olarak ayarlanır ve sınırları siyah renkle çizilir.
        setFill(Color.WHITE);
        setStrokeWidth(2);
        setStroke(Color.BLACK);

        setOnMouseClicked(e -> {
            //Eğer hücrenin rengi beyazsa ve oyun bitmemişse hücrenin rengi oyuncunun rengine değiştirilir.
            if (getFill() == Color.WHITE && !board.isGameOver()) {
                setFill(board.getPlayerColor());
                board.incrementCounter();//Tahtadaki hamle sayısı artırılır.
                board.changePlayerColor();//Sıradaki oyuncuya geçiş yapılır.
                board.checkWinner();//Kazanan kontrol edilir.
                System.out.println(" The player won on the attempt : " + board.getCounter());

                // hücreye tıklanınca animasyon çıkması
                ScaleTransition st = new ScaleTransition(Duration.millis(200), this);
                st.setByX(0.5);
                st.setByY(0.3);
                st.setAutoReverse(true);
                st.setCycleCount(2);
                st.play();
            }
        });
    }

    //Hücrenin tahtadaki koordinatlarını döndürmek için get methodları kullanılır.
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
