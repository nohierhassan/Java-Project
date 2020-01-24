package com.shahukhalroshan.tictactoe;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class GameBoard {
    
    private boolean alternateTurn = false;
    private Button[] buttons = new Button[9];
    private int counter = 0;
    private Stage stage;
    private Scene scene;
   
    
    public GameBoard(Stage stage) {
        
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

        scene = new Scene(gridPane, 230, 230);

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
            changeTurn(button);
            
        }
    }

    public Button[] getButtons() {
        return buttons;
    }
    
    private void changeTurn(Button button) {
        button.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {
                
                String winnerString = "";
                if (button.getText().isEmpty()) {
                    if (!alternateTurn) {
                        button.setText("X");
                        alternateTurn = true;
                    } else {
                        button.setText("O");
                        alternateTurn = false;
                    }
                    
                    winnerString = button.getText();
                    counter++;
                }
                
                
                if( 
                        // first col
                        checkForWin(buttons[0].getText(), buttons[1].getText(),buttons[2].getText() )    ||
                        // second col
                         checkForWin(buttons[3].getText(), buttons[4].getText(),buttons[5].getText() )  ||
                        // third col
                         checkForWin(buttons[6].getText(), buttons[7].getText(),buttons[8].getText() )  ||
                        // first row
                        checkForWin(buttons[0].getText(), buttons[3].getText(),buttons[6].getText() )||
                         // second row
                         checkForWin(buttons[1].getText(), buttons[4].getText(),buttons[7].getText() )||
                        // third row
                         checkForWin(buttons[2].getText(), buttons[5].getText(),buttons[8].getText() ) ||
                         // left diagonal
                        checkForWin(buttons[0].getText(), buttons[4].getText(),buttons[8].getText() ) ||
                         // right diagonal
                        checkForWin(buttons[2].getText(), buttons[4].getText(),buttons[6].getText() )  )
                {
                    PopUp popup = new PopUp(stage);
                    if(winnerString.equals("X"))
                    {
                        popup.setMessage(Welcome.firstPlayerName + " wins");
                    }
                    else
                    {
                        popup.setMessage(Welcome.secondPlayerName + " wins");
                    }
                    
                } else if (counter >= 9){
                    PopUp popup = new PopUp(stage);
                    popup.setMessage("Draw... ");
                }
            }
        });
    }
    private boolean checkForWin(String s1,String s2, String s3)
    {
        if(!s1.equals("") && s1.equals(s2) && s1.equals(s3) )
                return true;
        else
            return false;
       
    }
}
