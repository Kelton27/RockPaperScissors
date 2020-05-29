package com.mygdx.game.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public enum ChoiceType {
    //These are the choices that are in the game to play  with
    ROCK(new Texture(Gdx.files.internal("rock.png"))),
    PAPER(new Texture(Gdx.files.internal("paper.png"))),
    SCISSORS(new Texture(Gdx.files.internal("scissors.png")));

    //Instance variable
    Texture texture;

    //This method takes a texture and sets it to the texture variable
    ChoiceType(Texture texture) {
        this.texture = texture;
    }

    //This method gets and returns the texture variable
    public Texture getTexture() { return this.texture; }
}
