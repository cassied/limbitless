package com.mucsc3550.cassie.framework;

import com.mucsc3550.cassie.framework.impl.AndroidGame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class GamePlay extends Screen {
    ArrayList<Character> guessedLetters = new ArrayList<Character>();
    public static String line1, line2;
    public static boolean guessRight, gameOver = false;
    public static char letter = '\0';
    public static int xKey, yKey, wrongGuesses = 0, rightGuesses = 0, wordLength = 0;

    enum GameState { Running, GameOver }
    GameState state = GameState.Running;
    int score = 0;

    public GamePlay(Game game) {
        super(game);
        gameOver = false;
        state = GameState.Running;
        pickWord();
    }

    @Override
    public void update(double deltaTime) {
        List<Input.TouchEvent> touchEvents = game.getInput().getTouchEvents();

        if(state == GameState.Running)
            updateRunning(touchEvents);
        if(state == GameState.GameOver)
            updateGameOver(touchEvents);
    }

    private void updateRunning(List<Input.TouchEvent> touchEvents) {
        int len = touchEvents.size();
        for(int i = 0; i < len; i++) {
            Input.TouchEvent event = touchEvents.get(i);
            if(event.type == Input.TouchEvent.TOUCH_UP) {
                if(event.x < 32 && event.y < 32) {
                    if(Settings.soundEnabled)
                        Assets.click.play(1);
                    game.setScreen(new MainMenuScreen(game));
                }
                else if(event.y >= 320) {
                    if(Settings.soundEnabled)
                        Assets.click.play(1);

                    getCharacterFromCoordinates(event.x, event.y);

                    if(!guessedLetters.contains(letter)) {
                        guessRight = checkGuessedLetter(line1, 200);
                        if (line2 != null)
                            guessRight = checkGuessedLetter(line2, 250);

                        if(!guessRight)
                            drawLimb();

                    }

                    checkGameStatus();
                    guessRight = false;

                }
            }
        }

        if(gameOver) {
            state = GameState.GameOver;
        }
    }

    private void updateGameOver(List<Input.TouchEvent> touchEvents) {
        int len = touchEvents.size();
        for(int i = 0; i < len; i++) {
            Input.TouchEvent event = touchEvents.get(i);
            if(event.type == Input.TouchEvent.TOUCH_UP) {
                if(event.x >= 128 && event.x <= 192 && event.y >= 220 && event.y <= 284) {
                    if(Settings.soundEnabled)
                        Assets.click.play(1);
                    game.setScreen(new MainMenuScreen(game));
                    return;
                }
            }
        }
    }

    @Override
    public void present(double deltaTime) {
        Graphics g = game.getGraphics();

        if(state == GameState.Running)
            drawRunningUI();
        if(state == GameState.GameOver)
            drawGameOverUI();

    }

    private void drawRunningUI() {
        Graphics g = game.getGraphics();
        g.drawPixmap(Assets.buttons, 0, 0, 0, 193, 32, 32);
    }

    private void drawGameWorld() {
        Graphics g = game.getGraphics();

        g.drawPixmap(Assets.background, 0, 0);
        g.drawPixmap(Assets.keyboard, 0, 310, 0, 0, 320, 165);
        g.drawPixmap(Assets.hangman, 119, 10, 0, 0, 82, 167);

        drawBlanks(g, line1.length(), 205);

        if(line2 != null) {
            drawBlanks(g, line2.length(), 255);
        }
    }

    private void drawGameOverUI() {
        Graphics g = game.getGraphics();
        g.drawPixmap(Assets.gameOver, 55, 150);
        g.drawPixmap(Assets.buttons, 128, 220, 0, 128, 64, 64);
    }

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void dispose() {
        Settings.addScore(score);
        Settings.save(game.getFileIO());
    }

    private void pickWord() {
        wrongGuesses = 0;
        rightGuesses = 0;
        line1 = null;
        line2 = null;
        guessedLetters.clear();

        int random = (int)(Math.random() * (AndroidGame.words.length));

        String word = AndroidGame.words[random];

        if(word.matches(".*\\s+.*")) {
            String[] split = word.split("\\s+");
            line1 = split[0];
            line2 = split[1];
            wordLength = line1.length() + line2.length();
        }
        else {
            line1 = word;
            wordLength = word.length();
        }
        drawGameWorld();
    }

    private void drawBlanks(Graphics g, int len, int y) {
        int x = (320 - (len*22)) / 2;
        for(int i = 0; i < len; i++) {
            g.drawPixmap(Assets.letters, x, y, 650, 0, 22, 32);
            x+= 22;
        }
    }

    private void getCharacterFromCoordinates(int x, int y) {
        yKey = -1;
        xKey = -1;
        letter = '\0';

        ArrayList<Integer> yCoords = new ArrayList<Integer>(Assets.keys.keySet());
        Collections.sort(yCoords);

        for(int tempYKey : yCoords){
            if(tempYKey <= y )
                yKey = tempYKey;
        }

        if(yKey == -1) return;

        HashMap<Integer, Character> currentRow = Assets.keys.get(yKey);
        ArrayList<Integer> xCoords = new ArrayList<Integer>(currentRow.keySet());
        Collections.sort(xCoords);

        for(int tempXKey : xCoords){
            if(tempXKey <= x )
                xKey = tempXKey;
        }

        if(xKey == -1) return;

        letter = currentRow.get(xKey);
    }

    private Boolean checkGuessedLetter(String word, int y) {
        Graphics g = game.getGraphics();
        boolean guess = false;

        for(int i = 0; i < word.length(); i++) {
            if(word.charAt(i) == letter) {
                drawRightLetter(g, word, y, i);
                guess = true;
                rightGuesses++;
            }
        }

        guessedLetters.add(letter);

        if(guessRight) {
            return true;
        }
        else
            return guess;

    }

    private void drawRightLetter(Graphics g, String word, int y, int position) {
        int x = ((320 - (word.length()*22)) / 2)  + (22 * position);
        int srcX = ((int) letter - 97) * 25;

        g.drawPixmap(Assets.letters, x, y, srcX, 0, 22, 32);
    }

    private void drawLimb() {
        Graphics g = game.getGraphics();
        if(wrongGuesses == 0)
            g.drawPixmap(Assets.hangman, 126, 128, 88, 118, 32, 47);

        if(wrongGuesses == 1)
            g.drawPixmap(Assets.hangman, 163, 128, 125, 118, 32, 47);

        if(wrongGuesses == 2)
            g.drawPixmap(Assets.hangman, 157, 67, 119, 57, 8, 59);

        if(wrongGuesses == 3)
            g.drawPixmap(Assets.hangman, 126, 67, 88, 57, 32, 47);

        if(wrongGuesses == 4)
            g.drawPixmap(Assets.hangman, 163, 67, 125, 57, 32, 47);

        if(wrongGuesses == 5)
            g.drawPixmap(Assets.hangman, 136, 10, 97, 0, 55, 55);

        wrongGuesses++;
    }

    private void checkGameStatus() {
        if(wrongGuesses > 5) {
            Settings.addScore(score);
            Settings.save(game.getFileIO());

            score = 0;
            gameOver = true;
        }
        else if(rightGuesses == wordLength) {
            score++;
            pickWord();
        }
    }
}
