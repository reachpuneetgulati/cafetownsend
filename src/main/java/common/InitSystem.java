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
        if (mDriver == null)
            return null;
        Settings settings = Settings.initialize();
        Properties properties = loadProperties();
        settings.setVersion(Integer.parseInt(properties.getProperty("site.version")));
        Browser.initBrowser(this.mDriver,properties);
        return settings;
    }

    private Properties loadProperties() {
        InputStream inputStream = null;
        Properties props = new Properties();
        try {
            inputStream = new FileInputStream(new File("./resources/config/config.properties"));
            props.load(inputStream);
        }
        catch (Exception ex) {}
        return props;
    }
}
