package Basic.run;

import Basic.bussinessImp.Product;
import Basic.color.ColorStorage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class ProductManagement {
    public static List<Product> productList = new ArrayList<Product>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //create color
        String borderColor = ColorStorage.BLUE;
        String thickBorderbottom = borderColor + "╚════════════════════════════════════════════════╝" + ColorStorage.RESET;
        String thinBorder = borderColor + "║" + ColorStorage.RESET;
        boolean isOut = true;
        do {
            String thickBorder = borderColor + "╔══════════════════════MENU══════════════════════╗" + ColorStorage.RESET;
            System.out.println(thickBorder);
            System.out.println(thinBorder + " " + borderColor + "1. Enter number of products and information    " + thinBorder);
            System.out.println(thinBorder + " " + borderColor + "2. Show all products                           " + thinBorder);
            System.out.println(thinBorder + " " + borderColor + "3. Sort products by interest                   " + thinBorder);
            System.out.println(thinBorder + " " + borderColor + "4. Delete product by id                        " + thinBorder);
            System.out.println(thinBorder + " " + borderColor + "5. Search product by name                      " + thinBorder);
            System.out.println(thinBorder + " " + borderColor + "6. Change status of product                    " + thinBorder);
            System.out.println(thinBorder + " " + borderColor + "7. Exit                                        " + thinBorder);
            System.out.println(thickBorderbottom);
            System.out.println("Enter your choice: ");
            int choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1:
                    addProduct(sc);
                    break;
                case 2:
                    showAllProducts();
                    break;
                case 3:
                    sortProductByInterest();
                    break;
                case 4:
                    deleteProductById(sc);
                    break;
                case 5:
                    searchProductByName(sc);
                    break;
                case 6:
                    changeStatus(sc);
                    break;
                case 7:
                    isOut = false;
            }

        } while (isOut);
    }

    public static void addProduct(Scanner sc) {
        System.out.println("Enter number of products: ");
        do{
            String num = sc.nextLine();
            try {
                if(Integer.parseInt(num)>0) {
                    for (int i = 0; i < Integer.parseInt(num); i++) {
                        System.out.println("Product " + (i+1) + " information:");
                        Product product = new Product();
                        product.inputData();
                        productList.add(product);
                    }
                    return;
                }else {
                    System.out.println("Invalid input number");
                }
            } catch (NumberFormatException e) {
                System.err.println("Invalid number value. Try again.");
            }
        }while (true);
    }
    public static void showAllProducts() {
        System.out.printf("%-5s %-20s %-20s %-20s %-10s %-10s %-10s %-10s%n\n","ID","Name","Title","Description","Import","Export","Interest","Status");
        for (Product product : productList) {
            product.displayData();
        }
    }
    public static void sortProductByInterest() {
        Collections.sort(productList);
        for (Product product : productList) {
            product.displayData();
        }
    }
    public static void deleteProductById(Scanner sc) {

    }
    public static void searchProductByName(Scanner sc) {}
    public static void changeStatus(Scanner sc) {}
}
