package com.mucsc3550.cassie.framework.impl;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;

import com.mucsc3550.cassie.framework.Input;

import java.util.List;

public class AndroidInput implements Input {

    Input touchHandler;
    public AndroidInput(Context context, View view, float scaleX, float scaleY) {
        touchHandler = new SingleTouchHandler(view, scaleX, scaleY);
    }

    public boolean isTouchDown(int pointer) {
        return touchHandler.isTouchDown(pointer);
    }

    public int getTouchX(int pointer) {
        return touchHandler.getTouchX(pointer);
    }

    public int getTouchY(int pointer) {
        return touchHandler.getTouchY(pointer);
    }


    public List<TouchEvent> getTouchEvents() {
        return touchHandler.getTouchEvents();
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        return false;
    }
}
