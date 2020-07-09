package com.tistory.ospace.simplesecurity2.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cache.ehcache.EhCacheFactoryBean;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.acls.AclPermissionEvaluator;
import org.springframework.security.acls.domain.AclAuthorizationStrategy;
import org.springframework.security.acls.domain.AclAuthorizationStrategyImpl;
import org.springframework.security.acls.domain.ConsoleAuditLogger;
import org.springframework.security.acls.domain.DefaultPermissionGrantingStrategy;
import org.springframework.security.acls.domain.EhCacheBasedAclCache;
import org.springframework.security.acls.jdbc.BasicLookupStrategy;
import org.springframework.security.acls.jdbc.JdbcMutableAclService;
import org.springframework.security.acls.jdbc.LookupStrategy;
import org.springframework.security.acls.model.PermissionGrantingStrategy;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

@Configuration
@EnableAutoConfiguration
public class AclSecurityContext {
//	private static Logger logger = LoggerFactory.getLogger(AclSecurityContext.class);
	
	@Autowired
	private DataSource dataSource;
	
	@Bean
	public MethodSecurityExpressionHandler defaultMethodSecurityExpressionHandler() {
		DefaultMethodSecurityExpressionHandler expressionHandler =
				new DefaultMethodSecurityExpressionHandler();
	    AclPermissionEvaluator permissionEvaluator =
	    		new AclPermissionEvaluator(aclService());
	    expressionHandler.setPermissionEvaluator(permissionEvaluator);
	    return expressionHandler;
	}
	
	@Bean 
	public JdbcMutableAclService aclService() { 
	    return new JdbcMutableAclService(dataSource, lookupStrategy(), aclCache()); 
	}
	
	/*
	 * the AclAuthorizationStrategy is in charge of concluding
	 * whether a current user possesses all required permissions
	 * on certain objects or not.
	 */
	@Bean
	public AclAuthorizationStrategy aclAuthorizationStrategy() {
	    return new AclAuthorizationStrategyImpl(
	      new SimpleGrantedAuthority("ROLE_ADMIN"));
	}
	 
	@Bean
	public PermissionGrantingStrategy permissionGrantingStrategy() {
	    return new DefaultPermissionGrantingStrategy(new ConsoleAuditLogger());
	}
	 
	@Bean
	public EhCacheBasedAclCache aclCache() {
	    return new EhCacheBasedAclCache(aclEhCacheFactoryBean().getObject(),
	    		permissionGrantingStrategy(), aclAuthorizationStrategy());
	}
	 
	@Bean
	public EhCacheFactoryBean aclEhCacheFactoryBean() {
	    EhCacheFactoryBean ehCacheFactoryBean = new EhCacheFactoryBean();
	    ehCacheFactoryBean.setCacheManager(aclCacheManager().getObject());
	    ehCacheFactoryBean.setCacheName("aclCache");
	    return ehCacheFactoryBean;
	}
	 
	@Bean
	public EhCacheManagerFactoryBean aclCacheManager() {
	    return new EhCacheManagerFactoryBean();
	}
	 
	@Bean
	public LookupStrategy lookupStrategy() {
	    return new BasicLookupStrategy(dataSource, aclCache(), aclAuthorizationStrategy(), new ConsoleAuditLogger()); 
	}

}
