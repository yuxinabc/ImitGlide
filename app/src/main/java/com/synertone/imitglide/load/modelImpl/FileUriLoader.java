package com.synertone.imitglide.load.modelImpl;

import android.content.ContentResolver;
import android.net.Uri;
import com.synertone.imitglide.core.ObjectKey;
import com.synertone.imitglide.load.ModelLoader;
import com.synertone.imitglide.load.dataImpl.FileUriFetcher;
import com.synertone.imitglide.load.regist.ModelLoaderRegistry;


import java.io.InputStream;

/**
 * Created by Administrator on 2018/5/7.
 */
public class FileUriLoader implements ModelLoader<Uri, InputStream> {

    private final ContentResolver contentResolver;

    public FileUriLoader(ContentResolver contentResolver) {
        this.contentResolver = contentResolver;
    }

    @Override
    public boolean handles(Uri uri) {
//        ContentResolver.SCHEME_FILE
        return ContentResolver.SCHEME_FILE.equalsIgnoreCase(uri.getScheme());
    }

    @Override
    public LoadData<InputStream> buildData(Uri uri) {
        return new LoadData<>(new ObjectKey(uri), new FileUriFetcher(uri, contentResolver));
    }
    public static class Factory implements ModelLoaderFactory<Uri, InputStream> {

        private final ContentResolver contentResolver;

        public Factory(ContentResolver contentResolver) {
            this.contentResolver = contentResolver;
        }

        @Override
        public ModelLoader<Uri, InputStream> build(ModelLoaderRegistry registry) {
            return new FileUriLoader(contentResolver);
        }
    }

}
