/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blackjack;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;

/**
 *
 * @author Consek
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML private Label label,userSum,croupierSum;
    @FXML private TableView<Card> userTable,croupierTable;
    @FXML private Button hitButton,standButton,doubleButton;
    private ObservableList<Card> uTable;
    private ObservableList<Card> cTable;
    private List<Card> test;
    private Card temp;
    private CardDeck cardDeck;
    private int uSum,cSum,hiddenCard;
    
        
    @FXML
    private void handleHit(ActionEvent event)  {
        if(cardDeck.isEmpty()){
            label.setText("Card Deck is empty...");
            return;
        }
        
        uTableAdd(cardDeck.draw());
        
    }
    
    private void uTableAdd(int t){
        temp = new Card();
        temp.setCard(t);
        uSum += temp.getCard();
        
        uTable.add(temp);
        
        if(uSum>21){
            recountuSum();
            if(uSum>21){
                lost();
            }
        }
        userSum.setText(Integer.toString(uSum) );
    }
    
    @FXML
    private void handleStand(){
        
        while(cSum<17){
            cTableAdd(cardDeck.draw());
        }
        if(cSum>21){
            win();
        }
        
        if(cSum<uSum){
            win();
        }else{
            if(cSum>uSum){
                lost();
            }else{
                draw();
            }   
        }
    }
    
    private void draw(){
        hitButton.setDisable(true);
        standButton.setDisable(true);
        doubleButton.setDisable(true);
        label.setText("Remis!");
    }
    
    private void lost(){
        hitButton.setDisable(true);
        standButton.setDisable(true);
        doubleButton.setDisable(true);
        label.setText("Przegrałeś!");
    }
    
    private void recountuSum(){
        int as = 0;
        uSum = 0;
        int tmp = 0;
        for(int i=0;i<uTable.size();i++){
            tmp = uTable.get(i).getCard();
            if(tmp == 11){
                as++;
            }
            uSum += tmp;
            
            if(uSum > 21 && as > 0){
                uSum -= 10;
                as--;
            }
        }
    }
    
    private void recountcSum(){
        int as = 0;
        cSum = 0;
        int tmp = 0;
        for(int i=0;i<cTable.size();i++){
            tmp = cTable.get(i).getCard();
            if(tmp == 11){
                as++;
            }
            cSum += tmp;
            
            if(cSum > 21 && as > 0){
                cSum -= 10;
                as--;
            }
        }
    }
    
    private void cTableAdd(int t){
        temp = new Card();
        temp.setCard(t);
        cSum += temp.getCard();
        cTable.add(temp);
        
        if(cSum>21){
            recountcSum();
            if(cSum>21){
                win();
            }
        }
        croupierSum.setText(Integer.toString(cSum));
    }
    
    private void win(){
        hitButton.setDisable(true);
        standButton.setDisable(true);
        doubleButton.setDisable(true);
        label.setText("Wygrana!");
    }
    
    @FXML
    private void handleNewGame(ActionEvent event){
        cardDeck = new CardDeck();
        cTable.clear();
        uTable.clear();
        uSum = 0;
        cSum = 0;
        label.setText("Black Jack");
        uTableAdd(cardDeck.draw());
        uTableAdd(cardDeck.draw());
        cTableAdd(cardDeck.draw());
        hiddenCard = cardDeck.draw();
        hitButton.setDisable(false);
        standButton.setDisable(false);
    }
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cardDeck = new CardDeck();
        uTable = userTable.getItems();
        cTable = croupierTable.getItems();
        test=new ArrayList<>();
        uSum = 0;
        cSum = 0;
        uTableAdd(cardDeck.draw());
        uTableAdd(cardDeck.draw());
        cTableAdd(cardDeck.draw());
        hiddenCard = cardDeck.draw();
    }    
    
}
