package com.mucsc3550.cassie.framework;

import android.graphics.Canvas;
import android.graphics.Rect;

import com.mucsc3550.cassie.framework.Input.TouchEvent;
import com.mucsc3550.cassie.framework.impl.AndroidPixmap;

import java.util.List;

public class MainMenuScreen extends Screen {
    public MainMenuScreen(Game game) {
        super(game);
    }

    public void update(double deltaTime) {
        Graphics g = game.getGraphics();
        List<TouchEvent> touchEvents = game.getInput().getTouchEvents();

        int len = touchEvents.size();
        for(int i = 0; i < len; i++) {
            TouchEvent event = touchEvents.get(i);
            if(event.type == TouchEvent.TOUCH_UP) {
                if(inBounds(event, 0, g.getHeight() - 64, 64, 64)) {
                    Settings.soundEnabled = !Settings.soundEnabled;
                    if(Settings.soundEnabled) Assets.click.play(1);
                }
                if(inBounds(event, 64, 220, 192, 60)) {
                    game.setScreen(new GamePlay(game));
                    if(Settings.soundEnabled) Assets.click.play(1);
                }
                if(inBounds(event, 64, 220 + 65, 192, 55)) {
                    Settings.addScore(77);
                    game.setScreen(new HighScoreScreen(game));
                    if(Settings.soundEnabled) Assets.click.play(1);
                }
                if(inBounds(event, 64, 220 + 120, 192, 51)) {
                    game.setScreen(new HelpScreen(game));
                    if(Settings.soundEnabled) Assets.click.play(1);
                }
            }
        }
    }

    @Override
    public void present(double deltaTime) {
        Graphics g = game.getGraphics();
        g.drawPixmap(Assets.background, 0, 0);
        g.drawPixmap(Assets.logo, 45, 120);
        g.drawPixmap(Assets.mainMenu, 53, 220);

        if(Settings.soundEnabled)
            g.drawPixmap(Assets.buttons, 0, 410,0,0,64,64);
        else
            g.drawPixmap(Assets.buttons, 0, 410,64,0,64,64);
    }

    @Override
    public void pause() {
        Settings.save(game.getFileIO());
    }

    @Override
    public void resume() {}

    @Override
    public void dispose() {}

    private boolean inBounds(TouchEvent event, int x, int y, int width, int height) {
        if(event.x > x && event.x < x + width - 1 && event.y > y && event.y < y + height - 1) {
            return true;
        }
        else
            return false;
    }
}
