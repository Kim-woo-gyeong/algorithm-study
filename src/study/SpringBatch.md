### Spring Boot Batch 환경 구성
##### 목차

> [DB 연동](#db-연동)
>> [티베로 DB 연동](#티베로-db-연동)

> [JPA 연동](#jpa-연동)


### DB 연동
##### 티베로 DB 연동
> Oracle 인 경우 application.properties
> ```bash
> spring.datasource.driver-class-name=oracle.jdbc.OracleDriver
> spring.datasource.url=jdbc:oracle:thin:@localhost:[Port번호]:DB명
> spring.datasource.username=root
> spring.datasource.password=password
> spring.batch.jdbc.initialize-schema=always
> ```
> spring.batch.jdbc.initialize-schema 설정은 스키마생성을 자동생성 또는 수동생성 하는 옵션 <br>
>  ```bash
> ※ 참고 ※
> ALWAYS: 스크립트를 항상 실행합니다. RDBMS 설정이 되어 있을 경우 내장 DB보다 우선적으로 실행됩니다.
> EMBEDDED: 내장 DB일 때만 실행되며 스키마가 자동 생성됩니다. (기본값)
> NEVER: 스크립트를 항상 실행하지 않습니다. 운영에서 수동으로 스크립트 생성 후 설정하는 것을 권장합니다. 내장 DB일 경우 스크립트가 생성이 안되기 때문에 오류가 발생합니다.
> ```
> 티베로 인 경우에는 spring batch 에서 지원되지 않는 dataType 이기 때문에 티베로와 비슷한 Oracle DB 로 설정 해야한다.<br>
> Spring Batch에서 지원되는 데이터베이스 타입 : DERBY, DB2, DB2ZOS, HSQL, SQLSERVER, MYSQL, ORACLE, POSTGRES, SYBASE, H2
> ```bash
> -- 비표준 데이터베이스 유형에 대해 참고 링크
> https://www.egovframe.go.kr/wiki/doku.php?id=egovframework:rte2:brte:batch_core:job_repository
> https://docs.spring.io/spring-batch/docs/current/reference/html/index-single.html#nonStandardDatabaseTypesInRepository
> ```
> 티베로 DB 연동 필요한 환경 구성
> ```bash
> 1. tibero6-jdbc.jar 설치
> pom.xml 에 was에 있는 tibero 를 불러 올 수 있도록 설정. 만약에 was 가 별도로 없다면, tibero6-jdbc.jar 파일을 로컬경로에 지정해놓으면 됨.
> <dependency> 
> 	<groupId>[tibero가 저장되 있는 was 경로]</groupId>
> 	<artifactId>tibero6</artifactId>
> 	<version>1.0</version>
> </dependency>
>
> 2. application.properties 설정
> spring.batch.jdbc.initialize-schema=never
> 
> 스키마 자동 생성 되지 않도록 설정.
> ++ application.properties 에서 db 설정을 하거나 java 단에서 bean 주입이 되도록 설정!
>
> 3. DB Config 설정
>
> 1 ~ 3번만 하면 환경 구축이 잘 된줄 알았으나 계속해서 Database Type 이 안 맞는다는 오류가 발생.
> 여러 사이트를 보며 고민한 결과, 
> Spring Batch 에서는 batch 의 성공여부 등을 관리하는 MetaTable 이 필요하였고 MetaTable 을 생성 및 설정을 해주었더니 정상적으로 DB 연동 됐음.
> 아래 [비즈니스 DB Configuration] 코드를 보면 비즈니스 DB 와 MetaTable DB 를 설정한 것을 확인 할 수 있음.
> ```
>
> ```bash
> -- Spring Batch 에서 Metatable 이 필수적인 이유 참고 링크
> http://blog.devjoshua.me/2021/03/14/spring-boot-without-metadata-table/
> ```

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
