# Spring Batch + JPA 설정

1. pom.xml
```Java
		<!-- JPA 설정 -->
		<dependency>
		        <groupId>org.springframework.boot</groupId>
		        <artifactId>spring-boot-starter-data-jpa</artifactId>
		</dependency>
		<dependency>
		    <groupId>net.bytebuddy</groupId>
		    <artifactId>byte-buddy</artifactId>
		    <version>1.11.0</version>
		</dependency>
```

2. jpa interface 설정

```Java
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import [패키지].db.entity.SAPL231Entity;

import java.util.List;
import java.util.Map;

@Repository
public interface SubjectRepository extends JpaRepository<SAPL231Entity, String>, JpaSpecificationExecutor<SAPL231Entity>{

    @Query(value =
    		"	SELECT SUBJT_CD\n"+
    		"		 , STUNO\n"+
    		"	FROM [SCHEMA].[TABLE명]\n"+
    		"	WHERE 1 = 1\n"+
    		"		AND SYY = :syy\n"+
    		"		AND SMT_CD = '10'\n"+
    		"		AND ATNLC_DTA_DEL_DISP_CD = '10'\n"+
            "		AND SUBJT_CD = :subjtCd\n",
            nativeQuery = true)
    List<Map<String, Object>> findMemberListSubjtCd(@Param("syy") String syy, @Param("subjtCd") String subjtCd);
}
```

org.hibernate.HibernateException: Unable to determine Dialect to use [name=Tibero, majorVersion=6]; user must register resolver or explicitly set 'hibernate.dialect'
오류 발생

3. application.properties 설정 <br/>
-> <br/>
spring.jpa.database=oracle <br/>
spring.jap.database-platform=org.hibernate.dialect.Oracle10gDialect <br/>
spring.jpa.hibernate.ddl-auto=validate <br/>

hibernate 설정.. hibernate 에 대해 자세히 설명할 예정.

이렇게 1~3 번까지 설정하면

```Java
15:12:45.142 WARN  o.h.e.jdbc.spi.SqlExceptionHelper - SQL Error: -8033, SQLState: 42S02
15:12:45.142 ERROR o.h.e.jdbc.spi.SqlExceptionHelper - JDBC-8033:Specified schema object was not found.
at line 3, column 8 of null:
	FROM [SCHEMA].[TABLE명]
	     ^
```

계속해서 위와 같이 스키마를 못 찾는 오류가 발생..

https://willbfine.tistory.com/559 해당 블로그 참고해서 해결하였음.

```Java
import java.util.HashMap;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableJpaRepositories(
		basePackages = "[JPA Repository 패키지 명]",	// jpa Repository 위치
		entityManagerFactoryRef = "masterEntityManager",
		transactionManagerRef = "masterTransactionManager"
		)
public class JpaDataSourceConfiguration {
	@Autowired
	@Qualifier("tiberoDataSource")
	private DataSource tiberoDataSource;

	@Bean
	public LocalContainerEntityManagerFactoryBean masterEntityManager() {
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(tiberoDataSource);

		//Entity 패키지 경로
        em.setPackagesToScan(new String[] { "[JPA Entity 패키지 명]" });

		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		em.setJpaVendorAdapter(vendorAdapter);

        //Hibernate 설정
		HashMap<String, Object> properties = new HashMap<>();
		properties.put("hibernate.hbm2ddl.auto","validate");
		properties.put("hibernate.dialect","org.hibernate.dialect.Oracle10gDialect");
		properties.put("hibernate.show_sql","true");
		properties.put("hibernate.format_sql","true");
//		properties.put("hibernate.SQL","debug");
//		properties.put("hibernate.type.descriptor.sql","trace");
		em.setJpaPropertyMap(properties);
		return em;
	}

	@Bean
	public PlatformTransactionManager masterTransactionManager() {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(masterEntityManager().getObject());
		return transactionManager;
	}
```
