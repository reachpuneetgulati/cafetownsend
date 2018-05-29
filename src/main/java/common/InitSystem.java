package common;


import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class InitSystem {

    private Constants.Driver mDriver;

    public InitSystem(){
        mDriver = null;
    }

    public InitSystem WithBrowser(Constants.Driver driver){
        this.mDriver = driver;
        return this;
    }

    public Settings Init(){
        Settings settings = Settings.initialize();
        Properties properties = loadProperties();
        if (settings != null) {
            settings.setVersion(Integer.parseInt(properties.getProperty("site.version","1")));
        }
        Browser.initBrowser(Constants.Driver.valueOf(properties.getProperty(Constants.USE_BROWSER,"FIREFOX")),properties);
        return settings;
    }

    private Properties loadProperties() {
        InputStream inputStream;
        Properties props = new Properties();
        try {
            inputStream = new FileInputStream(new File("./resources/config/config.properties"));
            props.load(inputStream);
        }
        catch (Exception ignored) {}
        return props;
    }
}
