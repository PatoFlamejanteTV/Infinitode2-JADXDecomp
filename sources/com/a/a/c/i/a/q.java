package com.a.a.c.i.a;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/* loaded from: infinitode-2.jar:com/a/a/c/i/a/q.class */
public final class q {

    /* renamed from: a, reason: collision with root package name */
    private static Set<String> f509a;

    /* renamed from: b, reason: collision with root package name */
    private Set<String> f510b = f509a;
    private static final q c;

    static {
        HashSet hashSet = new HashSet();
        hashSet.add("org.apache.commons.collections.functors.InvokerTransformer");
        hashSet.add("org.apache.commons.collections.functors.InstantiateTransformer");
        hashSet.add("org.apache.commons.collections4.functors.InvokerTransformer");
        hashSet.add("org.apache.commons.collections4.functors.InstantiateTransformer");
        hashSet.add("org.codehaus.groovy.runtime.ConvertedClosure");
        hashSet.add("org.codehaus.groovy.runtime.MethodClosure");
        hashSet.add("org.springframework.beans.factory.ObjectFactory");
        hashSet.add("com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl");
        hashSet.add("org.apache.xalan.xsltc.trax.TemplatesImpl");
        hashSet.add("com.sun.rowset.JdbcRowSetImpl");
        hashSet.add("java.util.logging.FileHandler");
        hashSet.add("java.rmi.server.UnicastRemoteObject");
        hashSet.add("org.springframework.beans.factory.config.PropertyPathFactoryBean");
        hashSet.add("org.springframework.aop.config.MethodLocatingFactoryBean");
        hashSet.add("org.springframework.beans.factory.config.BeanReferenceFactoryBean");
        hashSet.add("org.apache.tomcat.dbcp.dbcp2.BasicDataSource");
        hashSet.add("com.sun.org.apache.bcel.internal.util.ClassLoader");
        hashSet.add("org.hibernate.jmx.StatisticsService");
        hashSet.add("org.apache.ibatis.datasource.jndi.JndiDataSourceFactory");
        hashSet.add("org.apache.ibatis.parsing.XPathParser");
        hashSet.add("jodd.db.connection.DataSourceConnectionProvider");
        hashSet.add("oracle.jdbc.connector.OracleManagedConnectionFactory");
        hashSet.add("oracle.jdbc.rowset.OracleJDBCRowSet");
        hashSet.add("org.slf4j.ext.EventData");
        hashSet.add("flex.messaging.util.concurrent.AsynchBeansWorkManagerExecutor");
        hashSet.add("com.sun.deploy.security.ruleset.DRSHelper");
        hashSet.add("org.apache.axis2.jaxws.spi.handler.HandlerResolverImpl");
        hashSet.add("org.jboss.util.propertyeditor.DocumentEditor");
        hashSet.add("org.apache.openjpa.ee.RegistryManagedRuntime");
        hashSet.add("org.apache.openjpa.ee.JNDIManagedRuntime");
        hashSet.add("org.apache.openjpa.ee.WASRegistryManagedRuntime");
        hashSet.add("org.apache.axis2.transport.jms.JMSOutTransportInfo");
        hashSet.add("com.mysql.cj.jdbc.admin.MiniAdmin");
        hashSet.add("ch.qos.logback.core.db.DriverManagerConnectionSource");
        hashSet.add("org.jdom.transform.XSLTransformer");
        hashSet.add("org.jdom2.transform.XSLTransformer");
        hashSet.add("net.sf.ehcache.transaction.manager.DefaultTransactionManagerLookup");
        hashSet.add("net.sf.ehcache.hibernate.EhcacheJtaTransactionManagerLookup");
        hashSet.add("ch.qos.logback.core.db.JNDIConnectionSource");
        hashSet.add("com.zaxxer.hikari.HikariConfig");
        hashSet.add("com.zaxxer.hikari.HikariDataSource");
        hashSet.add("org.apache.cxf.jaxrs.provider.XSLTJaxbProvider");
        hashSet.add("org.apache.commons.configuration.JNDIConfiguration");
        hashSet.add("org.apache.commons.configuration2.JNDIConfiguration");
        hashSet.add("org.apache.xalan.lib.sql.JNDIConnectionPool");
        hashSet.add("com.sun.org.apache.xalan.internal.lib.sql.JNDIConnectionPool");
        hashSet.add("org.apache.commons.dbcp.cpdsadapter.DriverAdapterCPDS");
        hashSet.add("org.apache.commons.dbcp.datasources.PerUserPoolDataSource");
        hashSet.add("org.apache.commons.dbcp.datasources.SharedPoolDataSource");
        hashSet.add("com.p6spy.engine.spy.P6DataSource");
        hashSet.add("org.apache.log4j.receivers.db.DriverManagerConnectionSource");
        hashSet.add("org.apache.log4j.receivers.db.JNDIConnectionSource");
        hashSet.add("net.sf.ehcache.transaction.manager.selector.GenericJndiSelector");
        hashSet.add("net.sf.ehcache.transaction.manager.selector.GlassfishSelector");
        hashSet.add("org.apache.xbean.propertyeditor.JndiConverter");
        hashSet.add("org.apache.hadoop.shaded.com.zaxxer.hikari.HikariConfig");
        hashSet.add("com.ibatis.sqlmap.engine.transaction.jta.JtaTransactionConfig");
        hashSet.add("br.com.anteros.dbcp.AnterosDBCPConfig");
        hashSet.add("br.com.anteros.dbcp.AnterosDBCPDataSource");
        hashSet.add("javax.swing.JEditorPane");
        hashSet.add("javax.swing.JTextPane");
        hashSet.add("org.apache.shiro.realm.jndi.JndiRealmFactory");
        hashSet.add("org.apache.shiro.jndi.JndiObjectFactory");
        hashSet.add("org.apache.ignite.cache.jta.jndi.CacheJndiTmLookup");
        hashSet.add("org.apache.ignite.cache.jta.jndi.CacheJndiTmFactory");
        hashSet.add("org.quartz.utils.JNDIConnectionProvider");
        hashSet.add("org.apache.aries.transaction.jms.internal.XaPooledConnectionFactory");
        hashSet.add("org.apache.aries.transaction.jms.RecoverablePooledConnectionFactory");
        hashSet.add("com.caucho.config.types.ResourceRef");
        hashSet.add("org.aoju.bus.proxy.provider.RmiProvider");
        hashSet.add("org.aoju.bus.proxy.provider.remoting.RmiProvider");
        hashSet.add("org.apache.activemq.ActiveMQConnectionFactory");
        hashSet.add("org.apache.activemq.ActiveMQXAConnectionFactory");
        hashSet.add("org.apache.activemq.spring.ActiveMQConnectionFactory");
        hashSet.add("org.apache.activemq.spring.ActiveMQXAConnectionFactory");
        hashSet.add("org.apache.activemq.pool.JcaPooledConnectionFactory");
        hashSet.add("org.apache.activemq.pool.PooledConnectionFactory");
        hashSet.add("org.apache.activemq.pool.XaPooledConnectionFactory");
        hashSet.add("org.apache.activemq.jms.pool.XaPooledConnectionFactory");
        hashSet.add("org.apache.activemq.jms.pool.JcaPooledConnectionFactory");
        hashSet.add("org.apache.commons.proxy.provider.remoting.RmiProvider");
        hashSet.add("org.apache.commons.jelly.impl.Embedded");
        hashSet.add("oadd.org.apache.xalan.lib.sql.JNDIConnectionPool");
        hashSet.add("oadd.org.apache.commons.dbcp.cpdsadapter.DriverAdapterCPDS");
        hashSet.add("oadd.org.apache.commons.dbcp.datasources.PerUserPoolDataSource");
        hashSet.add("oadd.org.apache.commons.dbcp.datasources.SharedPoolDataSource");
        hashSet.add("oracle.jms.AQjmsQueueConnectionFactory");
        hashSet.add("oracle.jms.AQjmsXATopicConnectionFactory");
        hashSet.add("oracle.jms.AQjmsTopicConnectionFactory");
        hashSet.add("oracle.jms.AQjmsXAQueueConnectionFactory");
        hashSet.add("oracle.jms.AQjmsXAConnectionFactory");
        hashSet.add("org.jsecurity.realm.jndi.JndiRealmFactory");
        hashSet.add("com.pastdev.httpcomponents.configuration.JndiConfiguration");
        hashSet.add("com.nqadmin.rowset.JdbcRowSetImpl");
        hashSet.add("org.arrah.framework.rdbms.UpdatableJdbcRowsetImpl");
        hashSet.add("org.apache.commons.dbcp2.datasources.PerUserPoolDataSource");
        hashSet.add("org.apache.commons.dbcp2.datasources.SharedPoolDataSource");
        hashSet.add("org.apache.commons.dbcp2.cpdsadapter.DriverAdapterCPDS");
        hashSet.add("com.newrelic.agent.deps.ch.qos.logback.core.db.JNDIConnectionSource");
        hashSet.add("com.newrelic.agent.deps.ch.qos.logback.core.db.DriverManagerConnectionSource");
        hashSet.add("org.apache.tomcat.dbcp.dbcp.cpdsadapter.DriverAdapterCPDS");
        hashSet.add("org.apache.tomcat.dbcp.dbcp.datasources.PerUserPoolDataSource");
        hashSet.add("org.apache.tomcat.dbcp.dbcp.datasources.SharedPoolDataSource");
        hashSet.add("org.apache.tomcat.dbcp.dbcp2.cpdsadapter.DriverAdapterCPDS");
        hashSet.add("org.apache.tomcat.dbcp.dbcp2.datasources.PerUserPoolDataSource");
        hashSet.add("org.apache.tomcat.dbcp.dbcp2.datasources.SharedPoolDataSource");
        hashSet.add("com.oracle.wls.shaded.org.apache.xalan.lib.sql.JNDIConnectionPool");
        hashSet.add("org.docx4j.org.apache.xalan.lib.sql.JNDIConnectionPool");
        f509a = Collections.unmodifiableSet(hashSet);
        c = new q();
    }

    protected q() {
    }

    public static q a() {
        return c;
    }

    public final void a(com.a.a.c.g gVar, com.a.a.c.j jVar, com.a.a.c.b bVar) {
        Class<?> b2 = jVar.b();
        String name = b2.getName();
        if (!this.f510b.contains(name)) {
            if (!b2.isInterface()) {
                if (name.startsWith("org.springframework.")) {
                    Class<?> cls = b2;
                    while (true) {
                        Class<?> cls2 = cls;
                        if (cls2 != null && cls2 != Object.class) {
                            String simpleName = cls2.getSimpleName();
                            if ("AbstractPointcutAdvisor".equals(simpleName) || "AbstractApplicationContext".equals(simpleName)) {
                                break;
                            } else {
                                cls = cls2.getSuperclass();
                            }
                        } else {
                            return;
                        }
                    }
                } else if (!name.startsWith("com.mchange.v2.c3p0.") || !name.endsWith("DataSource")) {
                    return;
                }
            } else {
                return;
            }
        }
        gVar.a(bVar, "Illegal type (%s) to deserialize: prevented for security reasons", name);
    }
}
