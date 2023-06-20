### Spring Boot Batch 환경 구성
##### 목차

> [DB 연동](#db-연동)
>> [티베로 DB 연동](#티베로-db-연동)

> [JPA 연동](#jpa-연동)


### DB 연동
##### 티베로 DB 연동
 Oracle 이었으면 application.properties 에서 db 정보만 설정해주면
됐을테지만 티베로인 경우에는 Spring Batch 에서 호환하는 DB 가 아니기 때문에
JobRepository 를 설정해줘야 하는 문제가 발생했음.

그러다 보니 자연스럽게 티베로 DB 설정도 Java 로 설정하게 됨.

* * *
- 비즈니스 DB Configuration
```Java
import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;


@Configuration
public class SpringBatchDataSourceConfiguration {

	// spring batch MetaTable DB 정보
    @Bean
    public DataSourceProperties tiberoBatchMetaTableProperties() {
    	DataSourceProperties dataSource = new DataSourceProperties();
    	dataSource.setDriverClassName("비즈니스 DB driver-class-name");
    	dataSource.setUrl("비즈니스 DB url");
    	dataSource.setUsername("MetaTable DB user-name");
    	dataSource.setPassword("MetaTable DB password");
        return dataSource;
    }

    @Bean
    @Primary
    public DataSource tiberoBatchMetaTable() {
        return tiberoBatchMetaTableProperties().initializeDataSourceBuilder().build();

    }

	// 비즈니스 DB 정보
    @Bean
    public DataSourceProperties batchDataSourceProperties() {
    	DataSourceProperties dataSource = new DataSourceProperties();
    	dataSource.setDriverClassName("비즈니스 DB driver-class-name");
    	dataSource.setUrl("비즈니스 DB url");
    	dataSource.setUsername("비즈니스 DB user-name");
    	dataSource.setPassword("비즈니스 DB password");
        return dataSource;
    }

    @Bean
    public DataSource tiberoDataSource() {
        return batchDataSourceProperties().initializeDataSourceBuilder().build();

    }
}
```
* * *
- JobRepository 설정
```Java
import javax.sql.DataSource;

import org.springframework.batch.core.configuration.annotation.DefaultBatchConfigurer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class TiberoBatchConfiguration extends DefaultBatchConfigurer{

	@Autowired
	@Qualifier("tiberoBatchMetaTable")
	private DataSource tiberoBatchMetaTable;

	@Autowired
	@Qualifier("tiberoDataSource")
	private DataSource tiberoDataSource;

	@Autowired
	private PlatformTransactionManager transactionManager;
	@Autowired
    private ApplicationContext applicationContext;

	 public TiberoBatchConfiguration() {
	    super();
	}

	public TiberoBatchConfiguration(DataSource dataSource) {
	    super(dataSource);
	}

	@Override
	protected JobRepository createJobRepository() throws Exception{
        JobRepositoryFactoryBean factory = new JobRepositoryFactoryBean();
        factory.setDataSource(tiberoBatchMetaTable);
        factory.setDatabaseType("ORACLE");           // Tibero Db 는 지원하지 않는 DB 기 때문에 지원되는 DB로 set
        factory.setTablePrefix("원하는 스키마 설정");
        factory.setTransactionManager(transactionManager);
        factory.afterPropertiesSet();
        return factory.getObject();
	}
}
```
* * *

### JPA 연동
```Java
private Runnable getRunnable(String params) {
    return new Runnable() {
        @Override
        public void run() {
            log.info("######### = {}", System.currentTimeMillis());
            jobRunner.start(params);
        }
    };
}
```
```Java
private Runnable getRunnable(String params) {
    return new Runnable() {
        @Override
        public void run() {
            log.info("######### = {}", System.currentTimeMillis());
            jobRunner.start(params);
        }
    };
}
```
```Java
private Runnable getRunnable(String params) {
    return new Runnable() {
        @Override
        public void run() {
            log.info("######### = {}", System.currentTimeMillis());
            jobRunner.start(params);
        }
    };
}
```
```Java
private Runnable getRunnable(String params) {
    return new Runnable() {
        @Override
        public void run() {
            log.info("######### = {}", System.currentTimeMillis());
            jobRunner.start(params);
        }
    };
}
```

## DB 연동

## JPA 연동

## Batch Job 개발
## 개발을 하고 싶어요
## Coding을 잘하고 싶어요
