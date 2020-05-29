package com.mygdx.game.ai;

import com.mygdx.game.utils.ChoiceType;

//Class extends the abstract player class
public class User extends Player {

    //Instance variable
    public String name;

    //Constructor
    public User(String n){
        super();
        name = n;
    }

    //This method gets and returns the name variable
    public String name() { return name; }

    //This method takes the ChoiceType as a parameter and sets the _choice variable to it
    public void getInput(ChoiceType c){
        _choice = c;
    }
}
