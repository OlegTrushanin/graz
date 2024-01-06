package oleg.trushanin.graz;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "special_dark_options")
public class SpecialDarkOptions {


    @PrimaryKey
    @SerializedName("id")
    int id;
    @SerializedName("identifier")
    int identifier;
    @SerializedName("name")
    String name;
    @SerializedName("price")
    float price;
    @SerializedName("mod")
    int mod;
    @SerializedName("is")
    boolean is;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdentifier() {
        return identifier;
    }

    public void setIdentifier(int identifier) {
        this.identifier = identifier;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getMod() {
        return mod;
    }

    public void setMod(int mod) {
        this.mod = mod;
    }

    public boolean isIs() {
        return is;
    }

    public void setIs(boolean is) {
        this.is = is;
    }


    public SpecialDarkOptions(int id, int identifier, String name, float price, int mod, boolean is) {
        this.id = id;
        this.identifier = identifier;
        this.name = name;
        this.price = price;
        this.mod = mod;
        this.is = is;
    }

    //дополнительный конструктор чтобы создавать новый объект при копировании из одной коллекции в другую
    //без него изменение данных в одной коллекции влечет изменения в другой
    public SpecialDarkOptions(SpecialDarkOptions other) {
        this.id = other.id; // предположим, что у вас есть поле id
        this.identifier = other.identifier;
        this.name = other.name;
        this.price = other.price;
        this.mod = other.mod;
        this.is = other.is;

    }

    @Override
    public String toString() {
        return "SpecialDarkOptions{" +
                "id=" + id +
                ", identifier=" + identifier +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", mod=" + mod +
                ", is=" + is +
                '}';
    }
}
