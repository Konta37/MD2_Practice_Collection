package categoryAndProduct.entity;

import categoryAndProduct.feature.ICategories;
import categoryAndProduct.run.ShopManagement;

import java.util.Scanner;

public class Category implements ICategories {
    private int catalogId; //>0, only one
    private String catalogName;//6-30char
    private String description;//cant emty
    private boolean catalogStatus; //true/false
    private int parentId; //?

    public Category() {
    }

    public Category(int catalogId, String catalogName, String description, boolean catalogStatus, int parentId) {
        this.catalogId = catalogId;
        this.catalogName = catalogName;
        this.description = description;
        this.catalogStatus = catalogStatus;
        this.parentId = parentId;
    }

    public int getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(int catalogId) {
        this.catalogId = catalogId;
    }

    public String getCatalogName() {
        return catalogName;
    }

    public void setCatalogName(String catalogName) {
        this.catalogName = catalogName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isCatalogStatus() {
        return catalogStatus;
    }

    public void setCatalogStatus(boolean catalogStatus) {
        this.catalogStatus = catalogStatus;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    @Override
    public void inputData(Scanner sc) {
        this.catalogId = inputCatalogId(sc);
        this.catalogName = inputCatalogName(sc);
        this.description = inputDescription(sc);
        this.catalogStatus = inputCatalogStatus(sc);
        this.parentId = inputParentId(sc);
    }

    @Override
    public void displayData() {
        System.out.printf("%-5s %-20s %-20s %-10s %-10s%n\n", this.catalogId, this.catalogName, this.description, this.catalogStatus, this.parentId);
    }

    public int inputCatalogId(Scanner sc){
        System.out.println("Enter Catalog ID: ");
        do {
            String cataId = sc.nextLine();
            try {
                if (Integer.parseInt(cataId) >= 0) {
                    boolean isExist = false;
                    for (Category categories : ShopManagement.categoriesList){
                        if (categories.getCatalogId() == Integer.parseInt(cataId)) {
                            isExist = true;
                            break;
                        }
                    }
                    if (!isExist) {
                        return Integer.parseInt(cataId);
                    }else {
                        System.err.println("Catalog ID " + cataId + " already exist");
                    }
                } else {
                    System.err.println("Invalid catalog Id value. Must enter number > 0. Please try again.");
                }
            } catch (NumberFormatException e) {
                System.err.println("Invalid catalog Id value. Try again.");
            }
        }while (true);
    }

    public String inputCatalogName(Scanner sc){
        System.out.println("Enter Catalog Name: ");
        do {
            String cataName = sc.nextLine();
            if (cataName.length() >=6 && cataName.length() <= 30) {
                return cataName;
            }else {
                System.err.println("Invalid Catalog Name value (6-30). Try again.");
            }
        }while (true);
    }

    public String inputDescription(Scanner sc){
        System.out.println("Enter Description: ");
        do {
            String desc = sc.nextLine();
            if (!desc.isEmpty()){
                return desc;
            }else {
                System.err.println("Invalid Description value. Can not empty Try again.");
            }
        }while (true);
    }

    public boolean inputCatalogStatus(Scanner sc){
        System.out.println("Enter Catalog Status: ");
        do {
            String cataStatus = sc.nextLine();
            if (cataStatus.equalsIgnoreCase("true") || cataStatus.equalsIgnoreCase("false")) {
                return Boolean.parseBoolean(cataStatus);
            } else {
                System.err.println("Invalid Catalog Status (true/false). Please try again.");
            }
        }while (true);
    }

    public int inputParentId(Scanner sc){
        System.out.println("Enter Parent ID: ");
        do {
            String parId = sc.nextLine();
            try {
                if (Integer.parseInt(parId) >= 0) {
                    int count =0;
                    //Parent ID have only 3 category -> count
                    for (Category categories : ShopManagement.categoriesList){
                        if (categories.getParentId() == Integer.parseInt(parId)) {
                            count++;
                        }
                    }
                    if (count < 3) {
                        return Integer.parseInt(parId);
                    }else {
                        System.err.println("Parent ID " + parId + " already has over 3 categories. Try again.");
                    }
                    return Byte.parseByte(parId);
                } else {
                    System.err.println("Invalid parent Id value. Must enter number > 0. Please try again.");
                }
            } catch (NumberFormatException e) {
                System.err.println("Invalid parent Id value. Try again.");
            }
        }while (true);
    }
}
