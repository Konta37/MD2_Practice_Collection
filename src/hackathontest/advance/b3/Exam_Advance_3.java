package hackathontest.advance.b3;

import hackathontest.Basic.color.ColorStorage;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class Exam_Advance_3 {
    public static Queue<String> customerQueue = new ArrayDeque<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //create color
        String borderColor = ColorStorage.BLUE;
        String thickBorderbottom = borderColor + "╚═════════════════════════════════════════════════════════════╝" + ColorStorage.RESET;
        String thinBorder = borderColor + "║" + ColorStorage.RESET;
        boolean isOut = true;
        do {
            String thickBorder = borderColor +   "╔══════════════════════════MENU═══════════════════════════════╗" + ColorStorage.RESET;
            System.out.println(thickBorder);
            System.out.println(thinBorder + " " + borderColor + "1. Enter Customer Name who is waiting in line to buy tickets" + thinBorder);
            System.out.println(thinBorder + " " + borderColor + "2. Next Customer                                            " + thinBorder);
            System.out.println(thinBorder + " " + borderColor + "3. Exit                                                     " + thinBorder);
            System.out.println(thickBorderbottom);
            System.out.println("Enter your choice: ");
            int choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1:
                    addCustomer(sc);
                    break;
                case 2:
                    showFirstAndDelFirst(sc);
                    break;
                case 3:
                    isOut = false;
                    break;
                default:
                    System.err.println("Invalid choice. Try again");
            }

        } while (isOut);
    }

    public static void addCustomer(Scanner sc){
        //add customer into queue
        System.out.println("Enter customer name: ");
        customerQueue.offer(sc.nextLine());

        //show full list queue
        System.out.print("List customer: ");
        for (String s : customerQueue){
            System.out.print(s + ",");
        }
        System.out.println();

    }
    public static void showFirstAndDelFirst(Scanner sc){
        if(customerQueue.isEmpty()){
            System.out.println("There is no customer in line. Wait for new customer.");
        }else {
            System.out.println("First customer is: " + customerQueue.poll() + " DONE. Next Customer please!");
        }
    }

}
