package model.item;

public class ItemYear {
    private int month;
    private int amount;
    private boolean is_expense;

    public ItemYear(int month, int amount, boolean is_expense) {
        this.month = month;
        this.amount = amount;
        this.is_expense = is_expense;
    }

    public int getMonth() {
        return month;
    }

    public int getAmount() {
        return amount;
    }

    public boolean getIs_expense() {
        return is_expense;
    }
}
