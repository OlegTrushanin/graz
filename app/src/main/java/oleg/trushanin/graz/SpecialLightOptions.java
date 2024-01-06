package oleg.trushanin.graz;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

@Entity(tableName = "special_light_options")
public class SpecialLightOptions {

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


    public SpecialLightOptions(int id, int identifier, String name, float price, int mod, boolean is) {
        this.id = id;
        this.identifier = identifier;
        this.name = name;
        this.price = price;
        this.mod = mod;
        this.is = is;
    }

    //дополнительный конструктор чтобы создавать новый объект при копировании из одной коллекции в другую
    //без него изменение данных в одной коллекции влечет изменения в другой
    public SpecialLightOptions(SpecialLightOptions other) {
        this.id = other.id; // предположим, что у вас есть поле id
        this.identifier = other.identifier;
        this.name = other.name;
        this.price = other.price;
        this.mod = other.mod;
        this.is = other.is;

    }

    //создаем конструктор для кастования
    public SpecialLightOptions(SpecialDarkOptions other) {
        this.id = other.id; // предположим, что у вас есть поле id
        this.identifier = other.identifier;
        this.name = other.name;
        this.price = other.price;
        this.mod = other.mod;
        this.is = other.is;

    }




    //переопределяем equals т.к. без этого не будет работать удаление из коллекции choos
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        SpecialLightOptions that = (SpecialLightOptions) obj;
        return identifier == that.identifier;
        // Проверка равенства по уникальному идентификатору (или другому ключевому полю)
    }
    //переопределяем hashCode т.к. без этого не будет работать удаление из коллекции choos
    @Override
    public int hashCode() {
        return Objects.hash(identifier);
        // Хэш-код, основанный на уникальном идентификаторе (или другом ключевом поле)
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
