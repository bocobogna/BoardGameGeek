package intern.BGGStart.utils;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileReader;

public class Utils {

    public static JSONObject readJsonFromFile(String filePath) {
        JSONObject jsonObject = null;
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader(filePath));
            jsonObject = (JSONObject) obj;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    public static String getPathToResourcesFiles(String fileName) {
        File file = new File("src/test/resources/" + fileName);
        return file.getPath();
    }
}
