import java.util.Scanner;

class Material {
    private String name;
    private double quantity;
    private String unit;
    private String expirationDate;

    public Material(String name, double quantity, String unit, String expirationDate) {
        this.name = name;
        this.quantity = quantity;
        this.unit = unit;
        this.expirationDate = expirationDate;
    }

    public String getName() {
        return name;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public String getUnit() {
        return unit;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void displayInfo() {
        System.out.println("Material Name: " + name);
        System.out.println("Quantity: " + quantity + " " + unit);
        System.out.println("Expiration Date: " + expirationDate);
    }
}

class Chemical extends Material {
    private String hazardLevel;
    private String storageTemp;

    public Chemical(String name, double quantity, String unit, String expirationDate, String hazardLevel, String storageTemp) {
        super(name, quantity, unit, expirationDate);
        this.hazardLevel = hazardLevel;
        this.storageTemp = storageTemp;
    }

    public String getHazardLevel() {
        return hazardLevel;
    }

    public String getStorageTemp() {
        return storageTemp;
    }

    public void setHazardLevel(String hazardLevel) {
        this.hazardLevel = hazardLevel;
    }

    public void setStorageTemp(String storageTemp) {
        this.storageTemp = storageTemp;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Hazard Level: " + hazardLevel);
        System.out.println("Storage Temperature: " + storageTemp);
    }
}

class MaterialInventory {
    private Material[] materials;
    private int count;

    public MaterialInventory(int capacity) {
        materials = new Material[capacity];
        count = 0;
    }

    public void addMaterial(Material material) {
        if (count < materials.length) {
            materials[count++] = material;
            System.out.println(material.getName() + " added to inventory.");
        } else {
            System.out.println("Inventory is full! Cannot add more materials.");
        }
    }

    public void updateMaterialQuantity(String name, double quantity) {
        for (int i = 0; i < count; i++) {
            if (materials[i].getName().equalsIgnoreCase(name)) {
                materials[i].setQuantity(materials[i].getQuantity() + quantity);
                System.out.println("Updated quantity of " + name + " to " + materials[i].getQuantity() + " " + materials[i].getUnit());
                return;
            }
        }
        System.out.println("Material " + name + " not found in inventory.");
    }

    public void updateChemical(String name, String hazardLevel, String storageTemp) {
        for (int i = 0; i < count; i++) {
            if (materials[i] instanceof Chemical && materials[i].getName().equalsIgnoreCase(name)) {
                Chemical chemical = (Chemical) materials[i];
                if (hazardLevel != null) {
                    chemical.setHazardLevel(hazardLevel); // Use setter method to update hazard level
                }
                if (storageTemp != null) {
                    chemical.setStorageTemp(storageTemp); // Use setter method to update storage temperature
                }
                System.out.println("Updated properties of chemical: " + name);
                return;
            }
        }
        System.out.println("Chemical " + name + " not found in inventory.");
    }
    
    public void checkMaterial(String name) {
        for (int i = 0; i < count; i++) {
            if (materials[i].getName().equalsIgnoreCase(name)) {
                System.out.println(name + " is in stock:");
                materials[i].displayInfo();
                return;
            }
        }
        System.out.println(name + " is not in stock.");
    }

    public void displayInventory() {
        if (count == 0) {
            System.out.println("Inventory is empty.");
        } else {
            System.out.println("Inventory:");
            for (int i = 0; i < count; i++) {
                materials[i].displayInfo();
                System.out.println("--------------------");
            }
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MaterialInventory inventory = new MaterialInventory(100);

        while (true) {
            System.out.println("\n1. Add Material");
            System.out.println("2. Add Chemical");
            System.out.println("3. Update Material Quantity");
            System.out.println("4. Check Material");
            System.out.println("5. Update Chemical Properties");
            System.out.println("6. Display Inventory");
            System.out.println("7. Exit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline

            switch (choice) {
                case 1:
                    System.out.print("Enter material name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter quantity: ");
                    double quantity = scanner.nextDouble();
                    scanner.nextLine();
                    System.out.print("Enter unit: ");
                    String unit = scanner.nextLine();
                    System.out.print("Enter expiration date (YYYY-MM-DD): ");
                    String expirationDate = scanner.nextLine();

                    Material material = new Material(name, quantity, unit, expirationDate);
                    inventory.addMaterial(material);
                    break;

                case 2:
                    System.out.print("Enter chemical name: ");
                    String chemicalName = scanner.nextLine();
                    System.out.print("Enter quantity: ");
                    double chemicalQuantity = scanner.nextDouble();
                    scanner.nextLine();
                    System.out.print("Enter unit: ");
                    String chemicalUnit = scanner.nextLine();
                    System.out.print("Enter expiration date (YYYY-MM-DD): ");
                    String chemicalExpiration = scanner.nextLine();
                    System.out.print("Enter hazard level: ");
                    String hazardLevel = scanner.nextLine();
                    System.out.print("Enter storage temperature: ");
                    String storageTemp = scanner.nextLine();

                    Chemical chemical = new Chemical(chemicalName, chemicalQuantity, chemicalUnit, chemicalExpiration, hazardLevel, storageTemp);
                    inventory.addMaterial(chemical);
                    break;

                case 3:
                    System.out.print("Enter material name to update: ");
                    String updateName = scanner.nextLine();
                    System.out.print("Enter quantity to add/remove: ");
                    double updateQuantity = scanner.nextDouble();
                    scanner.nextLine();
                    inventory.updateMaterialQuantity(updateName, updateQuantity);
                    break;

                case 4:
                    System.out.print("Enter material or chemical name to check: ");
                    String nameToCheck = scanner.nextLine();
                    inventory.checkMaterial(nameToCheck);
                    break;

                case 5:
                    System.out.print("Enter chemical name to update: ");
                    String chemicalNameToUpdate = scanner.nextLine();
                    System.out.print("Enter new hazard level (or press Enter to keep unchanged): ");
                    String newHazardLevel = scanner.nextLine();
                    if (newHazardLevel.isEmpty()) newHazardLevel = null;
                    System.out.print("Enter new storage temperature (or press Enter to keep unchanged): ");
                    String newStorageTemp = scanner.nextLine();
                    if (newStorageTemp.isEmpty()) newStorageTemp = null;
                    inventory.updateChemical(chemicalNameToUpdate, newHazardLevel, newStorageTemp);
                    break;

                case 6:
                    inventory.displayInventory();
                    break;

                case 7:
                    System.out.println("Exiting...");
                    return;

                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 7.");
                    scanner.close();
                    
            }
        }
    }
}


