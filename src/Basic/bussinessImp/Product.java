package Basic.bussinessImp;

import Basic.bussiness.IProduct;

import java.util.Scanner;

public class Product implements IProduct,Comparable<Product> {

    private int productId;
    private String productName;
    private String title;
    private String description;
    private float importPrice;
    private float exportPrice;
    private float interest; //loi nhuan
    private Boolean productStatus;

    public Product() {
    }

    public Product(int productId, String productName, String title, String description, float importPrice, float exportPrice, float interest, Boolean productStatus) {
        this.productId = productId;
        this.productName = productName;
        this.title = title;
        this.description = description;
        this.importPrice = importPrice;
        this.exportPrice = exportPrice;
        this.interest = interest;
        this.productStatus = productStatus;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public float getInterest() {
        return interest;
    }

    public void setInterest(float interest) {
        this.interest = interest;
    }

    public Boolean isProductStatus() {
        return productStatus;
    }

    public void setProductStatus(Boolean productStatus) {
        this.productStatus = productStatus;
    }

    @Override
    public void inputData() {
        Scanner sc = new Scanner(System.in);
        this.productId = inputProductId(sc);
        this.productName = inputProductName(sc);
        this.title = inputTitle(sc);
        this.description = inputDescription(sc);
        this.importPrice = inputImportPrice(sc);
        this.exportPrice = inputExportPrice(sc);
        this.interest = inputInterest();
        this.productStatus = inputProductStatus(sc);
    }

    @Override
    public void displayData() {
        System.out.printf("%-5s %-20s %-20s %-20s %-10s %-10s %-10s %-10s%n\n",
                this.productId, this.productName, this.title, this.description, this.importPrice, this.exportPrice, this.interest, this.productStatus);
    }

    public int inputProductId(Scanner sc) {
        System.out.println("Enter product id (number): ");
        do {
            String productId = sc.nextLine();
            try {
                if (Integer.parseInt(productId) >= 0) {
                    return Integer.parseInt(productId);
                } else {
                    System.err.println("Invalid product Id value. Must enter number > 0. Please try again.");
                }
            } catch (NumberFormatException e) {
                System.err.println("Invalid product Id value. Try again.");
            }
        }while (true);
    }

    public String inputProductName(Scanner sc) {
        System.out.println("Enter product name: ");
        do {
            String productName = sc.nextLine();
            if (!productName.isEmpty()){
                return productName;
            }else {
                System.err.println("Invalid product name value. Can not empty. Try again.");
            }
        }while (true);
    }

    public String inputTitle(Scanner sc) {
        System.out.println("Enter product title: ");
        do {
            String productTitle = sc.nextLine();
            if (!productTitle.isEmpty()){
                return productTitle;
            }else {
                System.err.println("Invalid product title value. Can not empty. Try again.");
            }
        }while (true);
    }

    public String inputDescription(Scanner sc) {
        System.out.println("Enter product description: ");
        do {
            String productDescription = sc.nextLine();
            if (!productDescription.isEmpty()){
                return productDescription;
            }else {
                System.err.println("Invalid product description value. Can not empty. Try again.");
            }
        }while (true);
    }

    public float inputImportPrice(Scanner sc) {
        System.out.println("Enter product import price: ");
        do {
            String productImportPrice = sc.nextLine();
            try {
                if (Float.parseFloat(productImportPrice)>=0){
                    return Float.parseFloat(productImportPrice);
                }else {
                    System.err.println("Invalid product import price value. Try again.");
                }
            }catch (NumberFormatException e){
                System.err.println("Invalid product import price value. Try again.");
            }
        }while (true);
    }

    public float inputExportPrice(Scanner sc) {
        System.out.println("Enter product export price: ");
        do {
            String productExportPrice = sc.nextLine();
            try {
                if (Float.parseFloat(productExportPrice)>=this.importPrice){
                    return Float.parseFloat(productExportPrice);
                }else {
                    System.err.println("Invalid product export price value. Try again.");
                }
            }catch (NumberFormatException e){
                System.err.println("Invalid product export price value. Try again.");
            }
        }while (true);
    }

    public float inputInterest() {
        return this.exportPrice-this.importPrice;
    }

    public boolean inputProductStatus(Scanner sc) {
        System.out.println("Enter product status: ");
        do {
            String productStatus = sc.nextLine();
            if(productStatus.equalsIgnoreCase("true") || productStatus.equalsIgnoreCase("false")){
                return Boolean.parseBoolean(productStatus);
            }else {
                System.err.println("Invalid product status value (true/false). Try again.");
            }
        }while (true);
    }

    @Override
    public int compareTo(Product o) {
        return Float.compare(this.interest, o.interest);
    }
}
