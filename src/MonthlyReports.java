import java.util.ArrayList;
import java.util.HashMap;

public class MonthlyReports {
    int year;
    HashMap<Integer, ArrayList<ItemMonth>> monthsData = new HashMap<Integer, ArrayList<ItemMonth>>();

    public void setYear(int year) {
        this.year = year;
    }

    public void read() {
        FileReader fileReader = new FileReader();
        System.out.println("Считываем 12 отчетов " + year + " года.");
        for (int i = 1; i <= 12; i++) {
            String path = "resources/" + (i < 10 ? "m." + year +"0" + i + ".csv" : "m." + year + i + ".csv");
            String fileContent = fileReader.readFileContestsOrNull(path);
            if (fileContent != null) {
                String[] lines = fileContent.split("\\n");
                ArrayList<ItemMonth> items = new ArrayList<ItemMonth>();

                for (int j = 1; j < lines.length; j++) {
                    String[] lineContent = lines[j].split(",");
                    ItemMonth itemMonth = new ItemMonth(
                            lineContent[0],
                            Boolean.parseBoolean(lineContent[1]),
                            Integer.parseInt(lineContent[2]),
                            Integer.parseInt(lineContent[3])
                    );
                    items.add(itemMonth);
                }

                if (items.isEmpty())
                    continue;
                else {
                    monthsData.put(i, items);
                    System.out.println("Считан отчет №" + i);
                }
            }
        }
    }

    public void printInformation() {
        if (monthsData.isEmpty()) {
            System.out.println("Отчёты по месяцам еще не считаны!");
            return;
        }

        for (Integer monthIndex : monthsData.keySet()) {
            ArrayList<ItemMonth> month = monthsData.get(monthIndex);
            String profitableProduct = "";
            String expense = "";
            int profitSum = 0;
            int expensesSum = 0;

            System.out.println("Месяц №" + monthIndex);

            for (ItemMonth item : month) {
                if (!item.getIs_expense())
                    profitSum = Integer.max(profitSum, item.getQuantity() * item.getSum_of_one());
                else
                    expensesSum = Integer.max(expensesSum, item.getQuantity() * item.getSum_of_one());

            }
            System.out.println("Cамый прибыльный товар: " + profitableProduct + " - " + profitSum);
            System.out.println("Самая большая трата: " + expense +" - " + expensesSum);
        }
    }

}
