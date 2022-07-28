package wordle;

import java.io.File;
import java.util.Scanner;

public class WordGenerator {
    
    Scanner reader;
    
    private final String WORDS_BASE_PATH = "/Users/coditum/Documents/Wordle/Wordle/src/wordle/" ;
    private final String ALL_WORDS_PATH =  "all_words.txt";// CHANGE FILE SIZE VARIABLE AS WELL WHEN CHANGING ME
    private final  String SOME_WORDS_PATH = "some_words.txt"; // CHANGE FILE SIZE VARIABLE AS WELL WHEN CHANGING ME

    /*
        To update difficulty

        In getRandomWords():
            
            Easy Mode:
                WORDS_BASE_PATH + SOME_WORDS_PATH

            Hard Mode:
                WORDS_BASE_PATH + ALL_WORDS_PATH

            * Make sure to update the WORD_FILE_SIZE variable when changing as well
     */


    private final int WORD_FILE_SIZE = 15_900; //PLEASE CHANGE ME WHEN CHANGING THE FILE PATH VARIABLE
    public WordGenerator(){
       
       
    }

    String getRandomWord() {
       
        try{
            int count = 0;
            int randIdx = (int)(Math.random() * WORD_FILE_SIZE);

            reader = new Scanner(new File(WORDS_BASE_PATH + ALL_WORDS_PATH)); //Update this to set difficulty. 
            String currentWord = "";
            
            while(reader.hasNext()){
                currentWord = reader.nextLine();
                //System.out.println("Testing: " + currentWord);

                if (count == randIdx)
                    return currentWord;

                count++;
            }

        }catch(Exception e){
            System.out.println("Could not find words file.");
            e.printStackTrace();
            System.exit(1);
        }
       
       
        return ""; //Will not happen
        
        
    }

    boolean isWord(String word){
        try{
            reader = new Scanner(new File(WORDS_BASE_PATH + ALL_WORDS_PATH)); //DO NOT CHANGE ME
            String currentWord = "";
            
            while(reader.hasNext()){
                currentWord = reader.nextLine();
                System.out.println("Checking: " + currentWord + "...");
                if (currentWord.equals(word))
                    return true;
            }

        }catch(Exception e){
            System.out.println("Could not find word file.");
            e.printStackTrace();
            System.exit(1);
            return false;
        }

       return false;

    }
    
    
}
