# quandl4j-web-master
quandl4j ehcache support

Apache Tomcat 8/9, ehcache, JSF2.2, Primefaces 6.1, quandl4j

JSF2.2 web application deployed with maven, which support ehcache .
You can add quandl4j as dependency and create your application.
You can find already configured ehcache.xml file.
Please have a look following configuration in it :
<diskStore path="user.home/ehcache_storage/quandl4j"/>	
<cache name="quandl.list.cache" maxElementsInMemory="100000"
		eternal="false" overflowToDisk="true" timeToIdleSeconds="1200"
		diskPersistent="false" diskExpiryThreadIntervalSeconds="1200">

		<cacheEventListenerFactory
			class="net.sf.ehcache.distribution.RMICacheReplicatorFactory"
			properties="replicateAsynchronously=true,
                replicatePuts=true,
                replicateUpdates=true,
                replicateUpdatesViaCopy=true,
                replicateRemovals=true,
                asynchronousReplicationIntervalMillis=100" />
	</cache>
	
	Also have a look at com.progress.frontend.cache.CacheHandler.java class
	If you set diskStore path and add quandl4j as a dependency, you can build/deploy and run, in the index.xhtml page you can find
	test web form which is do simple call, and after it save it in the cache.
