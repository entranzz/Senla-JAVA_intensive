package Ecosystem;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Ecosystem {

    private List<Plant> plants;  // Список растений
    private List<Animal> animals;  // Список животных

    // Условия окружающей среды
    private double temperature;
    private double humidity;
    private double sunlight;
    private double waterResources;
    private int food;
    private final Logger logger;

    // Конструктор для инициализации экосистемы
    public Ecosystem(double initialTemperature, double initialHumidity, double initialSunlight, double initialWater, int food, String logFilePath) {
        this.plants = new ArrayList<>();
        this.animals = new ArrayList<>();
        this.temperature = initialTemperature;
        this.humidity = initialHumidity;
        this.sunlight = initialSunlight;
        this.waterResources = initialWater;
        this.food = food;
        this.logger = new Logger(logFilePath);
    }
    public void setTemperature(double temperature) {
        this.temperature = temperature;
        logger.log("Temperature changed to: " + temperature);
    }
    public void setFood(int food) {
        this.food = food;
        logger.log("Food changed to: " + food);
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
        logger.log("Humidity changed to: " + humidity);
    }

    public void setSunlight(double sunlight) {
        this.sunlight = sunlight;
        logger.log("Sunlight changed to: " + sunlight);
    }

    public void setWaterResources(double waterResources) {
        this.waterResources = waterResources;
        logger.log("Water resources changed to: " + waterResources);
    }


    // Метод для добавления растений и животных
    public void addPlant(Plant plant) {
        plants.add(plant);
    }

    public void addAnimal(Animal animal) {
        animals.add(animal);
    }

    public double getTemperature() {
        return  temperature;
    }
    public List<Plant> getPlants() {
        return  plants;
    }
    public List<Animal> getAnimals() {
        return  animals;
    }

    public Logger getLogger() {
        return  logger;
    }

    public double getHumidity() {
        return  humidity;
    }
    public double getSunlight() {
        return sunlight;
    }
    public double getWaterResources() {
        return  waterResources;
    }
    public double getFood() {
        return  food;
    }



    // Базовый метод для отображения текущего состояния ресурсов
    public void displayEnvironmentStatus() {
        System.out.println("Eco System status:");
        System.out.println("Temperature: " + String.format("%.1f", temperature) + "°C");
        System.out.println("Humidity: " + String.format("%.1f", humidity) + "%");
        System.out.println("Sunlight: " + String.format("%.1f", sunlight));
        System.out.println("Water: " + String.format("%.1f", waterResources) + " litres");
        System.out.println("food: " + String.format("%.1f", waterResources));
    }

    public void simulateRain(double amount) {
        setWaterResources(amount);
        logger.log("Rain simulation: Water replenished by " + amount);
    }




    // Метод для обновления состояния среды
    public void updateEnvironment() {
        // Пример изменений
        double waterConsumption = 0;
        double waterConsumption1 = 0;
        int foodConsumption = 0;
        temperature += (Math.random() - 0.5) * 2; // Изменение температуры на случайную величину
        humidity += (Math.random() - 0.5) * 5;    // Изменение влажности
        sunlight += (Math.random() - 0.5);        // Изменение солнечного света
        for (int i = 0; i < plants.size(); i++)
        {
            waterConsumption+= plants.get(i).getWaterConsumption();
        }

        for (int i = 0; i < animals.size(); i++)
        {
            waterConsumption1+= animals.get(i).getWaterConsumption();
            foodConsumption+= (int) animals.get(i).getFoodConsumption();
        }
        waterResources -= (waterConsumption+ waterConsumption1); // уменьшение воды в зависимости от числа организмов
        food -= (foodConsumption); // уменьшение воды в зависимости от числа организмов

        // Проверка пределов значений
        if (humidity < 0) humidity = 0;
        if (humidity > 100) humidity = 100;
        if (waterResources < 0) waterResources = 0;
        if (waterResources > 100) waterResources = 100;

        displayEnvironmentStatus(); // Отображаем обновленные условия
    }


    public void saveEcosystemState(String filename) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            // Сохранение параметров экосистемы
            writer.println("Temperature: " + temperature);
            writer.println("Humidity: " + humidity);
            writer.println("Sunlight: " + sunlight);
            writer.println("WaterResources: " + waterResources);
            writer.println("Food:" + food);

            // Сохранение растений
            writer.println("Plants:");
            for (Plant plant : plants) {
                writer.println(plant.getName() + "," + plant.getWaterConsumption() + "," + plant.getSunlightConsumption());
            }

            // Сохранение животных
            writer.println("Animals:");
            for (Animal animal : animals) {
                writer.println(animal.getName() + "," + animal.getWaterConsumption() + "," + animal.getFoodConsumption());
            }

            System.out.println("Eco System status was saved in " + filename);
        } catch (IOException e) {
            System.out.println("Saving error: " + e.getMessage());
        }


    }

    public void displayOrganisms() {
        System.out.println("\n--- Organisms in the Ecosystem ---");

        if (plants.isEmpty() && animals.isEmpty()) {
            System.out.println("No organisms in the ecosystem.");
        } else {
            if (!plants.isEmpty()) {
                System.out.println("Plants:");
                for (Plant plant : plants) {
                    System.out.println(" - " + plant.getName());
                }
            }
            if (!animals.isEmpty()) {
                System.out.println("Animals:");
                for (Animal animal : animals) {
                    System.out.println(" - " + animal.getName());
                }
            }
        }
    }

    public void loadEcosystemState(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            // Загрузка параметров экосистемы
            temperature = Double.parseDouble(reader.readLine().split(": ")[1]);
            humidity = Double.parseDouble(reader.readLine().split(": ")[1]);
            sunlight = Double.parseDouble(reader.readLine().split(": ")[1]);
            waterResources = Double.parseDouble(reader.readLine().split(": ")[1]);
            food = Integer.parseInt(reader.readLine().split(": ")[1]);

            plants.clear();
            animals.clear();

            String line;
            boolean readingAnimals = false; // Флаг для отслеживания, когда читаем животных

            while ((line = reader.readLine()) != null) {
                if (line.equals("Plants:")) {
                    while ((line = reader.readLine()) != null && !line.equals("Animals:")) {
                        String[] plantData = line.split(",");
                        if (plantData.length == 3) { // Проверка, что данные корректные
                            plants.add(new Plant(plantData[0], Double.parseDouble(plantData[1]), Double.parseDouble(plantData[2])));
                        }
                    }
                    readingAnimals = true; // Мы теперь собираемся читать животных
                } else if (readingAnimals && line.equals("Animals:")) {
                    while ((line = reader.readLine()) != null) {
                        String[] animalData = line.split(",");

                            animals.add(new Animal(animalData[0], Double.parseDouble(animalData[1]), Integer.parseInt(animalData[2])));

                    }
                }
            }

            System.out.println("Eco System status loaded from " + filename);
        } catch (IOException | NumberFormatException e) {
            System.out.println("Loading error " + e.getMessage());
        }
    }


    public void simulateStep() {
        System.out.println("Simulating step...");

        // Проверяем и удаляем мёртвые растения
        plants.removeIf(plant -> {
            boolean alive = plant.consumeWater(waterResources);
            boolean unfavorable = plant.checkUnfavorableConditions(temperature, humidity);
            if ((!alive || unfavorable) && plant.isDead())  {
                System.out.println(plant.getName() + " died.");
                return true; // Удаляем растение
            }
            return false;
        });

        // Проверяем и удаляем мёртвых животных
        animals.removeIf(animal -> {
            boolean hasWater = animal.consumeWater(waterResources);
            boolean hasFood = animal.consumeFood(10.0); // Например, есть 10 единиц пищи
            boolean unfavorable = animal.checkUnfavorableConditions(temperature, humidity);
            if ((!hasWater || !hasFood || unfavorable) && animal.isDead()) {
                System.out.println(animal.getName() + " died.");
                return true; // Удаляем животное
            }
            return false;
        });

        // Обновление окружающей среды
        updateEnvironment();

        // Растения потребляют воду и солнечный свет
        for (Plant plant : plants) {
            if (waterResources >= plant.getWaterConsumption()) {
                plant.consumeWater(waterResources);
                plant.checkConditions();
                waterResources -= plant.getWaterConsumption();
            }
            plant.consumeSunlight(sunlight);
        }

        // Животные потребляют воду и ищут пищу
        for (Animal animal : animals) {
            if (waterResources >= animal.getWaterConsumption()) {
                animal.consumeWater(waterResources);
                animal.checkConditions();
                waterResources -= animal.getWaterConsumption();
            }

        }


        // Показ текущего статуса после симуляции
        displayEnvironmentStatus();
        System.out.println("End of step");

    }



    public void startAutomaticSimulation(int steps) {
        System.out.println("Beginning auto-simulation  of" + steps + " steps...");

        for (int i = 1; i <= steps; i++) {
            System.out.println("\nStep " + i + ":");
            simulateStep();
        }

        System.out.println("Auto-simulation is finished");
    }





}
