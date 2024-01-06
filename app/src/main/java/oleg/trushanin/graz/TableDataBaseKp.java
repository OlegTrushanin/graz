package oleg.trushanin.graz;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@Entity(tableName = "kp_table")
public class TableDataBaseKp implements Serializable {


    public String getModelPPC() {
        return modelPPC;
    }

    public void setModelPPC(String modelPPC) {
        this.modelPPC = modelPPC;
    }

    public String getTypeMaterial() {
        return typeMaterial;
    }

    public void setTypeMaterial(String typeMaterial) {
        this.typeMaterial = typeMaterial;
    }

    public double getVolumePPC() {
        return volumePPC;
    }

    public void setVolumePPC(double volumePPC) {
        this.volumePPC = volumePPC;
    }

    public int getCountCapsule() {
        return countCapsule;
    }

    public void setCountCapsule(int countCapsule) {
        this.countCapsule = countCapsule;
    }

    public String getTypeAxelCount() {
        return typeAxelCount;
    }

    public void setTypeAxelCount(String typeAxelCount) {
        this.typeAxelCount = typeAxelCount;
    }

    public String getTypeMarkAxel() {
        return typeMarkAxel;
    }

    public void setTypeMarkAxel(String typeMarkAxel) {
        this.typeMarkAxel = typeMarkAxel;
    }

    public String getDateKp() {
        return dateKp;
    }

    public void setDateKp(String dateKp) {
        this.dateKp = dateKp;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public float getPlusPricePpc() {
        return plusPricePpc;
    }

    public void setPlusPricePpc(float plusPricePpc) {
        this.plusPricePpc = plusPricePpc;
    }

    public String getUriPdf() {
        return uriPdf;
    }

    public void setUriPdf(String uriPdf) {
        this.uriPdf = uriPdf;
    }

    public String getTypeAxel() {
        return typeAxel;
    }

    public void setTypeAxel(String typeAxel) {
        this.typeAxel = typeAxel;
    }

    public String getNumberPrice() {
        return numberPrice;
    }

    public void setNumberPrice(String numberPrice) {
        this.numberPrice = numberPrice;
    }

    public float getDeliveryCost() {
        return deliveryCost;
    }

    public void setDeliveryCost(float deliveryCost) {
        this.deliveryCost = deliveryCost;
    }

    public float getTotalPriceKp() {
        return totalPriceKp;
    }

    public void setTotalPriceKp(float totalPriceKp) {
        this.totalPriceKp = totalPriceKp;
    }

    public TableDataBaseKp(String modelPPC, String typeMaterial, double volumePPC, int countCapsule,
                           String typeAxelCount, String typeMarkAxel, String typeAxel, String dateKp,
                           String client, float totalPrice, float plusPricePpc, String uriPdf,
                           String numberPrice, float deliveryCost, float totalPriceKp) {
        this.modelPPC = modelPPC;
        this.typeMaterial = typeMaterial;
        this.volumePPC = volumePPC;
        this.countCapsule = countCapsule;
        this.typeAxelCount = typeAxelCount;
        this.typeMarkAxel = typeMarkAxel;
        this.typeAxel = typeAxel;
        this.dateKp = dateKp;
        this.client = client;
        this.totalPrice = totalPrice;
        this.plusPricePpc = plusPricePpc;
        this.uriPdf = uriPdf;
        this.numberPrice = numberPrice;
        this.deliveryCost = deliveryCost;
        this.totalPriceKp = totalPriceKp;
    }

    @Override
    public String toString() {
        return "TableDataBaseKp{" +
                "id=" + id +
                ", modelPPC='" + modelPPC + '\'' +
                ", typeMaterial='" + typeMaterial + '\'' +
                ", volumePPC=" + volumePPC +
                ", countCapsule=" + countCapsule +
                ", typeAxelCount='" + typeAxelCount + '\'' +
                ", typeMarkAxel='" + typeMarkAxel + '\'' +
                ", typeAxel='" + typeAxel + '\'' +
                ", dateKp='" + dateKp + '\'' +
                ", client='" + client + '\'' +
                ", totalPrice=" + totalPrice +
                ", plusPricePpc=" + plusPricePpc +
                ", uriPdf='" + uriPdf + '\'' +
                '}';
    }

    @PrimaryKey(autoGenerate = true)
    @SerializedName("id")
    int id;
    @SerializedName("modelPPC")
    String modelPPC = "";
    @SerializedName("typeMaterial")
    String typeMaterial = "";
    @SerializedName("volumePPC")
    double volumePPC = 0;
    @SerializedName("countCapsule")
    int countCapsule = 3;
    @SerializedName("typeAxelCount")
    String typeAxelCount = ""; // осевая формула
    @SerializedName("typeMarkAxel")
    String typeMarkAxel = "BPW";
    @SerializedName("typeAxel")
    String typeAxel = "";
    @SerializedName("dateKp")
    String dateKp = "";
    @SerializedName("client")
    String client = "";
    @SerializedName("totalPrice")
    float totalPrice = 0;
    @SerializedName("plusPricePpc")
    float plusPricePpc = 0;
    @SerializedName("uriPdf")
    String uriPdf = "";
    @SerializedName("numberPrice")
    String numberPrice = "";
    @SerializedName("deliveryCost")
    float deliveryCost = 0;
    @SerializedName("totalPriceKp")
    float totalPriceKp = 0;









}
