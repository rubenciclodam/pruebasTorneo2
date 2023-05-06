/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package pruebastorneo2;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author rubic
 */
public class PruebasTorneo2 extends Application {
    
    //Creo el root y la Scene
    VBox root = new VBox();             
    Scene scene = new Scene(root, 400, 400);
    
    //Defino los layouts
    HBox parteAlta = new HBox();
    HBox parteBaja = new HBox();
    VBox parteAltaIzq = new VBox();
    VBox parteAltaDer = new VBox();
    VBox parteBajaIzq = new VBox();
    VBox parteBajaMedio = new VBox();
    VBox parteBajaDer = new VBox();   
    
    @Override
    public void start(Stage primaryStage) {
        
        //Creo controles
        Button botonAgregar = new Button();
        Button botonEliminar = new Button();
        Label labelFijaParticipantes = new Label();
        Label labelNombresParticipantes = new Label();
        
        //Edito controles
        botonAgregar.setText("Añadir participante");
        botonAgregar.setOnAction(new EventHandler<ActionEvent>() {            
            @Override
            public void handle(ActionEvent event) {
                crearBoton();
            }
        });
        
        botonEliminar.setText("Añadir participante");
        botonEliminar.setOnAction(new EventHandler<ActionEvent>() {            
            @Override
            public void handle(ActionEvent event) {
                crearBoton();
            }
        });
        
        labelFijaParticipantes.setText("PARTICIPANTES");
        labelFijaParticipantes.setId("labelFijaParticipantes");
        labelFijaParticipantes.setMaxSize(100, 40);
        labelFijaParticipantes.setMinSize(80, 30);
        labelFijaParticipantes.setPrefSize(100, 40);
        //labelFijaParticipantes.setTranslateX(100);
        //labelFijaParticipantes.setTranslateY(100);
        //labelFijaParticipantes.setMaxSize(100, 40);
        labelNombresParticipantes.setId("labelNombresParticipantes");
        labelNombresParticipantes.setMinSize(80, 30);
        labelNombresParticipantes.setPrefSize(100, 40);
        //labelNombresParticipantes.setTranslateX(100);
        //labelNombresParticipantes.setTranslateY(100);
        
        labelNombresParticipantes.setId("labelNombresParticipantes");
        
        //Asigno jerarquía de layouts
        root.getChildren().add(parteAlta);
        root.getChildren().add(parteBaja);
        parteAlta.getChildren().add(parteAltaIzq);
        parteAlta.getChildren().add(parteAltaDer);
        parteBaja.getChildren().add(parteBajaIzq);
        parteBaja.getChildren().add(parteBajaMedio);
        parteBaja.getChildren().add(parteBajaDer);
        
        //Defino layouts
        parteAltaIzq.setMaxSize(200, 80);
        parteAltaIzq.setMinSize(30, 20);
        parteAltaIzq.setPrefSize(200, 80);
        
        parteAltaDer.setMaxSize(200, 80);
        parteAltaDer.setMinSize(30, 20);
        parteAltaDer.setPrefSize(200, 80);
        
        //Añado controles a los layouts
        parteAltaIzq.getChildren().add(botonAgregar);       
        parteBajaIzq.getChildren().add(labelFijaParticipantes);
        parteBajaIzq.getChildren().add(labelNombresParticipantes);
        

        // Estilos desde CSS	
        scene.getStylesheets().add(getClass().getResource("pruebasTorneo2.css").toExternalForm());
        parteAltaIzq.setId("parteAltaIzq");
        parteAltaDer.setId("parteAltaDer");
        
        primaryStage.setTitle("Torneo");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    void crearBoton(){
        Button button2 = new Button();
        button2.setMaxSize(100, 40);
        button2.setMinSize(30, 20);
        button2.setPrefSize(100, 40);
        //button2.setTranslateX(100);
        //button2.setTranslateY(100);
        button2.setText("Soy el botón nuevo"); 
        this.parteAltaDer.getChildren().add(button2);
    }
  
    public static void main(String[] args) {
        launch(args);
    }
    
}
