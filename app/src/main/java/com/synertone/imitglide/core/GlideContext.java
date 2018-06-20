package com.synertone.imitglide.core;

import android.content.Context;

import com.synertone.imitglide.core.request.RequestOptions;
import com.synertone.imitglide.load.Engine;


/**
 * @author Lance
 * @date 2018/5/9
 */

public class GlideContext {

    Context context;
    RequestOptions defaultRequestOptions;
    Engine engine;
    Registry registry;

    public GlideContext(Context context, RequestOptions defaultRequestOptions, Engine engine,
                        Registry registry) {
        this.context = context;
        this.defaultRequestOptions = defaultRequestOptions;
        this.engine = engine;
        this.registry = registry;
    }

    public Context getContext() {
        return context;
    }


    public RequestOptions getDefaultRequestOptions() {
        return defaultRequestOptions;
    }

    public Engine getEngine() {
        return engine;
    }

    public Registry getRegistry() {
        return registry;
    }

    public Context getApplicationContext() {
        return context;
    }
}
