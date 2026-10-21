package ir.tamin.incomeBank.util;

import ir.tamin.framework.cdi.CDIBeanFactory;
import ir.tamin.framework.cdi.util.*;
import ir.tamin.framework.core.persistence.ProcedureManager;
import ir.tamin.framework.core.util.Bundle;
import ir.tamin.framework.rest.httpclient.HttpClientJmxConfig;
import ir.tamin.framework.ws.rest.repository.DBFunctionRepository;
import ir.tamin.framework.ws.rest.repository.DBFunctionRepositoryImpl;
import ir.tamin.framework.ws.rest.repository.EntityRepository;
import ir.tamin.framework.ws.rest.repository.EntityRepositoryImpl;
import java.util.Locale;
import java.util.logging.Logger;
import java.util.ResourceBundle;
import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;

/**
 *
 * @author s_tayari
 *
 */
@ApplicationScoped
public class WebResources {

    @Inject
    private CDIBeanFactory cdiBeanFactory;

    @PersistenceContext(unitName = "primary")
    private EntityManager em;

    @Resource(lookup = "datasources/coreaccountDS")
    private DataSource dataSource;

    @Inject
    @ConfigFile
    private Bundle configFile;

    @Produces
    @WebProperties
    @ApplicationScoped
    public Bundle produceConfigFile() {
        return configFile;
    }

    @Produces
    public DataSource produceDataSource() {
        return dataSource;
    }

    @Produces
    public EntityManager produceEntityManager() {
        return em;
    }

    @Produces
    public ProcedureManager produceProcedureManager() {
        return new ProcedureManager(dataSource);
    }

    @Produces
    @ConfigFile
    @DevelopmentConfig
    public Bundle produceDevAppProps() {
        return new Bundle(ResourceBundle.getBundle("dev-web"));
    }

    @Produces
    @ConfigFile
    @SnapshotConfig
    public Bundle produceSnapshotProps() {
        return new Bundle(ResourceBundle.getBundle("snapshot-web"));
    }

    @Produces
    @ConfigFile
    @StageConfig
    public Bundle produceStageProps() {
        return new Bundle(ResourceBundle.getBundle("stage-web"));
    }

    @Produces
    @ConfigFile
    @TestConfig
    public Bundle produceTestProps() {
        return new Bundle(ResourceBundle.getBundle("test-web"));
    }

    @Produces
    @ConfigFile
    @ProductionConfig
    public Bundle produceProdAppProps() {
        return new Bundle(ResourceBundle.getBundle("prod-web"));
    }

    @Produces
    @MessageBundle
    @Named("WebMessages")
    public Bundle produceMessageBundle() {
        return new Bundle("messages/WebMessages", new Locale(configFile.getProperty("application.locale")));
//        return null;
    }

    @Produces
    public Logger produceLogger() {
        return Logger.getAnonymousLogger();
    }

    @Produces
    @ApplicationScoped
    @Named("EntityRepository")
    public EntityRepository produceEntityRepository() {
        EntityRepository entityRepository = new EntityRepositoryImpl("models", em, cdiBeanFactory);
        return entityRepository;
    }

    @Produces
    @ApplicationScoped
    @Named("FunctionRepository")
    public DBFunctionRepository produceFunctionRepository() {
        DBFunctionRepository dbFunctionRepository = new DBFunctionRepositoryImpl("functions", configFile.getProperty("package.root") + "." + configFile.getProperty("package.functions"), dataSource, cdiBeanFactory);
        return dbFunctionRepository;
    }
    @Produces
    @ApplicationScoped
    public HttpClientJmxConfig produceJmxConfig() {
        return new HttpClientJmxConfig(configFile.getProperty("package.root"));
    }
}