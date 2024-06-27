package hackathontest.advance.b2;

import hackathontest.Basic.color.ColorStorage;

import java.util.Scanner;
import java.util.Stack;

public class Exam_Advance_2 {
    public static Stack<String> stackURL = new Stack<>();
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
            System.out.println(thinBorder + " " + borderColor + "1. Enter URL                                   " + thinBorder);
            System.out.println(thinBorder + " " + borderColor + "2. Return                                      " + thinBorder);
            System.out.println(thinBorder + " " + borderColor + "3. Exit                                        " + thinBorder);
            System.out.println(thickBorderbottom);
            System.out.println("Enter your choice: ");
            int choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1:
                    addURL(sc);
                    break;
                case 2:
                    comeback(sc);
                    break;
                case 3:
                    isOut = false;
                    break;
                default:
                    System.err.println("Invalid choice. Try again");
            }

        } while (isOut);
    }

    public static void addURL(Scanner sc){
        System.out.println("Enter URL to add: ");
        stackURL.push(sc.nextLine());
        System.out.println("List URL: ");
        for (String s : stackURL) {
            System.out.println(s);
        }
    }
    public static void comeback(Scanner sc){
        if (stackURL.isEmpty()){
            System.out.println("List URL is empty");
        }else if (stackURL.size()<2){
            System.out.println(stackURL.pop() + " is removed");
            System.out.println("List URL is empty");
        }else {
            System.out.println(stackURL.pop() + " is removed");
            System.out.println(stackURL.get(stackURL.size()-1) +" is after");
        }

    }


}
