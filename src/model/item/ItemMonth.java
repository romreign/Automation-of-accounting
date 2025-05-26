package model.item;

public class ItemMonth {
    private String item_name;
    private boolean is_expense;
    private int quantity;
    private int sum_of_one;

    public ItemMonth(String item_name, boolean is_expense, int quantity, int sum_of_one) {
        this.item_name = item_name;
        this.is_expense = is_expense;
        this.quantity = quantity;
        this.sum_of_one = sum_of_one;
    }

    public String getItem_name() {
        return item_name;
    }

    public boolean getIs_expense() {
        return is_expense;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getSum_of_one() {
        return sum_of_one;
    }
}
