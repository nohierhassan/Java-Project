
package com.shahukhalroshan.tictactoe;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class replayGame {
    
    private Button[] buttons = new Button[9];
    //private int counter = 0;
    private Stage stage;
    private Scene scene;
    private ResultSet rs;
    private Button nextButton;
    private Button newGameButton;
    private Button exitButton;
   // private int[] order = new int[9];
  Queue<Integer> order = new LinkedList<>(); 
    private String value = "";
    boolean flag = true;
    private int inOrder;
    
    
    public replayGame(Stage stage)
    {
        
        try
        {
            String url = "jdbc:mysql://localhost:3306/southwind";
            String user = "non";
            String password = "Java123$";
            String query = "select * from test";
            Connection con = DriverManager.getConnection(url, user, password);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            int counter = 0;
            
            while(rs.next())
                {
                System.out.println(rs.getInt("num"));
                order.add(rs.getInt("num")) ;
                counter++;
               
                }
            st.close();
            con.close();
            
        }
        
        catch(SQLException ex)
        {
                ex.printStackTrace();
                
        }
        
         setButtons(buttons);

        GridPane gridPane = new GridPane();
        
        gridPane.setPadding(new Insets(20));
        gridPane.setAlignment(Pos.CENTER);
     
        
       

        int counter = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (counter > 8) {
                    break;
                }
                gridPane.add(buttons[counter], i, j);
                counter++;
            }
        }
        
        

            nextButton = new Button("Next");
            nextButton.setPrefWidth(80);
            nextButton.setMaxWidth(80);
            nextButton.setPrefHeight(80);
            nextButton.setMaxHeight(80);
            newGameButton = new Button("New");
            exitButton = new Button("exit");
            newGameButton.setPrefWidth(80);
            newGameButton.setMaxWidth(80);
            newGameButton.setPrefHeight(80);
            newGameButton.setMaxHeight(80);
            
            exitButton.setPrefWidth(80);
            exitButton.setMaxWidth(80);
            exitButton.setPrefHeight(80);
            exitButton.setMaxHeight(80);
        gridPane.add(nextButton,0,4);
        gridPane.add(newGameButton,1,4);
        gridPane.add(exitButton,2,4);
        nextButton.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent arg0) {
                if(!order.isEmpty())
                {
                    if(flag)
                        value="X";
                    else
                        value = "O";
                    buttons[order.remove()].setText(value);
                   flag = !flag;
                }
                else
                {
                    nextButton.setDisable(true);
                }
                
            }
        });
        newGameButton.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent arg0) {
               new GameBoard((stage));
            }
        });
        scene = new Scene(gridPane, 250, 250);
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);
        this.stage = stage;
    }
        private void setButtons(Button[] buttons) {
        
        for (int i = 0; i < 9; i++) {
            Button button = new Button();
            buttons[i] = button;
            button.setPrefWidth(80);
            button.setMaxWidth(80);
            button.setPrefHeight(80);
            button.setMaxHeight(80);
            
        }
        
        

        
    }
        
    

}
