package com.synertone.imitglide.core.manager;

public interface Lifecycle {

    void addListener(LifecycleListener listener);

    void removeListener(LifecycleListener listener);
}
