/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blackjack;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 *
 * @author Consek
 */
public class CardDeck {
    
    private List<Integer> cards;
    
    /**
     *
     */
    public CardDeck(){
        cards = new ArrayList<>();
        cards.clear();
        for(int i=0;i<4;i++){
            for(int j=2;j<=11;j++){
                cards.add(j);
            }
        }
        for(int i=0;i<12;i++){
            cards.add(10);
        }
    }
    
    public int draw(){
        int ret;
        
        if(cards.size()>0){
            
        Random r =new Random();
        int index = r.nextInt(cards.size());
        ret = cards.get(index);
        cards.remove(index);
        
        }else{
            ret = 0;
        }
        return ret;
    }
    
    public boolean isEmpty(){
        if(cards.size()<=0){
            return true;
        }else{
            return false;
        }
    }
}
