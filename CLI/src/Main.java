public class Main {
    public static void main(String[] args) {
        // Create a new configuration
        Configuration config = new Configuration(true);

        //Save the configuration from file
        config.saveToFile();

        // Load the configuration from file
        Configuration loadedConfig = config.loadFromFile();
        System.out.println("Loaded configuration: " + loadedConfig);
    }
}
