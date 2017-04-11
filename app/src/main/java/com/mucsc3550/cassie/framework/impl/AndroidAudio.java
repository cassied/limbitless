package com.mucsc3550.cassie.framework.impl;

import android.app.Activity;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.SoundPool;

import com.mucsc3550.cassie.framework.Audio;
import com.mucsc3550.cassie.framework.Music;
import com.mucsc3550.cassie.framework.Sound;

import java.io.IOException;

public class AndroidAudio implements Audio {
    AssetManager assets;
    SoundPool soundPool;

    public AndroidAudio(Activity activity) {
        activity.setVolumeControlStream(AudioManager.STREAM_MUSIC);
        this.assets = activity.getAssets();
        this.soundPool = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
    }

    public Music newMusic(String filename) {
        try {
            AssetFileDescriptor assetDescripter = assets.openFd(filename);
            return new AndroidMusic(assetDescripter);
        } catch (IOException e) {
            throw new RuntimeException("Couldn't load music: " +filename);
        }
    }

    public Sound newSound(String filename) {
        try {
            AssetFileDescriptor assetDescripter = assets.openFd(filename);
            int soundId = soundPool.load(assetDescripter, 0);
            return new AndroidSound(soundPool, soundId);
        } catch (IOException e) {
            throw new RuntimeException("Couldn't load sound: " +filename);
        }
    }
}
