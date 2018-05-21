package bukalapak.utility;

import org.testng.IExecutionListener;

/**
 * Created by Aldo Christian on 21/05/18.
 */

public class TestNGExecutionListener implements IExecutionListener {
    @Override
    public void onExecutionStart() {
        System.out.println("TestNG is staring the execution\n");
    }

    @Override
    public void onExecutionFinish() {
        System.out.println("Generating the Masterthought Report");
        GenerateReport.GenerateMasterthoughtReport();
        System.out.println("TestNG has finished, the execution");
    }
}
