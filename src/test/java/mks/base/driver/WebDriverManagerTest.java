package mks.base.driver;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.attribute.PosixFilePermission;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.SystemUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;

import mks.java.util.Sleep;
import mks.uiautowagon.interactor.WagonerFacade;
import org.testng.annotations.Test;


import io.github.bonigarcia.wdm.WebDriverManager;

public class WebDriverManagerTest {

	@Test
	public void testMarketo() throws Exception {

		String url = "https://app-sjqe.marketo.com/";
		WebDriver driver = null;
		
		
		
		WebDriverManager.chromedriver().driverVersion("2.44").setup();
		WebDriverManager.chromedriver().driverVersion("2.44").setup();
		driver = new ChromeDriver(createChromeOptions());
		
		driver.get(url);
		WagonerFacade wagoner = new WagonerFacade(driver);
		wagoner.textField.get("Email").sendKeys("sadsads");
		Sleep.for2Seconds();
		wagoner.getCount();
		System.out.println("Going to find LOGIN");
		wagoner.clickElement.get("LOGIN").click();
		Sleep.for5Seconds();
		
		System.err.println("");
		
		wagoner.textField.get("Email").sendKeys("sadsads");
		Sleep.for2Seconds();
		wagoner.getCount();
		System.out.println("Going to find LOGIN");
		wagoner.clickElement.get("LOGIN").click();
		Sleep.for5Seconds();
	}

    /*private static void setDriverPermissions(String driverPath) throws Exception {
        if (!SystemUtils.IS_OS_WINDOWS) {
            try {
                Set<PosixFilePermission> perms = new HashSet<>();
                List<PosixFilePermission> driverPerms = Arrays.asList(PosixFilePermission.GROUP_EXECUTE, PosixFilePermission.GROUP_READ, PosixFilePermission.OTHERS_EXECUTE, PosixFilePermission.OTHERS_READ, PosixFilePermission.OWNER_EXECUTE,
                        PosixFilePermission.OWNER_READ, PosixFilePermission.OWNER_WRITE);
                perms.addAll(driverPerms);
                Files.setPosixFilePermissions(Paths.get(driverPath), perms);
            } catch (UnsupportedOperationException e) {
            	System.err.println("Driver could not be set as an executable");
            } catch (IOException e) {
                throw new Exception(e);
            }
        }
    }*/
    

    private static ChromeOptions createChromeOptions() throws Exception {
        
        ChromeOptions chromeOptions = new ChromeOptions();
        
        // Set Preferences
        Map<String, Object> prefs = new HashMap<String, Object>();
        prefs.put("credentials_enable_service", false);
        prefs.put("password_manager_enabled", false);
        
        // Enable Do Not Tracking?
        if (Boolean.parseBoolean(System.getProperty("enableDNT"))) {
            System.out.println("Enabling do not track feature");
            prefs.put("enable_do_not_track", true);
        }
        
        chromeOptions.setExperimentalOption("prefs", prefs);
        // Notifications enabled for Sky support
        chromeOptions.addArguments("--enable-notifications");
        
        // Incognito?
        if (Boolean.parseBoolean(System.getProperty("privateBrowsingEnabled"))) {
            System.out.println("Setting up private browsing");
            chromeOptions.addArguments("--incognito");
        }
        
        chromeOptions.setCapability(CapabilityType.TAKES_SCREENSHOT, true);
        chromeOptions.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        chromeOptions.setCapability(CapabilityType.SUPPORTS_JAVASCRIPT, true);
        chromeOptions.setCapability("marionette", false);
        
        /*if ((System.getProperty("disableExtensions")!=null) || (Boolean.parseBoolean(System.getProperty("disableExtensions")))) {
            chromeOptions.addArguments("--disable-extensions");
        }else {
            // Load Extensions
            List<File> extensions = getChromeExtensions();
            if (!extensions.isEmpty()) {
                System.out.println("Detected Chrome extensions to load!");
                chromeOptions.addExtensions(extensions);
            }
        }*/
        
        
        return chromeOptions;
    }
    
    

    private static List<File> getChromeExtensions() throws Exception {
        ArrayList<File> extensions = new ArrayList<>();
        // TODO Put chrome plugins into chromeplugins folder
        // Get paths of all files in chromeplugins folder
        File folder = new File(Loader.findAbsolutePath("chromeplugins"));
        for (final File fileEntry : folder.listFiles()) {
            extensions.add(fileEntry);
        }
        return extensions;
    }
}

class Loader {
	private static ClassLoader classLoader = Loader.class.getClassLoader();
	public static String findAbsolutePath(String filePath) throws Exception {
		URL url = classLoader.getResource(filePath);
		if (url == null) {
			throw new Exception(String.format("Unable to find path: %s", filePath));
		}
		return url.getPath();
	}
}
