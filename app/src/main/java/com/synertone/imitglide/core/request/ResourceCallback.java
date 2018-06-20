package com.synertone.imitglide.core.request;


import com.synertone.imitglide.cache.recycle.Resource;

/**
 * @author Lance
 * @date 2018/4/21
 */

public interface ResourceCallback {
    void onResourceReady(Resource reference);
}
