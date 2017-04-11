package com.mucsc3550.cassie.framework;

import android.content.res.AssetFileDescriptor;
import android.text.method.Touch;
import com.mucsc3550.cassie.framework.Graphics;
import com.mucsc3550.cassie.framework.Game;
import com.mucsc3550.cassie.framework.Screen;
import com.mucsc3550.cassie.framework.Input.TouchEvent;
import java.util.List;
import java.util.Set;

public class HighScoreScreen extends Screen {
    String lines[] = new String[5];

    public HighScoreScreen(Game game) {
        super(game);

        for (int i = 4, j = 0; i>=0; i--, j++) {

            lines[j] = "" + (j+1) + ". " + Settings.highscores[i];

        }
    }

    @Override
    public void update(double deltaTime) {
        List<TouchEvent> touchEvents = game.getInput().getTouchEvents();

        int len = touchEvents.size();
        for (int i = 0; i<len; i++) {
            TouchEvent event = touchEvents.get(i);
            if(event.type == TouchEvent.TOUCH_UP) {
                if(event.x < 64 && event.y > 416) {
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
        g.drawPixmap(Assets.background, 0, 0);
        g.drawPixmap(Assets.mainMenu, 53, 20, 0, 65, 220, 55);

        int y = 100;
        for(int i = 0; i < 5; i++) {
            drawText(g, lines[i], 20, y);
            y+= 50;
        }

        g.drawPixmap(Assets.buttons, 0, 410,64,64,64,64);
    }

    private void drawText(Graphics g, String line, int x, int y) {
        int len = line.length();
        for(int i = 0; i < len; i++) {
            char character = line.charAt(i);

            if(character == ' ') {
                x+= 20;
                continue;
            }

            int srcX = 0;
            int srcWidth = 0;

            if(character == '.') {
                srcX = 200;
                srcWidth = 10;
            } else {
                srcX = (character - '0') * 20;
                srcWidth = 20;
            }

            g.drawPixmap(Assets.numbers, x, y, srcX, 0, srcWidth, 32);
            x+= srcWidth;
        }
    }

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void dispose() {}
}
