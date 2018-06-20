package com.synertone.imitglide.cache;

import android.graphics.Bitmap;

public interface BitmapPool {
    void put(Bitmap bitmap);
    Bitmap get(int width, int height, Bitmap.Config config);
    void clearMemory();
    void trimMemory(int level);
}
