package oleg.trushanin.graz;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

public class SelectTypePPCViewModel extends AndroidViewModel {

    public SelectTypePPCViewModel(@NonNull Application application) {
        super(application);
        bazaModelDao = DataBase.getInstance(application).bazaModelDao();
    }

    public LiveData<List<BazaModel>> getListCostNamePPC() {
        return listCostNamePPC;
    }

    private LiveData<List<BazaModel>> listCostNamePPC = new MutableLiveData<>();

    BazaModelDao bazaModelDao;



    public void loadCostNamePPC(double volumePPC, String typeRatio,
                                String typeAxel, String typeAxelCount,
                                String typeMaterial, String axelWeight){

        listCostNamePPC = bazaModelDao.getNameCostPPC(volumePPC,typeRatio,typeAxel,
                typeAxelCount,typeMaterial,axelWeight);
    }





}
