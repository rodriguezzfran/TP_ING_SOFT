package main;

public class Game {

    private  gameWindow gameWindow;
    private gameScreen gameScreen;

    public Game(){
        gameScreen = new gameScreen();
        gameWindow = new gameWindow(gameScreen);
        gameScreen.requestFocus();
    }

}
