package oleg.trushanin.graz;

import android.app.Application;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.io.InputStream;
import java.io.OutputStream;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;
import okhttp3.ResponseBody;

public class LoadPriceViewModel extends AndroidViewModel {

    ContentResolver resolver;
    SharedPreferences sharedPreferences;
    CompositeDisposable compositeDisposable = new CompositeDisposable();

    public LiveData<Boolean> getLoadBool() {
        return loadBool;
    }

    MutableLiveData<Boolean> loadBool = new MutableLiveData<>(); // используем для уведомления о скачивании файла

    public LiveData<Boolean> getLoadBoolError() {
        return loadBoolError;
    }

    MutableLiveData<Boolean> loadBoolError = new MutableLiveData<>(); // используем для уведомления о скачивании файла

    public LiveData<Boolean> getLoadBoolToast() {
        return loadBoolToast;
    }

    MutableLiveData<Boolean> loadBoolToast = new MutableLiveData<>(); // используем для уведомления о скачивании файла



    public LoadPriceViewModel(@NonNull Application application) {
        super(application);
        resolver = application.getContentResolver();
        sharedPreferences = application.getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
    }


    //загружаем и сохраняем прайс на телефон
    public void loadPrice(String url){

        Disposable disposable = ApiFactoryLoadPrice.apiServiceLoadPrice
                .downloadPricePdf(url)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Throwable {
                        loadBool.setValue(true);
                    }
                })
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody responseBody) throws Throwable {
                        savePrice(responseBody);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Throwable {
                        loadBoolError.setValue(true);
                        loadBool.setValue(false);
                        loadBoolToast.setValue(false);
                    }
                });


        compositeDisposable.add(disposable);



    }

    //сохраняем прайс на телефон создаем ссылку на него
    private void savePrice(ResponseBody responseBody) {

        ContentValues values = new ContentValues();
        values.put(MediaStore.MediaColumns.DISPLAY_NAME, "priceGraz.pdf");
        values.put(MediaStore.MediaColumns.MIME_TYPE, "application/pdf");
        values.put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_DOWNLOADS);

        Uri existingFileUri = findExistingFileUri("priceGraz.pdf");


        if (existingFileUri != null) { // Файл уже существует, используем этот Uri для перезаписи

            writeFileToUri(existingFileUri, responseBody);

        } else { // Файл не найден, вставляем новый

            Uri newFileUri = resolver.insert(MediaStore.Files.getContentUri("external"), values);

            if (newFileUri != null) {
                writeFileToUri(newFileUri, responseBody);
            }
        }
    }

    // непосредственно записываем сам файл
    private void writeFileToUri(Uri existingFileUri, ResponseBody responseBody) {
        SharedPreferences.Editor myEdit = sharedPreferences.edit();
        try (OutputStream outputStream = resolver.openOutputStream(existingFileUri);
             InputStream inputStream = responseBody.byteStream()) {

            if (outputStream != null) {
                byte[] buf = new byte[1024];
                int len;
                while ((len = inputStream.read(buf)) > 0) {
                    outputStream.write(buf, 0, len);
                }
                myEdit.putString("uriPrice", existingFileUri.toString());
                myEdit.putBoolean("urlIs", true); // используем для отображения кнопки просмотра прайса
                myEdit.apply();
                loadBool.setValue(false);
                loadBoolToast.setValue(true);

            }
        } catch (Exception e) {

        }


    }

    private Uri findExistingFileUri(String fileName) {

        Uri collection = MediaStore.Files.getContentUri("external");
        String[] projection = new String[]{MediaStore.Files.FileColumns._ID};
        String selection = MediaStore.Files.FileColumns.DISPLAY_NAME + "=?";
        String[] selectionArgs = new String[]{fileName};

        Uri fileUri = null;
        try (Cursor cursor = resolver.query(collection, projection, selection, selectionArgs, null)) {
            if (cursor != null && cursor.moveToFirst()) {
                int idColumn = cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns._ID);
                long id = cursor.getLong(idColumn);
                fileUri = Uri.withAppendedPath(MediaStore.Files.getContentUri("external"), Long.toString(id));
            }
        } catch (Exception e) {

        }
        return fileUri;

    }


    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.dispose();
    }
}
