package com.mucsc3550.cassie.framework;

import com.mucsc3550.cassie.R;

import java.util.ResourceBundle;

public class LoadingScreen extends Screen {
    public LoadingScreen(Game game) {
        super(game);
    }

    public void update(double deltaTime) {
        Graphics g = game.getGraphics();
        Assets.background = g.newPixmap("background.png", Graphics.PixmapFormat.RGB565);
        Assets.logo = g.newPixmap("logo.png", Graphics.PixmapFormat.ARGB4444);
        Assets.mainMenu = g.newPixmap("mainmenu.png", Graphics.PixmapFormat.ARGB4444);
        Assets.keyboard = g.newPixmap("keyboard.png", Graphics.PixmapFormat.ARGB4444);
        Assets.keyboard_pressed = g.newPixmap("keyboard_pressed.png", Graphics.PixmapFormat.ARGB4444);
        Assets.logo = g.newPixmap("logo.png", Graphics.PixmapFormat.ARGB4444);
        Assets.buttons = g.newPixmap("buttons.png", Graphics.PixmapFormat.ARGB4444);
        Assets.help1 = g.newPixmap("help1.png", Graphics.PixmapFormat.ARGB4444);
        Assets.help2 = g.newPixmap("help2.png", Graphics.PixmapFormat.ARGB4444);
        Assets.help3 = g.newPixmap("help3.png", Graphics.PixmapFormat.ARGB4444);
        Assets.numbers = g.newPixmap("numbers.png", Graphics.PixmapFormat.ARGB4444);
        Assets.letters = g.newPixmap("letters.png", Graphics.PixmapFormat.ARGB4444);
        Assets.ready = g.newPixmap("ready.png", Graphics.PixmapFormat.ARGB4444);
        Assets.pause = g.newPixmap("pausemenu.png", Graphics.PixmapFormat.ARGB4444);
        Assets.gameOver = g.newPixmap("gameover.png", Graphics.PixmapFormat.ARGB4444);
        Assets.hangman = g.newPixmap("hangman.png", Graphics.PixmapFormat.ARGB4444);

        Assets.click = game.getAudio().newSound("click.ogg");

        Settings.load(game.getFileIO());
        game.setScreen(new MainMenuScreen(game));
    }

    @Override
    public void present(double deltaTime) {}
    @Override
    public void pause() {}
    @Override
    public void resume() {}
    @Override
    public void dispose() {}
}
