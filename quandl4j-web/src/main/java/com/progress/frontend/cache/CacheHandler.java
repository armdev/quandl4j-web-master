package com.progress.frontend.cache;

import com.progress.web.auth.Quandl;
import java.io.InputStream;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

/**
 *
 * @author Armen Arzumanyan/armen.arzumanyan@gmail.com
 */
@ApplicationScoped
@ManagedBean(eager = false)
public class CacheHandler implements Serializable {

    private static final long serialVersionUID = 1L;

    private CacheManager manager;
    private Cache userCache;
  

    public CacheHandler() {
    }

    @PostConstruct
    public void init() {
        try {
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            InputStream fis = classLoader.getResourceAsStream("ehcache.xml");
            this.manager = CacheManager.create(fis);
            this.userCache = this.manager.getCache("quandl.list.cache");            
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public boolean putQuandlListToCache(Serializable key, List<Quandl> value) {
        if (key == null || value == null) {
            return false;
        }
        if (this.userCache == null) {
            this.userCache = this.manager.getCache("quandl.list.cache");
        }
        System.out.println("###mainCache put value ");
        userCache.put(new Element(key, value));
        return true;
    }

    public Serializable getQuandlListFromCache(String key) {
        try {
            if (this.userCache == null) {
                this.userCache = this.manager.getCache("quandl.list.cache");
            }
            Element elem = this.userCache.get(key);
            if ((elem != null) && (elem.getValue() != null)) {
                return elem.getValue();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Cache getUserCache() {
        return userCache;
    }

    public void setUserCache(Cache userCache) {
        this.userCache = userCache;
    }
  
    public CacheManager getManager() {
        return manager;
    }

    public void setManager(CacheManager manager) {
        this.manager = manager;
    }

}
