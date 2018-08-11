/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.ic2.colciencias.cache;

import co.com.ic2.colciencias.gruplac.GrupoInvestigacion;
import java.util.ArrayList;
import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;

/**
 *
 * @author Difer
 */
public class CacheManagerScraper {
    
    private CacheManager cacheManager;
    private static CacheManagerScraper cacheManagerScraper;
    
    public CacheManagerScraper(){
        cacheManager = CacheManagerBuilder.newCacheManagerBuilder()
        .build(); 
        cacheManager.init();
        
        cacheManager.createCache("cacheGrupoInvestigacion", 
            CacheConfigurationBuilder.newCacheConfigurationBuilder(String.class, GrupoInvestigacion.class, ResourcePoolsBuilder.heap(300)));
        
        cacheManager.createCache("cacheCienciaWar", 
            CacheConfigurationBuilder.newCacheConfigurationBuilder(String.class, ArrayList.class, ResourcePoolsBuilder.heap(500)));
    }

    public Cache<String, GrupoInvestigacion> getCacheGrupo() {
        return cacheManager.getCache("cacheGrupoInvestigacion", String.class, GrupoInvestigacion.class);
    }
    
    public Cache<String, ArrayList> getCacheCienciaWar() {
        return cacheManager.getCache("cacheCienciaWar", String.class, ArrayList.class);
    }

    public void setCacheManager(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }
    
    public static CacheManagerScraper getInstance(){
        if(cacheManagerScraper==null){
            cacheManagerScraper=new CacheManagerScraper();
        }
        return cacheManagerScraper;
    }
}
