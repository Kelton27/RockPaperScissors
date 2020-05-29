package com.mygdx.game.screens;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.MainRPS;
import com.mygdx.game.ai.Player;
import com.mygdx.game.ai.User;
import com.mygdx.game.utils.ChoiceType;
import com.mygdx.game.utils.WinType;

public abstract class PlayScreen extends ApplicationAdapter implements Screen {

    //Instance variables
    public Viewport viewPort;
    public Viewport viewport;
    public Stage stage;
    public Table P1Choice;
    public Table P2Choice;
    public MainRPS game;
    public Player player1;
    public Player player2;
    public SpriteBatch batch;
    public TextButton rockBtn;
    public TextButton paperBtn;
    public TextButton scissorsBtn;
    private TextButton.TextButtonStyle textButtonStyle;

    public Player currentPlayer;

    public String titleText;
    public String subtitleText;

    private boolean drawImages = true;
    private boolean pvp;

    public int totalGames;
    private int totalRounds = 1;

    private Label subtitle;
    private Label rounds;
    private Label games;
    private Label p1Wins;
    private Label p2Wins;
    private Label playerTurn;
    private Skin skin;

    //Constructor
    public PlayScreen(MainRPS game, String titleText, boolean pvp){
        this.game = game;
        viewPort = new StretchViewport(800,800, new OrthographicCamera());
        stage = new Stage(viewPort);
        P1Choice = new Table();
        P2Choice = new Table();
        player1 = new User("Player 1");
        this.titleText = titleText;
        this.pvp = pvp;
    }

    //This method is called only once when this class is constructed and sets everything up for the screen
    @Override
    public void show() {
        initUI();
    }

    //This method is called 60 times a second to match the frame-rate and will constantly update anything inside of it
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
        batch.begin();
        if (drawImages) {
            if (player1._choice != null)
                batch.draw(player1._choice.getTexture(), 75, 60, 100, 100);
            if (player2._choice != null)
                batch.draw(player2._choice.getTexture(), Gdx.graphics.getWidth() - 100 - 75, 60, 100, 100);
        }
        batch.end();
    }

    //This method is used to destroy objects to increase performance
    @Override
    public void dispose() {
        stage.dispose();
    }

    //This method is used to initialize the graphical user interface of the screen
    private void initUI() {
        batch = new SpriteBatch();
        viewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), new OrthographicCamera());
        stage = new Stage(viewport, batch);
        Gdx.input.setInputProcessor(stage);
        skin = new Skin(Gdx.files.internal("uiskin.json"));
        currentPlayer = player1;
        Label.LabelStyle labelStyle = new Label.LabelStyle();
        BitmapFont font2 = new BitmapFont();
        labelStyle.font = font2;
        labelStyle.fontColor = Color.WHITE;

        rounds = new Label("ROUND: " + totalRounds, skin);
        rounds.setAlignment(Align.left);
        rounds.setScale(25, 25);
        rounds.setFontScale(2, 2);
        rounds.setPosition(0, Gdx.graphics.getHeight() - rounds.getHeight() - 10);

        games = new Label("TOTAL GAMES: " + totalGames, skin);
        games.setAlignment(Align.left);
        games.setScale(25, 25);
        games.setFontScale(2, 2);
        games.setPosition(0, Gdx.graphics.getHeight() - games.getHeight() - 60);

        p1Wins = new Label("PLAYER 1: 0", skin);
        p1Wins.setAlignment(Align.left);
        p1Wins.setScale(25, 25);
        p1Wins.setFontScale(2, 2);
        p1Wins.setPosition(10, 275);

        p2Wins = new Label("PLAYER 2: 0", skin);
        p2Wins.setAlignment(Align.right);
        p2Wins.setScale(25, 25);
        p2Wins.setFontScale(2, 2);
        p2Wins.setPosition(Gdx.graphics.getWidth() - p2Wins.getWidth() - 10, 275);

        subtitle = new Label(subtitleText, skin);
        subtitle.setAlignment(Align.center);
        subtitle.setScale(25, 25);
        subtitle.setFontScale(2, 2);
        subtitle.setPosition(Gdx.graphics.getWidth() / 2 - subtitle.getWidth() / 2, 225);

        playerTurn = new Label(currentPlayer.name + " TURN", skin);
        playerTurn.setAlignment(Align.center);
        playerTurn.setScale(25, 25);
        playerTurn.setFontScale(2, 2);
        playerTurn.setPosition(Gdx.graphics.getWidth() / 2 - playerTurn.getWidth() / 2, 345);

        textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.up = skin.newDrawable("button", Color.WHITE);
        textButtonStyle.down = skin.newDrawable("button-down", Color.WHITE);
        textButtonStyle.font = font2;

        final TextButton exitBtn = new TextButton("Back To Main", textButtonStyle);
        exitBtn.setPosition(Gdx.graphics.getWidth() - exitBtn.getWidth(), Gdx.graphics.getHeight() - exitBtn.getHeight());
        exitBtn.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent e, float x, float y, int point, int button) {
                super.touchDown(e, x, y, point, button);
                if(MainScreen.highScore < player1.wins) MainScreen.highScore = player1.wins;
                game.setScreen(new MainScreen(game));
                return false;
            }
        });

        initBtns();

        stage.addActor(rounds);
        stage.addActor(games);
        stage.addActor(p1Wins);
        stage.addActor(p2Wins);
        stage.addActor(playerTurn);
        stage.addActor(subtitle);
        stage.addActor(exitBtn);
    }

    //This method is used to initialise all of the buttons used on the playscreen
    private void initBtns(){
        rockBtn = new TextButton("Rock", textButtonStyle);
        rockBtn.setPosition(Gdx.graphics.getWidth() / 2 - rockBtn.getWidth() / 2 - 100, 60);
        rockBtn.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent e, float x, float y, int point, int button) {
                super.touchDown(e, x, y, point, button);
                Gdx.app.log("PvC", "Rock Button Touched");
                currentPlayer._choice = ChoiceType.ROCK;
                buttonPress();
                return false;
            }
        });

        paperBtn = new TextButton("Paper", textButtonStyle);
        paperBtn.setPosition(Gdx.graphics.getWidth() / 2 - paperBtn.getWidth() / 2, 60);
        paperBtn.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent e, float x, float y, int point, int button) {
                super.touchDown(e, x, y, point, button);
                Gdx.app.log("PvC", "Paper Button Touched");
                currentPlayer._choice = ChoiceType.PAPER;
                buttonPress();
                return false;
            }
        });

        scissorsBtn = new TextButton("Scissors", textButtonStyle);
        scissorsBtn.setPosition(Gdx.graphics.getWidth() / 2 - scissorsBtn.getWidth() / 2 + 100, 60);
        scissorsBtn.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent e, float x, float y, int point, int button) {
                super.touchDown(e, x, y, point, button);
                Gdx.app.log("PvC", "Rock Button Touched");
                currentPlayer._choice = ChoiceType.SCISSORS;
                buttonPress();
                return false;
            }
        });

        stage.addActor(paperBtn);
        stage.addActor(rockBtn);
        stage.addActor(scissorsBtn);
    }

    //This method is used whenever a round ends and if the amount is passed the max amount it will increment the amount of games played and display who won
    private void incrementRounds() {
        totalRounds++;
        if (totalRounds > 3) {
            totalGames++;
            totalRounds = 1;
            games.setText("TOTAL GAMES: " + totalGames);
        }
        rounds.setText("ROUND: " + totalRounds);

        p1Wins.setText("PLAYER 1: " + player1.wins);
        p2Wins.setText("PLAYER 2: " + player2.wins);
    }

    public abstract ChoiceType getPlayer2Choice();

    //This method is called when a button is pressed and will use some logic to determine what to do next
    private void buttonPress() {
        if (!pvp) {
            getPlayer2Choice();
            win();
            incrementRounds();
        } else {
            drawImages = false;

            if (currentPlayer.equals(player1))
                currentPlayer = player2;
            else
                currentPlayer = player1;
            playerTurn.setText(currentPlayer.name + " TURN");

            if (player2._choice != null && currentPlayer.equals(player1)) {
                win();
                drawImages = true;
            }
            incrementRounds();
        }
    }

    //This method is called when a win condition is achieved and will display what happened on the screen
    private void win() {
        if (player1.isWin(player2._choice).equals(WinType.WIN)) {
            subtitleText = "PLAYER 1 WIN!";
            player1.wins++;
        } else if (player1.isWin(player2._choice).equals(WinType.LOSE)) {
            subtitleText = "PLAYER 2 WIN!";
            player2.wins++;
        } else {
            subtitleText = "TIE!";
        }
        subtitle.setText(subtitleText);
    }
}
