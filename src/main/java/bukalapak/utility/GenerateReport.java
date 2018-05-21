package bukalapak.utility;

import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aldo Christian on 21/05/18.
 */

public class GenerateReport {

    public static void GenerateMasterthoughtReport() {
        try {
            File reportOutputDirectory = new File("target");
            List<String> jsonFiles = new ArrayList<>();
            jsonFiles.add("target/cucumber-json-report.json");
            String projectName = "Bukalapak Automation";
            Configuration configuration = new Configuration(reportOutputDirectory, projectName);

            ReportBuilder reportBuilder = new ReportBuilder(jsonFiles, configuration);
            reportBuilder.generateReports();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
