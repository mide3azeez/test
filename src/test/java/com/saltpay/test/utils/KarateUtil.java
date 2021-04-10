package com.saltpay.test.utils;

import com.intuit.karate.cucumber.CucumberRunner;
import com.intuit.karate.cucumber.KarateStats;
import cucumber.api.CucumberOptions;
import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertTrue;


@CucumberOptions()
public class KarateUtil {

    private static Logger log = Logger.getLogger(KarateUtil.class);

    @Test
    public void testParallel() throws Exception{
        String karateOutputPath = "target/surefire-reports";
        KarateStats stats = CucumberRunner.parallel(getClass(), 5, karateOutputPath);
        generateReport(karateOutputPath, "testParallel");
        assertTrue("there are scenario failures", stats.getFailCount() == 0);
    }

    /**
     * This function generates the report after execution
     * @param karateOutputPath
     * @param runnerName
     */
    public static void generateReport(String karateOutputPath, String runnerName) {
        DateTime dt = new DateTime();
        String reportFolderLocation = "./Reports/"+runnerName+"_"+dt.getFormatedTimeStamp();
        try{
            File reportFolder =  new File(reportFolderLocation);
            if(reportFolder.exists()){
                log.info("Report folder located :::::::::  Going on without recreating the folder");
            }
            else {
                reportFolder.mkdir();
            }

            Collection<File> jsonFiles = FileUtils.listFiles(new File(karateOutputPath), new String[] {"json"}, true);
            List<String> jsonPaths = new ArrayList(jsonFiles.size());
            jsonFiles.forEach(file -> jsonPaths.add(file.getAbsolutePath()));
            Configuration config = new Configuration(new File(reportFolderLocation), "VAS");
//            config.setBuildNumber("1233");
            ReportBuilder reportBuilder = new ReportBuilder(jsonPaths, config);
            reportBuilder.generateReports();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     *
     * @return method name
     */
    public static String getMethodName(){
        return Thread.currentThread().getStackTrace()[2].getMethodName();
    }

}