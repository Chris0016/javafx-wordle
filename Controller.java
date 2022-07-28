package wordle;


import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

import java.util.ArrayList;

import javax.lang.model.util.ElementScanner6;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;



public class Controller {

    @FXML
    private Button enterButton;

    @FXML
    private GridPane wordGrid1;

    @FXML
    private GridPane wordGrid2;

    @FXML
    private GridPane wordGrid3;

    @FXML
    private GridPane wordGrid4;

    @FXML
    private GridPane wordGrid5;

    @FXML
    private GridPane wordGrid6;

    private final int MAX_WORD_SIZE = 5;
    private final int MAX_TRIES = 6;  //EQUIVALENT TO THE NUMBER OF ROWS SET
    
    private ArrayList<GridPane> wordGrids;

    GridPane currentGridpane;
    int currentGridPaneCount;
    String currentWord;

    Alert invalidInputAlert;
    Alert notAWordAlert;
    Alert winGameAlert;
    Alert loseGameAlert;

    Background greenBackground;
    Background yellowBackground;
    Background grayBackground;
    Background resetBackground;


    String secretWord;

    WordGenerator wordUtil;


    public Controller() {
        wordGrids = new ArrayList<>();
       
        currentGridPaneCount  = 0;
        currentWord = "";

        wordUtil = new WordGenerator();
        secretWord = wordUtil.getRandomWord();
        System.out.println("Secret Word: " + secretWord);


        invalidInputAlert = new Alert(AlertType.ERROR);
        invalidInputAlert.setTitle("Invalid Input");
        invalidInputAlert.setContentText("Invalid input. Please make sure that every box has a letter only have one letter per box.");

        notAWordAlert = new Alert(AlertType.ERROR);
        notAWordAlert.setTitle("Not A Word");
        notAWordAlert.setContentText("Invalid input. Not a word.");

        winGameAlert = new Alert(AlertType.CONFIRMATION);
        winGameAlert.setTitle("You win");
        winGameAlert.setContentText("Congratulations you won. The secret word was " + secretWord);


        loseGameAlert = new Alert(AlertType.CONFIRMATION);
        loseGameAlert.setTitle("You loose");
        loseGameAlert.setContentText("Sorry you loose. The secret word was " + secretWord);
       
        greenBackground = new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY));
        yellowBackground = new Background(new BackgroundFill(Color.YELLOW, CornerRadii.EMPTY, Insets.EMPTY));
        grayBackground = new Background(new BackgroundFill(Color.GRAY, CornerRadii.EMPTY, Insets.EMPTY));
        resetBackground = new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY));
        
        
       
        
    }

    @FXML
    void onEnterClick(ActionEvent event) {
       
        currentWord = "";
        String currentLetter = "";
        TextField currentTextField;
        
        for(int i=0; i < MAX_WORD_SIZE; i++){
           
            currentTextField = ((TextField)currentGridpane.getChildren().get(i));
            currentLetter = currentTextField.getText();

            System.out.println("Current letter: " + currentLetter);

            if (isValidLetter(currentLetter)){
                currentWord += currentLetter;

            }
                
            else {
                invalidInputAlert.showAndWait();
                return;
            }
     
        }
        if(!wordUtil.isWord(currentWord)){
            notAWordAlert.showAndWait();
            return;
        }
        setColors();

        //Check if player wins game
        if(secretWord.equalsIgnoreCase(currentWord)){
            winGameAlert.show();
            resetGame();
            return;
        } 

        //Check if player loses game(i.e. is in last row and guess is wrong)
        if (currentGridPaneCount == MAX_TRIES - 1){
            loseGameAlert.show();
            resetGame();
            return;
        }
            
        updateCurrentGridPane();
        System.out.println(currentWord);
        
    }

    boolean isValidLetter(String letter){
        int len = letter.length();

        //System.out.println("Letter Length: " + len);
        if (len != 1)
            return false;

        int asciiValue =  letter.charAt(0);

        //System.out.println("Ascii value for: " + letter + "-> " + asciiValue);

        boolean isLowerCasedLetter =  asciiValue > 64 && asciiValue < 91 ;
        boolean isUpperCasedLetter =  asciiValue > 96 && asciiValue < 123;

        return isLowerCasedLetter || isUpperCasedLetter;
    }

    void setColors(){

        TextField currentTextField;
        String currentLetter;

        for(int i=0; i < MAX_WORD_SIZE; i++){
            
            currentTextField = ((TextField)currentGridpane.getChildren().get(i));
            currentLetter = currentTextField.getText();

            if (currentLetter.charAt(0) == secretWord.charAt(i))
                currentTextField.setBackground( greenBackground );
            else if (secretWord.contains(currentLetter))
                currentTextField.setBackground( yellowBackground );
            else
                currentTextField.setBackground( grayBackground );
        }
    }
    public void initialize() {
        
        wordGrids.add(wordGrid1);
        wordGrids.add(wordGrid2);
        wordGrids.add(wordGrid3);
        wordGrids.add(wordGrid4);
        wordGrids.add(wordGrid5);
        wordGrids.add(wordGrid6);

        currentGridpane = wordGrids.get(currentGridPaneCount);
        
        printWordGrids();        
    }


    void updateCurrentGridPane(){
        currentGridPaneCount++;
        currentGridpane = wordGrids.get(currentGridPaneCount);
    }

    void printWordGrids(){
        for(GridPane pane : wordGrids){
            System.out.println(pane);
        }

        System.out.println("Current pane: " + currentGridpane);
    }


    void resetGame(){
        currentGridPaneCount = 0;
        currentWord = "";

        for(GridPane row : wordGrids){
            for(Node currentNode : row.getChildren()){
               ((TextField)currentNode).setText("");
               ((TextField)currentNode).setBackground(resetBackground);
            }
        }
    }
}
