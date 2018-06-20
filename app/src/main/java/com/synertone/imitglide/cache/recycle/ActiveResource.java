package com.synertone.imitglide.cache.recycle;

import com.synertone.imitglide.cache.Key;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class ActiveResource {
    private final Resource.ResourceListener listener;
    private ReferenceQueue<Resource> queue;
    private Map<Key,ResourceWeakReference> activeResources=new HashMap<>();
    private Thread cleanReferenceQueueThread;
    private boolean isShutdown;

    public ActiveResource(Resource.ResourceListener listener) {
        this.listener=listener;
    }
    //加入活动缓存
    public void activate(Key key,Resource resource){
        resource.setListener(key,listener);
        activeResources.put(key, new ResourceWeakReference(key, resource, getReferenceQueue()));
    }
    //移除活动缓存
    public Resource deactivate(Key key){
        ResourceWeakReference reference=activeResources.remove(key);
        if(reference!=null){
             return reference.get();
        }
        return null;
    }
    /**
     * 获得对应value
     * @param key
     * @return
     */
    public Resource get(Key key) {
        ResourceWeakReference reference = activeResources.get(key);
        if (reference != null) {
            return reference.get();
        }
        return null;
    }
    private ReferenceQueue<Resource> getReferenceQueue() {
        if(null==queue){
            queue=new ReferenceQueue<>();
           cleanReferenceQueueThread=new Thread(new Runnable() {
                @Override
                public void run() {
                    while (!isShutdown){
                        try {
                            ResourceWeakReference remove = (ResourceWeakReference) queue.remove();
                            activeResources.remove(remove.key);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
            cleanReferenceQueueThread.start();
        }
       return  queue;
    }
  public void shutdown(){
      isShutdown = true;
      if (cleanReferenceQueueThread != null) {
          cleanReferenceQueueThread.interrupt();
          try {
              //5s  必须结束掉线程
              cleanReferenceQueueThread.join(TimeUnit.SECONDS.toMillis(5));
              if (cleanReferenceQueueThread.isAlive()) {
                  throw new RuntimeException("Failed to join in time");
              }
          } catch (InterruptedException e) {
              e.printStackTrace();
          }
      }
  }
    static final class ResourceWeakReference extends WeakReference<Resource>{
       final Key key;
        public ResourceWeakReference(Key key,Resource referent, ReferenceQueue q) {
            super(referent, q);
            this.key=key;
        }
    }

}
