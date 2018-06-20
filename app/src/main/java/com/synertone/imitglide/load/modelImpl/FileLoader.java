package com.synertone.imitglide.load.modelImpl;

import android.net.Uri;
import android.support.annotation.NonNull;


import com.synertone.imitglide.load.ModelLoader;
import com.synertone.imitglide.load.regist.ModelLoaderRegistry;

import java.io.File;
import java.io.InputStream;

/**
 * @author Lance
 * @date 2018/4/21
 */

public class FileLoader<Data> implements ModelLoader<File, Data> {
    private final ModelLoader<Uri, Data> loader;


    public FileLoader(ModelLoader<Uri, Data> loader) {
        this.loader = loader;
    }


    @Override
    public boolean handles(File file) {
        return true;
    }

    @Override
    public LoadData<Data> buildData(File file) {
        return loader.buildData(Uri.fromFile(file));
    }

    public static class Factory implements ModelLoaderFactory<File, InputStream> {

        @NonNull
        @Override
        public ModelLoader<File, InputStream> build(ModelLoaderRegistry modelLoaderRegistry) {
            return new FileLoader(modelLoaderRegistry.build(Uri.class, InputStream
                    .class));
        }

    }

}
