/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package pruebastorneo2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.geometry.HPos;
import static javafx.geometry.HPos.CENTER;
import static javafx.geometry.HorizontalDirection.RIGHT;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import static javafx.geometry.Pos.BOTTOM_CENTER;
import static javafx.geometry.Pos.TOP_CENTER;
import javafx.geometry.VPos;
import static javafx.geometry.VPos.BOTTOM;
import static javafx.geometry.VPos.CENTER;
import static javafx.geometry.VPos.TOP;
import javafx.scene.Group;


import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import static javafx.scene.control.ButtonBar.ButtonData.LEFT;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import static javafx.scene.text.TextAlignment.JUSTIFY;
import static javafx.scene.text.TextAlignment.CENTER;
import static javafx.scene.text.TextAlignment.LEFT;
import javafx.stage.Stage;

/**
 *
 * @author rubic
 */
public class PruebasTorneo2 extends Application {
    
    //Creo el ArrayList de participantes y los demás
    ArrayList<String> listaParticipantesNombre = new ArrayList<String>();
    ArrayList<Participante> listaParticipantesObjeto = new ArrayList<>();
    ArrayList<String> listaParticipantesNombreOrdenados;
    ArrayList<String> listaClasificados;
    ArrayList<String[]>  listaEmparejados;
    ArrayList<String[]> listaSorteados;
    ArrayList<BotonTorneo> listaBotones = new ArrayList<BotonTorneo>();
    ArrayList<Label> listaEtiquetas=new ArrayList<Label>();
    ArrayList<HBox> listaHBox=new ArrayList<HBox>();
    ArrayList<HBox> listaHBoxClasificacion=new ArrayList<HBox>();
    ArrayList<Label> listaLabelPosiciones = new ArrayList<Label>();
    
    //Creo el contenedor y la Scene
    StackPane root = new StackPane();
    VBox contenedor = new VBox();
    Scene scene = new Scene(root);
    
 
    
    
    //Defino los layouts
    HBox parteAlta = new HBox();
    HBox parteBaja = new HBox();
    GridPane parteAltaIzq = new GridPane();
    HBox parteAltaDer = new HBox();
    VBox parteBajaDer = new VBox();
    VBox parteBajaMedio = new VBox();
    VBox parteBajaIzq = new VBox();
    VBox zonaEnfrentamientos = new VBox();
    VBox zonaClasificacion = new VBox();
    ScrollPane spClasificacion = new ScrollPane();
    ScrollPane spEnfrentamientos = new ScrollPane();
    ScrollPane spParticipantes = new ScrollPane();
    HBox cartelesClasificacion = new HBox();
    HBox cartelesEnfrentamientos = new HBox();
    
    
    //Creo controles
    Button botonAgregar = new Button();
    Button botonEliminar = new Button();
    Button botonSortear = new Button();
    Button botonClasificar =new Button();
    TextField cuadroEscribirParticipantes = new TextField();
    Label labelNombresParticipantes = new Label();
    Label labelFijaEnfrentamientos = new Label();
    Label cartelEnfrentamiento = new Label();
    Label cartelGanador = new Label();
    Label labelFijaClasificacion = new Label();
    Label labelFijaParticipantes = new Label();
    
 
    //pongo banderas para desactivar los botones
    boolean sortearActivo = true;
    boolean clasificarActivo = false;
    boolean agregarActivo = true;
    boolean eliminarActivo = true;
    boolean listaCompleta = false;
    
    @Override
    public void start(Stage primaryStage) {
                
        root.getChildren().addAll(contenedor);
        
        //Asigno jerarquía de layouts
        contenedor.getChildren().add(parteAlta);
        contenedor.getChildren().add(parteBaja);
        parteAlta.getChildren().add(parteAltaIzq);
        parteAlta.getChildren().add(parteAltaDer);
        parteBaja.getChildren().add(parteBajaIzq);
        parteBaja.getChildren().add(parteBajaMedio);
        parteBaja.getChildren().add(parteBajaDer);
        
        //Defino layouts
        contenedor.setMinWidth(700);
        //contenedor.setPrefWidth(600);
        //contenedor.setMaxWidth(600);
        
        //parteAltaIzq.setMaxSize(300, 80);
        parteAltaIzq.setMinSize(460, 80);
        //parteAltaIzq.setPrefSize(200, 80);
        
        //parteAltaDer.setMaxSize(300, 80);
        parteAltaDer.setMinSize(457, 80);
        //parteAltaDer.setPrefSize(200, 80);
        
        //parteBajaIzq.setMaxSize(300, 80);
        parteBajaIzq.setMinSize(100, 300);
        //parteBajaIzq.setPrefSize(200, 80);
        
        //parteBajaMedio.setMaxSize(300, 80);
        parteBajaMedio.setMinSize(400, 400);
        //parteBajaMedio.setPrefSize(200, 80);
        
        //parteBajaDer.setMaxSize(300, 80);
        parteBajaDer.setMinSize(200, 300);
        //parteBajaDer.setPrefSize(200, 80);
        
        
        //Asigno controles a layouts y los edito
        cuadroEscribirParticipantes.setId("cuadroEscribirParticipantes");
        //cuadroEscribirParticipantes.setMaxSize(150,50);
        //cuadroEscribirParticipantes.setMinSize(150,50);
        //cuadroEscribirParticipantes.setPrefSize(150,50);
        
        parteAltaDer.setAlignment(Pos.CENTER);
        parteAltaDer.setSpacing(30);
        parteAltaDer.setPadding(new Insets(15));
        
        labelFijaParticipantes.setText("PARTICIPANTES");
        labelFijaParticipantes.getStyleClass().clear();
        labelFijaParticipantes.getStyleClass().add("titulos");
        labelFijaParticipantes.setId("labelFijaParticipantes");
        //labelFijaParticipantes.setMaxSize(100, 40);
        labelFijaParticipantes.setMinSize(200, 50);
        //labelFijaParticipantes.setPrefSize(100, 40);
        //labelFijaParticipantes.setTranslateX(100);
        //labelFijaParticipantes.setTranslateY(100);
        //labelFijaParticipantes.setMaxSize(100, 40);
        
        labelNombresParticipantes.setId("labelNombresParticipantes");    
        labelNombresParticipantes.setMinSize(200,390);
        
        zonaClasificacion.setId("zonaClasificacion");
        zonaClasificacion.setMinSize(300,400);
        zonaClasificacion.setPrefSize(300,400);
        
        //labelNombresParticipantes.setPrefSize(100, 40);
        //labelNombresParticipantes.setTranslateX(100);
        //labelNombresParticipantes.setTranslateY(100);
        
        labelFijaEnfrentamientos.setText("ENFRENTAMIENTOS");
        labelFijaEnfrentamientos.getStyleClass().clear();
        labelFijaEnfrentamientos.getStyleClass().add("titulos");
        labelFijaEnfrentamientos.setId("labelFijaEnfrentamientos");
        //labelFijaEnfrentamientos.setMaxSize(200, 40);
        labelFijaEnfrentamientos.setMinSize(401, 30);
       // labelFijaEnfrentamientos.setPrefSize(200, 40);
        cartelesEnfrentamientos.getChildren().add(cartelEnfrentamiento);
        cartelEnfrentamiento.setMinSize(260, 20);
        cartelEnfrentamiento.setText("ENFRENTAMIENTO");
        cartelEnfrentamiento.setId("cartelEnfrentamiento");
        cartelEnfrentamiento.getStyleClass().clear();
        cartelEnfrentamiento.getStyleClass().add("subtitulos");
        cartelesEnfrentamientos.getChildren().add(cartelGanador);
        cartelGanador.setMinSize(141, 20);
        cartelGanador.setText("GANADOR");
        cartelGanador.setId("cartelGanador");
        cartelGanador.getStyleClass().clear();
        cartelGanador.getStyleClass().add("subtitulos");
        
        
        labelFijaClasificacion.setText("CLASIFICACIÓN");
        labelFijaClasificacion.setId("labelFijaClasificacion");
        labelFijaClasificacion.getStyleClass().clear();
        labelFijaClasificacion.getStyleClass().add("titulos");
        labelFijaClasificacion.setMinSize(316, 30);
        
        Label cartelPosicion = new Label("PUESTO");
        cartelPosicion.setTextAlignment(TextAlignment.LEFT);
        cartelesClasificacion.getChildren().add(cartelPosicion);
        cartelPosicion.setMinSize(70, 20);
        cartelPosicion.getStyleClass().clear();
        cartelPosicion.getStyleClass().add("subtitulos");
        cartelPosicion.setId("cartelPosicion");
        Label cartelClasificados = new Label("NOMBRE");
        cartelClasificados.setTextAlignment(TextAlignment.LEFT);
        cartelesClasificacion.getChildren().add(cartelClasificados);
        cartelClasificados.setMinSize(150, 20);
        cartelClasificados.setId("cartelClasificados");
        cartelClasificados.getStyleClass().clear();
        cartelClasificados.getStyleClass().add("subtitulos");
        Label cartelVictorias = new Label("VICTORIAS");
        cartelVictorias.setTextAlignment(TextAlignment.LEFT);
        cartelesClasificacion.getChildren().add(cartelVictorias);
        cartelVictorias.setMinSize(95, 20);
        cartelVictorias.getStyleClass().clear();
        cartelVictorias.getStyleClass().add("subtitulos");
        cartelVictorias.setId("cartelVictorias");
        

        //Edito controles        
        cuadroEscribirParticipantes.setPromptText("Escribe un nombre");
        cuadroEscribirParticipantes.setPadding(new Insets(5));
        
        botonAgregar.setText("Añadir participante");
        botonAgregar.setMinSize(140, 30);
        botonAgregar.setMaxSize(140, 30);
        botonAgregar.setPrefSize(140, 30);
        botonAgregar.setOnAction(new EventHandler<ActionEvent>() {            
            @Override
            public void handle(ActionEvent event) {
                if(agregarActivo==true){
                String nuevoParticipante = cuadroEscribirParticipantes.getText();
                agregarParticipante(nuevoParticipante);
                listarParticipantes();
                cuadroEscribirParticipantes.clear();
                }
            }
        });
        
        botonEliminar.setText("Eliminar participante");
        botonEliminar.setMinSize(140, 30);
        botonEliminar.setMaxSize(140, 30);
        botonEliminar.setPrefSize(140, 30);
        botonEliminar.setOnAction(new EventHandler<ActionEvent>() {            
            @Override
            public void handle(ActionEvent event) {
                if(eliminarActivo==true){
                    String sobraParticipante = cuadroEscribirParticipantes.getText();
                    eliminarParticipante(sobraParticipante);
                    listarParticipantes();
                    cuadroEscribirParticipantes.clear();                   
                }

            }
        });
        
        botonSortear.setText("Sortear");
        botonSortear.setMinSize(140, 30);
        botonSortear.setMaxSize(140, 30);
        botonSortear.setPrefSize(140, 30);
        botonSortear.setOnAction(new EventHandler<ActionEvent>() {            
            @Override
            public void handle(ActionEvent event) {
                if(sortearActivo==true){
                    sortearActivo=false;
                    clasificarActivo=true;
                    agregarActivo=false;
                    eliminarActivo=false;
                    sortear(ordenarEmparejamientos(combinatoria(listaParticipantesNombre)));
                    //Dibujo los botones y las etiquetas de los enfrentamientos
                    for (int i=0; i<listaEtiquetas.size(); i++){

                        HBox hbox = new HBox();

                        hbox.getStyleClass().add("hboxEnfrentamiento");

                        for (int j=0; j<listaBotones.size();j++){
                            if(listaBotones.get(j).getEnfrentamiento()==i+1){
                                hbox.getChildren().add(listaBotones.get(j));     
                            }
                       }

                        hbox.getChildren().add(listaEtiquetas.get(i));
                        listaEtiquetas.get(i).setText("");

                        zonaEnfrentamientos.getChildren().add(hbox);
                        listaHBox.add(hbox);

                    }

                    //doy funcionalidad a los botones para que pongan su texto
                    //en la etiqueta correspondiente
                    for (int k=0; k<listaBotones.size();k++){
                        int n=k;
                        listaBotones.get(n).setOnAction(new EventHandler<ActionEvent>(){
                            @Override
                            public void handle(ActionEvent event) {
                                for (int m=0; m<listaEtiquetas.size();m++){
                                    if(("label"+listaBotones.get(n).getEnfrentamiento()).equals(listaEtiquetas.get(m).getId())){
                                        //listaEtiquetas.get(m).setText(listaBotones.get(k).getText());
                                        listaEtiquetas.get(m).setText(listaBotones.get(n).getText());
                                    }
                                }
                            }            
                        });
                    }                     
                }
            }       
        });
        
        botonClasificar.setText("Clasificar");
        botonClasificar.setMinSize(140, 30);
        botonClasificar.setMaxSize(140, 30);
        botonClasificar.setPrefSize(140, 30);
        botonClasificar.setOnAction(new EventHandler<ActionEvent>() {            
            @Override
            public void handle(ActionEvent event) {
                int contadorGanadores=0;
                for (Label p: listaEtiquetas){
                    if(!p.getText().equals("")){
                        contadorGanadores+=1;               
                    }
                }
                if(contadorGanadores==listaEtiquetas.size()){
                    listaCompleta=true;
                }
                if(clasificarActivo==true && listaCompleta==true){
                    clasificarActivo=false;
                for (Participante i: listaParticipantesObjeto){
                    for (Label j: listaEtiquetas){
                        if(j.getText().equals(i.getNombre())){
                            i.setVictorias(i.getVictorias()+1);
                        }
                    }
                    calcularPuesto(i);
                }
                Collections.sort(listaParticipantesObjeto);
                           

                //Dibujo los datos de clasificación
                for (int i=0; i<listaParticipantesObjeto.size(); i++){
                

                    HBox hbox2 = new HBox();                    

                    Label posicion = new Label();
                    //listaLabelPosiciones.add(new Label("hola"));
                    posicion.setText(String.valueOf(listaParticipantesObjeto.get(i).getPuesto()));
                    posicion.setMinSize(cartelPosicion.getMinWidth(), 25);
                    posicion.setMaxSize(cartelPosicion.getMaxWidth(), 25);
                    posicion.setPrefSize(cartelPosicion.getPrefWidth(), 25);                                     
                    hbox2.getChildren().add(posicion);
                    posicion.getStyleClass().add("centrarTexto");
                    if(listaParticipantesObjeto.get(i).getPuesto()==1){
                        posicion.getStyleClass().add("textoGrande");
                    }
                    

                    Label participanteClasificado = new Label();
                    participanteClasificado.setText(listaParticipantesObjeto.get(i).getNombre());
                    participanteClasificado.setMinSize(cartelClasificados.getMinWidth(), 25);
                    participanteClasificado.setMaxSize(cartelClasificados.getMinWidth(), 25);
                    participanteClasificado.setPrefSize(cartelClasificados.getMinWidth(), 25);
                    hbox2.getChildren().add(participanteClasificado);
                    participanteClasificado.setPadding(new Insets(1,1,1,3)); 
                    if(listaParticipantesObjeto.get(i).getPuesto()==1){
                        participanteClasificado.getStyleClass().add("textoGrande");
                    }                    

                    Label victorias = new Label();
                    victorias.setText(String.valueOf(listaParticipantesObjeto.get(i).getVictorias()));
                    victorias.setMinSize(cartelVictorias.getMinWidth(), 25);
                    victorias.setMaxSize(cartelVictorias.getMaxWidth(), 25);
                    victorias.setPrefSize(cartelVictorias.getPrefWidth(), 25);
                    hbox2.getChildren().add(victorias);
                    victorias.getStyleClass().add("centrarTexto");
                    if(listaParticipantesObjeto.get(i).getPuesto()==1){
                        victorias.getStyleClass().add("textoGrande");
                    }                    
                    
                    hbox2.setId("filaClasificados"+i);

                    zonaClasificacion.getChildren().add(hbox2);
                    listaHBoxClasificacion.add(hbox2);
                }                   

            }
         }          
        });        
        
        
   
        //listaHBoxClasificacion.get(0).setId("formatoGanador");
        
        //Añado controles a los layouts
        
        parteAltaIzq.getChildren().addAll(botonAgregar, botonEliminar, cuadroEscribirParticipantes);

        parteAltaDer.getChildren().addAll(botonSortear);
        parteAltaDer.getChildren().addAll(botonClasificar);
        
        parteBajaDer.getChildren().add(labelFijaClasificacion);
        parteBajaDer.getChildren().add(cartelesClasificacion);
        parteBajaDer.getChildren().add(spClasificacion); 

        parteBajaMedio.getChildren().add(labelFijaEnfrentamientos);
        parteBajaMedio.getChildren().add(cartelesEnfrentamientos);
        parteBajaMedio.getChildren().add(spEnfrentamientos);
        
        parteBajaIzq.getChildren().add(labelFijaParticipantes);
        parteBajaIzq.getChildren().add(spParticipantes);
        

        
     
        
        
        
        //Ubico controles en layouts
        parteAltaIzq.setConstraints(botonAgregar, 0, 0); // column=2 row=0
        parteAltaIzq.setConstraints(botonEliminar, 1, 0);
        parteAltaIzq.setConstraints(cuadroEscribirParticipantes, 0, 1, 2, 1, HPos.CENTER, VPos.CENTER);
        parteAltaIzq.setPadding(new Insets(10));
        parteAltaIzq.setVgap(10);
        parteAltaIzq.setHgap(20);
        parteAltaIzq.setAlignment(TOP_CENTER);

        // Estilos desde CSS	
        scene.getStylesheets().add(getClass().getResource("pruebasTorneo2.css").toExternalForm());
        parteAltaIzq.setId("parteAltaIzq");
        parteAltaDer.setId("parteAltaDer");
        parteBajaIzq.setId("parteBajaIzq");
        zonaEnfrentamientos.setId("zonaEnfrentamientos");
        parteBajaMedio.setId("parteBajaMedio");
        parteBaja.setId("parteBaja");
        contenedor.setId("contenedor");
        root.setId("root");
        
        //spEnfrentamientos.setStyle("-fx-background-color: #dddddd");
        //root.setStyle("-fx-background-color: dddddd");
        //parteBajaMedio.setStyle("-fx-background-color: dddddd");
        //parteBaja.setStyle("-fx-background-color: dddddd");
        
        
        //Ajusto el scroll
        spClasificacion.setId("spClasificacion");
        spClasificacion.setContent(zonaClasificacion);
        spClasificacion.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        spClasificacion.setMinSize(200, 400);
        spEnfrentamientos.setId("spEnfrentamientos");
        spEnfrentamientos.setContent(zonaEnfrentamientos);
        spEnfrentamientos.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS); 
        spEnfrentamientos.setMinSize(400, 400);
        spParticipantes.setId("spParticipantes");
        spParticipantes.setContent(labelNombresParticipantes);
        spParticipantes.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        spParticipantes.setMinSize(200, 400);


        
        zonaEnfrentamientos.setMinSize(spEnfrentamientos.getMinWidth(), spEnfrentamientos.getMinHeight()-10);
        //spEnfrentamientos.setPadding(new Insets(10,10,10,10));
        //listaLabelPosiciones.get(1).setText("45");
        /*
        if(listaHBoxClasificacion.size()!=0){
            listaHBoxClasificacion.get(0).getStyleClass().add("hboxganador");
        }
        
        */
                      
        
        primaryStage.setTitle("Torneo");
        primaryStage.setScene(scene);
        
        
        primaryStage.show();

    }
   
    void agregarParticipante(String participante){
       
        if(comprobarParticipante(participante)==true){
           cuadroEscribirParticipantes.setPromptText("Ese participante ya existe"); 
        }
        else if(participante.equals("")){
           cuadroEscribirParticipantes.setPromptText("Escribe un nombre"); 
        }
        else{
            listaParticipantesNombre.add(participante);
            listaParticipantesObjeto.add(new Participante(participante));
            cuadroEscribirParticipantes.setPromptText("Escribe un nombre");
        }
       
/* Cosas para controlar las medidas y el scroll 
        listaParticipantesNombre.add(participante);
        cuadroEscribirParticipantes.setText(String.valueOf(contenedor.getHeight()));
*/
    }
    
    void eliminarParticipante(String participante){
        
        if(comprobarParticipante(participante)==true){
            listaParticipantesNombre.remove(participante);
            
            Participante paraEliminar = new Participante();
            for(Participante i: listaParticipantesObjeto){
                if(i.getNombre().equals(participante)){
                    paraEliminar=i;
                }               
            }
            listaParticipantesObjeto.remove(paraEliminar);
            cuadroEscribirParticipantes.setPromptText("Escribe un nombre");
        }else{
            cuadroEscribirParticipantes.setPromptText("Ese participante no existe"); 
        }
    }

    void listarParticipantes(){
        String texto = "";
        int contador=1;
        for (String i: listaParticipantesNombre){
            texto=texto+" "+contador+". "+i+"\n";
            contador+=1;
        }
        labelNombresParticipantes.setText(texto);
    }    
    
    boolean comprobarParticipante(String participante){
        boolean existe=false;
        for (String i: listaParticipantesNombre){
            if (i.equals(participante)){
                existe=true;
            }
        }
        return existe;
    }
    
    void sortear(ArrayList<String[]> sorteados){
        
        //Creo lista de participantes según su orden en el sorteo
        ArrayList<String> participantesOrdenados = new ArrayList<String>();
        for (String[] i: sorteados){
            participantesOrdenados.add(i[0]);
            participantesOrdenados.add(i[1]);
        }
        
        //Creo lista de botones según su orden (ojo, las posiciones en la lista
        //empiezan por el índice cero, pero los id empiezan por el 1, así que 
        //el elemento en posición 0 de la lista tendrá como id "boton1" Y el atributo
        //posicionenLista empieza por 0
        for (int i=0; i<participantesOrdenados.size(); i++){
            BotonTorneo boton = new BotonTorneo();
            boton.setId("boton"+(i+1));
            boton.getStyleClass().add("botonesEnfrentamiento");
            boton.setMinSize(130,25);
            boton.setMaxSize(130,25);
            boton.setPrefSize(130,25);
            if(i%2==0){
               boton.setColumna(1); 
            }else{
                boton.setColumna(2);
            }
            boton.setText(participantesOrdenados.get(i));

            boton.setPosicionEnLista(i);
            if(i%2==0){
               boton.setEnfrentamiento(i/2+1); 
            }else{
                boton.setEnfrentamiento((Math.round(i/2+0.5f)));
            }
            
            listaBotones.add(boton);
        }
        
        //Creo lista de etiquetas según su orden. El índice de la lista empieza
        //por cero y el id por "label1"
        for (int j=0; j<sorteados.size();j++){
            Label etiqueta = new Label();
            etiqueta.setId("label"+(j+1));
            etiqueta.getStyleClass().add("etiquetasGanadores");
            etiqueta.setPadding(new Insets(1,1,1,3));
            etiqueta.setMinSize(zonaEnfrentamientos.getMinWidth()-2*listaBotones.get(0).getMinWidth()-15,25);
            etiqueta.setMaxSize(zonaEnfrentamientos.getMinWidth()-2*listaBotones.get(0).getMinWidth()-15,25);
            etiqueta.setPrefSize(zonaEnfrentamientos.getMinWidth()-2*listaBotones.get(0).getMinWidth()-15,25);
            listaEtiquetas.add(etiqueta);           
        }   
    }
    
    
    ArrayList<String[]> ordenarEmparejamientos(ArrayList<String[]> emparejados){
        
        //los ordeno primero de forma aleatoria con random
        int talla = emparejados.size();
        ArrayList<Integer> orden = new ArrayList<Integer>();
        while (orden.size()<talla){
            int numero = (int)(Math.random()*(talla));
            if(!orden.contains(numero)){
                orden.add(numero);
            }
        }       
        ArrayList<String[]> aleatorios=new ArrayList<String[]>();
        for (int i=0;i<talla;i++){
            aleatorios.add(emparejados.get(orden.get(i)));
        }

        //una vez ordenados de forma aleatoria hago aplico el algoritmo para
        //separar los enfrentamientos de cada jugador
        
        
        ArrayList<String[]> listanueva= new ArrayList<String[]>();        
        ArrayList<String[]> listarotante=aleatorios;
        ArrayList<String[]> listarotante2=new ArrayList<String[]>();
        
        boolean vale1=true;
        while(!listarotante.isEmpty() && vale1==true){
            for(int i=0; i<listarotante.size(); i++){
                vale1 = true;
                if ((listanueva.size()==1 && (listarotante.get(i)[0].equals(listanueva.get(listanueva.size()-1)[0]) || 
                        listarotante.get(i)[0].equals(listanueva.get(listanueva.size()-1)[1]) || 
                        listarotante.get(i)[1].equals(listanueva.get(listanueva.size()-1)[0]) ||
                        listarotante.get(i)[1].equals(listanueva.get(listanueva.size()-1)[1]))) ||                                 
                    (listanueva.size()>1 && (listarotante.get(i)[0].equals(listanueva.get(listanueva.size()-1)[0]) || 
                        listarotante.get(i)[0].equals(listanueva.get(listanueva.size()-1)[1]) || 
                        listarotante.get(i)[1].equals(listanueva.get(listanueva.size()-1)[0]) ||
                        listarotante.get(i)[1].equals(listanueva.get(listanueva.size()-1)[1]) ||            
                        listarotante.get(i)[0].equals(listanueva.get(listanueva.size()-2)[0]) ||
                        listarotante.get(i)[0].equals(listanueva.get(listanueva.size()-2)[1]) || 
                        listarotante.get(i)[1].equals(listanueva.get(listanueva.size()-2)[0]) ||
                        listarotante.get(i)[1].equals(listanueva.get(listanueva.size()-2)[1])))){
                    vale1=false;
                }  
                
                if (vale1==true){
                    listanueva.add(listarotante.get(i));
                    listarotante.remove(i);
                    listarotante2.clear();
                    
                    if(!listarotante.isEmpty()){
                        for(int j=i; j<listarotante.size(); j++){
                            listarotante2.add(listarotante.get(j));
                        }
                        for(int j=0; j<i; j++){
                            listarotante2.add(listarotante.get(j));
                        }                    

                        listarotante.clear();                        
                    }

                    if(!listarotante2.isEmpty()){
                        for(int k=0; k<listarotante2.size(); k++){
                            listarotante.add(listarotante2.get(k));
                            
                        }                    
                    }
                    break;
                }               
            }
        }
              
        boolean vale2=true;    
        while(!listarotante.isEmpty() && vale2==true){
            for(int i=0; i<listarotante.size(); i++){
                vale2 = true;
                if ((listanueva.size()>1 && (listarotante.get(i)[0].equals(listanueva.get(listanueva.size()-1)[0]) || 
                        listarotante.get(i)[0].equals(listanueva.get(listanueva.size()-1)[1]) || 
                        listarotante.get(i)[1].equals(listanueva.get(listanueva.size()-1)[0]) ||
                        listarotante.get(i)[1].equals(listanueva.get(listanueva.size()-1)[1])))){
                    vale2=false;
                }  
                
                if (vale2==true){
                    listanueva.add(listarotante.get(i));
                    listarotante.remove(i);
                    listarotante2.clear();
                    
                    if(!listarotante.isEmpty()){
                        for(int j=i; j<listarotante.size(); j++){
                            listarotante2.add(listarotante.get(j));
                        }
                        for(int j=0; j<i; j++){
                            listarotante2.add(listarotante.get(j));
                        }                    

                        listarotante.clear();                        
                    }

                    if(!listarotante2.isEmpty()){
                        for(int k=0; k<listarotante2.size(); k++){
                            listarotante.add(listarotante2.get(k));
                            
                        }                    
                    }
                    break;
                }               
            }
        }
        while(!listarotante.isEmpty()){
            for(int i=0; i<listarotante.size(); i++){
                    listanueva.add(listarotante.get(i));
                    listarotante.remove(i);
                    listarotante2.clear();
                    
                    if(!listarotante.isEmpty()){
                        for(int j=i; j<listarotante.size(); j++){
                            listarotante2.add(listarotante.get(j));
                        }
                        for(int j=0; j<i; j++){
                            listarotante2.add(listarotante.get(j));
                        }                    
                        listarotante.clear();                        
                    }
                    if(!listarotante2.isEmpty()){
                        for(int k=0; k<listarotante2.size(); k++){
                            listarotante.add(listarotante2.get(k));                            
                        }                    
                    }
                    break;
                }               
        } 
        return listanueva;   
    }
    
    
    ArrayList<String[]> combinatoria(ArrayList<String> participantes){
        ArrayList<String[]> emparejamientos = new ArrayList<String[]>();
        String[] nuevoArray1=new String[2];
        String[] nuevoArray2=new String[2];       
        for(int i=0; i<participantes.size(); i++){
            for (int j=0; j<participantes.size(); j++){
                boolean vale=true;
                nuevoArray1[0]=participantes.get(j);
                nuevoArray1[1]=participantes.get(i);
                nuevoArray2[0]=participantes.get(i);
                nuevoArray2[1]=participantes.get(j);
                
                for (String[] k: emparejamientos){             
                    if ((k[0].equals(participantes.get(j)) && k[1].equals(participantes.get(i))) || nuevoArray2[0].equals(nuevoArray2[1])){
                        vale=false;
                    }
                }
                
                if (i==0 && j==0){
                    vale=false;
                }
                
                if(vale==true){
                    emparejamientos.add(new String[2]);
                    emparejamientos.get(emparejamientos.size()-1)[0]=participantes.get(i);
                    emparejamientos.get(emparejamientos.size()-1)[1]=participantes.get(j);
                
                }
            }
        }
        return emparejamientos;
    }
    
    void calcularPuesto(Participante participante){
        
        ArrayList<Participante> listaParticipantesObjetoCopia =new ArrayList<Participante>();
        for(Participante i: listaParticipantesObjeto){
            listaParticipantesObjetoCopia.add(i);
        }
        int pos = 1;
        int contador = 1;
        while (listaParticipantesObjetoCopia.size()>0){
            int max = 0;
            for (Participante i: listaParticipantesObjetoCopia){
                if(i.getVictorias()>=max){
                    max=i.getVictorias();
                }
            }
        
            for (int j=(listaParticipantesObjetoCopia.size()-1); j>=0; j--){
                if (listaParticipantesObjetoCopia.get(j).getVictorias()==max){
                    listaParticipantesObjetoCopia.get(j).setPuesto(pos);
                    listaParticipantesObjetoCopia.remove(j);
                    contador+=1;
                }
            }
            pos=contador;
        }    
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
}
