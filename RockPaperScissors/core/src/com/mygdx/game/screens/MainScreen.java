package com.mygdx.game.screens;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.MainRPS;

//This class extends the ApplicationAdapter class and implements the Screen class provided with the engine to use and implement their methods
public class MainScreen extends ApplicationAdapter implements Screen {

    //Instance variables
    private MainRPS game;
    private Stage stage;
    static int highScore;

    //Constructor
    public MainScreen(final MainRPS game){
        this.game = game;
    }

    //This method is called only once when this class is constructed and sets everything up for the screen
    @Override
    public void show() {
        SpriteBatch batch = new SpriteBatch();
        Viewport viewport = new FitViewport(800, 800, new OrthographicCamera());
        stage = new Stage(viewport, batch);

        Gdx.input.setInputProcessor(stage);
        Skin skin = new Skin(Gdx.files.internal("uiskin.json"));

        //These groups of code set up what you see on the screen and places the buttons and labels on a grid called a table
        Table table = new Table();
        table.center();
        table.setFillParent(true);

        Label.LabelStyle labelStyle = new Label.LabelStyle();
        BitmapFont font2 = new BitmapFont();
        labelStyle.font = font2;
        labelStyle.fontColor = Color.WHITE;

        final Label title = new Label("RPS!", skin);
        title.setAlignment(Align.center);
        title.setScale(25, 25);
        title.setFontScale(2, 2);
        title.setPosition(Gdx.graphics.getWidth() / 2 - title.getWidth() / 2, 300);

        final Label highscore = new Label("HIGH SCORE: " + highScore, skin);
        highscore.setAlignment(Align.center);
        highscore.setScale(25, 25);
        highscore.setFontScale(2, 2);
        highscore.setPosition(Gdx.graphics.getWidth() / 2 - title.getWidth() / 2, 400);

        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.up = skin.newDrawable("button", Color.WHITE);
        textButtonStyle.down = skin.newDrawable("button-down", Color.WHITE);
        textButtonStyle.font = font2;

        TextButton btn = new TextButton("PvP", textButtonStyle);
        btn.setPosition(600, 60);
        btn.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent e, float x, float y, int point, int button) {
                super.touchDown(e, x, y, point, button);
                game.setScreen(new PvP_Screen(game));
                Gdx.app.log("MainScreen", "PvP Touched");
                return false;
            }
        });

        TextButton btn2 = new TextButton("PvA", textButtonStyle);
        btn2.setPosition(176, 60);
        btn2.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent e, float x, float y, int point, int button) {
                super.touchDown(e, x, y, point, button);
                game.setScreen(new PvC_Screen(game));
                Gdx.app.log("MainScreen", "PvC Touched");
                return false;
            }
        });

        TextButton exitBtn = new TextButton("Exit", textButtonStyle);
        exitBtn.setPosition(630, 720);
        exitBtn.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent e, float x, float y, int point, int button) {
                super.touchDown(e, x, y, point, button);
                Gdx.app.exit();
                return false;
            }
        });

        //Adds all of the things that need to be seen on the screen to the stage to be rendered
        stage.addActor(table);
        stage.addActor(title);
        stage.addActor(btn2);
        stage.addActor(btn);
        stage.addActor(exitBtn);
        stage.addActor(highscore);
    }

    //This method is called 60 times a second to match the frame-rate and will constantly update anything inside of it
    @Override
    public void render(float delta) {
        //Sets up the background of the screen
        Gdx.gl.glClearColor(0f, 0.5f, 1f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //Updates the stage in which everything is placed on
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    @Override
    public void hide() { }

    //This method is used to destroy objects to increase performance
    @Override
    public void dispose(){
        stage.dispose();
    }
}
