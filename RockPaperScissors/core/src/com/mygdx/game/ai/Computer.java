package com.mygdx.game.ai;

import com.mygdx.game.utils.ChoiceType;

public class Computer extends Player {
    //Constructor
    public Computer(String n){
        super();
        name = n;
    }

    @Override
    public String name() {
        return name;
    }

    //This method sets the _choice to one of the 3 predetermined choices
    public ChoiceType getChoice() {
        _choice = ChoiceType.values()[(int) (Math.random() * 3)];
        return _choice;
    }
}
