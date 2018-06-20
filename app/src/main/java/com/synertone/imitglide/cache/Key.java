package com.synertone.imitglide.cache;

import java.security.MessageDigest;

public interface Key {
    void updateDiskCacheKey(MessageDigest messageDigest);
    byte[] getKeyBytes();
}
