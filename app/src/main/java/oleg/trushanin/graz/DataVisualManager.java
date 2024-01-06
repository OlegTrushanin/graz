package oleg.trushanin.graz;

import java.util.ArrayList;

public class DataVisualManager {



    InitViewPosition initViewPosition = InitViewPosition.getInstance();

    private static DataVisualManager dataVisualManager;
    private DataVisualManager() { }
    public static DataVisualManager getInstance(){

        if(dataVisualManager == null){
            dataVisualManager = new DataVisualManager();
        }
        return dataVisualManager;

    }

    public ArrayList<DataVisualPair> getVisualPairArrayList() {
        return visualPairArrayList;
    }

    public void resetVisualPairArrayList(){

        visualPairArrayList.clear();
    }

    private ArrayList<DataVisualPair> visualPairArrayList = new ArrayList<>();



    public void loadVisualPair(){
        int number = 0;
        visualPairArrayList.clear();
        visualPairArrayList.add(new DataVisualPair(++number,"Марка, модель","ГРАЗ, "
                + initViewPosition.getModelPPC()));
        visualPairArrayList.add(new DataVisualPair(++number,"Форма цистерны",
                initViewPosition.getTypeRatio()));
        visualPairArrayList.add(new DataVisualPair(++number,"Объем цистерны",
                String.valueOf(initViewPosition.getVolumePPC())));
        visualPairArrayList.add(new DataVisualPair(++number,"Максимальная плотность груза, т/м3",
                "0.86"));
        visualPairArrayList.add(new DataVisualPair(++number,"Перевозимый продукт",
                "Светлые нефтепродукты"));
        visualPairArrayList.add(new DataVisualPair(++number,"Количество отсеков",
                String.valueOf(initViewPosition.getCountCapsule())));
        visualPairArrayList.add(new DataVisualPair(++number,"Материал цистерны",
                initViewPosition.getTypeMaterial()));
        visualPairArrayList.add(new DataVisualPair(++number,"Высота ССУ",
                initViewPosition.getHighSSU()));
        visualPairArrayList.add(new DataVisualPair(++number,"Дыхательный клапан",
                "УД-2 80"));
        visualPairArrayList.add(new DataVisualPair(++number,"Донный клапан",
                initViewPosition.getTypeBottomValve()));
        visualPairArrayList.add(new DataVisualPair(++number,"Насос",
                initViewPosition.getTypePump()));
        visualPairArrayList.add(new DataVisualPair(++number,"Рукава",
                "2x4 м, композитные"));
        visualPairArrayList.add(new DataVisualPair(++number,"БРС",
                "Kamlock/Elaflex"));
        visualPairArrayList.add(new DataVisualPair(++number,"Горловины",
                initViewPosition.getTypeGorlovin()));
        visualPairArrayList.add(new DataVisualPair(++number,"Крышки горловин",
                initViewPosition.getTypeGorlCovers()));

        if(initViewPosition.getTypeStorSliv().equals("На 2 стороны")) {
            visualPairArrayList.add(new DataVisualPair(++number, "Сторона слива",
                    initViewPosition.getTypeStorSliv() + '\n' + initViewPosition.getTypeCombSliv()));
        }else {
            if (initViewPosition.getIsNNandRec().equals("Нет")||initViewPosition.getIsNNandRec().equals("Подготовка")) {
                visualPairArrayList.add(new DataVisualPair(++number, "Сторона слива",
                        initViewPosition.getTypeStorSliv() + " шаровые краны"));
            }else{
                visualPairArrayList.add(new DataVisualPair(++number, "Сторона слива",
                        initViewPosition.getTypeStorSliv() + " Api-адаптеры"));
            }
        }

        visualPairArrayList.add(new DataVisualPair(++number,"Тип подвески",
                initViewPosition.getTypeAxel()));
        visualPairArrayList.add(new DataVisualPair(++number,"Оси",
                initViewPosition.getTypeMarkAxel() + " "
                        +(initViewPosition.getAxelWeight().equals("9 тонн")? "шоссейные": initViewPosition.getAxelWeight()) + '\n'
                        + initViewPosition.getTypeAxelCount()));
        visualPairArrayList.add(new DataVisualPair(++number,"Тормоза",
                initViewPosition.getTypeBreakMeh()));
        visualPairArrayList.add(new DataVisualPair(++number,"Подъемные оси",
                initViewPosition.getLiftAxel()));
        visualPairArrayList.add(new DataVisualPair(++number,"Шины",
                initViewPosition.getTypeMarkTire()));
        visualPairArrayList.add(new DataVisualPair(++number,"Противооткатные упоры",
                "2"));
        visualPairArrayList.add(new DataVisualPair(++number,"Запасное колесо, шт",
                String.valueOf(initViewPosition.getCountZapKol() == 0 ? "Нет" :initViewPosition.getCountZapKol() )));
        visualPairArrayList.add(new DataVisualPair(++number,"Место под зап. колесо",
                initViewPosition.getTypePlaceZap()));
        visualPairArrayList.add(new DataVisualPair(++number,"Заземляющий проводник",
                "Барабан заземления, 30 м"));
        visualPairArrayList.add(new DataVisualPair(++number,"Расположение лестницы",
                "Сзади"));
        visualPairArrayList.add(new DataVisualPair(++number,"Тормозная система",
                initViewPosition.getTypeBreakSyst()));
        visualPairArrayList.add(new DataVisualPair(++number,"Система T-EBS",
                initViewPosition.getT_EBS()));
        visualPairArrayList.add(new DataVisualPair(++number,"Система управления подвеской",
                initViewPosition.getTypeSystemInformPPC()));

        if(initViewPosition.getIsNNandRec().equals("Нет")){
            visualPairArrayList.add(new DataVisualPair(++number,"Нижний налив и рекуперация",
                    "Нет"));
        } else if (initViewPosition.getIsNNandRec().equals("Подготовка")) {
            visualPairArrayList.add(new DataVisualPair(++number,"Нижний налив и рекуперация",
                    "Нет"));
            visualPairArrayList.add(new DataVisualPair(++number,"Подготовка под нижний налив",
                    initViewPosition.getTypeNNandRec()));
        }else {
            visualPairArrayList.add(new DataVisualPair(++number,"Нижний налив и рекуперация",
                    initViewPosition.getTypeNNandRec()));
        }

        if(initViewPosition.getVolumePPC() == 45) {
            visualPairArrayList.add(new DataVisualPair(++number, "Свидетельство о поверке",
                    "Нет"));
        }else {
            visualPairArrayList.add(new DataVisualPair(++number, "Свидетельство о поверке",
                    "Да"));
        }

        if(initViewPosition.getTypeOkraska().equals("ТУ завода")) {
            visualPairArrayList.add(new DataVisualPair(++number, "Окраска емкости",
                    "ТУ завода"));
        }else if(initViewPosition.getColorCountKo()>1) {
            visualPairArrayList.add(new DataVisualPair(++number, "Окраска емкости",
                    "Карта окраски, " + initViewPosition.getThicknessLkpKo()+ '\n' + "количество цветов " +
                            initViewPosition.getColorCountKo()));
        }else{ // если выбрана карта окраски но количество цветов 1, то будет ТУ Завода
            visualPairArrayList.add(new DataVisualPair(++number, "Окраска емкости",
                    "ТУ завода" + " " + initViewPosition.getThicknessLkpKo()));
        }
    }

    public void loadVisualPairPpcb(){
        visualPairArrayList.clear();
        visualPairArrayList.add(new DataVisualPair(1,"Марка, модель","ГРАЗ, "
                + initViewPosition.getModelPPC()));
        visualPairArrayList.add(new DataVisualPair(2,"Форма цистерны",
                initViewPosition.getTypeRatio()));
        visualPairArrayList.add(new DataVisualPair(3,"Объем цистерны",
                String.valueOf(initViewPosition.getVolumePPC())));
        visualPairArrayList.add(new DataVisualPair(5,"Максимальная плотность груза, т/м3",
                "1"));
        visualPairArrayList.add(new DataVisualPair(6,"Перевозимый продукт",
                "Темные нефтепродукты"));
        visualPairArrayList.add(new DataVisualPair(7,"Количество отсеков",
                String.valueOf(1)));
        visualPairArrayList.add(new DataVisualPair(8,"Материал цистерны",
                initViewPosition.getTypeMaterial()));
        visualPairArrayList.add(new DataVisualPair(9,"Высота ССУ",
                initViewPosition.getHighSSU()));
        visualPairArrayList.add(new DataVisualPair(10,"Дыхательный клапан",
                "УД-2 80"));
        visualPairArrayList.add(new DataVisualPair(11,"Донный клапан",
                initViewPosition.getTypeBottomValve()));
        visualPairArrayList.add(new DataVisualPair(12,"Насос",
                initViewPosition.getTypePump()));
        visualPairArrayList.add(new DataVisualPair(13,"Рукава",
                "1x4 м, композитный"));
        visualPairArrayList.add(new DataVisualPair(14,"БРС",
                "Kamlock/Elaflex"));
        visualPairArrayList.add(new DataVisualPair(15,"Расположение слива",
                "Сзади"));
        visualPairArrayList.add(new DataVisualPair(16,"Тип подвески",
                initViewPosition.getTypeAxel()));
        visualPairArrayList.add(new DataVisualPair(17,"Оси",
                initViewPosition.getTypeMarkAxel() + " "
                        +(initViewPosition.getAxelWeight().equals("9 тонн")? "шоссейные": initViewPosition.getAxelWeight()) + '\n'
                        + initViewPosition.getTypeAxelCount()));
        visualPairArrayList.add(new DataVisualPair(18,"Тормоза",
                initViewPosition.getTypeBreakMeh()));
        visualPairArrayList.add(new DataVisualPair(19,"Подъемные оси",
                initViewPosition.getLiftAxel()));
        visualPairArrayList.add(new DataVisualPair(20,"Шины",
                initViewPosition.getTypeMarkTire()));
        visualPairArrayList.add(new DataVisualPair(21,"Противооткатные упоры",
                "2"));
        visualPairArrayList.add(new DataVisualPair(22,"Запасное колесо, шт",
                String.valueOf(initViewPosition.getCountZapKol() == 0 ? "Нет" :initViewPosition.getCountZapKol() )));
        visualPairArrayList.add(new DataVisualPair(23,"Место под зап. колесо",
                "В корзине между опорными лапами и подкатной тележкой"));
        visualPairArrayList.add(new DataVisualPair(24,"Тормозная система",
                initViewPosition.getTypeBreakSyst()));
        visualPairArrayList.add(new DataVisualPair(25,"Система T-EBS",
                initViewPosition.getT_EBS()));
        visualPairArrayList.add(new DataVisualPair(26,"Система управления подвеской",
                initViewPosition.getTypeSystemInformPPC()));
        visualPairArrayList.add(new DataVisualPair(27, "Свидетельство о поверке",
                "Да"));
        visualPairArrayList.add(new DataVisualPair(28, "Окраска емкости",
                "ТУ завода"));

    }


}
