package oleg.trushanin.graz;

import android.net.Uri;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class InitViewPosition {

    private static InitViewPosition initViewPosition;

    public static InitViewPosition getInstance(){
        if(initViewPosition == null){
            initViewPosition = new InitViewPosition();
        }
            return initViewPosition;
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


    public String getAxelWeight() {
        return axelWeight;
    }

    public void setAxelWeight(String axelWeight) {
        this.axelWeight = axelWeight;
    }

    public String getTypeAxel() {
        return typeAxel;
    }

    public void setTypeAxel(String typeAxel) {
        this.typeAxel = typeAxel;
    }



    public int getCountCapsule() {
        return countCapsule;
    }

    public void setCountCapsule(int countCapsule) {
        this.countCapsule = countCapsule;
    }



    public String getTypeMaterial() {
        return typeMaterial;
    }

    public void setTypeMaterial(String typeMaterial) {
        this.typeMaterial = typeMaterial;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public String getTermalChick() {
        return termalChick;
    }

    public void setTermalChick(String termalChick) {
        this.termalChick = termalChick;
    }


    public double getVolumePPC() {
        return volumePPC;
    }

    public void setVolumePPC(double volumePPC) {
        this.volumePPC = volumePPC;
    }

    public String getModelPPC() {
        return modelPPC;
    }

    public void setModelPPC(String modelPPC) {
        this.modelPPC = modelPPC;
    }

    public float getCostPPC() {
        return costPPC;
    }

    public void setCostPPC(float costPPC) {
        this.costPPC = costPPC;
    }

    public String getT_EBS() {
        return t_EBS;
    }

    public void setT_EBS(String t_EBS) {
        this.t_EBS = t_EBS;
    }

    public String getHighSSU() {
        return highSSU;
    }

    public void setHighSSU(String highSSU) {
        this.highSSU = highSSU;
    }

    public String getTypeGorlovin() {
        return typeGorlovin;
    }

    public void setTypeGorlovin(String typeGorlovin) {
        this.typeGorlovin = typeGorlovin;
    }

    public String getTypeGorlCovers() {
        return typeGorlCovers;
    }

    public void setTypeGorlCovers(String typeGorlCovers) {
        this.typeGorlCovers = typeGorlCovers;
    }

    public String getTypeBottomValve() {
        return typeBottomValve;
    }

    public void setTypeBottomValve(String typeBottomValve) {
        this.typeBottomValve = typeBottomValve;
    }


    public String getTypeMarkTire() {
        return typeMarkTire;
    }

    public void setTypeMarkTire(String typeMarkTire) {
        this.typeMarkTire = typeMarkTire;
    }

    public String getTypeMarkAxel() {
        return typeMarkAxel;
    }

    public void setTypeMarkAxel(String typeMarkAxel) {
        this.typeMarkAxel = typeMarkAxel;
    }

    public String getTypeBreakMeh() {
        return typeBreakMeh;
    }

    public void setTypeBreakMeh(String typeBreakMeh) {
        this.typeBreakMeh = typeBreakMeh;
    }

    public String getLiftAxel() {
        return liftAxel;
    }

    public void setLiftAxel(String liftAxel) {
        this.liftAxel = liftAxel;
    }

    public String getTypeBreakSyst() {
        return typeBreakSyst;
    }

    public void setTypeBreakSyst(String typeBreakSyst) {
        this.typeBreakSyst = typeBreakSyst;
    }

    public String getTypeSystemInformPPC() {
        return typeSystemInformPPC;
    }

    public void setTypeSystemInformPPC(String typeSystemInformPPC) {
        this.typeSystemInformPPC = typeSystemInformPPC;
    }

    public String getIsNNandRec() {
        return isNNandRec;
    }

    public void setIsNNandRec(String isNNandRec) {
        this.isNNandRec = isNNandRec;
    }

    public String getTypeNNandRec() {
        return typeNNandRec;
    }

    public void setTypeNNandRec(String typeNNandRec) {
        this.typeNNandRec = typeNNandRec;
    }

    public String getTypePump() {
        return typePump;
    }

    public void setTypePump(String typePump) {
        this.typePump = typePump;
    }

    public String getTypeStorSliv() {
        return typeStorSliv;
    }

    public void setTypeStorSliv(String typeStorSliv) {
        this.typeStorSliv = typeStorSliv;
    }

    public String getTypeCombSliv() {
        return typeCombSliv;
    }

    public void setTypeCombSliv(String typeCombSliv) {
        this.typeCombSliv = typeCombSliv;
    }

    public String getTypePlaceZap() {
        return typePlaceZap;
    }

    public void setTypePlaceZap(String typePlaceZap) {
        this.typePlaceZap = typePlaceZap;
    }

    public int getCountZapKol() {
        return countZapKol;
    }

    public void setCountZapKol(int countZapKol) {
        this.countZapKol = countZapKol;
    }

    public String getTypeOkraska() {
        return typeOkraska;
    }

    public void setTypeOkraska(String typeOkraska) {
        this.typeOkraska = typeOkraska;
    }

    public String getThicknessLkpKo() {
        return thicknessLkpKo;
    }

    public void setThicknessLkpKo(String thicknessLkpKo) {
        this.thicknessLkpKo = thicknessLkpKo;
    }

    public int getColorCountKo() {
        return colorCountKo;
    }

    public void setColorCountKo(int colorCountKo) {
        this.colorCountKo = colorCountKo;
    }

    public int getLevelDifficultKo() {
        return LevelDifficultKo;
    }

    public void setLevelDifficultKo(int levelDifficultKo) {
        LevelDifficultKo = levelDifficultKo;
    }


    public float getCostKoPPC() {
        return costKoPPC;
    }

    public void setCostKoPPC(float costKoPPC) {
        this.costKoPPC = costKoPPC;
    }

    public boolean isDataChanged() {
        return isDataChanged;
    }

    public void setDataChanged(boolean dataChanged) {
        isDataChanged = dataChanged;
    }

    public float getCostMainOPtion() {
        return costMainOPtion;
    }

    public void setCostMainOPtion(float costMainOPtion) {
        this.costMainOPtion = costMainOPtion;
    }


    //Контроль изменений на первом экране
    boolean isDataChanged = false; // изменения на первом экране

    public ArrayList<DataVisualPair> getListKP() {
        return listKP;
    }

    public void setListKP(ArrayList<DataVisualPair> listKP) {
        this.listKP = listKP;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<SpecialDarkOptions> getSpecialDarkOptionsList() {
        return specialDarkOptionsList;
    }

    public void setSpecialDarkOptionsList(List<SpecialDarkOptions> specialDarkOptionsList) {
        this.specialDarkOptionsList = specialDarkOptionsList;
    }

    public List<SpecialLightOptions> getSpecialLightOptionsList() {
        return specialLightOptionsList;
    }

    public void setSpecialLightOptionsList(List<SpecialLightOptions> specialLightOptionsList) {
        this.specialLightOptionsList = specialLightOptionsList;
    }

    public Set<SpecialLightOptions> getChoosSpecialLightOptionsList() {
        return choosSpecialLightOptionsList;
    }

    public void setChoosSpecialLightOptionsList(Set<SpecialLightOptions> choosSpecialLightOptionsList) {
        this.choosSpecialLightOptionsList = choosSpecialLightOptionsList;
    }

    public Set<SpecialDarkOptions> getChoosSpecialDarkOptionsList() {
        return choosSpecialDarkOptionsList;
    }

    public void setChoosSpecialDarkOptionsList(Set<SpecialDarkOptions> choosSpecialDarkOptionsList) {
        this.choosSpecialDarkOptionsList = choosSpecialDarkOptionsList;
    }

    public float getCostSpecialOption() {
        return costSpecialOption;
    }

    public void setCostSpecialOption(float costSpecialOption) {
        this.costSpecialOption = costSpecialOption;
    }

    public Uri getUriPdfKp() {
        return uriPdfKp;
    }

    public void setUriPdfKp(Uri uriPdfKp) {
        this.uriPdfKp = uriPdfKp;
    }

    public float getPlusPricePpc() {
        return plusPricePpc;
    }

    public void setPlusPricePpc(float plusPricePpc) {
        this.plusPricePpc = plusPricePpc;
    }

    public int getPrepaidPpc() {
        return prepaidPpc;
    }

    public void setPrepaidPpc(int prepaidPpc) {
        this.prepaidPpc = prepaidPpc;
    }

    public boolean isTimePay() {
        return timePay;
    }

    public void setTimePay(boolean timePay) {
        this.timePay = timePay;
    }

    public int getTimePayDay() {
        return timePayDay;
    }

    public void setTimePayDay(int timePayDay) {
        this.timePayDay = timePayDay;
    }

    public boolean isDeliveryPpc() {
        return deliveryPpc;
    }

    public void setDeliveryPpc(boolean deliveryPpc) {
        this.deliveryPpc = deliveryPpc;
    }

    public float getDeliveryCost() {
        return deliveryCost;
    }

    public void setDeliveryCost(float deliveryCost) {
        this.deliveryCost = deliveryCost;
    }

    public String getDeliveryAdress() {
        return deliveryAdress;
    }

    public void setDeliveryAdress(String deliveryAdress) {
        this.deliveryAdress = deliveryAdress;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public int getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(int deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public int getDayValidity() {
        return dayValidity;
    }

    public void setDayValidity(int dayValidity) {
        this.dayValidity = dayValidity;
    }

    public int getNumberKP() {
        return numberKP;
    }

    public void setNumberKP(int numberKP) {
        this.numberKP = numberKP;
    }


    public String getDateKp() {
        return dateKp;
    }

    public void setDateKp(String dateKp) {
        this.dateKp = dateKp;
    }


    public int getCurrentPageKp() {
        return currentPageKp;
    }

    public void setCurrentPageKp(int currentPageKp) {
        this.currentPageKp = currentPageKp;
    }

    public int getCurrentPagePrice() {
        return currentPagePrice;
    }

    public void setCurrentPagePrice(int currentPagePrice) {
        this.currentPagePrice = currentPagePrice;
    }

    public Uri getUriPdfPrice() {
        return uriPdfPrice;
    }

    public void setUriPdfPrice(Uri uriPdfPrice) {
        this.uriPdfPrice = uriPdfPrice;
    }

    public float getZoomLevelPricePdf() {
        return zoomLevelPricePdf;
    }

    public void setZoomLevelPricePdf(float zoomLevelPricePdf) {
        this.zoomLevelPricePdf = zoomLevelPricePdf;
    }

    public float getOffsetXPricePdf() {
        return offsetXPricePdf;
    }

    public void setOffsetXPricePdf(float offsetXPricePdf) {
        this.offsetXPricePdf = offsetXPricePdf;
    }

    public float getOffsetYPricePdf() {
        return offsetYPricePdf;
    }

    public void setOffsetYPricePdf(float offsetYPricePdf) {
        this.offsetYPricePdf = offsetYPricePdf;
    }


    // заполнение в первом экране сборки
    private String termalChick = "нет"; // термоизоляция
    private double volumePPC = 0; // объем ППЦ
    private String typeAxel = ""; // тип подвески (пневм, пневм 2, комбинир)
    private String typeRatio = ""; // сечение
    private String typeAxelCount = ""; // осевая формула
    private String typeMaterial = ""; // материал цистерны
    private String type = ""; // тип ППЦБ, ППЦА, ППЦС
    private String axelWeight = "9 тонн";
    private String modelPPC = ""; // модель ППЦ по ОТТС/прайс - запрос к БД
    private float costPPC = 0; // цена базового выбранного ППЦ - запрос к БД

//**************************************************

// Заполнение на втором экране сборки
    private int countCapsule = 3; // количество отсеков по умолчанию 3

    private String highSSU = "1150-1200"; // высота ССУ по умолчанию

    private String typeGorlovin = "ГОСТ"; //

    private String typeGorlCovers = "Промприбор/Эксполюкс-НН";

    private String typeBottomValve = "Промприбор/Эксполюкс-НН";

    private String typeMarkAxel = "BPW";

    private String typeBreakMeh = "Барабанные";

    private String liftAxel = ""; // подъемные оси

    private String typeMarkTire = "Cordiant 386/65 R22";

    private String typeBreakSyst = "Wabco";

    private String t_EBS = "T-EBS"; // система ТЕБС, по умолчанию есть, если ППЦ 4 оси то можно поменять

    private String typeSystemInformPPC = "Нет";

    private String isNNandRec = "Нет";

    private String typeNNandRec = ""; // нижний налив и рекуперация принимает также значения для подготовки к ННиР

    private String typePump = "Нет"; // марка насоса

    private String typeStorSliv = "Слева"; // сторона слива

    private String typeCombSliv = ""; // комбинация сливной коммуникации

    private String typePlaceZap = "В тех. шкафу";

    private int countZapKol = 1;

    private String typeOkraska = "ТУ завода";


    //Переменные на экране заполнения карты окраски

    private String thicknessLkpKo = ""; // толщина ЛКП

    private int colorCountKo = 1; // количество цветов

    private int LevelDifficultKo = 0;// коэф. сложности

    private float costKoPPC = 0; // цена КО

    private float costMainOPtion = 0;

    private float costSpecialOption = 0;

    private ArrayList<DataVisualPair> listKP = new ArrayList<>();

    private float totalPrice = 0;


    //Специальные опции
    private List<SpecialDarkOptions> specialDarkOptionsList = new ArrayList<>();

    private List<SpecialLightOptions> specialLightOptionsList = new ArrayList<>();

    // используем для хранения выбранных опций
    private Set<SpecialLightOptions> choosSpecialLightOptionsList = new LinkedHashSet<>();

    private Set<SpecialDarkOptions> choosSpecialDarkOptionsList = new LinkedHashSet<>();

    private Uri uriPdfKp = null;

    private int currentPageKp = 0;
    ///////////////////////////Заполнение сущ. условий КП



    private float plusPricePpc = 0;

    private int prepaidPpc = 0;

    private boolean timePay = false;

    private int timePayDay = 0;

    private boolean deliveryPpc = false;

    private float deliveryCost = 0;

    private String deliveryAdress = "";

    private int deliveryTime = 0;

    private String client = "";

    private int dayValidity = 0;

    private int numberKP = 0;

    private String dateKp = "";

    //отображение страницы ПДФ прайс
    private int currentPagePrice = 0;

    private Uri uriPdfPrice = null;

    private float zoomLevelPricePdf = 1;
    private float offsetXPricePdf = 0;
    private float offsetYPricePdf = 0;




    static void resetValuesOneScreen(){
        initViewPosition.termalChick = "нет"; // термоизоляция
        initViewPosition.volumePPC = 0; // объем ППЦ
        initViewPosition.typeAxel = ""; // тип подвески (пневм, пневм 2, комбинир)
        initViewPosition.typeRatio = ""; // сечение
        initViewPosition.typeAxelCount = ""; // осевая формула
        initViewPosition.typeMaterial = ""; // материал цистерны
        initViewPosition.type = ""; // тип ППЦБ, ППЦА, ППЦС
        initViewPosition.axelWeight = "9 тонн";
        initViewPosition.modelPPC = ""; // модель ППЦ по ОТТС/прайс - запрос к БД
        initViewPosition.costPPC = 0; // цена базового выбранного ППЦ - запрос к БД
    }


    static void resetValuesTwoScreen(){

        initViewPosition.countCapsule = 3; // количество отсеков по умолчанию 3

        initViewPosition.highSSU = "1150-1200"; // высота ССУ по умолчанию

        initViewPosition.typeGorlovin = "ГОСТ"; //

        initViewPosition.typeGorlCovers = "Промприбор/Эксполюкс-НН";

        initViewPosition.typeBottomValve = "Промприбор/Эксполюкс-НН";

        initViewPosition.typeMarkAxel = "BPW";

        initViewPosition.typeBreakMeh = "Барабанные";

        initViewPosition.liftAxel = ""; // подъемные оси

        initViewPosition.typeMarkTire = "Cordiant 386/65 R22";

        initViewPosition.typeBreakSyst = "Wabco";

        initViewPosition.t_EBS = "T-EBS"; // система ТЕБС, по умолчанию есть, если ППЦ 4 оси то можно поменять

        initViewPosition.typeSystemInformPPC = "Нет";

        initViewPosition.isNNandRec = "Нет";

        initViewPosition.typeNNandRec = ""; // нижний налив и рекуперация принимает также значения для подготовки к ННиР

        initViewPosition.typePump = "Нет"; // марка насоса

        initViewPosition.typeStorSliv = "Слева"; // сторона слива

        initViewPosition.typeCombSliv = ""; // комбинация сливной коммуникации

        initViewPosition.typePlaceZap = "В тех. шкафу";

        initViewPosition.countZapKol = 1;

        initViewPosition.typeOkraska = "ТУ завода";

        initViewPosition.thicknessLkpKo = "";

        initViewPosition.colorCountKo = 1;

        initViewPosition.LevelDifficultKo = 0;

        initViewPosition.costKoPPC = 0;

        initViewPosition.costMainOPtion = 0;

        initViewPosition.costSpecialOption = 0;

        initViewPosition.listKP.clear();

        initViewPosition.clearSpecialDarkOptions();

        initViewPosition.clearSpecialLightOptions();

        initViewPosition.uriPdfKp = null;

        initViewPosition.plusPricePpc = 0;

        initViewPosition.prepaidPpc = 0;

        initViewPosition.timePay = false;

        initViewPosition.timePayDay = 0;

        initViewPosition.deliveryPpc = false;

        initViewPosition.deliveryCost = 0;

        initViewPosition.deliveryAdress = "";

        initViewPosition.deliveryTime = 0;

        initViewPosition.client = "";

        initViewPosition.dayValidity = 0;

        initViewPosition.currentPageKp = 0;

        initViewPosition.currentPagePrice = 0;

        initViewPosition.uriPdfPrice = null;

        initViewPosition.zoomLevelPricePdf = 1;

        initViewPosition.offsetXPricePdf = 0;

        initViewPosition.offsetYPricePdf = 0;


    }

    @NonNull
    @Override
    public String toString() {
        return "InitViewPosition{" +
                "termalChick='" + termalChick + '\'' +
                ", volumePPC=" + volumePPC +
                ", typeAxel='" + typeAxel + '\'' +
                ", typeRatio='" + typeRatio + '\'' +
                ", typeAxelCount='" + typeAxelCount + '\'' +
                ", typeMaterial='" + typeMaterial + '\'' +
                ", type='" + type + '\'' +
                ", axelWeight='" + axelWeight + '\'' +
                ", modelPPC='" + modelPPC + '\'' +
                ", costPPC=" + costPPC +
                ", countCapsule=" + countCapsule +
                ", highSSU='" + highSSU + '\'' +
                ", typeGorlovin='" + typeGorlovin + '\'' +
                ", typeGorlCovers='" + typeGorlCovers + '\'' +
                ", typeBottomValve='" + typeBottomValve + '\'' +
                ", typeMarkAxel='" + typeMarkAxel + '\'' +
                ", typeBreakMeh='" + typeBreakMeh + '\'' +
                ", liftAxel='" + liftAxel + '\'' +
                ", typeMarkTire='" + typeMarkTire + '\'' +
                ", typeBreakSyst='" + typeBreakSyst + '\'' +
                ", t_EBS='" + t_EBS + '\'' +
                ", typeSystemInformPPC='" + typeSystemInformPPC + '\'' +
                ", isNNandRec='" + isNNandRec + '\'' +
                ", typeNNandRec='" + typeNNandRec + '\'' +
                ", typePump='" + typePump + '\'' +
                ", typeStorSliv='" + typeStorSliv + '\'' +
                ", typeCombSliv='" + typeCombSliv + '\'' +
                ", typePlaceZap='" + typePlaceZap + '\'' +
                ", countZapKol=" + countZapKol +
                ", typeOkraska='" + typeOkraska + '\'' +
                ", thicknessLkpKo='" + thicknessLkpKo + '\'' +
                ", colorCountKo=" + colorCountKo +
                ", LevelDifficultKo=" + LevelDifficultKo +
                ", costKoPPC=" + costKoPPC +
                '}';
    }

    static void resetAllValues(){

        initViewPosition.isDataChanged = false;

        initViewPosition.termalChick = "нет"; // термоизоляция

        initViewPosition.volumePPC = 0; // объем ППЦ

        initViewPosition.typeAxel = ""; // тип подвески (пневм, пневм 2, комбинир)

        initViewPosition.typeRatio = ""; // сечение

        initViewPosition.typeAxelCount = ""; // осевая формула

        initViewPosition.typeMaterial = ""; // материал цистерны

        initViewPosition.type = ""; // тип ППЦБ, ППЦА, ППЦС

        initViewPosition.axelWeight = "9 тонн"; // грузоподъемность осей

        initViewPosition.modelPPC = ""; // модель ППЦ по ОТТС/прайс - запрос к БД

        initViewPosition.costPPC = 0; // цена базового выбранного ППЦ - запрос к БД

        initViewPosition.countCapsule = 3; // количество отсеков по умолчанию 3

        initViewPosition.highSSU = "1150-1200"; // высота ССУ по умолчанию

        initViewPosition.typeGorlovin = "ГОСТ"; //

        initViewPosition.typeGorlCovers = "Промприбор/Эксполюкс-НН";

        initViewPosition.typeBottomValve = "Промприбор/Эксполюкс-НН";

        initViewPosition.typeMarkAxel = "BPW";

        initViewPosition.typeBreakMeh = "Барабанные";

        initViewPosition.liftAxel = ""; // подъемные оси

        initViewPosition.typeMarkTire = "Cordiant 386/65 R22";

        initViewPosition.typeBreakSyst = "Wabco";

        initViewPosition.t_EBS = "T-EBS"; // система ТЕБС, по умолчанию есть, если ППЦ 4 оси то можно поменять

        initViewPosition.typeSystemInformPPC = "Нет";

        initViewPosition.isNNandRec = "Нет";

        initViewPosition.typeNNandRec = ""; // нижний налив и рекуперация принимает также значения для подготовки к ННиР

        initViewPosition.typePump = "Нет"; // марка насоса

        initViewPosition.typeStorSliv = "Слева"; // сторона слива

        initViewPosition.typeCombSliv = ""; // комбинация сливной коммуникации

        initViewPosition.typePlaceZap = "В тех. шкафу";

        initViewPosition.countZapKol = 1;

        initViewPosition.typeOkraska = "ТУ завода";

        initViewPosition.thicknessLkpKo = "";

        initViewPosition.colorCountKo = 1;

        initViewPosition.LevelDifficultKo = 0;

        initViewPosition.costKoPPC = 0;

        initViewPosition.costMainOPtion = 0;

        initViewPosition.costSpecialOption = 0;

        initViewPosition.listKP.clear();

        initViewPosition.totalPrice = 0;

        initViewPosition.clearSpecialDarkOptions();

        initViewPosition.clearSpecialLightOptions();

        initViewPosition.uriPdfKp = null;

        initViewPosition.plusPricePpc = 0;

        initViewPosition.prepaidPpc = 0;

        initViewPosition.timePay = false;

        initViewPosition.timePayDay = 0;

        initViewPosition.deliveryPpc = false;

        initViewPosition.deliveryCost = 0;

        initViewPosition.deliveryAdress = "";

        initViewPosition.deliveryTime = 0;

        initViewPosition.client = "";

        initViewPosition.dayValidity = 0;

        initViewPosition.numberKP = 0;

        initViewPosition.dateKp = "";

        initViewPosition.currentPageKp = 0;

        initViewPosition.currentPagePrice = 0;

        initViewPosition.uriPdfPrice = null;

        initViewPosition.zoomLevelPricePdf = 1;

        initViewPosition.offsetXPricePdf = 0;

        initViewPosition.offsetYPricePdf = 0;

    }



    //приводим лист опций к изначальному варианту
     public void clearSpecialDarkOptions(){

        List<SpecialDarkOptions> listDarkOptions = initViewPosition.getSpecialDarkOptionsList();

        for(SpecialDarkOptions sp: listDarkOptions){

            sp.setIs(false);

        }

         initViewPosition.choosSpecialDarkOptionsList.clear();

     }

    //приводим лист опций к изначальному варианту
    public void clearSpecialLightOptions(){

        List<SpecialLightOptions> listLightOptions = initViewPosition.getSpecialLightOptionsList();

        for(SpecialLightOptions sp: listLightOptions){

            sp.setIs(false);

        }

        initViewPosition.choosSpecialLightOptionsList.clear();


    }

    public void deleteSpecialDarkOptions(){

        initViewPosition.specialDarkOptionsList.clear();
        initViewPosition.specialLightOptionsList.clear();
        initViewPosition.choosSpecialLightOptionsList.clear();

    }

    public void addChoosSpecialLightOptionsList(SpecialLightOptions specialLightOptions){

        initViewPosition.choosSpecialLightOptionsList.add(new SpecialLightOptions(specialLightOptions));
    }

    public void addChoosSpecialDarkOptionsList(SpecialDarkOptions specialDarkOptions){

        initViewPosition.choosSpecialDarkOptionsList.add(specialDarkOptions);
    }


    public void removeChoosSpecialLightOptionsList(SpecialLightOptions specialLightOptions){

        initViewPosition.choosSpecialLightOptionsList.remove(specialLightOptions);

    }


    public void removeChoosSpecialDarkOptionsList(SpecialDarkOptions specialDarkOptions){

        initViewPosition.choosSpecialDarkOptionsList.remove(specialDarkOptions);
    }


}
