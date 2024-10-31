package Ecosystem;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class EcosystemSimulator {

    public EcosystemSimulator(Ecosystem ecosystem) {
        this.ecosystem = ecosystem;
    }

    // Инициализация экосистемы с начальными параметрами
    Ecosystem ecosystem = new Ecosystem(25.0, 70.0, 8.0, 1000.0, 10, "eco1.txt");
    Scanner scanner = new Scanner(System.in);
    boolean running = true;

    public void setUserDefinedEnvironment() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter temperature: ");
        double temperature = scanner.nextDouble();

        System.out.print("Enter humidity: ");
        double humidity = scanner.nextDouble();

        System.out.print("Enter amount of sunlight: ");
        double sunlight = scanner.nextDouble();

        System.out.print("Enter amount of water: ");
        double waterResources = scanner.nextDouble();

        System.out.print("Enter amount of food: ");
        int food = scanner.nextInt();

        ecosystem.setTemperature(temperature);
        ecosystem.setHumidity(humidity);
        ecosystem.setSunlight(sunlight);
        ecosystem.setWaterResources(waterResources);
        ecosystem.setFood(food);

        System.out.println("User Defined Environment was set");
    }
    private final Map<String, Plant> plantData = new HashMap<>();
    private final Map<String, Animal> animalData = new HashMap<>();

    public void EcosystemSimulation() {
        initializePlantData();
        initializeAnimalData();
    }

    private void initializePlantData() {
        plantData.put("Oak", new Plant("Oak", 15, 25));
        plantData.put("Pine", new Plant("Pine", 5, 20));
        plantData.put("Cactus", new Plant("Cactus", 30, 45));

    }

    private void initializeAnimalData() {
        animalData.put("Deer", new Animal("Deer", 10, 25));
        animalData.put("Wolf", new Animal("Wolf", 5, 15));
        animalData.put("Rabbit", new Animal("Rabbit", 15, 30));

    }

    public void runPrediction(Ecosystem environment) {
        PredictionSystem predictionSystem = new PredictionSystem(ecosystem);
        String prediction = predictionSystem.predictPopulationChanges(environment);
        System.out.println(prediction);
    }
}

