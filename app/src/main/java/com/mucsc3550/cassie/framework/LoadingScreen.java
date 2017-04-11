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
        populateKeys();
        
        Settings.load(game.getFileIO());
        game.setScreen(new MainMenuScreen(game));
    }

    private void populateKeys() {
        Assets.row1.put(5, 'q');
        Assets.row1.put(40, 'w');
        Assets.row1.put(70, 'e');
        Assets.row1.put(100, 'r');
        Assets.row1.put(130, 't');
        Assets.row1.put(160, 'y');
        Assets.row1.put(190, 'u');
        Assets.row1.put(220, 'i');
        Assets.row1.put(250, 'o');
        Assets.row1.put(280, 'p');

        Assets.row2.put(5, 'a');
        Assets.row2.put(40, 's');
        Assets.row2.put(70, 'd');
        Assets.row2.put(100, 'f');
        Assets.row2.put(130, 'g');
        Assets.row2.put(160, 'h');
        Assets.row2.put(190, 'j');
        Assets.row2.put(220, 'k');
        Assets.row2.put(250, 'l');

        Assets.row3.put(70, 'z');
        Assets.row3.put(100, 'x');
        Assets.row3.put(130, 'c');
        Assets.row3.put(160, 'v');
        Assets.row3.put(190, 'b');
        Assets.row3.put(220, 'n');
        Assets.row3.put(250, 'm');

        Assets.keys.put(320, Assets.row1);
        Assets.keys.put(372, Assets.row2);
        Assets.keys.put(434, Assets.row3);
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
