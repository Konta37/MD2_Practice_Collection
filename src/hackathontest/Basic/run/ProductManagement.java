package hackathontest.Basic.run;

import hackathontest.Basic.bussinessImp.Product;
import hackathontest.Basic.color.ColorStorage;

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
        System.out.println("Finish show all products");
    }
    public static void sortProductByInterest() {
        Collections.sort(productList);
        System.out.printf("%-5s %-20s %-20s %-20s %-10s %-10s %-10s %-10s%n\n","ID","Name","Title","Description","Import","Export","Interest","Status");
        for (Product product : productList) {
            product.displayData();
        }
        System.out.println("Finish sorting products by interest");
    }
    public static void deleteProductById(Scanner sc) {
        System.out.println("Enter product id to delete: ");
        int id = Integer.parseInt(sc.nextLine());
        int indexDel = findId(id);

        if (indexDel != -1) {
            productList.remove(indexDel);
            System.out.println("Product deleted successfully");
        }else {
            System.out.println("Can't find product with id " + id);
        }
    }
    public static void searchProductByName(Scanner sc) {
        System.out.println("Enter product name to search: ");
        String name = sc.nextLine();
        int count =0;

        for (Product product : productList) {
            if (product.getProductName().contains(name)){
                System.out.printf("%-5s %-20s %-20s %-20s %-10s %-10s %-10s %-10s%n\n","ID","Name","Title","Description","Import","Export","Interest","Status");
                product.displayData();
                count++;
            }
        }
        System.out.println("There are " + count + " products with the name " + name);
        System.out.println("Finish searching product by name");
    }
    public static void changeStatus(Scanner sc) {
        System.out.println("Enter product id to change status: ");
        int id = Integer.parseInt(sc.nextLine());
        int indexUpdateStatus = findId(id);

        if (indexUpdateStatus != -1) {
            if (productList.get(indexUpdateStatus).isProductStatus()){
                productList.get(indexUpdateStatus).setProductStatus(false);
                System.out.println("Product status changed to false");
            }else {
                productList.get(indexUpdateStatus).setProductStatus(true);
                System.out.println("Product status changed to true");
            }
        }else {
            System.out.println("Can't find product with id " + id);
        }
    }

    public static int findId(int id){
        for (int i = 0; i < productList.size(); i++) {
            if (id == productList.get(i).getProductId()){
                return i;
            }
        }
        return -1;
    }
}
