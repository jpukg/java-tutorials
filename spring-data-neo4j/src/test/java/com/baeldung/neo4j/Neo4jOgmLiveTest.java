package com.nklkarthi.neo4j;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.neo4j.ogm.config.Configuration;
import org.neo4j.ogm.model.Result;
import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.session.SessionFactory;

import com.nklkarthi.spring.data.neo4j.domain.Car;
import com.nklkarthi.spring.data.neo4j.domain.Company;
import org.neo4j.ogm.transaction.Transaction;

public class Neo4jOgmLiveTest {

    @Test
    public void testOgm() {
        Configuration conf = new Configuration();
        conf.driverConfiguration().setDriverClassName("org.neo4j.ogm.drivers.embedded.driver.EmbeddedDriver");

        SessionFactory factory = new SessionFactory(conf, "com.nklkarthi.spring.data.neo4j.domain");
        Session session = factory.openSession();

        Car tesla = new Car("tesla", "modelS");
        Company nklkarthi = new Company("nklkarthi");

        nklkarthi.setCar(tesla);

        session.save(nklkarthi);

        Assert.assertEquals(1, session.countEntitiesOfType(Company.class));

        Map<String, String> params = new HashMap<>();
        params.put("make", "tesla");
        Result result = session.query("MATCH (car:Car) <-[:owns]- (company:Company)" +
                " WHERE car.make=$make" +
                " RETURN company", params);

        Map<String, Object> firstResult = result.iterator().next();

        Assert.assertEquals(firstResult.size(), 1);

        Company actual = (Company) firstResult.get("company");
        Assert.assertEquals(actual.getName(), nklkarthi.getName());
    }
}
