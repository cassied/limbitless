package com.mucsc3550.cassie.framework;

public abstract class Screen {
    protected final Game game;

    public Screen(Game game) {
        this.game = game;
    }

    public abstract void update(double deltaTime);

    public abstract void present(double deltaTime);

    public abstract void pause();

    public abstract void resume();

    public abstract void dispose();
}
