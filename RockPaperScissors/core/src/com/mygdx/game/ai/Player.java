package com.mygdx.game.ai;

import com.mygdx.game.utils.ChoiceType;
import com.mygdx.game.utils.WinType;

import static com.mygdx.game.utils.ChoiceType.*;
import static com.mygdx.game.utils.WinType.*;

public abstract class Player {

    //Instance Variables
    public ChoiceType _choice;
    public boolean turn;
    public int wins;
    public String name;

    //Constructor
    public Player() {
        turn = false;
        wins = 0;
    }

    //Abstract Variable
    public abstract String name();

    //Calculates whether the player has won or loss by using the basic rules of the game
    public WinType isWin(ChoiceType other) {
        if(_choice.equals(other)) return TIE;
        switch (_choice) {
            case ROCK:
                if(other.equals(SCISSORS)) {
                    return WIN;
                }
                break;
            case PAPER:
                if(other.equals(ROCK)){
                    return WIN;
                }
                break;
            case SCISSORS:
                if (other.equals(PAPER)) {
                    return WIN;
                }
                break;
        }
        return LOSE;
    }
}