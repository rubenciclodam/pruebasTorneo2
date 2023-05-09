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
import javafx.geometry.Orientation;
import javafx.geometry.VPos;
import static javafx.geometry.VPos.CENTER;
import javafx.scene.Group;


import javafx.scene.Scene;
import javafx.scene.control.Button;
import static javafx.scene.control.ButtonBar.ButtonData.LEFT;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
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
    
    //Creo el contenedor y la Scene
    Group root = new Group();
    VBox contenedor = new VBox();   
    ScrollBar sc = new ScrollBar();
    Scene scene = new Scene(root, 400, 400);
    
 
    
    
    //Defino los layouts
    HBox parteAlta = new HBox();
    HBox parteBaja = new HBox();
    GridPane parteAltaIzq = new GridPane();
    HBox parteAltaDer = new HBox();
    VBox parteBajaIzq = new VBox();
    VBox parteBajaMedio = new VBox();
    VBox parteBajaDer = new VBox();

    //Creo controles
    Button botonAgregar = new Button();
    Button botonEliminar = new Button();
    Button botonSortear = new Button();
    Button botonClasificar =new Button();
    TextField cuadroEscribirParticipantes = new TextField();
    Label labelFijaParticipantes = new Label();
    Label labelNombresParticipantes = new Label();
    Label labelFijaEnfrentamientos = new Label();
    Label labelFijaGanador = new Label();
    Label labelFijaClasificacion = new Label();
    
    //pongo banderas para desactivar los botones
    boolean sortearActivo = true;
    boolean clasificarActivo = false;
    boolean agregarActivo = true;
    boolean eliminarActivo = true;
    boolean listaCompleta = false;
    
    @Override
    public void start(Stage primaryStage) {
                
        root.getChildren().addAll(contenedor, sc);
              
        //Edito controles
        
        cuadroEscribirParticipantes.setPromptText("Escribe un nombre");
        
        botonAgregar.setText("Añadir participante");
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
        
        botonSortear.setText("SORTEAR");
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

                        for (int j=0; j<listaBotones.size();j++){
                            if(listaBotones.get(j).getEnfrentamiento()==i+1){
                                hbox.getChildren().add(listaBotones.get(j));     
                            }
                       }

                        hbox.getChildren().add(listaEtiquetas.get(i));
                        listaEtiquetas.get(i).setText("");

                        parteBajaMedio.getChildren().add(hbox);
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
        
        botonClasificar.setText("CLASIFICAR");
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
                }
                Collections.sort(listaParticipantesObjeto);
                           
                //dibujo la parte de clasificación            
                HBox cartelesClasificacion = new HBox();
                Label cartelPosicion = new Label("PUESTO");
                cartelesClasificacion.getChildren().add(cartelPosicion);    

                Label cartelClasificados = new Label("NOMBRE");
                cartelesClasificacion.getChildren().add(cartelClasificados); 

                Label cartelVictorias = new Label("VICTORIAS");
                cartelesClasificacion.getChildren().add(cartelVictorias);

                parteBajaDer.getChildren().add(cartelesClasificacion);

                for (int i=0; i<listaParticipantesObjeto.size(); i++){

                    HBox hbox2 = new HBox();

                    Label posicion = new Label();
                    posicion.setText(String.valueOf(i+1));
                    hbox2.getChildren().add(posicion);

                    Label participanteClasificado = new Label();
                    participanteClasificado.setText(listaParticipantesObjeto.get(i).getNombre());
                    hbox2.getChildren().add(participanteClasificado);

                    Label victorias = new Label();
                    victorias.setText(String.valueOf(listaParticipantesObjeto.get(i).getVictorias()));
                    hbox2.getChildren().add(victorias);                

                    parteBajaDer.getChildren().add(hbox2);
                    listaHBoxClasificacion.add(hbox2);
                }                   

            }
 
         }   
            
            
        });        
        
        
        cuadroEscribirParticipantes.setId("cuadroEscribirParticipantes");
        cuadroEscribirParticipantes.setMaxSize(150,50);
        cuadroEscribirParticipantes.setMinSize(150,50);
        cuadroEscribirParticipantes.setPrefSize(150,50);
        
        
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
        //labelNombresParticipantes.setPrefSize(100, 40);
        //labelNombresParticipantes.setTranslateX(100);
        //labelNombresParticipantes.setTranslateY(100);
        
        labelFijaEnfrentamientos.setText("ENFRENTAMIENTOS");
        labelFijaEnfrentamientos.setId("labelFijaEnfrentamientos");
        labelFijaEnfrentamientos.setMaxSize(200, 40);
        labelFijaEnfrentamientos.setMinSize(80, 30);
        labelFijaEnfrentamientos.setPrefSize(200, 40);
        
        //Asigno jerarquía de layouts
        contenedor.getChildren().add(parteAlta);
        contenedor.getChildren().add(parteBaja);
        parteAlta.getChildren().add(parteAltaIzq);
        parteAlta.getChildren().add(parteAltaDer);
        parteBaja.getChildren().add(parteBajaIzq);
        parteBaja.getChildren().add(parteBajaMedio);
        parteBaja.getChildren().add(parteBajaDer);
        
        //Defino layouts
        
        contenedor.setMaxSize(400, 2000);
        contenedor.setMinSize(400, 400);
        
        parteAltaIzq.setMaxSize(200, 80);
        parteAltaIzq.setMinSize(200, 80);
        parteAltaIzq.setPrefSize(200, 80);
        
        parteAltaDer.setMaxSize(200, 80);
        parteAltaDer.setMinSize(30, 20);
        parteAltaDer.setPrefSize(200, 80);
        
        //Añado controles a los layouts
        
        parteAltaIzq.getChildren().addAll(botonAgregar, botonEliminar, cuadroEscribirParticipantes);

        parteAltaDer.getChildren().addAll(botonSortear);
        parteAltaDer.getChildren().addAll(botonClasificar);
        
        parteBajaIzq.getChildren().add(labelFijaParticipantes);
        parteBajaIzq.getChildren().add(labelNombresParticipantes);
        
        parteBajaMedio.getChildren().add(labelFijaEnfrentamientos);
        
        parteBajaDer.getChildren().add(labelFijaClasificacion);
        
             
        
        
        //Ubico controles en layouts
        parteAltaIzq.setConstraints(botonAgregar, 0, 0); // column=2 row=0
        parteAltaIzq.setConstraints(botonEliminar, 1, 0);
        parteAltaIzq.setConstraints(cuadroEscribirParticipantes, 0, 1, 2, 1, HPos.CENTER, VPos.CENTER);
        
        

        // Estilos desde CSS	
        scene.getStylesheets().add(getClass().getResource("pruebasTorneo2.css").toExternalForm());
        parteAltaIzq.setId("parteAltaIzq");
        parteAltaDer.setId("parteAltaDer");
        
        primaryStage.setTitle("Torneo");
        primaryStage.setScene(scene);
        
        
        //Ajusto el scroll
        sc.setLayoutX(scene.getWidth()-sc.getWidth());
        sc.setMin(0);
        sc.setMax(800);  
        sc.setOrientation(Orientation.VERTICAL);
        //sc.setPrefHeight(400);
        //double medida = Double.(String.valueOf(contenedor.getHeight()));
        double medida = 400;
        sc.setPrefHeight(medida);
        
        sc.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,
                Number old_val, Number new_val) {
                    contenedor.setLayoutY(-new_val.doubleValue());
            }
        });
        

        
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
    
    public static void main(String[] args) {
        launch(args);
    }
    
}
