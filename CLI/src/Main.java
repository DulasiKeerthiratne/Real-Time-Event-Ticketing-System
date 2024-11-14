import com.google.gson.Gson;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        Configuration newConfig = new Configuration(true);

        //work with gson library
        Gson gson = new Gson();

        //convert to JSON
        String myJson = gson.toJson(newConfig);
        System.out.println(myJson);

        //write to a file
        try (FileWriter writer = new FileWriter("configuration.json")){
            gson.toJson(newConfig, writer);
        } catch (IOException e){
            throw new RuntimeException(e);
        }

        //read form a file
        try{
            FileReader reader = new FileReader("configuration.json");
            Configuration oldConfig = gson.fromJson(reader, Configuration.class);
            System.out.println(oldConfig);
        } catch (FileNotFoundException e){
            throw new RuntimeException(e);
        }
    }
}