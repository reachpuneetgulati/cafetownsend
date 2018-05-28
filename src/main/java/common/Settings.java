package common;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;

public class Settings {

    private WebDriver mDriver;
    private String mMasterWindowHandle;
    private static ArrayList<SiteVersion> mAllVersions;
    private static ArrayList<SiteVersion> mVersions;
    private static int version;
    private static Logger LOG;

    public Settings(@JsonProperty("siteVersions") ArrayList<SiteVersion> versions) {
        Settings.mVersions = versions;
        Settings.mAllVersions = new ArrayList<SiteVersion>();
        for (SiteVersion version : versions){
            Settings.mAllVersions.add(version);
        }
    }

    public static Settings initialize(){
        InputStream in = null;
        try {
            File file = new File("./resources/ui/map.json");
            in = new FileInputStream(file);
            return (Settings)new ObjectMapper().readValue(in, (Class)Settings.class);
        }
        catch (Exception e) {
            Settings.LOG.error((Object)"Error processing metadata.json resource", (Throwable)e);
        }
        finally {
            IOUtils.closeQuietly(in);
        }
        return null;
    }

    public static String getDateFormatPattern(String dateFormatStartDate) {
        Integer index = SiteVersion.getBestIndexFromList(getVersion(), Settings.mAllVersions);
        SiteVersion siteVersion = Settings.mVersions.get(index);
        while (index >= 0) {
            final String byConstant = siteVersion.getDateFormat(dateFormatStartDate);
            if (byConstant != null) {
                return byConstant;
            }
            --index;
            siteVersion = Settings.mVersions.get(index);
        }
        return null;
    }

    public WebDriver getDriver(){
        return mDriver;
    }

    public static int getVersion() {
        return Settings.version;
    }

    public static String getXpath(String label) {
        Integer index = SiteVersion.getBestIndexFromList(getVersion(), Settings.mAllVersions);
        SiteVersion siteVersion = Settings.mVersions.get(index);
        while (index >= 0) {
            final String byConstant = siteVersion.getByConstant(label);
            if (byConstant != null) {
                return byConstant;
            }
            --index;
            siteVersion = Settings.mVersions.get(index);
        }
        return null;
    }

    public WebDriver getmDriver() {
        return mDriver;
    }

    public String getmMasterWindowHandle() {
        return mMasterWindowHandle;
    }

    public void setmMasterWindowHandle(String mMasterWindowHandle) {
        this.mMasterWindowHandle = mMasterWindowHandle;
    }
    public void setmDriver(WebDriver mDriver) {
        this.mDriver = mDriver;
        this.setmMasterWindowHandle(this.mDriver.getWindowHandle());
    }


    public void setVersion(int version) {
        this.version = version;
    }

    static {
        LOG = LogManager.getLogger(Settings.class);
    }
}
