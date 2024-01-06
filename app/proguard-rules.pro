# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile
# Please add these rules to your existing keep rules in order to suppress warnings.
# This is generated automatically by the Android Gradle plugin.
# Retrofit и OkHttp:

# Пример для GSON:

-keep class oleg.trushanin.graz.model.** { *; }

# Для OkHttp:

-dontwarn okhttp3.**
-dontwarn io.reactivex.**

# Room Database:

# Исключите сущности, DAO и базы данных:

-keep class * extends androidx.room.RoomDatabase { *; }
-keep @androidx.room.Entity class *
-keep @androidx.room.Dao class *

# Glide:

-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public enum com.bumptech.glide.load.ImageHeaderParser$** { **[] $VALUES; }

# Firebase:

-keep class com.google.firebase.** { *; }

# Itext PDF:

-keep class com.itextpdf.** { *; }
-dontwarn com.itextpdf.**

# Android Security Crypto:

-keep class androidx.security.crypto.** { *; }

# Android PDF Viewer:
-keep class com.github.barteksc.pdfviewer.** { *; }


# LoadViewModel

-keep class io.reactivex.rxjava3.** { *; }
-keep class io.reactivex.rxjava3.disposables.Disposable { *; }
-keep class io.reactivex.rxjava3.schedulers.Schedulers { *; }
-keep class io.reactivex.rxjava3.android.schedulers.AndroidSchedulers { *; }

-keep class oleg.trushanin.graz.SpecificaitAdapter { *; }
-keep class oleg.trushanin.graz.AdapterSpecialOptionsDetail { *; }

-keep class oleg.trushanin.graz.InitViewPosition { *; }


# AdapterSpecialOptions

-keep interface oleg.trushanin.graz.AdapterSpecialOptions$OnClickItemListener { *; }
-keep class oleg.trushanin.graz.SpecialLightOptions { *; }

# ApiService

-keep interface oleg.trushanin.graz.ApiService { *; }
-keep class oleg.trushanin.graz.Price { *; }
-keep class oleg.trushanin.graz.BazaModel { *; }
-keep class oleg.trushanin.graz.MainOptions { *; }
-keep class io.reactivex.rxjava3.** { *; }
-keep class io.reactivex.rxjava3.disposables.Disposable { *; }

# ApiFactory

-keep class oleg.trushanin.graz.ApiFactory { *; }

# AdapterViewBd

-keep class oleg.trushanin.graz.AdapterViewBd$ElementBdViewHolder { *; }

# BazaModel

-keep @androidx.room.Entity class oleg.trushanin.graz.BazaModel { *; }
-keepattributes *Annotation*

# BazaModelDao

-keep @androidx.room.Dao class oleg.trushanin.graz.BazaModelDao { *; }
-keepclassmembers class oleg.trushanin.graz.BazaModelDao { *; }


# ViewBdViewModel

-keep class oleg.trushanin.graz.ViewBdViewModel { *; }
-keep class io.reactivex.rxjava3.** { *; }
-keep class io.reactivex.rxjava3.disposables.Disposable { *; }
-keep class io.reactivex.rxjava3.schedulers.Schedulers { *; }
-keep class io.reactivex.rxjava3.android.schedulers.AndroidSchedulers { *; }

# AdapterSpecialOptions

-keep class oleg.trushanin.graz.AdapterSpecialOptions { *; }
-keep interface oleg.trushanin.graz.AdapterSpecialOptions{ *; }

# AdapterSpecialOptionsDetail

-keep class oleg.trushanin.graz.AdapterSpecialOptionsDetail$SpecialOptionsDetailVieHolder { *; }

# AdapterViewBd

-keep class oleg.trushanin.graz.AdapterViewBd$ElementBdViewHolder { *; }

# ApiFactory

-keep class oleg.trushanin.graz.ApiFactory { *; }

# ApiFactoryLoadPrice

-keep class oleg.trushanin.graz.ApiFactoryLoadPrice { *; }
-keep class retrofit2.** { *; }
-keepclasseswithmembers class * {
    @retrofit2.http.* <methods>;
}
-keep class io.reactivex.rxjava3.** { *; }
-keep interface oleg.trushanin.graz.ApiServiceLoadPrice { *; }

# ApiService

-keep interface oleg.trushanin.graz.ApiService { *; }
-keep class retrofit2.** { *; }
-keepclasseswithmembers class * {
    @retrofit2.http.* <methods>;
}
-keep class io.reactivex.rxjava3.** { *; }
-keep class oleg.trushanin.graz.Price { *; }
-keep class oleg.trushanin.graz.BazaModel { *; }
-keep class oleg.trushanin.graz.MainOptions { *; }
-keep class oleg.trushanin.graz.Ko { *; }
-keep class oleg.trushanin.graz.SpecialDarkOptions { *; }
-keep class oleg.trushanin.graz.SpecialLightOptions { *; }

# ApiServiceLoadPrice

-keep interface oleg.trushanin.graz.ApiServiceLoadPrice { *; }
-keep class retrofit2.** { *; }
-keepclasseswithmembers class * {
    @retrofit2.http.* <methods>;
}
-keep class io.reactivex.rxjava3.** { *; }
-keep class okhttp3.** { *; }
-keep class okio.** { *; }
-keep class okhttp3.ResponseBody { *; }

# BazaModel

-keep @androidx.room.Entity class oleg.trushanin.graz.BazaModel { *; }
-keep class oleg.trushanin.graz.BazaModel { *; }
-keepattributes *Annotation*
-keepclassmembers class oleg.trushanin.graz.BazaModel { *; }

# BazaModelDao

-keep @androidx.room.Dao class oleg.trushanin.graz.BazaModelDao { *; }
-keepclassmembers class oleg.trushanin.graz.BazaModelDao { *; }
-keep class io.reactivex.rxjava3.** { *; }
-keep class androidx.lifecycle.LiveData { *; }


# DataBase

-keep @androidx.room.Database class oleg.trushanin.graz.DataBase { *; }
-keep class oleg.trushanin.graz.PriceDao { *; }
-keep class oleg.trushanin.graz.BazaModelDao { *; }
-keep class oleg.trushanin.graz.MainOptionsDao { *; }
-keep class oleg.trushanin.graz.SpecialDarkOptionsDao { *; }
-keep class oleg.trushanin.graz.SpecialLightOptionsDao { *; }
-keep class oleg.trushanin.graz.KoDao { *; }
-keep class oleg.trushanin.graz.Price { *; }
-keep class oleg.trushanin.graz.BazaModel { *; }
-keep class oleg.trushanin.graz.MainOptions { *; }
-keep class oleg.trushanin.graz.SpecialDarkOptions { *; }
-keep class oleg.trushanin.graz.SpecialLightOptions { *; }
-keep class oleg.trushanin.graz.Ko { *; }

# DataBaseKp

-keep @androidx.room.Database class oleg.trushanin.graz.DataBaseKp { *; }
-keep class oleg.trushanin.graz.TableDataBaseKpDao { *; }
-keep class oleg.trushanin.graz.TableDataBaseKp { *; }

# DataVisualManager

-keep class oleg.trushanin.graz.DataVisualManager { *; }
-keepclassmembers class oleg.trushanin.graz.DataVisualManager { *; }
-keep class oleg.trushanin.graz.InitViewPosition { *; }
-keep class oleg.trushanin.graz.DataVisualPair { *; }

# DataVisualPair

-keep class oleg.trushanin.graz.DataVisualPair { *; }
-keepclassmembers class oleg.trushanin.graz.DataVisualPair { *; }

# InitViewPosition

-keep class oleg.trushanin.graz.InitViewPosition { *; }
-keepclassmembers class oleg.trushanin.graz.InitViewPosition { *; }
-keep class oleg.trushanin.graz.SpecialDarkOptions { *; }
-keep class oleg.trushanin.graz.SpecialLightOptions { *; }

# Ko

-keep @androidx.room.Entity class oleg.trushanin.graz.Ko { *; }
-keep class oleg.trushanin.graz.Ko { *; }
-keepattributes *Annotation*
-keepclassmembers class oleg.trushanin.graz.Ko { *; }

# KoDao

-keep @androidx.room.Dao class oleg.trushanin.graz.KoDao { *; }
-keepclassmembers class oleg.trushanin.graz.KoDao { *; }
-keep class io.reactivex.rxjava3.** { *; }
-keep class androidx.lifecycle.LiveData { *; }

# LoadPriceViewModel

-keep class oleg.trushanin.graz.LoadPriceViewModel { *; }
-keep class androidx.lifecycle.LiveData { *; }
-keep class androidx.lifecycle.MutableLiveData { *; }
-keep class io.reactivex.rxjava3.** { *; }
-keep class io.reactivex.rxjava3.disposables.CompositeDisposable { *; }
-keep class android.content.ContentResolver { *; }
-keep class android.content.SharedPreferences { *; }
-keepclassmembers class oleg.trushanin.graz.LoadPriceViewModel { *; }

# LoadViewModel

-keep class oleg.trushanin.graz.LoadViewModel { *; }
-keep class androidx.lifecycle.LiveData { *; }
-keep class androidx.lifecycle.MutableLiveData { *; }
-keep class oleg.trushanin.graz.PriceDao { *; }
-keep class oleg.trushanin.graz.BazaModelDao { *; }
-keep class oleg.trushanin.graz.MainOptionsDao { *; }
-keep class oleg.trushanin.graz.SpecialDarkOptionsDao { *; }
-keep class oleg.trushanin.graz.SpecialLightOptionsDao { *; }
-keep class oleg.trushanin.graz.KoDao { *; }
-keep class io.reactivex.rxjava3.** { *; }
-keep class io.reactivex.rxjava3.disposables.CompositeDisposable { *; }
-keep class android.content.SharedPreferences { *; }
-keepclassmembers class oleg.trushanin.graz.LoadViewModel { *; }

# LoginActivity

-keep class oleg.trushanin.graz.LoginActivity { *; }
-keep class com.google.firebase.** { *; }
-keep class com.google.firebase.database.DatabaseReference { *; }
-keep class androidx.lifecycle.ViewModel { *; }
-keep class androidx.lifecycle.LiveData { *; }
-keep class androidx.lifecycle.MutableLiveData { *; }
-keep class androidx.lifecycle.ViewModelProvider { *; }
-keep class android.content.SharedPreferences { *; }
-keepclassmembers class oleg.trushanin.graz.LoginActivity { *; }

# LoginViewModel

-keep class oleg.trushanin.graz.LoginViewModel { *; }
-keep class com.google.firebase.auth.** { *; }
-keep class androidx.lifecycle.LiveData { *; }
-keep class androidx.lifecycle.MutableLiveData { *; }
-keep class com.google.firebase.auth.FirebaseUser { *; }
-keepclassmembers class oleg.trushanin.graz.LoginViewModel { *; }

# MainActivity

-keep class oleg.trushanin.graz.MainActivity { *; }
-keep class oleg.trushanin.graz.LoadViewModel { *; }
-keep class oleg.trushanin.graz.LoginViewModel { *; }
-keep class com.google.firebase.** { *; }
-keep class com.google.firebase.database.DatabaseReference { *; }
-keep class com.google.firebase.auth.FirebaseAuth { *; }
-keep class android.content.SharedPreferences { *; }
-keepclassmembers class oleg.trushanin.graz.MainActivity { *; }
-keep class androidx.lifecycle.LiveData { *; }
-keep class androidx.lifecycle.Observer { *; }

# MainOptions

-keep @androidx.room.Entity class oleg.trushanin.graz.MainOptions { *; }
-keep class oleg.trushanin.graz.MainOptions { *; }
-keepattributes *Annotation*
-keepclassmembers class oleg.trushanin.graz.MainOptions { *; }

# MainOptionsDao

-keep @androidx.room.Dao class oleg.trushanin.graz.MainOptionsDao { *; }
-keepclassmembers class oleg.trushanin.graz.MainOptionsDao {
    public *;
}
-keep class io.reactivex.rxjava3.** { *; }
-keep class androidx.lifecycle.LiveData { *; }
-keepclassmembers class oleg.trushanin.graz.MainOptionsDao {
    @androidx.room.Query <fields>;
}

# PageBreakHandler

-keep class oleg.trushanin.graz.PageBreakHandler { *; }
-keep class com.itextpdf.kernel.events.Event { *; }
-keep interface com.itextpdf.kernel.events.IEventHandler { *; }
-keepclassmembers class oleg.trushanin.graz.PageBreakHandler { *;}

# ResetPasswordActivity

-keep class oleg.trushanin.graz.ResetPasswordActivity { *; }
-keep class oleg.trushanin.graz.ResetPasswordViewModel { *; }
-keep class androidx.lifecycle.ViewModel { *; }
-keep class androidx.lifecycle.LiveData { *; }
-keep class androidx.lifecycle.Observer { *; }
-keepclassmembers class oleg.trushanin.graz.ResetPasswordActivity { *; }
-keepclassmembers class * extends android.view.View {
    public void onClick(android.view.View);
}
-keepclassmembers class oleg.trushanin.graz.ResetPasswordActivity {
    public <fields>;
}

#ResetPasswordViewModel

-keep class oleg.trushanin.graz.ResetPasswordViewModel { *; }
-keep class com.google.firebase.auth.FirebaseAuth { *; }
-keep class androidx.lifecycle.LiveData { *; }
-keep class androidx.lifecycle.MutableLiveData { *; }
-keep class com.google.android.gms.tasks.OnSuccessListener { *; }
-keep class com.google.android.gms.tasks.OnFailureListener { *; }
-keepclassmembers class oleg.trushanin.graz.ResetPasswordViewModel { *; }

#SelectMainOptionsViewModel

-keep class oleg.trushanin.graz.SelectMainOptionsViewModel { *; }
-keep class androidx.lifecycle.LiveData { *; }
-keep class androidx.lifecycle.MutableLiveData { *; }
-keep class oleg.trushanin.graz.MainOptionsDao { *; }
-keep class oleg.trushanin.graz.InitViewPosition { *; }
-keep class * extends androidx.room.RoomDatabase { *; }
-keep @androidx.room.Dao class * { *; }
-keep @androidx.room.Entity class * { *; }
-keepclassmembers class oleg.trushanin.graz.SelectMainOptionsViewModel { *; }
-keep public class * extends android.app.Application { *; }

#SelectTypePPCViewModel

-keep class oleg.trushanin.graz.SelectTypePPCViewModel { *; }
-keep class androidx.lifecycle.LiveData { *; }
-keep class androidx.lifecycle.MutableLiveData { *; }
-keep class oleg.trushanin.graz.BazaModelDao { *; }
-keep class * extends androidx.room.RoomDatabase { *; }
-keep @androidx.room.Dao class * { *; }
-keep @androidx.room.Entity class * { *; }
-keepclassmembers class oleg.trushanin.graz.SelectTypePPCViewModel { *; }
-keep public class * extends android.app.Application { *; }

#SpecialDarkOptions

-keep class oleg.trushanin.graz.SpecialDarkOptions { *; }
-keep class androidx.room.Entity { *; }
-keep class androidx.room.PrimaryKey { *; }
-keep class com.google.gson.annotations.SerializedName { *; }
-keepclassmembers class oleg.trushanin.graz.SpecialDarkOptions { *; }

#SpecialDarkOptionsDao

-keep class oleg.trushanin.graz.SpecialDarkOptionsDao { *; }
-keep class androidx.room.Dao { *; }
-keep class androidx.room.Insert { *; }
-keep class androidx.room.Query { *; }
-keepclassmembers class oleg.trushanin.graz.SpecialDarkOptionsDao { *; }
-keep class oleg.trushanin.graz.SpecialDarkOptions { *; }

#SpecialLightOptions

-keep class oleg.trushanin.graz.SpecialLightOptions { *; }
-keep class androidx.room.Entity { *; }
-keep class androidx.room.PrimaryKey { *; }
-keep class com.google.gson.annotations.SerializedName { *; }
-keepclassmembers class oleg.trushanin.graz.SpecialLightOptions { *; }
-keepclassmembers class oleg.trushanin.graz.SpecialLightOptions { <init>(...); }
-keepclassmembers class oleg.trushanin.graz.SpecialLightOptions { boolean equals(java.lang.Object); }
-keepclassmembers class oleg.trushanin.graz.SpecialLightOptions { int hashCode(); }
-keepclassmembers class oleg.trushanin.graz.SpecialLightOptions { java.lang.String toString(); }

#SpecialLightOptionsDao

-keep class oleg.trushanin.graz.SpecialLightOptionsDao { *; }
-keep class androidx.room.Dao { *; }
-keep class androidx.room.Insert { *; }
-keep class androidx.room.Query { *; }
-keepclassmembers class oleg.trushanin.graz.SpecialLightOptionsDao { *; }
-keepclassmembers class oleg.trushanin.graz.SpecialLightOptionsDao {
    @androidx.room.Query <methods>;
}

#SpecificaitAdapter

-keep class oleg.trushanin.graz.SpecificaitAdapter { *; }
-keep class oleg.trushanin.graz.SpecificaitAdapter$HolderSpecificationAdapter { *; }
-keepclassmembers class oleg.trushanin.graz.SpecificaitAdapter {
    <fields>;
    <methods>;
}
-keepclassmembers class oleg.trushanin.graz.SpecificaitAdapter$HolderSpecificationAdapter {
    <fields>;
    <methods>;
}

#TableDataBaseKp

-keep class oleg.trushanin.graz.TableDataBaseKp { *; }
-keepattributes *Annotation*
-keep class com.google.gson.** { *; }
-keep class androidx.room.** { *; }
-keep interface androidx.room.** { *; }

#TableDataBaseKpDao

-keep interface oleg.trushanin.graz.TableDataBaseKpDao { *; }
-keep class oleg.trushanin.graz.TableDataBaseKp { *; }
-keepattributes *Annotation*
-keep class androidx.room.** { *; }
-keep interface androidx.room.** { *; }
-keep class io.reactivex.rxjava3.** { *; }

#TableDataBaseKpViewModel

-keep class oleg.trushanin.graz.TableDataBaseKpViewModel { *; }
-keep class oleg.trushanin.graz.TableDataBaseKpDao { *; }
-keep class oleg.trushanin.graz.TableDataBaseKp { *; }
-keep class io.reactivex.rxjava3.** { *; }
-keep interface io.reactivex.rxjava3.** { *; }
-keepattributes *Annotation*

#ViewBdViewModel

-keep class oleg.trushanin.graz.ViewBdViewModel { *; }
-keep class oleg.trushanin.graz.TableDataBaseKpDao { *; }
-keep class oleg.trushanin.graz.TableDataBaseKp { *; }
-keep class io.reactivex.rxjava3.** { *; }
-keep interface io.reactivex.rxjava3.** { *; }
-keepattributes *Annotation*

#ActivityCreateAndSendPDF

-keep class com.itextpdf.** { *; }
-dontwarn com.itextpdf.**
-keep class org.apache.commons.io.** { *; }
-dontwarn org.apache.commons.io.**
-keep class oleg.trushanin.graz.InitViewPosition { *; }
-keep class oleg.trushanin.graz.TableDataBaseKp { *; }
-keep class oleg.trushanin.graz.PageBreakHandler { *; }
-keep class com.some.image.library.** { *; }
-dontwarn com.some.image.library.**

# Исключения для сохранения имени класса Activity и его методов жизненного цикла.
-keep public class oleg.trushanin.graz.ActivityCreateAndSendPDF {
    public <init>(...);
    void *(...);
}

# Сохранение пользовательских методов обработки событий и других функций.
-keepclassmembers class oleg.trushanin.graz.ActivityCreateAndSendPDF {
    void createAndSavePDF(...);
    void viewPDF(...);
    byte[] createPdfContent(...);
    void savePDFToDownloads(...);
    void sharePdf(...);

}

# Сохранение классов и методов iText для работы с PDF.
-keep class com.itextpdf.** { *; }

# Сохранение классов работы с AssetManager для загрузки ресурсов.
-keep class android.content.res.AssetManager { public <methods>; }

# Сохранение SharedPreferences и его методов.
-keep class android.content.SharedPreferences { public <methods>; }

# Сохранение ViewModel и его методов.
-keep class androidx.lifecycle.ViewModelProvider { public <methods>; }
-keep class oleg.trushanin.graz.TableDataBaseKpViewModel { public <methods>; }

# Сохранение классов и методов работы с URI.
-keep class android.net.Uri { public <methods>; }

# Сохранение классов работы с контент-резолвером.
-keep class android.content.ContentResolver { public <methods>; }
-keep class android.provider.MediaStore$Files { public <fields>; }

# Сохранение классов и методов для работы с правами доступа.
-keep class android.content.pm.PackageManager { public <methods>; }
-keep class android.Manifest { public <fields>; }

# Сохранение исключений для работы с файловой системой.
-keep class java.io.FileOutputStream { public <methods>; }
-keep class java.io.File { public <methods>; }

# Сохранение исключений для работы с IOUtils.
-keep class org.apache.commons.io.IOUtils { public <methods>; }

# Сохранение исключений для работы с потоками данных.
-keep class java.io.InputStream { public <methods>; }
-keep class java.io.OutputStream { public <methods>; }

# Сохранение классов и методов Android для работы с датой и временем.
-keep class java.text.SimpleDateFormat { public <methods>; }
-keep class java.util.Calendar { public <methods>; }
-keep class java.time.** { public <methods>; }


#ActivityDetailKP

# Исключение для сохранения имени Activity и методов обратного вызова жизненного цикла.
-keep public class oleg.trushanin.graz.ActivityDetailKP {
    public <init>(...);
    void *(...);
}

# Сохранение пользовательских методов обработки событий.
-keepclassmembers class oleg.trushanin.graz.ActivityDetailKP {
    void initView(...);
}

# Сохранение Enum и его полей.
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

# Сохранение классов и методов SharedPreferences.
-keep class android.content.SharedPreferences {
    public <fields>;
    public <methods>;
}

# Сохранение класса и методов SharedPreferences.Editor.
-keep class android.content.SharedPreferences$Editor {
    public <methods>;
}

# Сохранение имен классов и методов, взаимодействующих с RecyclerView и его адаптерами.
-keepclassmembers class androidx.recyclerview.widget.RecyclerView {
    public <methods>;
}
-keep class oleg.trushanin.graz.SpecificaitAdapter { *; }
-keep class oleg.trushanin.graz.AdapterSpecialOptionsDetail { *; }

# Сохранение ViewModel и его методов.
-keep class androidx.lifecycle.ViewModelProvider {
    public <methods>;
}
-keep class oleg.trushanin.graz.SelectMainOptionsViewModel {
    public <methods>;
}

# Сохранение FloatingActionButton и его обработчиков событий.
-keepclassmembers class com.google.android.material.floatingactionbutton.FloatingActionButton {
    public <methods>;
}

# Сохранение TextView и его методов.
-keepclassmembers class android.widget.TextView {
    void setText(java.lang.CharSequence);
}


-keep class android.support.v7.widget.** { *; }
-keep class android.support.design.widget.** { *; }
-keep class androidx.** { *; }
-dontwarn android.support.**
-keep class androidx.lifecycle.** { *; }
-dontwarn androidx.lifecycle.**
-keep public class * extends androidx.recyclerview.widget.RecyclerView$ViewHolder { *; }
-keep public class * extends androidx.recyclerview.widget.RecyclerView$Adapter { *; }
-keep public class * extends androidx.recyclerview.widget.RecyclerView$LayoutManager { *; }
-keep class androidx.recyclerview.widget.** { *; }
-dontwarn androidx.recyclerview.widget.**
-keep class oleg.trushanin.graz.InitViewPosition { *; }
-keep class oleg.trushanin.graz.DataVisualManager { *; }
-keep class oleg.trushanin.graz.SpecificaitAdapter { *; }
-keep class oleg.trushanin.graz.AdapterSpecialOptionsDetail { *; }

#ActivityKartaOkraski

-keep class oleg.trushanin.graz.InitViewPosition { *; }
-keep class * extends android.app.Activity { *; }
-keep class * extends android.app.Service { *; }
-keep class * extends android.content.BroadcastReceiver { *; }
-keep class * extends android.content.ContentProvider { *; }
-keep class * extends android.app.Application { *; }

#ActivityKartaOkraski

# Исключение для сохранения имени Activity и методов обратного вызова жизненного цикла.
-keep public class oleg.trushanin.graz.ActivityKartaOkraski {
    public <init>(...);
    void *(...);
}

# Сохранение пользовательских методов обработки событий.
-keepclassmembers class oleg.trushanin.graz.ActivityKartaOkraski {
     void *(...);
}

# Сохранение Enum и его полей.
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

# Сохранение классов и методов SharedPreferences.
-keep class android.content.SharedPreferences {
    public <fields>;
    public <methods>;
}

# Сохранение класса и методов SharedPreferences.Editor.
-keep class android.content.SharedPreferences$Editor {
    public <methods>;
}

# Сохранение имен классов и методов, взаимодействующих с TextView.
-keepclassmembers class android.widget.TextView {
    void setText(java.lang.CharSequence);
}



# Сохранение имен классов и методов, связанных с ArrayAdapter.
-keep class android.widget.ArrayAdapter {
    public <init>(android.content.Context, int);
    public void addAll(java.util.Collection);
    public void setDropDownViewResource(int);
}


#ActivityMenegElementBd

# Исключение для сохранения имени Activity и методов обратного вызова жизненного цикла.
-keep public class oleg.trushanin.graz.ActivityMenegElementBd {
    public <init>(...);
    void *(...);
}

# Сохранение пользовательских методов обработки событий.
-keepclassmembers class oleg.trushanin.graz.ActivityMenegElementBd {
     void *(...);
}

# Сохранение имен ViewModel и LiveData, если они используются.
-keep class * extends androidx.lifecycle.ViewModel {
    <fields>;
}
-keep class * extends androidx.lifecycle.LiveData {
    <fields>;
}

# Сохранение имен классов и методов, взаимодействующих с ContentResolver.
-keepclassmembers class * {
    android.content.ContentResolver getContentResolver();
    void query(android.net.Uri, java.lang.String[], java.lang.String, java.lang.String[], java.lang.String);
    int delete(android.net.Uri, java.lang.String, java.lang.String[]);
}

# Сохранение имен методов и переменных, используемых для работы с Intent.
-keepclassmembers class * {
    void putExtra(java.lang.String, android.os.Parcelable);
    void startActivity(android.content.Intent);
}

# Сохранение Enum и его полей.
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

# Сохранение имен классов и методов, связанных с Toast.
-keepclassmembers class android.widget.Toast {
    public static android.widget.Toast makeText(android.content.Context, java.lang.CharSequence, int);
    public void show();
}


#ActivityRequisitesKp

# Исключение для сохранения имен Activity и методов обратного вызова.
-keep public class oleg.trushanin.graz.ActivityRequisitesKp {
    public <init>(...);
    void *(...);
}

# Сохранение пользовательских методов обработки событий.
-keepclassmembers class oleg.trushanin.graz.ActivityRequisitesKp {
   void *(...);
}

# Сохранение имен членов классов, используемых в привязках данных и обработчиках событий.
-keepclassmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet);
    public void set*(...);
    public *** get*();
}

# Сохранение имен классов ViewModel и LiveData, если они используются в Activity.
-keep class * extends androidx.lifecycle.ViewModel {
    <fields>;
}
-keep class * extends androidx.lifecycle.LiveData {
    <fields>;
}

# Сохранение имен Enum и их полей, если они используются.
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

# Сохранение имен методов и переменных, используемых для работы с SharedPreferences.
-keepclassmembers class * {
    void put*(java.lang.String, ***);
    *** get*(java.lang.String, ***);
}


#ActivitySelectMainOptions

# Сохранение имен Activity, чтобы они могли быть найдены через Intent.
-keepclassmembers class * extends android.app.Activity {
    public void *(android.view.View);
}

# Сохранение имен компонентов пользовательского интерфейса и слушателей событий.
-keepclassmembers class * extends android.view.View {
    void set*(***);
    *** get*();
}



# Сохранение ViewModel и LiveData.
-keep class * extends androidx.lifecycle.ViewModel {
    <fields>;
}

-keep class * extends androidx.lifecycle.LiveData {
    <fields>;
}

# Сохранение SharedPreferences.
-keepclassmembers class * {
    void put*(java.lang.String, ***);
    *** get*(java.lang.String, ***);
}

# Сохранение класса ActivitySelectMainOptions.
-keep class oleg.trushanin.graz.ActivitySelectMainOptions {
    *;
}


# Сохранение имен Enum и их полей.
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}


#ActivitySelectTypePPC

# Исключения для сохранения имени класса Activity и его методов жизненного цикла.
-keep public class oleg.trushanin.graz.ActivitySelectTypePPC {
    public <init>(...);
    void *(...);
}

# Сохранение пользовательских методов обработки событий и других функций.
-keepclassmembers class oleg.trushanin.graz.ActivitySelectTypePPC {
    void initView(...);
    void inintArraySpiner(...);
    void updateVisibility(...);
    void updateSpiner(...);
    void loadFirstPosition(...);
    void clearButtonGroupForFirst(...);
    void initFirstPosition(...);
    boolean checkAxesGroup(...);
    void writeVariableType(...);
    void onDataChanged(...);
    void offDataChanged(...);
     void *(...);
}

# Сохранение классов и методов для работы с UI элементами.
-keep class android.widget.** { *; }

# Сохранение ViewModel и его методов.
-keep class androidx.lifecycle.ViewModelProvider { public <methods>; }
-keep class oleg.trushanin.graz.SelectTypePPCViewModel { public <methods>; }

# Сохранение классов и методов для работы с Intent и Bundle.
-keep class android.content.Intent { public <methods>; }
-keep class android.os.Bundle { public <methods>; }

# Сохранение классов и методов для работы с Toast.
-keep class android.widget.Toast { public <methods>; }

# Сохранение классов и методов для работы с RadioGroup и RadioButton.
-keep class android.widget.RadioGroup { public <methods>; }
-keep class android.widget.RadioButton { public <methods>; }

# Сохранение классов и методов для работы со Spinner и ArrayAdapter.
-keep class android.widget.Spinner { public <methods>; }
-keep class android.widget.ArrayAdapter { public <methods>; }

# Сохранение классов работы с ресурсами приложения.
-keep class android.content.res.Resources { public <methods>; }

# Сохранение классов и методов для работы с листенерами.
-keep class android.view.View$OnClickListener { *; }
-keep class android.widget.CompoundButton$OnCheckedChangeListener { *; }
-keep class android.widget.AdapterView$OnItemSelectedListener { *; }

#ActivitySelectTypePPC

# Исключения для сохранения имени класса Activity и его методов жизненного цикла.
-keep public class oleg.trushanin.graz.ActivitySelectTypePPC {
    public <init>(...);
    void *(...);
}

# Сохранение пользовательских методов обработки событий и других функций.
-keepclassmembers class oleg.trushanin.graz.ActivitySelectTypePPC {
    void initView(...);
    void inintArraySpiner(...);
    void updateVisibility(...);
    void updateSpiner(...);
    void loadFirstPosition(...);
    void clearButtonGroupForFirst(...);
    void initFirstPosition(...);
    boolean checkAxesGroup(...);
    void writeVariableType(...);
    void onDataChanged(...);
    void offDataChanged(...);
    void *(...);
}

# Сохранение классов и методов для работы с UI элементами.
-keep class android.widget.** { *; }

# Сохранение ViewModel и его методов.
-keep class androidx.lifecycle.ViewModelProvider { public <methods>; }
-keep class oleg.trushanin.graz.SelectTypePPCViewModel { public <methods>; }

# Сохранение классов и методов для работы с Intent и Bundle.
-keep class android.content.Intent { public <methods>; }
-keep class android.os.Bundle { public <methods>; }

# Сохранение классов и методов для работы с Toast.
-keep class android.widget.Toast { public <methods>; }

# Сохранение классов и методов для работы с RadioGroup и RadioButton.
-keep class android.widget.RadioGroup { public <methods>; }
-keep class android.widget.RadioButton { public <methods>; }

# Сохранение классов и методов для работы со Spinner и ArrayAdapter.
-keep class android.widget.Spinner { public <methods>; }
-keep class android.widget.ArrayAdapter { public <methods>; }

# Сохранение классов работы с ресурсами приложения.
-keep class android.content.res.Resources { public <methods>; }

# Сохранение классов и методов для работы с листенерами.
-keep class android.view.View$OnClickListener { *; }
-keep class android.widget.CompoundButton$OnCheckedChangeListener { *; }
-keep class android.widget.AdapterView$OnItemSelectedListener { *; }

#ActivitySpecialOptions

# Исключения для сохранения имени класса Activity и его методов жизненного цикла.
-keep public class oleg.trushanin.graz.ActivitySpecialOptions {
    public <init>(...);
    void *(...);
}

# Сохранение пользовательских методов и обработчиков событий.
-keepclassmembers class oleg.trushanin.graz.ActivitySpecialOptions {
    void getListOptions(...);
     void *(...);
}

# Сохранение классов и методов для работы с RecyclerView и его адаптерами.
-keep class androidx.recyclerview.widget.RecyclerView { public <methods>; }
-keep class androidx.recyclerview.widget.LinearLayoutManager { public <methods>; }
-keep class oleg.trushanin.graz.AdapterSpecialOptions { public <methods>; }

# Сохранение классов и методов для работы с листенерами.
-keep class oleg.trushanin.graz.AdapterSpecialOptions$OnClickItemListener { *; }

# Сохранение классов и методов для работы с листами (List) и потоковым API Java.
-keep class java.util.List { public <methods>; }
-keep class java.util.ArrayList { public <methods>; }
-keep class java.util.Optional { public <methods>; }
-keep class java.util.stream.Stream { public <methods>; }

# Сохранение пользовательских классов данных и их методов.
-keep class oleg.trushanin.graz.InitViewPosition { public <methods>; }
-keep class oleg.trushanin.graz.SpecialLightOptions { public <methods>; }
-keep class oleg.trushanin.graz.SpecialDarkOptions { public <methods>; }

# Сохранение классов и методов для работы с Android Activity.
-keep class android.os.Bundle { public <methods>; }
-keep class androidx.activity.OnBackPressedCallback { public <methods>; }
-keep class android.content.Intent { public <methods>; }

#ActivityUserInfo

# Общие правила для Android
-keepclassmembers class * {
    @androidx.annotation.Keep <methods>;
}

-keep public class * extends android.app.Activity
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider

# Сохранение нативных методов
-keepclasseswithmembernames class * {
    native <methods>;
}

# Сохранение методов жизненного цикла активности
-keepclassmembers class * extends android.app.Activity {
   public void *(android.view.View);
   public void onCreate(android.os.Bundle);
   public void onStart();
   public void onResume();
   public void onPause();
   public void onStop();
   public void onDestroy();
}

# Сохранение специфических классов и методов вашего приложения
-keep class oleg.trushanin.graz.** { *; }


# Сохранение ENUM значений
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

#ActivityViewPdfKp

# Сохранение Activity, сервисов, приемников широковещательных сообщений и провайдеров
-keep public class * extends android.app.Activity
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider

# Сохранение классов и методов PDFViewer
-keep class com.github.barteksc.pdfviewer.** { *; }
-keep class com.shockwave.**
-keep class com.shockwave.pdfium.** { *; }

# Сохранение классов, используемых в ActivityViewPdfKp
-keep class oleg.trushanin.graz.ActivityViewPdfKp { *; }

# Сохранение нативных методов
-keepclasseswithmembernames class * {
    native <methods>;
}

# Сохранение методов жизненного цикла Activity
-keepclassmembers class * extends android.app.Activity {
   public void onCreate(android.os.Bundle);
   public void onStart();
   public void onResume();
   public void onPause();
   public void onStop();
   public void onDestroy();
}

# Сохранение ENUM значений
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

# Если ваше приложение использует рефлексию
-keepclassmembers class * {
    <fields>;
    <methods>;
}

#ActivityViewPdfPrice

# Сохранение Activity
-keep public class * extends android.app.Activity

# Сохранение классов и методов библиотеки PDFViewer
-keep class com.github.barteksc.pdfviewer.** { *; }
-keep class com.shockwave.**
-keep class com.shockwave.pdfium.** { *; }

# Сохранение ActivityViewPdfPrice
-keep class oleg.trushanin.graz.ActivityViewPdfPrice { *; }

# Сохранение нативных методов
-keepclasseswithmembernames class * {
    native <methods>;
}

# Сохранение методов жизненного цикла Activity
-keepclassmembers class * extends android.app.Activity {
   public void onCreate(android.os.Bundle);
   public void onStart();
   public void onResume();
   public void onPause();
   public void onStop();
   public void onDestroy();
}

# Сохранение ENUM значений
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

# Если ваше приложение использует рефлексию
-keepclassmembers class * {
    <fields>;
    <methods>;
}

#MainActivity

# Сохранение Activity
-keep public class * extends android.app.Activity

# Сохранение классов Firebase
-keep class com.google.firebase.** { *; }
-dontwarn com.google.firebase.**

# Исключения для SharedPreferences
-keepclassmembers class * {
    *** getSharedPreferences(...);
}

# Сохранение классов ViewModel
-keep class androidx.lifecycle.ViewModelProvider { *; }
-keep class * extends androidx.lifecycle.ViewModel {
    <init>(...);
}

# Сохранение слушателей событий
-keepclassmembers class * {
    void on*(android.view.View);
    void on*(android.view.MenuItem);
}

# Сохранение методов жизненного цикла Activity
-keepclassmembers class * extends android.app.Activity {
   public void onCreate(android.os.Bundle);
   public void onStart();
   public void onResume();
   public void onPause();
   public void onStop();
   public void onDestroy();
}

# Сохранение ENUM значений
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

# Сохранение класса MainActivity
-keep class oleg.trushanin.graz.MainActivity { *; }

# Если ваше приложение использует рефлексию
-keepclassmembers class * {
    <fields>;
    <methods>;
}

# Сохранение нативных методов
-keepclasseswithmembernames class * {
    native <methods>;
}

# Исключения для логирования
-keepattributes *Annotation*, Exceptions, InnerClasses, Signature, Deprecated, SourceFile, LineNumberTable, *Annotation*, EnclosingMethod

#LoginActivity

# Сохранение Activity
-keep public class * extends android.app.Activity

# Сохранение классов Firebase
-keep class com.google.firebase.** { *; }
-dontwarn com.google.firebase.**

# Сохранение классов ViewModel
-keep class androidx.lifecycle.ViewModelProvider { *; }
-keep class * extends androidx.lifecycle.ViewModel {
    <init>(...);
}

# Сохранение слушателей событий
-keepclassmembers class * {
    void on*(android.view.View);
}

# Сохранение методов жизненного цикла Activity
-keepclassmembers class * extends android.app.Activity {
   public void onCreate(android.os.Bundle);
   public void onStart();
   public void onResume();
   public void onPause();
   public void onStop();
   public void onDestroy();
}

# Сохранение ENUM значений
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

# Сохранение класса LoginActivity
-keep class oleg.trushanin.graz.LoginActivity { *; }

# Если ваше приложение использует рефлексию
-keepclassmembers class * {
    <fields>;
    <methods>;
}

# Сохранение нативных методов
-keepclasseswithmembernames class * {
    native <methods>;
}

# Исключения для логирования
-keepattributes *Annotation*, Exceptions, InnerClasses, Signature, Deprecated, SourceFile, LineNumberTable, *Annotation*, EnclosingMethod


-keep class javax.naming.** { *; }
-keep class javax.naming.directory.** { *; }

-dontwarn javax.naming.NamingException
-dontwarn javax.naming.directory.Attribute
-dontwarn javax.naming.directory.Attributes
-dontwarn javax.naming.directory.DirContext
-dontwarn javax.naming.directory.InitialDirContext
-dontwarn org.slf4j.impl.StaticLoggerBinder

