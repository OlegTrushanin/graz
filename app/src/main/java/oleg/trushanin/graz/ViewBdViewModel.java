package oleg.trushanin.graz;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ViewBdViewModel extends AndroidViewModel {

    CompositeDisposable compositeDisposable = new CompositeDisposable();

    TableDataBaseKpDao tableDataBaseKpDao;

    public LiveData<List<TableDataBaseKp>> getListKp() {
        return listKp;
    }

    LiveData<List<TableDataBaseKp>> listKp = new MutableLiveData<>();

    private MutableLiveData<Boolean> deleteEvent = new MutableLiveData<>();

    public LiveData<Boolean> getDeleteEvent() {
        return deleteEvent;
    }



    public ViewBdViewModel(@NonNull Application application) {
        super(application);
        tableDataBaseKpDao = DataBaseKp.getInstance(application).tableDataBaseKpDao();
    }


    public void loadListKp(){

        listKp = tableDataBaseKpDao.getListKp();


    }

    public void deleteKp(String uriPdf){

        Disposable disposable = tableDataBaseKpDao.deleteKp(uriPdf)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> deleteEvent.postValue(true),
                        throwable -> deleteEvent.postValue(false));

        compositeDisposable.add(disposable);

    }








}
