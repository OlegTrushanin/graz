package oleg.trushanin.graz;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class TableDataBaseKpViewModel extends AndroidViewModel {

    TableDataBaseKpDao tableDataBaseKpDao;

    CompositeDisposable compositeDisposable;

    public TableDataBaseKpViewModel(@NonNull Application application) {
        super(application);
        tableDataBaseKpDao = DataBaseKp.getInstance(application).tableDataBaseKpDao();
        compositeDisposable = new CompositeDisposable();
    }


    public void insertKpBd(TableDataBaseKp tableDataBaseKp){

        Disposable disposable = tableDataBaseKpDao.insertKp(tableDataBaseKp)
                .subscribeOn(Schedulers.io())
                .subscribe(new Action() {
                    @Override
                    public void run() throws Throwable {

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Throwable {
                        Log.d("tableDataBaseKpDao", throwable.toString());
                    }
                });


        compositeDisposable.add(disposable);

    }




}
