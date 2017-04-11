package com.mucsc3550.cassie.framework;

import com.mucsc3550.cassie.framework.impl.AndroidGame;

public class Limbitless extends AndroidGame {

    public Screen getStartScreen() {
        return new LoadingScreen(this);
    }

}
