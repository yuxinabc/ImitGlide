package com.synertone.imitglide.load.modelImpl;

import android.net.Uri;

import com.synertone.imitglide.core.ObjectKey;
import com.synertone.imitglide.load.ModelLoader;
import com.synertone.imitglide.load.dataImpl.HttpUriFetcher;
import com.synertone.imitglide.load.regist.ModelLoaderRegistry;

import java.io.InputStream;

public class HttpUriLoader implements ModelLoader<Uri,InputStream> {
    @Override
    public boolean handles(Uri uri) {
        String scheme = uri.getScheme();
        return scheme.equalsIgnoreCase("http")||scheme.equalsIgnoreCase("https");
    }

    @Override
    public LoadData<InputStream> buildData(Uri uri) {
        return new LoadData<>(new ObjectKey(uri), new HttpUriFetcher(uri));
    }
    public static class Factory implements ModelLoaderFactory<Uri, InputStream> {

        @Override
        public ModelLoader<Uri, InputStream> build(ModelLoaderRegistry registry) {
            return new HttpUriLoader();
        }
    }
}
