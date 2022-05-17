public enum Car {
    SEDAN (1,"Седан", 1_000),
    WAGON (2,"Универсал", 1_200),
    SUV (3,"Внедорожник", 2_000);

    int type;
    String title;
    int price;

    Car(int type, String title, int price) {
        this.type = type;
        this.title = title;
        this.price = price;
    }

    public int getType() {
        return this.type;
    }

    public String getTitle() {
        return this.title;
    }

    public int getPrice() {
        return this.price;
    }

    public static Car getCar(int type) {
        switch (type) {
            case 1:
                return SEDAN;
            case 2:
                return WAGON;
            case 3:
                return SUV;
            default:
                return null;
        }
    }
}
