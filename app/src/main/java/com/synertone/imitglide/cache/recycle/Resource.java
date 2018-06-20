package com.synertone.imitglide.cache.recycle;

import android.graphics.Bitmap;

import com.synertone.imitglide.cache.Key;

public class Resource {
    private Bitmap bitmap;
    //引用计数
    private int acquired;
    private ResourceListener listener;
    private Key key;

    public Resource(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public interface ResourceListener{
        void resourceReleased(Key key,Resource resource);
    }

    public void setListener(Key key,ResourceListener listener) {
        this.key=key;
        this.listener = listener;
    }
    public void release(){
        if(--acquired==0){
            listener.resourceReleased(key,this);
        }
    }
    public void acquire(){
        if(bitmap.isRecycled()){
            throw new IllegalStateException("Acquire a recycled bitmap");
        }
        ++acquired;
    }
    public void recycle(){
        if(acquired>0){
            return;
        }
        if(!bitmap.isRecycled()){
            bitmap.recycle();
        }
    }
}
