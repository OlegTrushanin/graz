package oleg.trushanin.graz;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "price_table")
public class Price {

    @Override
    public String toString() {
        return "Price{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", coast=" + coast +
                '}';
    }


    @PrimaryKey
    @SerializedName("id")
    private int id;


    @SerializedName("name")
    private String name;

    @SerializedName("cost")
    private float coast;


    public float getCoast() {
        return coast;
    }



    public Price(int id, String name, float coast) {
        this.id = id;
        this.name = name;
        this.coast = coast;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCoast(float coast) {
        this.coast = coast;
    }
}
