import model.report.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int userInput;
        MonthlyReports monthlyReports = new MonthlyReports();
        YearlyReport yearlyReport = new YearlyReport();
        Reconciler reconciler = new Reconciler(monthlyReports, yearlyReport);

        while (true) {
            printInterface();
            userInput = scanner.nextInt();
            if (userInput == 1) {
                System.out.println("Введите год YYYY: ");
                userInput = scanner.nextInt();
                monthlyReports.setYear(userInput);
                monthlyReports.read();
            }
            else if (userInput == 2) {
                System.out.println("Введите год YYYY: ");
                userInput = scanner.nextInt();
                yearlyReport.setYear(userInput);
                yearlyReport.read();
            }
            else if (userInput == 3) {
                reconciler.check();
            }
            else if (userInput == 4) {
                monthlyReports.printInformation();
            }
            else if (userInput == 5) {
                yearlyReport.printInformation();
            }
            if (userInput == 6)
                break;
        }

    }

    public static void printInterface () {
        System.out.println("1 - Считать все месячные отчёты");
        System.out.println("2 - Считать годовой отчёт");
        System.out.println("3 - Сверить отчёты");
        System.out.println("4 - Вывести информацию о всех месячных отчётах");
        System.out.println("5 - Вывести информацию о годовом отчёте");
        System.out.println("6 - Выход");
        System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - -");
    }
}