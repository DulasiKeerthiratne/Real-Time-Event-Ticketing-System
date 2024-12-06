public class Main {
    public static void main(String[] args) {
        //create a new configuration
        Configuration config = new Configuration(0, 0, 0, 0);

        //save the configuration from file
        config.saveToFile();

        //load the configuration from file
        Configuration loadedConfig = config.loadFromFile();
        System.out.println("\n> Loaded configuration: " + loadedConfig);
    }
}