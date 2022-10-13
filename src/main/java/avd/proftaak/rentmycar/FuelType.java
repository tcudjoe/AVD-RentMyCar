package avd.proftaak.rentmycar;

public enum FuelType {
    Gasoline(0),
    Hybrid(1);

    private int value;

    FuelType(int value) {
        this.value = value;
    }
}
