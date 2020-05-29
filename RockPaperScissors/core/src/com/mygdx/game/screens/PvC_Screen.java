package com.mygdx.game.screens;

import com.mygdx.game.MainRPS;
import com.mygdx.game.ai.Computer;
import com.mygdx.game.utils.ChoiceType;

public class PvC_Screen extends PlayScreen {

    //Constructor
    public PvC_Screen(final MainRPS game){
        super(game, "PVC", false);
        player2 = new Computer("Player 2");
    }

    //This method will get the choice of the computer you are playing against and return it
    @Override
    public ChoiceType getPlayer2Choice() {
        player2._choice = ((Computer)player2).getChoice();
        System.out.println("COMPUTER PICKED " + player2._choice);
        return player2._choice;
    }

    @Override
    public void hide() { }
}

