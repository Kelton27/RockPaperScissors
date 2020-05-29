package com.mygdx.game.screens;

import com.mygdx.game.MainRPS;
import com.mygdx.game.ai.User;
import com.mygdx.game.utils.ChoiceType;

public class PvP_Screen extends PlayScreen {

    //Constructor
    public PvP_Screen(MainRPS game) {
        super(game, "PVP", true);
        player2 = new User("Player 2");
    }

    @Override
    public void hide() { }

    @Override
    public ChoiceType getPlayer2Choice() {
        return null;
    }
}
