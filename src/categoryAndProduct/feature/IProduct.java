package categoryAndProduct.feature;

import java.util.Scanner;

public interface IProduct {
    Float MIN_INTEREST_RATE = 0.2f;
    void inputData(Scanner sc);
    void displayData();
    void calProfit();
}
