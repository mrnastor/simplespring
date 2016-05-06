package com.javahris.springmvc.dao;

import javax.sql.DataSource;

import org.dbunit.database.DatabaseDataSourceConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.annotations.BeforeMethod;

import com.javahris.springmvc.configuration.HibernateTestConfiguration;
 
 
@ContextConfiguration(classes = { HibernateTestConfiguration.class })
public abstract class EntityDaoImplTest extends AbstractTransactionalTestNGSpringContextTests {
 
    @Autowired
    DataSource dataSource;
 
    @BeforeMethod
    public void setUp() throws Exception {
        IDatabaseConnection dbConn = new DatabaseDataSourceConnection(
                dataSource);
        DatabaseOperation.CLEAN_INSERT.execute(dbConn, getDataSet());
    }
    
/*    @Before
    public void setUpBefore() throws Exception {
//        IDatabaseConnection dbUnitCon = null;
//        EntityManager entityManager = entityManagerFactory.createEntityManager();
//        Session session = entityManager.unwrap(Session.class);
//        SessionImplementor si = (SessionImplementor) session;
//        Connection conn = si.getJdbcConnectionAccess().obtainConnection();
//        dbUnitCon = new DatabaseConnection(conn);

        //dbUnitCon.getConfig().setProperty(DatabaseConfig.FEATURE_QUALIFIED_TABLE_NAMES, true);
        IDatabaseConnection dbUnitCon = new DatabaseDataSourceConnection(
                dataSource);
        IDataSet dataSet = getDataSet();
        DatabaseOperation.INSERT.execute(dbUnitCon, dataSet);
    } */   
     
    protected abstract IDataSet getDataSet() throws Exception;
 
}
