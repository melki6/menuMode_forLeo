package domain;

public class Plate {
    private int plateId;
    private String name;
    private String type;
    private String drink;

    public Plate(int plateId, String name, String type, String drink, int idListIngridients) {
        this.plateId = plateId;
        this.name = name;
        this.type = type;
        this.drink = drink;
        this.idListIngridients = idListIngridients;
    }

    public int getPlateId() {
        return plateId;
    }

    public void setPlateId(int plateId) {
        this.plateId = plateId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDrink() {
        return drink;
    }

    public void setDrink(String drink) {
        this.drink = drink;
    }

    public int getIdListIngridients() {
        return idListIngridients;
    }

    public void setIdListIngridients(int idListIngridients) {
        this.idListIngridients = idListIngridients;
    }

    private int idListIngridients;

}
