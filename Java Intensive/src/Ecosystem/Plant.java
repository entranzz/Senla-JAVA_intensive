package Ecosystem;

public class Plant extends Organism {
    private double waterConsumption;
    private double sunlightConsumption;
    private int daysWithoutWater; // Счётчик дней без воды
    private int daysInUnfavorableConditions;
    private final int maxDaysWithoutWater = 3; // Максимум дней без воды
    private final int maxDaysInUnfavorableConditions = 3; // Лимит на дни в неблагоприятных условиях

    private final double optimalMinTemperature = 15.0;
    private final double optimalMaxTemperature = 30.0;
    private final double optimalMinHumidity = 50.0;
    private final double optimalMaxHumidity = 80.0;

    public Plant(String name, double waterConsumption, double sunlightConsumption) {
        super(name);
        this.waterConsumption = waterConsumption;
        this.sunlightConsumption = sunlightConsumption;
        this.daysWithoutWater = 0; // Начальный счётчик
    }

    public double getWaterConsumption() {
        return waterConsumption;
    }

    public double getSunlightConsumption() {
        return sunlightConsumption;
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
            System.out.println(name + " consumes not enough water.");
            daysWithoutWater++; // Увеличиваем счётчик
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
            daysInUnfavorableConditions = 0; // Сброс счётчика при нормальных условиях
        }
        return daysInUnfavorableConditions >= maxDaysInUnfavorableConditions;
    }
    // Проверка на смерть из-за отсутствия воды
    public boolean isDead() {
        return daysWithoutWater >= maxDaysWithoutWater || daysInUnfavorableConditions >= maxDaysInUnfavorableConditions;

    }

    // Метод для потребления солнечного света
    public void consumeSunlight(double sunlightAvailable) {
        System.out.println(name + " gets " + sunlightConsumption + " units of sunlight.");
    }
}
