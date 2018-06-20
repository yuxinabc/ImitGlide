package com.synertone.imitglide.cache;

import com.synertone.imitglide.cache.recycle.Resource;

public interface MemoryCache {
    void clearMemory();
    void trimMemory(int level);
    interface  ResourceRemoveListener{
        void onResourceRemoved(Resource resource);
    }
    void setResourceRemoveListener(ResourceRemoveListener listener);
    Resource put(Key key,Resource resource);
    Resource remove2(Key key);
}
