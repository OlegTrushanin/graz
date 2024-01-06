package oleg.trushanin.graz;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

public class SelectMainOptionsViewModel extends AndroidViewModel {

    MainOptionsDao mainOptionsDao;
    InitViewPosition initViewPosition = InitViewPosition.getInstance();

    public LiveData<List<Float>> getListPriceMainOptions() {
        return listPriceMainOptions;
    }

    public ArrayList<String> getMainOptionsPriceList() {
        return mainOptionsPriceList;
    }

    ArrayList<String> mainOptionsPriceList = new ArrayList<>();

    LiveData<List<Float>> listPriceMainOptions = new MutableLiveData<>();


    public SelectMainOptionsViewModel(@NonNull Application application) {
        super(application);
        mainOptionsDao = DataBase.getInstance(application).mainOptionsDao();
    }

    public void loadMainOptionsPrice(){

        mainOptionsPriceList.clear();

        mainOptionsPriceList.add(initViewPosition.getCountCapsule() +" " // поиск количества отсеков в зависимости от материала ППЦ
                +initViewPosition.getTypeMaterial());

        mainOptionsPriceList.add(initViewPosition.getLiftAxel() + " " // поиск подъемной оси (марка оси и грузоподъемность)
                + initViewPosition.getTypeMarkAxel() + " " + initViewPosition.getAxelWeight());

        mainOptionsPriceList.add("замена BPW на " + initViewPosition.getTypeMarkAxel() // замена BPW на SAF
                + " " + initViewPosition.getTypeAxelCount());

        mainOptionsPriceList.add(initViewPosition.getTypeMarkAxel() // поиск дискового тормоза SAF
                + " " + initViewPosition.getTypeBreakMeh());

        mainOptionsPriceList.add(initViewPosition.getTypeSystemInformPPC()); // поиск системы управления подвески

        mainOptionsPriceList.add(initViewPosition.getTypeMarkTire());// выбор резины

        if(initViewPosition.getIsNNandRec().equals("Подготовка")) {// поиск установленного нижнего налива или подготовки
            mainOptionsPriceList.add(initViewPosition.getTypeNNandRec());
        } else if (initViewPosition.getIsNNandRec().equals("Нет")) {

        }else {
            mainOptionsPriceList.add(initViewPosition.getTypeNNandRec()+ " " + initViewPosition.getCountCapsule());
        }

        mainOptionsPriceList.add(initViewPosition.getTypePump());// выбор насоса
        
        if(initViewPosition.getIsNNandRec().equals("Нет")&&initViewPosition.getTypeStorSliv().equals("На 2 стороны")){//поиск двойного слива по количеству отсеков
            mainOptionsPriceList.add(initViewPosition.getTypeCombSliv() + " " + initViewPosition.getCountCapsule());

        } else if (!initViewPosition.getIsNNandRec().equals("Подготовка")&& // если НН на одну сторону
                (initViewPosition.getTypeCombSliv().equals("Api-адаптеры слева, краны справа")
                        ||initViewPosition.getTypeCombSliv().equals("Краны слева, Api-адаптеры справа"))) {//Если есть НН и слив на 2 стороны
            mainOptionsPriceList.add(initViewPosition.getTypeCombSliv() +
                    " " + initViewPosition.getCountCapsule());


        } else if (!initViewPosition.getIsNNandRec().equals("Подготовка")&&initViewPosition.getTypeCombSliv().equals("Api-адаптеры с 2 сторон")) {//если есть НН и слив на 1-2 стороны
            mainOptionsPriceList.add(initViewPosition.getTypeCombSliv() +
                    " "+initViewPosition.getTypeNNandRec()+" " + initViewPosition.getCountCapsule());


        } else {//если только подготовка или нет НН
            mainOptionsPriceList.add(initViewPosition.getTypeCombSliv()+" " + initViewPosition.getCountCapsule());


        }

        mainOptionsPriceList.add(initViewPosition.getT_EBS() + " " + initViewPosition.getTypeAxelCount()); // поиск T-EBS с учетом осевой формулы

        mainOptionsPriceList.add(initViewPosition.getTypeBreakSyst()); // поиск тормозной системы

        mainOptionsPriceList.add("ДК " + initViewPosition.getTypeBottomValve()); // поиск донного клапана
        mainOptionsPriceList.add("КГ " + initViewPosition.getTypeGorlCovers()); // поиск крышки горловин

        mainOptionsPriceList.add(initViewPosition.getTypePlaceZap());
        mainOptionsPriceList.add("Колес "+initViewPosition.getCountZapKol()+" "+initViewPosition.getTypeMarkTire());

    }


    public LiveData<List<Float>> getPriceMainOptions(List<String> list){ // получение коллекции с ценами

        return mainOptionsDao.getPriceMainOptions(list);

        }


    public LiveData<List<MainOptions>> getPriceMainOptions1(List<String> list){ // получение коллекции с ценами

        return mainOptionsDao.getPriceMainOptions1(list);

    }




}
