package com.synertone.imitglide.load.generator;


import com.synertone.imitglide.cache.Key;

/**
 * @author Lance
 * @date 2018/4/21
 */

public interface DataGenerator {
    interface DataGeneratorCallback {

        enum DataSource {
            REMOTE,
            CACHE
        }

        void onDataReady(Key sourceKey, Object data, DataSource dataSource);

        void onDataFetcherFailed(Key sourceKey, Exception e);
    }

    boolean startNext();

    void cancel();
}
