package com.mucsc3550.cassie.framework;

import android.util.Log;
import com.mucsc3550.cassie.framework.impl.AndroidGame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class GamePlay extends Screen {
    public static String line1, line2;
    public static char Character = '\0';
    public static int xKey, yKey;
    Graphics g = game.getGraphics();

    public GamePlay(Game game) {
        super(game);
        line1 = null;
        line2 = null;
        pickWord();
        g.drawPixmap(Assets.background, 0, 0);
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
                    
                    Log.d("GamePlay", Character + " x: " +xKey + " y: " +yKey);
                }
            }
        }
    }

    @Override
    public void present(double deltaTime) {
        //draw left leg gone!
        //g.drawPixmap(Assets.hangman, 126, 128, 88, 118, 32, 47);

        //draw right leg gone!
        //g.drawPixmap(Assets.hangman, 163, 128, 125, 118, 32, 47);

        drawBlanks(g, line1, 205);
        if(line2 != null) {
            drawBlanks(g, line2, 255);
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
            int srcWidth = 0;

            srcX = ((int) character - 97) * 25;
            srcWidth = 22;

            g.drawPixmap(Assets.letters, x, y, srcX, 0, srcWidth, 32);
            x+= srcWidth;
        }
    }
    private void pickWord() {
        int random = (int)(Math.random() * (AndroidGame.easyWords.length));

        String word = AndroidGame.easyWords[random];
        if(word.matches(".*\\s+.*")) {
            String[] split = word.split("\\s+");
            line1 = split[0];
            line2 = split[1];
        }
        else {
            line1 = word;
        }
    }
    private void drawBlanks(Graphics g, String word, int y) {
        int len = word.length();
        int x = (320 - (len*22)) / 2;
        for(int i = 0; i < len; i++) {
            char character = word.charAt(i);

            int srcWidth = 22;
            int srcX = 650;

            g.drawPixmap(Assets.letters, x, y, srcX, 0, srcWidth, 32);
            x+= srcWidth;
        }
    }
    private void GetCharacterFromCoordinates(int x, int y) {
        yKey = -1;
        xKey = -1;
        Character = '\0';

        ArrayList<Integer> yList = new ArrayList<Integer>(Assets.keys.keySet());
        Collections.sort(yList);

        for(int tempYKey : yList){
            if(tempYKey <= y ){
                yKey = tempYKey;
            }
        }
        if(yKey == -1) return;

        HashMap<Integer, Character> currentRow = Assets.keys.get(yKey);
        ArrayList<Integer> xList = new ArrayList<Integer>(currentRow.keySet());
        Collections.sort(xList);

        for(int tempXKey : xList){
            if(tempXKey <= x ){
                xKey = tempXKey;
            }
        }
        if(xKey == -1) return;

        Character = currentRow.get(xKey);
    }
}
