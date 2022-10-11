package avd.proftaak.rentmycar;

public enum UserType {
    Customer(0),
    RentalService(1);

    private int value;

    UserType(int value) {
        this.value = value;
    }
}
