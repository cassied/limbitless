package com.mucsc3550.cassie.framework;

import android.util.Log;
import com.mucsc3550.cassie.framework.impl.AndroidGame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class GamePlay extends Screen {
    public static String line1, line2;
    public static char letter = '\0';
    public static int xKey, yKey;
    Graphics g = game.getGraphics();

    public GamePlay(Game game) {
        super(game);
        line1 = null;
        line2 = null;
        pickWord();
        g.drawPixmap(Assets.keyboard, 0, 310, 0, 0, 320, 165);
        g.drawPixmap(Assets.hangman, 119, 10, 0, 0, 82, 167);
        g.drawPixmap(Assets.buttons, 0, 0, 0, 193, 32, 32);
    }

    @Override
    public void update(double deltaTime) {
        List<Input.TouchEvent> touchEvents = game.getInput().getTouchEvents();
        int len = touchEvents.size();
        for(int i = 0; i < len; i++) {
            Input.TouchEvent event = touchEvents.get(i);
            if(event.type == Input.TouchEvent.TOUCH_UP) {
                if(event.x < 32 && event.y < 32) {
                    game.setScreen(new MainMenuScreen(game));
                    if(Settings.soundEnabled)
                        Assets.click.play(1);
                }
                if(event.y >= 320) {
                    if(Settings.soundEnabled)
                        Assets.click.play(1);

                    GetCharacterFromCoordinates(event.x, event.y);

                    Log.d("GamePlay", letter + " x: " +xKey + " y: " +yKey);
                    drawRightLetter(g, line1, 200);
                }
            }
        }
    }

    @Override
    public void present(double deltaTime) {
        g.drawPixmap(Assets.background, 0, 0);

        drawBlanks(g, line1.length(), 205);
        if(line2 != null) {
            drawBlanks(g, line2.length(), 255);
        }
    }

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void dispose() {}

    private void drawText(Graphics g, String word, int y) {
        int len = word.length();
        int x = (320 - (len*22)) / 2;
        for(int i = 0; i < len; i++) {
            char character = word.charAt(i);
            int srcX = 0;

            srcX = ((int) character - 97) * 25;

            g.drawPixmap(Assets.letters, x, y, srcX, 0, 22, 32);
            x+= 22;
        }
    }
    private void pickWord() {
        int random = (int)(Math.random() * (AndroidGame.words.length));

        String word = AndroidGame.words[random];
        if(word.matches(".*\\s+.*")) {
            String[] split = word.split("\\s+");
            line1 = split[0];
            line2 = split[1];
        }
        else {
            line1 = word;
        }
    }
    private void drawBlanks(Graphics g, int len, int y) {
        int x = (320 - (len*22)) / 2;
        for(int i = 0; i < len; i++) {
            g.drawPixmap(Assets.letters, x, y, 650, 0, 22, 32);
            x+= 22;
        }
    }
    private void GetCharacterFromCoordinates(int x, int y) {
        yKey = -1;
        xKey = -1;
        letter = '\0';

        ArrayList<Integer> yList = new ArrayList<Integer>(Assets.keys.keySet());
        Collections.sort(yList);

        for(int tempYKey : yList){
            if(tempYKey <= y )
                yKey = tempYKey;
        }

        if(yKey == -1) return;

        HashMap<Integer, Character> currentRow = Assets.keys.get(yKey);
        ArrayList<Integer> xList = new ArrayList<Integer>(currentRow.keySet());
        Collections.sort(xList);

        for(int tempXKey : xList){
            if(tempXKey <= x )
                xKey = tempXKey;
        }

        if(xKey == -1) return;

        letter = currentRow.get(xKey);
    }

    private void drawRightLetter(Graphics g, String word, int y) {
        int x = ((320 - (word.length()*22)) / 2);
        int srcX = ((int) letter - 97) * 25;

        if(word.indexOf(letter) > -1) {
            x = x + (22* word.indexOf(letter));
        }
        else {
            return;
        }
        g.drawPixmap(Assets.letters, x, y, srcX, 0, 22, 32);
    }

    private void drawLimb(Graphics g) {
        //draw left leg gone!
        //g.drawPixmap(Assets.hangman, 126, 128, 88, 118, 32, 47);

        //draw right leg gone!
        //g.drawPixmap(Assets.hangman, 163, 128, 125, 118, 32, 47);

    }
}
