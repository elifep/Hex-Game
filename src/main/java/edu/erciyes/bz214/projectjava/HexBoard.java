package edu.erciyes.bz214.projectjava;

import javafx.animation.FadeTransition;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.LinkedList;
import java.util.Queue;

public class HexBoard {
    public final static double r = 24;//Altıgen hücrelerin yarıçapı.
    public final static double n = r * Math.sqrt(3) / 2;//Altıgenin yüksekliğinin yarısı
    public final static double tileHeight = 2 * r;//altıgenin tam yüksekliği
    public final static double tileWidht = 2 * n;//altıgenin tam genişliği

    private int rowsNum = 5;//satır sayısı
    private int columnNum = 5;//sütun sayısı
    private final int horizontalOffset = 40;//tahtanın ekrandaki dikey kayması
    private final int verticalOffset = 40;//tahtanın ekrandaki yatay kayması

    private Color playerColor = Color.BLUE;//ilk oyuncu rengi mavi
    private Tile[][] board;//tahtayı temsil eden tile nesnelerinin matrisi
    private int counter = 0;//hamle sayısı
    private boolean gameOver = false;//oyunun bitip bitmediğini belirten bayrak

    private final AnchorPane tileMap;//tahtanın yerleştirildiği anchorpane
    private final Label statusLabel;//oyunun durumunu gösteren label

    public HexBoard(AnchorPane tileMap, Label statusLabel) {//constructor method bileşenler için başlatma yapar
        this.tileMap = tileMap;
        this.statusLabel = statusLabel;
    }

    public void setBoardSize(int rowsNum, int columnNum) {//tahtanın boyutlarını ayarlar
        this.rowsNum = rowsNum;
        this.columnNum = columnNum;
    }

    public void refreshTileMap() {//tahtayı ve hücreleri sıfırlar yeni oyun başlatır hücreleri ekrana yerleştirir
        tileMap.getChildren().clear();
        board = new Tile[columnNum][rowsNum];
        counter = 0;
        statusLabel.setText("Game started! Please pick up the board size.");
        gameOver = false;
        for (int x = 0; x < columnNum; x++) { 
            for (int y = 0; y < rowsNum; y++) {
                double xPosition = x * tileWidht + y * n + horizontalOffset;
                double yPosition = y * tileHeight * 0.75 + verticalOffset;

                Tile tile = new Tile(xPosition, yPosition, x, y, this, r, n, tileWidht);
                board[x][y] = tile;
                tileMap.getChildren().add(tile);
            }
        }
    }

    public void checkWinner() {//oyun bittiyse döner bitmemişse kazanma durumunu kontrol edip oyunu bitirir
        if (gameOver) return;
        if (checkWinner(Color.BLUE)) {
            endGame("Blue player wins!\t");
        } else if (checkWinner(Color.RED)) {
            endGame("Red player wins!\t");
        }
    }

    private boolean checkWinner(Color color) {
        //belirli bir rengin kazanma durumunu kontrol eder. BFS algoritması kullanarak, her iki oyuncunun da kazanmaya çalıştığı doğrultuda hücreleri ziyaret eder
        boolean[][] visited = new boolean[columnNum][rowsNum];
        Queue<Tile> queue = new LinkedList<>();

        if (color == Color.BLUE) {
            for (int y = 0; y < rowsNum; y++) {
                if (board[0][y].getFill() == color) {
                    queue.add(board[0][y]);
                    visited[0][y] = true;
                }
            }
        } else {
            for (int x = 0; x < columnNum; x++) {
                if (board[x][0].getFill() == color) {
                    queue.add(board[x][0]);
                    visited[x][0] = true;
                }
            }
        }

        while (!queue.isEmpty()) {
            Tile tile = queue.poll();
            int x = tile.getX();
            int y = tile.getY();

            if (color == Color.BLUE && x == columnNum - 1) {
                return true;
            }
            if (color == Color.RED && y == rowsNum - 1) {
                return true;
            }

            for (Tile neighbor : getNeighbors(x, y)) {
                int nx = neighbor.getX();
                int ny = neighbor.getY();
                if (!visited[nx][ny] && board[nx][ny].getFill() == color) {
                    queue.add(board[nx][ny]);
                    visited[nx][ny] = true;
                }
            }
        }
        return false;
    }

    private Tile[] getNeighbors(int x, int y) {//verilen bir hücrenin komşu hücrelerini döndürür
        int[][] directions = { {1, 0}, {-1, 0}, {0, 1}, {0, -1}, {1, -1}, {-1, 1} };

        LinkedList<Tile> neighbors = new LinkedList<>();
        for (int[] drctn : directions) {
            int nx = x + drctn[0];
            int ny = y + drctn[1];
            if (nx >= 0 && nx < columnNum && ny >= 0 && ny < rowsNum) {
                neighbors.add(board[nx][ny]);
            }
        }
        return neighbors.toArray(new Tile[0]);
    }

    private void endGame(String message) {
        //oyunu bitirir ve durumu günceller. Bir fade animasyonu ile oyun durumunu gösterir.
        statusLabel.setText(message + " Attempts: " + counter);
        FadeTransition ft = new FadeTransition(Duration.millis(1000), statusLabel);
        ft.setCycleCount(4);
        ft.setFromValue(1.0);
        ft.setToValue(0.1);
        ft.setAutoReverse(true);
        ft.play();
        statusLabel.setText(message + " Attempts: " + counter);
        Button newGameButton = new Button("Yeni Oyun");
        newGameButton.setOnAction(e -> {
            statusLabel.setText("");
            refreshTileMap();
        });

        Button finishButton = new Button("Bitir");
        finishButton.setOnAction(e -> Platform.exit());

        VBox messageBox = new VBox(10, statusLabel, newGameButton, finishButton);
        messageBox.setPadding(new Insets(10));
        messageBox.setStyle("-fx-border-color: black; -fx-background-color: white;");

        Stage messageStage = new Stage();
        Scene messageScene = new Scene(messageBox);
        messageStage.setScene(messageScene);
        messageStage.setTitle("Oyun Sonu");
        messageStage.show();
        gameOver = true;
    }

    public void changePlayerColor() {
        playerColor = (playerColor == Color.BLUE) ? Color.RED : Color.BLUE;
    }

    public Color getPlayerColor() {
        return playerColor;
    }

    public Tile getTile(int x, int y) {
        return board[x][y];
    }

    public void incrementCounter() {
        counter++;
    }

    public int getCounter() {
        return counter;
    }

    public boolean isGameOver() {
        return gameOver;
    }
}