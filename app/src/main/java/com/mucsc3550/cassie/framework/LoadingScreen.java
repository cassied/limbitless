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
        Assets.row1.put(9, 'q');
        Assets.row1.put(42, 'w');
        Assets.row1.put(82, 'e');
        Assets.row1.put(107, 'r');
        Assets.row1.put(137, 't');
        Assets.row1.put(166, 'y');
        Assets.row1.put(196, 'u');
        Assets.row1.put(227, 'i');
        Assets.row1.put(248, 'o');
        Assets.row1.put(282, 'p');
        Assets.row1.put(312, '\0');

        Assets.row2.put(27, 'a');
        Assets.row2.put(58, 's');
        Assets.row2.put(89, 'd');
        Assets.row2.put(121, 'f');
        Assets.row2.put(147, 'g');
        Assets.row2.put(180, 'h');
        Assets.row2.put(213, 'j');
        Assets.row2.put(235, 'k');
        Assets.row2.put(267, 'l');
        Assets.row2.put(293, '\0');

        Assets.row3.put(49, 'z');
        Assets.row3.put(77, 'x');
        Assets.row3.put(108, 'c');
        Assets.row3.put(140, 'v');
        Assets.row3.put(170, 'b');
        Assets.row3.put(201, 'n');
        Assets.row3.put(234, 'm');
        Assets.row3.put(271, '\0');

        Assets.keys.put(320, Assets.row1);
        Assets.keys.put(372, Assets.row2);
        Assets.keys.put(422, Assets.row3);
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
