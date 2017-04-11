package com.mucsc3550.cassie.framework;

import java.util.HashMap;

public class Assets {
    public static Pixmap background;
    public static Pixmap logo;
    public static Pixmap mainMenu;
    public static Pixmap keyboard;
    public static Pixmap keyboard_pressed;
    public static Pixmap buttons;
    public static Pixmap help1;
    public static Pixmap help2;
    public static Pixmap help3;
    public static Pixmap numbers;
    public static Pixmap letters;
    public static Pixmap ready;
    public static Pixmap pause;
    public static Pixmap gameOver;
    public static Pixmap hangman;

    public static Sound click;
    
    public static final HashMap<Integer, HashMap<Integer, Character>> keys = new HashMap<Integer, HashMap<Integer, Character>>();
    public static HashMap<Integer,Character> row1 = new HashMap<Integer,Character>();
    public static HashMap<Integer,Character> row2 = new HashMap<Integer,Character>();
    public static HashMap<Integer,Character> row3 = new HashMap<Integer,Character>();
}
