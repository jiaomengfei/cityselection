package utils;

import android.support.v4.util.LruCache;

import com.example.jiao.cityapplication.IndexBar.citybean.CityBean;

import java.util.List;

/**
 * Created by jiao3 on 2018/3/1.
 */

public class LruMemoryCache implements MemoryCache {

    private static LruMemoryCache mInstance;
    private LruCache<String, List<CityBean>> mLruCityCache;
    private static final int MAX_MEMORY = (int) (Runtime.getRuntime().maxMemory() / 1024);
    private static final int CITY_MAX_CACHESIZE = MAX_MEMORY / 16;

    private LruMemoryCache() {}

  public static LruMemoryCache getInstance() {
    if(null == mInstance) {
      synchronized (LruMemoryCache.class) {
        if(null == mInstance) {
          mInstance = new LruMemoryCache();
        }
      }
    }
    return mInstance;
  }

  public void init() {
    mLruCityCache = new LruCache<String, List<CityBean>>( CITY_MAX_CACHESIZE );
  }

    @Override
    public <T> boolean put(String key, T t) {
        return false;
    }

    @Override
    public <T> T get(String key, Class clazz) {
        return null;
    }

    @Override
    public <T> void remove(String key, T t) {

    }

    @Override
    public void clear() {
      if (mLruCityCache != null){
        mLruCityCache.evictAll();}
    }
}
