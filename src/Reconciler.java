import java.util.ArrayList;

public class Reconciler {
    MonthlyReports monthlyReports;
    YearlyReport yearlyReport;

    public Reconciler (MonthlyReports monthlyReports, YearlyReport yearlyReport) {
        this.monthlyReports = monthlyReports;
        this.yearlyReport = yearlyReport;
    }

    public void check() {
        if (monthlyReports.monthsData.isEmpty()) {
            System.out.println("Месячные отчёты еще не считан");
            return;
        }
        if (yearlyReport.allProfitSum.isEmpty() && yearlyReport.allExpenseSum.isEmpty()) {
            System.out.println("Годовой отчёт еще не считан!");
            return;
        }
        boolean hasErrors = false;

        for (Integer month : monthlyReports.monthsData.keySet()) {
            ArrayList<ItemMonth> monthlyItems = monthlyReports.monthsData.get(month);
            int monthlyExpense = 0;
            int monthlyProfit = 0;


            for(ItemMonth item : monthlyItems){
                if(item.getIs_expense())
                    monthlyExpense += (item.getQuantity() * item.getSum_of_one());
                else
                    monthlyProfit += (item.getQuantity() * item.getSum_of_one());
            }

            int yearlyExpense = 0;
            int yearlyProfit = 0;

            for (ItemYear item : yearlyReport.allExpenseSum) {
                if (item.getMonth() == month) {
                    yearlyExpense = item.getAmount();
                    break;
                }
            }

            for (ItemYear item : yearlyReport.allProfitSum) {
                if (item.getMonth() == month) {
                    yearlyProfit = item.getAmount();
                    break;
                }
            }

            if (monthlyExpense != yearlyExpense || monthlyProfit != yearlyProfit) {
                System.out.println("В " + month + " месяце обнаружено несоответствие:");
                System.out.println("Месячный отчёт - доход: " + monthlyProfit + ", расход: " + monthlyExpense);
                System.out.println("Годовой отчёт - доход: " + yearlyProfit + ", расход: " + yearlyExpense);
                hasErrors = true;
            }

        }
        if (!hasErrors)
            System.out.println("Сверка отчётов завершена успешно. Расхождений не обнаружено.");

    }
}
