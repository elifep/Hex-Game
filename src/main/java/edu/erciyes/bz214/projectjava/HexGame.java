package edu.erciyes.bz214.projectjava;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class HexGame extends Application {

    private HexBoard hexBoard;

    @Override
    public void start(Stage stage) {

        AnchorPane tileMap = new AnchorPane();
        BorderPane root = new BorderPane();
        HBox paneRadio = new HBox();
        Button btnStart = new Button("Start");
        Label statusLabel= new Label("");
        statusLabel.setFont(Font.font("Arial",20));
        statusLabel.setPadding(new Insets(5,5,5,5));
        statusLabel.setTextFill(Color.CADETBLUE);
        Scene scene = new Scene(root, 1150, 720);
        root.setStyle("-fx-background-color: #F5F5F5;");
        stage.setTitle("HEXGAME");
        stage.setScene(scene);
        stage.show();

        paneRadio.setSpacing(10.0);
        paneRadio.setStyle("-fx-border-color: black");
        paneRadio.setPadding(new Insets(5, 5, 5, 5));
        root.setBottom(paneRadio);

        //farklı tahta boyutları için radyo butonları oluşturulur
        RadioButton fiveBtn = new RadioButton("5x5");
        RadioButton elevenBtn = new RadioButton("11x11");
        RadioButton seventeenBtn = new RadioButton("17x17");

        //radyo butonları bir gruba ekleniyor böylece aynı anda sadece biri seçilebilir
        ToggleGroup btnGroup = new ToggleGroup();
        fiveBtn.setToggleGroup(btnGroup);
        elevenBtn.setToggleGroup(btnGroup);
        seventeenBtn.setToggleGroup(btnGroup);
        //radyo butonları ve başlat butonu panel içine eklenir
        paneRadio.getChildren().addAll(fiveBtn, elevenBtn, seventeenBtn);
        paneRadio.getChildren().add(btnStart);

        root.setTop(statusLabel);
        root.setCenter(tileMap);

        hexBoard = new HexBoard(tileMap, statusLabel);

        //radyo butonları için event kontrolü
        fiveBtn.setOnAction(event -> {
            if (fiveBtn.isSelected()) {
                hexBoard.setBoardSize(5, 5);
                hexBoard.refreshTileMap();
            }
        });

        elevenBtn.setOnAction(event -> {
            if (elevenBtn.isSelected()) {
                hexBoard.setBoardSize(11, 11);
                hexBoard.refreshTileMap();
            }
        });

        seventeenBtn.setOnAction(event -> {
            if (seventeenBtn.isSelected()) {
                hexBoard.setBoardSize(17, 17);
                hexBoard.refreshTileMap();
            }
        });

        btnStart.setOnAction(event -> hexBoard.refreshTileMap());
        //uygulama başladığında varsayılan olarak tahta yenileniyor
        hexBoard.refreshTileMap();
    }

    public static void main(String[] args) {
        launch();
}
}