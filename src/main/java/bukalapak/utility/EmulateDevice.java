package bukalapak.utility;

import org.openqa.selenium.Dimension;

/**
 * Created by Aldo Christian on 21/05/18.
 */

public class EmulateDevice {

    private static Dimension screenDimension;

    public static Dimension emulateDevice(String emulation) {
        switch (emulation) {
            case "Nexus 5":
                screenDimension = new Dimension(360, 640);
                break;
            case "Nexus 5X":
                screenDimension = new Dimension(412, 732);
                break;
            case "Nexus 7":
                screenDimension = new Dimension(600, 960);
                break;
            case "Pixel 2":
                screenDimension = new Dimension(411, 731);
                break;
            case "Pixel 2 XL":
                screenDimension = new Dimension(411, 823);
                break;
            case "Iphone 5/SE":
                screenDimension = new Dimension(320, 568);
                break;
            case "Iphone 6/7/8":
                screenDimension = new Dimension(375, 667);
                break;
            case "Iphone 6/7/8 Plus":
                screenDimension = new Dimension(414, 736);
                break;
            case "Iphone X":
                screenDimension = new Dimension(375, 812);
                break;
            case "Ipad":
                screenDimension = new Dimension(768, 1024);
                break;
            case "Ipad Pro":
                screenDimension = new Dimension(1024, 1366);
                break;
            default:
                screenDimension = new Dimension(360, 640);
                break;
        }

        return screenDimension;
    }

}
