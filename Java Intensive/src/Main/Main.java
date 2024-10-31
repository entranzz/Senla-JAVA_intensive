package Main;

import Ecosystem.Ecosystem;
import Ecosystem.Animal;
import Ecosystem.Plant;
import Ecosystem.EcosystemSimulator;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Ecosystem ecosystem = new Ecosystem(25.0, 70.0, 8.0, 1000.0, 10, "log.txt");
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        EcosystemSimulator simulator = new EcosystemSimulator(ecosystem);




        while (running) {
            System.out.println("\n--- Ecosystem Simulator ---");
            System.out.println("1. Add organism");
            System.out.println("2. Simulate interactions");
            System.out.println("3. Start automatic simulation");
            System.out.println("4. Display environment status");
            System.out.println("5. Set environment status");
            System.out.println("6. Save ecosystem");
            System.out.println("7. Load ecosystem");
            System.out.println("0. Exit");

            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Очистка новой строки после ввода числа

            switch (choice) {
                case 1:
                    System.out.print("Enter organism type (plant/animal): ");
                    String type = scanner.nextLine();
                    System.out.print("Enter name: ");
                    String name = scanner.nextLine();

                    if ("plant".equalsIgnoreCase(type)) {
                        System.out.print("Enter water consumption: ");
                        double waterConsumption = scanner.nextDouble();
                        System.out.print("Enter sunlight consumption: ");
                        double sunlightConsumption = scanner.nextDouble();
                        scanner.nextLine();

                        // Добавляем растение в экосистему
                        ecosystem.addPlant(new Plant(name, waterConsumption, sunlightConsumption));
                        System.out.println("Plant " + name + " added.");
                    } else if ("animal".equalsIgnoreCase(type)) {
                        System.out.print("Enter water consumption: ");
                        double waterConsumption = scanner.nextDouble();
                        System.out.print("Enter food consumption: ");
                        int foodConsumption = scanner.nextInt();
                        scanner.nextLine();

                        // Добавляем животное в экосистему
                        ecosystem.addAnimal(new Animal(name, waterConsumption, foodConsumption));
                        System.out.println("Animal " + name + " added.");
                    } else {
                        System.out.println("Unknown organism type. Please enter 'plant' or 'animal'.");
                    }
                    break;

                case 2:
                    ecosystem.simulateStep(); // Выполняем цикл симуляции
                    simulator.runPrediction(ecosystem);
                    break;

                case 3:
                    System.out.print("Enter the number of steps for automatic simulation: ");
                    int steps = scanner.nextInt();
                    ecosystem.startAutomaticSimulation(steps); // Выполняем автоматическую симуляцию
                    break;

                case 4:
                    System.out.println("Displaying current environment status:");
                    ecosystem.displayEnvironmentStatus(); // Показываем текущее состояние
                    ecosystem.displayOrganisms(); // Показываем все организмы
                    break;


                case 5:
                    simulator.setUserDefinedEnvironment();
                    break;

                case 6:
                    System.out.print("Enter filename to save: ");
                    String saveFile = scanner.nextLine();
                    ecosystem.saveEcosystemState(saveFile+".txt");
                    System.out.println("Ecosystem state saved to " + saveFile);
                    break;

                case 7:
                    System.out.print("Enter filename to load: ");
                    String loadFile = scanner.nextLine();
                    ecosystem.loadEcosystemState(loadFile+".txt");

                    break;

                case 0:
                    running = false;
                    System.out.println("Exiting simulator...");
                    break;

                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
        scanner.close();
    }
}
