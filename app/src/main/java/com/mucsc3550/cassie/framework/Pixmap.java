package com.mucsc3550.cassie.framework;

import com.mucsc3550.cassie.framework.Graphics.PixmapFormat;

public interface Pixmap {
    public int getWidth();

    public int getHeight();

    public PixmapFormat getFormat();

    public void dispose();
}
