package com.mucsc3550.cassie.framework;

import android.util.Log;
import android.widget.Toast;
import com.mucsc3550.cassie.framework.impl.AndroidGame;

import java.util.List;

public class GamePlay extends Screen {
    public static String line1, line2;
    public static int letter = 0;
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

                    keyboardInBounds(event.x, event.y);
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

        //drawText(g, line1, 200);

        drawBlanks(g, line1, 205);
        if(line2 != null) {
            drawBlanks(g, line2, 255);
        }
        if(letter != 0) {
            Log.d("GamePlay", "Letter: " +(char) letter);
            letter = 0;
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
    private void keyboardInBounds(int x, int y) {
        if(y >= 320 && y <= 372) {
            Log.d("GamePlay", "1st row" + x);
            g.drawPixmap(Assets.keyboard_pressed, 0, 310, 0, 0, 320, 63);
            letter = 65;
        }
        else if(y >= 373 && y <= 425) {
            Log.d("GamePlay", "2nd row");
        }
        else if(y >= 426 && y <= 478) {
            Log.d("GamePlay", "3rd row");
        }
    }
}
