package Screens;

import Entities.Sounds;
import Game.AstonAdventure;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;

/**
 * The Main Menu Class is used to display the games main menu. From the main menu the user can
 * start the game, exit the game, change characters and view controls.
 */
public class MainMenu implements Screen, Input.TextInputListener {

    private static final int BACKGROUND_WIDTH = 650;
    private static final int BACKGROUND_HEIGHT = 480;
    private static final int CHARACTER_WIDTH = 650;
    private static final int CHARACTER_HEIGHT = 480;

    //Character Information
    private static final int NUMBER_OF_CHARACTERS = 2;

    //MainMenu instance
    private static MainMenu mainMenuInstance;

    //Game
    private AstonAdventure game;

    //Background Texture
    private Texture background;

    //Character Selection Overlay
    private Texture character;

    //Sounds
    private Sounds Sm;

    //Screen / Button selectors
    private String select = "null";
    private String screen = "mainMenu";

    //Timer
    private float elapsedTimeEsc;
    private int currentCharacter;

    /**
     * Constructor for the MainMenu class. Initialises the background textures and sounds. Stores the
     * game object.
     *
     * @param game The Game object.
     */
    private MainMenu(AstonAdventure game) {
        this.game = game;
        background = new Texture("Screens/MainMenu/MainMenu.png");
        character = new Texture("Screens/MainMenu/FemaleCharacterSelect.png");
        Sm = new Sounds();
    }

    /**
     * This method allows this class to follow the Singleton design pattern to allow only one instance of the MainMenu
     * class to be produced
     * @param game {@link AstonAdventure} game
     * @return the single version of a MainMenu instance
     */
    static MainMenu getMainMenuInstance(AstonAdventure game) {
        if (mainMenuInstance == null) {
            mainMenuInstance = new MainMenu(game);
        }
        return mainMenuInstance;
    }

    /**
     * Render method used to display the current menu screen and play menu music.
     *
     * @param delta Elapsed Time.
     */
    @Override
    public void render(float delta) {

        //Setup
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();

        //Draw / Display background
        game.batch.draw(background, 0, 0, BACKGROUND_WIDTH, BACKGROUND_HEIGHT);

        //Draw / Display character overlay (If required)
        if (screen.equalsIgnoreCase("characterMenu")) {
            game.batch.draw(character, 0, 0, CHARACTER_WIDTH, CHARACTER_HEIGHT);
        }

        //Update elapsed time
        elapsedTimeEsc += Gdx.graphics.getDeltaTime();

        //Play menu music upon startup and loop
        if (!Sm.isMusicPlaying() && (!Sm.isSoundPlaying())) {
            Sm.menu();
        }

        //Display current menu screen
        if (screen.equalsIgnoreCase("mainMenu")) {
            mainScreen();
        } else if (screen.equalsIgnoreCase("optionsMenu")) {
            optionsScreen();
        } else if (screen.equalsIgnoreCase("characterMenu")) {
            charScreen();
        } else if (screen.equalsIgnoreCase("controlMenu")) {
            controlScreen();
        }

        //End
        game.batch.end();
    }

    /**
     * Method used when the game is on the main screen of the main menu. The method will update the background
     * and if a button is pressed a sound will play and a function will be performed (Based on which button
     * is pressed).
     */
    private void mainScreen() {

        //Play button
        if ((Gdx.input.getX() > 253) && (Gdx.input.getX() < 423) && ((Gdx.input.getY() > 126) && (Gdx.input.getY() < 172))) {

            //Highlights button when hovered over
            background = new Texture("Screens/MainMenu/MainMenuPlay.png");

            //Begins game upon press
            if (Gdx.input.isTouched()) {
                if (!Sm.isSoundPlaying()) {
                    Sm.menuSelect();
                    select = "gameSelect";
                }
            }
        }

        //Options button
        else if ((Gdx.input.getX() > 195) && (Gdx.input.getX() < 461) && ((Gdx.input.getY() > 200) && (Gdx.input.getY() < 243))) {

            //Highlights button when hovered over
            background = new Texture("Screens/MainMenu/MainMenuOptions.png");

            //Switches to options menu screen upon press
            if (Gdx.input.isTouched()) {
                if (!Sm.isSoundPlaying()) {
                    Sm.menuSelect();
                    select = "OptionsSelect";
                }
            }
        }

        //Exit button
        else if ((Gdx.input.getX() > 253) && (Gdx.input.getX() < 407) && ((Gdx.input.getY() > 270) && (Gdx.input.getY() < 314))) {

            //Highlights button when hovered over
            background = new Texture("Screens/MainMenu/MainMenuExit.png");

            //Exits application when pressed
            if (Gdx.input.isTouched()) {
                if (!Sm.isSoundPlaying()) {
                    Sm.menuSelect();
                    select = "ExitSelect";
                }
            }
        }

        //Standard Background
        else {
            background = new Texture("Screens/MainMenu/MainMenu.png");
        }


        //Begin game
        if (select.equalsIgnoreCase("gameSelect")) {

            if (!Sm.isSoundPlaying()) {
                game.setScreen(LevelOne.getLevelOneInstance(game));
                //Start the game at level two
                //game.setScreen(LevelTwo.getLevelTwoInstance(game));
                //Start the game at level three
                //game.setScreen(LevelThree.getLevelThreeInstance(game));
            }
        }

        //Switch to options screen
        else if (select.equalsIgnoreCase("optionsSelect")) {
            if (!Sm.isSoundPlaying()) {
                screen = "optionsMenu";
            }
        }

        //Exit game
        else if (select.equalsIgnoreCase("exitSelect")) {
            if (!Sm.isSoundPlaying()) {
                Gdx.app.exit();
            }
        }
    }

    /**
     * Method used when the game is on the options screen of the main menu. The method will update the background
     * and if a button is pressed a sound will play and a function will be performed (Based on which button
     * is pressed).
     */
    private void optionsScreen() {

        //Characters button
        if ((Gdx.input.getX() > 134) && (Gdx.input.getX() < 536) && ((Gdx.input.getY() > 241) && (Gdx.input.getY() < 285))) {

            //Highlights button when hovered over
            background = new Texture("Screens/MainMenu/OptionsMenuCharacters.png");

            //Switches to character selection screen
            if (Gdx.input.isTouched()) {
                if (!Sm.isSoundPlaying()) {
                    Sm.menuSelect();
                    select = "charSelect";
                }
            }
        }

        //Controls button
        else if ((Gdx.input.getX() > 178) && (Gdx.input.getX() < 500) && ((Gdx.input.getY() > 155) && (Gdx.input.getY() < 202))) {

            //Highlights button when hovered over
            background = new Texture("Screens/MainMenu/OptionsMenuControls.png");

            //Switches to controls screen when pressed
            if (Gdx.input.isTouched()) {
                if (!Sm.isSoundPlaying()) {
                    Sm.menuSelect();
                    select = "controlSelect";
                }
            }
        }

        //Standard Background
        else {
            background = new Texture("Screens/MainMenu/OptionsMenu.png");
        }

        //Return 'up' to main screen
        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE) && elapsedTimeEsc > 0.5) {
            elapsedTimeEsc = 0;
            select = "null";
            screen = "MainMenu";
        }

        //Switch to character selection screen
        if (select.equalsIgnoreCase("charSelect")) {
            if (!Sm.isSoundPlaying()) {
                screen = "characterMenu";
            }
        }

        //Switch to controls screen
        else if (select.equalsIgnoreCase("controlSelect")) {
            if (!Sm.isSoundPlaying()) {
                screen = "controlMenu";
            }
        }

    }

    /**
     * Method used when the game is on the character selection screen of the main menu. The method will update
     * the background and if a button is pressed a sound will play and a function will be performed (Based on
     * which button is pressed).
     */
    private void charScreen() {

        //Right arrow button
        if ((Gdx.input.getX() > 576) && (Gdx.input.getX() < 611) && ((Gdx.input.getY() > 222) && (Gdx.input.getY() < 266))) {

            //Highlights button when hovered over
            background = new Texture("Screens/MainMenu/CharacterMenuRight.png");

            //Switch to next character preview
            if (Gdx.input.isTouched()) {
                if (!Sm.isSoundPlaying()) {
                    Sm.menuSelect();
                    if (currentCharacter < NUMBER_OF_CHARACTERS - 1) {
                        currentCharacter++;
                    } else {
                        currentCharacter = 0;
                    }
                }
            }
        } else if ((Gdx.input.getX() > 31) && (Gdx.input.getX() < 66) && ((Gdx.input.getY() > 222) && (Gdx.input.getY() < 266))) {

            //Highlights button when hovered over
            background = new Texture("Screens/MainMenu/CharacterMenuLeft.png");

            //Switch to last character preview
            if (Gdx.input.isTouched()) {
                if (!Sm.isSoundPlaying()) {
                    Sm.menuSelect();
                    if (currentCharacter > 0) {
                        currentCharacter--;
                    } else {
                        currentCharacter = NUMBER_OF_CHARACTERS - 1;
                    }
                }
            }
        }

        //Standard background
        else {
            background = new Texture("Screens/MainMenu/CharacterMenu.png");
        }

        //Updates character overlay for display in the character selection screen
        if (game.characters.get(currentCharacter).equalsIgnoreCase("female")) {
            character = new Texture("Screens/MainMenu/FemaleCharacterSelect.png");
        } else if (game.characters.get(currentCharacter).equalsIgnoreCase("male")) {
            character = new Texture("Screens/MainMenu/MaleCharacterSelect.png");
        }

        //Updates saved character selection in game if character is selected
        if (Gdx.input.isKeyPressed(Input.Keys.ENTER)) {
            select = "null";
            screen = "OptionsMenu";
            game.changeCharacter(currentCharacter);
        }

        //Return 'up' to options menu screen without changing character
        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE) && elapsedTimeEsc > 0.5) {
            elapsedTimeEsc = 0;
            select = "null";
            screen = "OptionsMenu";
        }
    }

    /**
     * Method used when the game is on the control selection screen of the main menu. The method will update
     * the background and if a button is pressed a sound will play and a function will be performed (Based on
     * which button is pressed).
     */
    private void controlScreen() {

        //Standard Background
        background = new Texture("Screens/MainMenu/ControlsMenu.png");

        //Returns 'up' to options menu screen
        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE) && elapsedTimeEsc > 0.5) {
            elapsedTimeEsc = 0;
            select = "null";
            screen = "OptionsMenu";
        }

    }

    //Unused
    public void input(String text) {
        //this.text = text;
    }

    //Unused
    public void canceled() {
        // text = "canceled";
    }

    //Unused
    @Override
    public void show() {

    }

    //Unused
    @Override
    public void resize(int width, int height) {

    }

    //Unused
    @Override
    public void pause() {

    }

    //Unused
    @Override
    public void resume() {

    }

    //Unused
    @Override
    public void hide() {

    }

    //Unused
    @Override
    public void dispose() {
        background.dispose();
    }
}
