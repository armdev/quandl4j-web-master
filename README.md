# quandl4j-web-master

Quandl is a source of millions of free data sets covering financial, economic, sociological and country data via an open REST API. Quandl4j is a Java 7+ client-side wrapper for this API provided under the commercially friendly Apache V2 license. It provides a type safe and fluent API in a modern style that takes care of constructing URLs and processing JSON and CSV responses but nonetheless allows access to all the functionality of the underlying REST API.

This project is a front end for Quandl and it get stock values and store in the cache.
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
