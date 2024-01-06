package oleg.trushanin.graz;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "baza_model")
public class BazaModel {


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getVolumePPC() {
        return volumePPC;
    }

    public void setVolumePPC(double volumePPC) {
        this.volumePPC = volumePPC;
    }

    public String getNameModelPPC() {
        return nameModelPPC;
    }

    public void setNameModelPPC(String nameModelPPC) {
        this.nameModelPPC = nameModelPPC;
    }

    public String getTypeAxel() {
        return typeAxel;
    }

    public void setTypeAxel(String typeAxel) {
        this.typeAxel = typeAxel;
    }

    public String getTypeRatio() {
        return typeRatio;
    }

    public void setTypeRatio(String typeRatio) {
        this.typeRatio = typeRatio;
    }

    public String getTypeAxelCount() {
        return typeAxelCount;
    }

    public void setTypeAxelCount(String typeAxelCount) {
        this.typeAxelCount = typeAxelCount;
    }

    public String getTypeMaterial() {
        return typeMaterial;
    }

    public void setTypeMaterial(String typeMaterial) {
        this.typeMaterial = typeMaterial;
    }

    public String getAxelWeight() {
        return axelWeight;
    }

    public void setAxelWeight(String axelWeight) {
        this.axelWeight = axelWeight;
    }

    public float getModelPPCCost() {
        return modelPPCCost;
    }

    public void setModelPPCCost(float modelPPCCost) {
        this.modelPPCCost = modelPPCCost;
    }

    @Override
    public String toString() {
        return "BazaModel{" +
                "id=" + id +
                ", nameModelPPC='" + nameModelPPC + '\'' +
                ", volumePPC=" + volumePPC +
                ", typeRatio='" + typeRatio + '\'' +
                ", typeAxel='" + typeAxel + '\'' +
                ", typeAxelCount='" + typeAxelCount + '\'' +
                ", typeMaterial='" + typeMaterial + '\'' +
                ", axelWeight='" + axelWeight + '\'' +
                ", modelPPCCost=" + modelPPCCost +
                '}';
    }

    public BazaModel(int id, double volumePPC, String nameModelPPC, String typeAxel, String typeRatio, String typeAxelCount, String typeMaterial, String axelWeight, float modelPPCCost) {
        this.id = id;
        this.volumePPC = volumePPC;
        this.nameModelPPC = nameModelPPC;
        this.typeAxel = typeAxel;
        this.typeRatio = typeRatio;
        this.typeAxelCount = typeAxelCount;
        this.typeMaterial = typeMaterial;
        this.axelWeight = axelWeight;
        this.modelPPCCost = modelPPCCost;
    }

    @PrimaryKey
    @SerializedName("id")
    int id;
    @SerializedName("nameModelPPC")
    String nameModelPPC;
    @SerializedName("volumePPC")
    double volumePPC;
    @SerializedName("typeRatio")
    String typeRatio;
    @SerializedName("typeAxel")
    String typeAxel;
    @SerializedName("typeAxelCount")
    String typeAxelCount;
    @SerializedName("typeMaterial")
    String typeMaterial;
    @SerializedName("axelWeight")
    String axelWeight;
    @SerializedName("modelPPCCost")
    float modelPPCCost;

}
