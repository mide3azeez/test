package com.saltpay.test.runner;

import com.intuit.karate.cucumber.CucumberRunner;
import com.intuit.karate.cucumber.KarateStats;
import com.saltpay.test.utils.KarateUtil;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.apache.log4j.Logger;
import java.util.Arrays;
import java.util.List;

/**
 * an alternative way to run selected paths, tags and even features using the
 * java api here you don't need to use the CucumberOptions and you can
 * dynamically determine the features that need to be executed
 *
 * @author pthomas3
 */
public class ApiRunner {

    private Logger log = Logger.getLogger(ApiRunner.class);

    @BeforeClass
    public static void beforeClass() {
        System.out.println("Now running the test");
    }

    @Test
    public void runner() {
        try{
            List<String> tags = Arrays.asList("@Football-Api");
            List<String> features = Arrays.asList("src/test/resources/");
            String karateOutputPath = "target/surefire-reports";
            KarateStats stats = CucumberRunner.parallel(tags, features, 1, karateOutputPath);

            log.info("Pringitng out Karate Output :::: "+karateOutputPath);
            KarateUtil.generateReport(karateOutputPath);
            Assert.assertEquals(0, stats.getFailCount());
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
