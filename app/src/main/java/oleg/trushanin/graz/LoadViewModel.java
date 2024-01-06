package oleg.trushanin.graz;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class LoadViewModel extends AndroidViewModel {



    private SharedPreferences sharedPreferences;

    public LiveData<List<Price>> getListPrice() {
        return listPrice;
    }
    public MutableLiveData<List<BazaModel>> getListBazaModel() {
        return listBazaModel;
    }
    public MutableLiveData<List<MainOptions>> getListMainOptions() {
        return listMainOptions;
    }
    public MutableLiveData<List<SpecialDarkOptions>> getListSpecialDarkOptions() {
        return listSpecialDarkOptions;
    }
    public MutableLiveData<List<SpecialLightOptions>> getListSpecialLightOptions() {
        return listSpecialLightOptions;
    }
    public LiveData<List<SpecialLightOptions>> getListSpecialLightCashe() {
        return listSpecialLightCashe;
    }

    public LiveData<List<SpecialDarkOptions>> getListSpecialDarkCashe() {
        return listSpecialDarkCashe;
    }
    public MutableLiveData<List<Ko>> getListKo() {
        return listKo;
    }

    MutableLiveData<List<Price>> listPrice = new MutableLiveData<>();
    MutableLiveData<List<BazaModel>> listBazaModel = new MutableLiveData<>();
    MutableLiveData<List<MainOptions>> listMainOptions = new MutableLiveData<>();
    MutableLiveData<List<SpecialDarkOptions>> listSpecialDarkOptions = new MutableLiveData<>();
    MutableLiveData<List<SpecialLightOptions>> listSpecialLightOptions = new MutableLiveData<>();
    MutableLiveData<List<Ko>> listKo = new MutableLiveData<>();
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    LiveData<List<SpecialLightOptions>> listSpecialLightCashe = new MutableLiveData<>();

    LiveData<List<SpecialDarkOptions>> listSpecialDarkCashe = new MutableLiveData<>();



    PriceDao priceDao;
    BazaModelDao bazaModelDao;
    MainOptionsDao mainOptionsDao;
    SpecialDarkOptionsDao specialDarkOptionsDao;
    SpecialLightOptionsDao specialLightOptionsDao;
    KoDao koDao;





    public LoadViewModel(@NonNull Application application) {
        super(application);
        sharedPreferences = application.getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
//        try {//инициализируем инкрипт шаред преференс
//
//
//            MasterKey masterKey = new MasterKey.Builder(application)
//                    .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
//                    .build();
//
//            sharedPreferences = EncryptedSharedPreferences.create(
//                    application,
//                    "encrypted_shared_prefs",
//                    masterKey,
//                    EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
//                    EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
//            );
//        } catch (GeneralSecurityException | IOException e) {
//            throw new RuntimeException("Could not create MasterKey", e);
//        }

        priceDao = DataBase.getInstance(application).priceDao();
        bazaModelDao = DataBase.getInstance(application).bazaModelDao();
        mainOptionsDao = DataBase.getInstance(application).mainOptionsDao();
        specialDarkOptionsDao = DataBase.getInstance(application).specialDarkOptionsDao();
        specialLightOptionsDao = DataBase.getInstance(application).specialLightOptionsDao();
        koDao = DataBase.getInstance(application).koDao();
    }



    public void loadPrice() {

        String token = sharedPreferences.getString("token", "default_value"); // получаем токен из инкрипт шаред

        Disposable disposable = ApiFactory.apiService.loadPriceJSON(token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Price>>() {
                    @Override
                    public void accept(List<Price> priceList) throws Throwable {
                        if (priceList != null) {
                            listPrice.setValue(priceList);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Throwable {

                    }
                });
        compositeDisposable.add(disposable);
    }


        public void insertPrice(List<Price> price){
        Disposable disposable = priceDao.insertAll(price)
                .subscribeOn(Schedulers.io())
                .subscribe(new Action() {
                    @Override
                    public void run() throws Throwable {

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Throwable {

                    }
                });

        compositeDisposable.add(disposable);


    }


    public void deletePrice(){

        Disposable disposable = priceDao.deleteAll()
                .subscribeOn(Schedulers.io())
                .subscribe(new Action() {
                    @Override
                    public void run() throws Throwable {

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Throwable {

                    }
                });

        compositeDisposable.add(disposable);


    }

    public void loadBazaModel() { // загружаем базу модели из удаленной базы
        String token = sharedPreferences.getString("token", "default_value"); // получаем токен из инкрипт шаред
        Disposable disposable = ApiFactory.apiService.loadBazaModelJSON(token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<BazaModel>>() {
                    @Override
                    public void accept(List<BazaModel> bazaModels) throws Throwable {
                    if(bazaModels != null){
                        listBazaModel.setValue(bazaModels);

                    }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Throwable {

                    }
                });
        compositeDisposable.add(disposable);
    }

    public void insertBazaModel(List<BazaModel>bazaModels){ // записываем базу модели в память устройства

        Disposable disposable = bazaModelDao.deleteAll()
                .subscribeOn(Schedulers.io())
                .andThen(bazaModelDao.insertAll(bazaModels))
                .subscribe(new Action() {
                    @Override
                    public void run() {
                        // Успешное выполнение
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) {

                    }
                });

        compositeDisposable.add(disposable);

        }




    public void loadSpecialDarkOptions(){
        String token = sharedPreferences.getString("token", "default_value"); // получаем токен из инкрипт шаред
        Disposable disposable = ApiFactory.apiService.loadSpecialDarkJSON(token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<SpecialDarkOptions>>() {
                    @Override
                    public void accept(List<SpecialDarkOptions> specialDarkOptions) throws Throwable {
                        listSpecialDarkOptions.setValue(specialDarkOptions);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Throwable {

                    }
                });


        compositeDisposable.add(disposable);
    }

    public void insertSpecialDarkOptions(List<SpecialDarkOptions> specialDarkOptions){

        Disposable disposable = specialDarkOptionsDao.deleteAll()
                .subscribeOn(Schedulers.io())
                .andThen(specialDarkOptionsDao.insertAll(specialDarkOptions))
                .subscribe(new Action() {
                    @Override
                    public void run() throws Throwable {

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Throwable {

                    }
                });

        compositeDisposable.add(disposable);

    }

    public void loadSpecialLightOptions(){
        String token = sharedPreferences.getString("token","default_value");
        Disposable disposable = ApiFactory.apiService.loadSpecialLightJSON(token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<SpecialLightOptions>>() {
                    @Override
                    public void accept(List<SpecialLightOptions> specialLightOptions) throws Throwable {

                        listSpecialLightOptions.setValue(specialLightOptions);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Throwable {

                    }
                });
        compositeDisposable.add(disposable);

    }

    public void insertSpecialLightOptions(List<SpecialLightOptions> specialLightOptions){

        Disposable disposable = specialLightOptionsDao.deleteAll()
                .subscribeOn(Schedulers.io())
                .andThen(specialLightOptionsDao.insertAll(specialLightOptions))
                .subscribe(new Action() {
                    @Override
                    public void run() throws Throwable {

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Throwable {

                    }
                });

        compositeDisposable.add(disposable);


    }

    public void loadKo(){

        String token = sharedPreferences.getString("token","default_value");
        Disposable disposable = ApiFactory.apiService.loadKoJSON(token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Ko>>() {
                    @Override
                    public void accept(List<Ko> kos) throws Throwable {
                    listKo.setValue(kos);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Throwable {

                    }
                });

        compositeDisposable.add(disposable);
    }

    public void insertKo(List<Ko> ko){

        Disposable disposable = koDao.deleteAll()
                .subscribeOn(Schedulers.io())
                .andThen(koDao.insertAll(ko))
                .subscribe(new Action() {
                    @Override
                    public void run() throws Throwable {

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Throwable {

                    }
                });

        compositeDisposable.add(disposable);


    }


    public void loadMainOptions(){
        String token = sharedPreferences.getString("token", "default_value"); // получаем токен из инкрипт шаред
        Disposable disposable = ApiFactory.apiService.loadMainOptionsJSON(token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<MainOptions>>() {
                    @Override
                    public void accept(List<MainOptions> mainOptions) throws Throwable {
                        if(mainOptions != null) {
                            listMainOptions.setValue(mainOptions);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Throwable {

                    }
                });
        compositeDisposable.add(disposable);
    }

    public void insertMainOptions(List<MainOptions> mainOptions){
        Disposable disposable = mainOptionsDao.deleteAll()
                .subscribeOn(Schedulers.io())
                .andThen(mainOptionsDao.insertAll(mainOptions))
                .subscribe(new Action() {
                    @Override
                    public void run() {
                        // Успешное выполнение
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) {


                    }
                });

        compositeDisposable.add(disposable);


    }

    public void loadSpecialLightCashe(){

        listSpecialLightCashe = specialLightOptionsDao.getSpecialLight();

    }

    public void loadSpecialDarkCashe(){
        listSpecialDarkCashe = specialDarkOptionsDao.getSpecialDark();

    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.dispose();
    }
}
