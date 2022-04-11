package properties.readers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import properties.Config;

import java.io.File;
import java.io.IOException;


public class YMLreader {
    public static Config config;

    public Config getConfiguration() {
        return config;
    }

    public YMLreader() {
        try {
            ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
            this.config = mapper.readValue(new File("src/main/resources/configurations/config.yaml"), Config.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
