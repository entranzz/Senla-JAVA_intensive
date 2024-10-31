package Ecosystem;

import java.util.List;

public class PredictionSystem {

    private final List<Plant> plants; // Данные о растениях
    private final List<Animal> animals; // Данные о животных
    private final Logger logger;

    public PredictionSystem(Ecosystem ecosystem) {
        this.plants = ecosystem.getPlants(); // Получение данных растений из экосистемы
        this.animals = ecosystem.getAnimals(); // Получение данных животных из экосистемы
        this.logger = ecosystem.getLogger();
    }

    public String predictPopulationChanges(Ecosystem ecosystem) {
        StringBuilder prediction = new StringBuilder("Population Forecast:\n");

        // Прогноз для растений
        for (Plant plant : plants) {
            double temperature = ecosystem.getTemperature();
            double humidity = ecosystem.getHumidity();
            double resourceAvailability = ecosystem.getWaterResources();

            String change;
            if (isIdealConditions(plant, temperature, humidity, resourceAvailability)) {
                change = "increasing";
            } else if (isStableConditions(plant, temperature, humidity, resourceAvailability)) {
                change = "stable";
            } else {
                change = "decreasing";
            }

            logger.log(String.format("Plant %s is predicted to be: %s", plant.getName(), change));
            prediction.append(String.format("Plant %s: %s\n", plant.getName(), change));
        }

        // Прогноз для животных
        for (Animal animal : animals) {
            double temperature = ecosystem.getTemperature();
            double humidity = ecosystem.getHumidity();
            double foodAvailability = ecosystem.getFood();

            String change;
            if (isIdealConditions(animal, temperature, humidity, foodAvailability)) {
                change = "increasing";
            } else if (isStableConditions(animal, temperature, humidity, foodAvailability)) {
                change = "stable";
            } else {
                change = "decreasing";
            }

            logger.log(String.format("Animal %s is predicted to be: %s", animal.getName(), change));

            prediction.append(String.format("Animal %s: %s\n", animal.getName(), change));
        }

        return prediction.toString();
    }

    private boolean isIdealConditions(Plant plant, double temperature, double humidity, double resourceAvailability) {
        return temperature >= plant.getOptimalMinTemperature() && temperature <= plant.getOptimalMaxTemperature()
                && humidity >= plant.getOptimalMinHumidity() && humidity <= plant.getOptimalMaxHumidity()
                && resourceAvailability > plant.getWaterConsumption();
    }

    private boolean isStableConditions(Plant plant, double temperature, double humidity, double resourceAvailability) {
        return temperature >= plant.getOptimalMinTemperature() && temperature <= plant.getOptimalMaxTemperature()
                && humidity >= plant.getOptimalMinHumidity() && humidity <= plant.getOptimalMaxHumidity()
                && resourceAvailability >= plant.getWaterConsumption() * 0.5;
    }

    private boolean isIdealConditions(Animal animal, double temperature, double humidity, double foodAvailability) {
        return temperature >= animal.getOptimalMinTemperature() && temperature <= animal.getOptimalMaxTemperature()
                && humidity >= animal.getOptimalMinHumidity() && humidity <= animal.getOptimalMaxHumidity()
                && foodAvailability > animal.getFoodConsumption();
    }

    private boolean isStableConditions(Animal animal, double temperature, double humidity, double foodAvailability) {
        return temperature >= animal.getOptimalMinTemperature() && temperature <= animal.getOptimalMaxTemperature()
                && humidity >= animal.getOptimalMinHumidity() && humidity <= animal.getOptimalMaxHumidity()
                && foodAvailability >= animal.getFoodConsumption() * 0.5;
    }
}
