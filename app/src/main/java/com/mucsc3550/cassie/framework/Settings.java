package com.mucsc3550.cassie.framework;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Settings {

    public static boolean soundEnabled;
    public static String settingsArray[];
    public static int[] highscores = new int[5];

    public static void load(FileIO files) {

        BufferedReader in = null;
        try {
            in = new BufferedReader(new InputStreamReader(files.readFile("settings.txt")));
            String settings = in.readLine();
            settingsArray = settings.split(",");

            if(settingsArray[0] != null)
                soundEnabled = Boolean.parseBoolean(settingsArray[0]);
            else
                soundEnabled = true;

            for (int i = 0; i < 5; i++) {
                if(settingsArray[i+1] != null)
                    highscores[i] = Integer.parseInt(settingsArray[i+1]);
                else
                    highscores[i] = 0;
            }
        } catch (IOException e) { e.printStackTrace(); }
        catch (NumberFormatException e) { e.printStackTrace(); }
        finally {
            try {
                if (in != null)
                    in.close();
            } catch (IOException e) { e.printStackTrace(); }
        }
    }

    public static void save(FileIO files) {
        BufferedWriter out = null;
        try {
            out = new BufferedWriter(new OutputStreamWriter(files.writeFile("settings.txt")));
            out.write(Boolean.toString(soundEnabled) + ",");

            for (int i = 0; i < 5; i++) {
                out.write(Integer.toString(highscores[i]) + ",");
            }
        } catch (IOException e) { e.printStackTrace(); }
        finally {
            try {
                if (out != null)
                    out.close();
            } catch (IOException e) { e.printStackTrace();  }
        }
    }

    public static void addScore (int score) {
        if (score >= highscores[0]) {
            highscores[0] = score;
        }

        Arrays.sort(highscores);
    }
}
