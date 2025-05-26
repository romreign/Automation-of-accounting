package model.report;

import model.item.ItemYear;
import model.util.FileReader;

import java.util.ArrayList;
import java.util.HashMap;

public class YearlyReport {
    int year;
    HashMap<Integer, Integer> monthProfit = new HashMap<>();
    HashMap<Integer, Integer> monthExpense = new HashMap<>();
    ArrayList<ItemYear> allProfitSum = new ArrayList<ItemYear>();
    ArrayList<ItemYear> allExpenseSum = new ArrayList<ItemYear>();

    public void setYear(int year) {
        this.year = year;
    }

    public void read() {
        FileReader fileReader = new FileReader();
        String path = "resources/y." + year + ".csv";
        String fileContent = fileReader.readFileContestsOrNull(path);
        String[] lines = fileContent.split("\\n");

        for (int i = 1; i < lines.length; i++ ) {
            String[] lineContent = lines[i].split(",");
            ItemYear item = new ItemYear(
                    Integer.parseInt(lineContent[0]),
                    Integer.parseInt(lineContent[1]),
                    Boolean.parseBoolean(lineContent[2])
            );

            int month = item.getMonth();
            int amount = item.getAmount();

            if (item.getIs_expense()) {
                allExpenseSum.add(item);
                monthExpense.put(month, monthExpense.getOrDefault(month, 0) + amount);
            } else {
                allProfitSum.add(item);
                monthProfit.put(month, monthProfit.getOrDefault(month, 0) + amount);
            }
        }

        if (!allProfitSum.isEmpty() || !allExpenseSum.isEmpty()) {
            System.out.println("Годовой отчёт считан!");
        }
    }

    public void printInformation() {
        if (allProfitSum.isEmpty() && allExpenseSum.isEmpty()) {
            System.out.println("Годовой отчёт еще не считан!");
            return;
        }

        System.out.println("Отчёт " + year + " года.");

        for (int i = 1; i <= 12; i++) {
            int profit = monthProfit.getOrDefault(i, 0);
            int expense = monthExpense.getOrDefault(i, 0);
            if (profit != 0 || expense != 0)
                System.out.println("Месяц №" + i + ": прибыль - " + (profit - expense));
        }

        if (!allProfitSum.isEmpty()) {
            int profitSum = sum(allProfitSum);
            System.out.println("Средний доход за все месяцы - " + profitSum / allProfitSum.size());
        }
        else
            System.out.println("Средний доход за все месяцы - 0");


        if (!allExpenseSum.isEmpty()) {
            int expensesSum = sum(allExpenseSum);
            System.out.println("Средний расход за все месяцы - " + expensesSum / allExpenseSum.size());
        }
        else
            System.out.println("Средний расход за все месяцы - 0");

    }

    private int sum(ArrayList<ItemYear> List) {
        int answer = 0;

        for (ItemYear line : List)
            answer += line.getAmount();
        return answer;
    }
}
