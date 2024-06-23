package categoryAndProduct.entity;

import categoryAndProduct.feature.IProduct;
import categoryAndProduct.run.ShopManagement;
import classAndStudent.entity.Student;
import classAndStudent.feature.impl.StudentFeatureImpl;

import java.util.Scanner;

public class Product implements IProduct {
    private String productId; //4char: Cxxx
    private String productName; //6-50char, only one
    private String title; //6-30char
    private float importPrice;
    private float exportPrice; //= > min_interest_rate
    private float profit; //ex -im
    private String description;
    private boolean productStatus;
    private Category categories;

    public Product() {
    }

    public Product(String productId, String productName, String title, float importPrice, float exportPrice, float profit, String description, boolean productStatus, Category categories) {
        this.productId = productId;
        this.productName = productName;
        this.title = title;
        this.importPrice = importPrice;
        this.exportPrice = exportPrice;
        this.profit = profit;
        this.description = description;
        this.productStatus = productStatus;
        this.categories = categories;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public float getImportPrice() {
        return importPrice;
    }

    public void setImportPrice(float importPrice) {
        this.importPrice = importPrice;
    }

    public float getExportPrice() {
        return exportPrice;
    }

    public void setExportPrice(float exportPrice) {
        this.exportPrice = exportPrice;
    }

    public float getProfit() {
        return profit;
    }

    public void setProfit(float profit) {
        this.profit = profit;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isProductStatus() {
        return productStatus;
    }

    public void setProductStatus(boolean productStatus) {
        this.productStatus = productStatus;
    }

    public Category getCategories() {
        return categories;
    }

    public void setCategories(Category categories) {
        this.categories = categories;
    }

    @Override
    public void inputData(Scanner sc) {
        this.productId = inputProductId(sc);
        this.productName = inputProductName(sc);
        this.title = inputTitle(sc);
        this.importPrice = inputImportPrice(sc);
        this.exportPrice = inputExportPrice(sc);
        calProfit();
        this.description = inputDescription(sc);
        this.productStatus = inputProductStatus(sc);
        this.categories = inputCategories(sc);
    }

    @Override
    public void displayData() {
        System.out.printf("%-4s %-20s %-20s %-15s %-15s %-15s %-20s %-10s%n\n",
                          this.productId, this.productName, this.title, this.importPrice, this.exportPrice, this.profit, this.description, this.categories.getCatalogName());
    }

    @Override
    public void calProfit() {
         this.profit = this.exportPrice-this.importPrice;
    }

    public String inputProductId(Scanner sc) {
        System.out.println("Enter product ID: ");
        do {
            String productId = sc.nextLine();
            String regex = "C\\w{3}";
            if (productId.matches(regex)) {
                boolean isExits = false;
                for (Product product : ShopManagement.productsList) {
                    if (product.getProductId().equals(productId)) {
                        isExits = true;
                        break;
                    }
                }
                if (!isExits) {
                    return productId;
                } else {
                    System.err.println("Product ID (Cxxx) has been duplicate. Please try again.");
                }
            } else {
                System.err.println("Invalid product ID (Cxxx)");
            }
        } while (true);
    }

    public String inputProductName(Scanner sc) {
        System.out.println("Enter product name: ");
        do {
            String productName = sc.nextLine();
            if (productName.length() >= 6 && productName.length() <= 50) {
                for (Product product : ShopManagement.productsList) {
                    if (product.getProductName().equals(productName)) {
                        return productName;
                    } else {
                        System.err.println("Product name already exist. Please try again.");
                    }
                }
            } else {
                System.err.println("Invalid product name. Please try again.");
            }
        } while (true);
    }

    public String inputTitle(Scanner sc) {
        System.out.println("Enter product title: ");
        do {
            String productTitle = sc.nextLine();
            if (productTitle.length() >= 6 && productTitle.length() <= 50) {
                return productTitle;
            } else {
                System.err.println("Invalid product title. Please try again.");
            }
        } while (true);
    }

    public float inputImportPrice(Scanner sc) {
        System.out.println("Enter product import price: ");
        do {
            String imPrice = sc.nextLine();
            try {
                if (Float.parseFloat(imPrice) >= 0) {
                    return Float.parseFloat(imPrice);
                } else {
                    System.err.println("Invalid import price value. Must enter >0. Please try again.");
                }
            } catch (NumberFormatException e) {
                System.err.println("Invalid import price value. Try again.");
            }
        } while (true);
    }

    public float inputExportPrice(Scanner sc) {
        System.out.println("Enter export price:");
        float check = this.getImportPrice() + this.getImportPrice() * MIN_INTEREST_RATE;
        do {
            String exPrice = sc.nextLine();
            try {
                if (Float.parseFloat(exPrice) >= check) {
                    return Float.parseFloat(exPrice);
                } else {
                    System.err.println("Invalid export price value. Must enter > min rate. Please try again.");
                }
            } catch (NumberFormatException e) {
                System.err.println("Invalid export price value. Try again.");
            }
        } while (true);
    }

    public String inputDescription(Scanner sc) {
        System.out.println("Enter product description: ");
        do {
            String productDescription = sc.nextLine();
            if (!productDescription.isEmpty()) {
                return productDescription;
            } else {
                System.err.println("Invalid product description. Can not empty. Please try again.");
            }
        } while (true);
    }

    public boolean inputProductStatus(Scanner sc) {
        System.out.println("Enter product status: ");
        do {
            String productStatus = sc.nextLine();
            if (productStatus.equalsIgnoreCase("true") || productStatus.equalsIgnoreCase("false")) {
                return Boolean.parseBoolean(productStatus);
            } else {
                System.err.println("Invalid Product Status (true/false). Please try again.");
            }
        } while (true);
    }

    public Category inputCategories(Scanner sc) {
        System.out.println("Enter product category Id: ");
        do {
            String cataId = sc.nextLine();
            try {
                if (Integer.parseInt(cataId) >= 0) {
                    int cateIndex = -1;
                    for (int i = 0; i < ShopManagement.categoriesList.size(); i++) {
                        if (ShopManagement.categoriesList.get(i).getCatalogId() == Integer.parseInt(cataId)) {
                            cateIndex = i;
                            break;
                        }
                    }
                    if (cateIndex >= 0) {
                        return ShopManagement.categoriesList.get(cateIndex);
                    } else {
                        System.err.println("Invalid product category Id. Please try again.");
                    }
                } else {
                    System.err.println("Invalid catalog Id value. Must enter number > 0. Please try again.");
                }
            } catch (NumberFormatException e) {
                System.err.println("Invalid catalog Id value. Try again.");
            }
        } while (true);
    }
}
