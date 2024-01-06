package oleg.trushanin.graz;

import android.app.Application;
import android.content.Context;

public class App extends Application {

    private static App instance;

    public static App getInstance() {
        return instance;
    }

    public static Context getContext() {
        return instance; // Возвращает экземпляр Application, который является Context
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }
}