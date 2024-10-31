package Ecosystem;

public class Animal extends Organism {
    private double waterConsumption;
    private int foodConsumption;
    private int daysWithoutWater; // Счётчик дней без воды
    private int daysWithoutFood; // Счётчик дней без еды
    private int daysInUnfavorableConditions;
    private final int maxDaysWithoutWater = 3; // Максимум дней без воды
    private final int maxDaysWithoutFood = 2; // Максимум дней без еды
    private final int maxDaysInUnfavorableConditions = 3;


    private final double optimalMinTemperature = 10.0;
    private final double optimalMaxTemperature = 35.0;
    private final double optimalMinHumidity = 40.0;
    private final double optimalMaxHumidity = 85.0;

    public Animal(String name, double waterConsumption, int foodConsumption) {
        super(name);
        this.waterConsumption = waterConsumption;
        this.foodConsumption = foodConsumption;
        this.daysWithoutWater = 0; // Начальный счётчик
        this.daysWithoutFood = 0; // Начальный счётчик
    }

    public double getWaterConsumption() {
        return waterConsumption;
    }

    public double getFoodConsumption() {
        return foodConsumption;
    }

    public double getOptimalMinTemperature() {
        return optimalMinTemperature;
    }

    public double getOptimalMaxTemperature() {
        return optimalMaxTemperature;
    }

    public double getOptimalMinHumidity() {
        return optimalMinHumidity;
    }

    public double getOptimalMaxHumidity() {
        return optimalMaxHumidity;
    }

    // Метод для потребления воды
    public boolean consumeWater(double availableWater) {
        if (availableWater >= waterConsumption) {
            System.out.println(name + " consumes " + waterConsumption + " litres of water.");
            daysWithoutWater = 0; // Сброс счётчика при потреблении воды
            return true;
        } else {
            System.out.println(name + " consumes  not enough water.");
            daysWithoutWater++; // Увеличиваем счётчик
            return false;
        }
    }

    // Метод для поиска пищи
    public boolean consumeFood(double availableFood) {
        if (availableFood >= foodConsumption) {
            System.out.println(name + " consumes " + foodConsumption + " food units.");
            daysWithoutFood = 0; // Сброс счётчика при потреблении пищи
            return true;
        } else {
            System.out.println(name + " is hungry.");
            daysWithoutFood++; // Увеличиваем счётчик
            return false;
        }
    }
    public void checkConditions() {
        if (daysInUnfavorableConditions > 0) {
            System.out.println(name + " is in unfavorable conditions ");

        }
    }

    public boolean checkUnfavorableConditions(double currentTemperature, double currentHumidity) {
        if (currentTemperature < optimalMinTemperature || currentTemperature > optimalMaxTemperature ||
                currentHumidity < optimalMinHumidity || currentHumidity > optimalMaxHumidity) {
            daysInUnfavorableConditions++;
        } else {
            daysInUnfavorableConditions = 0;
        }
        return daysInUnfavorableConditions >= maxDaysInUnfavorableConditions;
    }

    // Проверка на смерть из-за отсутствия ресурсов
    public boolean isDead() {
        return daysWithoutWater >= maxDaysWithoutWater || daysWithoutFood >= maxDaysWithoutFood || daysInUnfavorableConditions >= maxDaysInUnfavorableConditions;
    }
}
