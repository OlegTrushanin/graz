package oleg.trushanin.graz;

import static oleg.trushanin.graz.InitViewPosition.resetAllValues;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity {



    Button buttonNewKP;
    LoadViewModel loadViewModel;
    LoginViewModel loginViewModel;
    Button buttonSignOut;
    Button buttonUserName;
    Button buttonBd;

    TextView textViewPriceNumber;

    InitViewPosition initViewPosition;

    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference;
    FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser(); // получаем ссылку на юзера
    SharedPreferences sharedPreferences;
    String validPathExit;
    String validPath;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViewPosition = InitViewPosition.getInstance();// чтобы
        sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        loadViewModel = new ViewModelProvider(this).get(LoadViewModel.class);
        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        loadViewModel.loadPrice();
        loadViewModel.loadBazaModel();
        loadViewModel.loadMainOptions();
        loadViewModel.loadSpecialDarkOptions();
        loadViewModel.loadSpecialLightOptions();
        loadViewModel.loadKo();


        validPath = "";
        if (currentUser != null) { // если юзер не налл, то получаем с него эмэйл
            String email = currentUser.getEmail();
            if (email != null) {
                // Заменяем точки на запятые или любой другой допустимый символ
                validPath = email.replace(".", "-"); // заменяем . на -
                databaseReference = firebaseDatabase.getReference();
            }
        }

        exitAndSignOut(); // проверяем нет ли запрета на вход в приложение у юзера








        String formattedDateTime; //форматируем дату и время. Для отслеживания входа юзеров
        String formattedYear;
        String formattedMonth;
        String formattedDay;

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            LocalDateTime now = LocalDateTime.now(ZoneId.of("Europe/Moscow"));

            DateTimeFormatter formatterDateTime = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            formattedDateTime = now.format(formatterDateTime);

            DateTimeFormatter formatterMonth = DateTimeFormatter.ofPattern("yyyy-MM");
            formattedMonth = now.format(formatterMonth);

            DateTimeFormatter formatterDay = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            formattedDay = now.format(formatterDay);

            DateTimeFormatter formatterYear = DateTimeFormatter.ofPattern("yyyy");
            formattedYear = now.format(formatterYear);



        } else {
            SimpleDateFormat formatterDateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
            formatterDateTime.setTimeZone(TimeZone.getTimeZone("Europe/Moscow"));
            formattedDateTime = formatterDateTime.format(Calendar.getInstance().getTime());

            SimpleDateFormat formatterMonth = new SimpleDateFormat("yyyy-MM", Locale.getDefault());
            formatterMonth.setTimeZone(TimeZone.getTimeZone("Europe/Moscow"));
            formattedMonth = formatterMonth.format(Calendar.getInstance().getTime());

            SimpleDateFormat formatterDay = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            formatterDay.setTimeZone(TimeZone.getTimeZone("Europe/Moscow"));
            formattedDay = formatterDay.format(Calendar.getInstance().getTime());

            SimpleDateFormat formatterYear = new SimpleDateFormat("yyyy", Locale.getDefault());
            formatterYear.setTimeZone(TimeZone.getTimeZone("Europe/Moscow"));
            formattedYear = formatterYear.format(Calendar.getInstance().getTime());

        }


        String userPath = "visit/"  + formattedYear +"/" + validPath + "/"+ formattedMonth + " month/"+ formattedDay;
        // Вывод отформатированной строки

        databaseReference.child(userPath).push().setValue(formattedDateTime);




        buttonNewKP = findViewById(R.id.buttonNewKP);
        buttonSignOut = findViewById(R.id.buttonSignOut);
        buttonUserName = findViewById(R.id.buttonUserName);
        buttonBd = findViewById(R.id.buttonBd);
        textViewPriceNumber = findViewById(R.id.textViewPriceNumber);

        String numberPrice = sharedPreferences.getString("нумпрайс", "0");
        if(!numberPrice.equals("0")){
            textViewPriceNumber.setText(numberPrice);
        }







        buttonNewKP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ActivitySelectTypePPC.class);
                startActivity(intent);
            }
        });

        loadViewModel.getListPrice().observe(MainActivity.this, new Observer<List<Price>>() {
            @Override
            public void onChanged(List<Price> priceList) {

                loadViewModel.insertPrice(priceList);
            }
        });

        loadViewModel.getListBazaModel().observe(MainActivity.this, new Observer<List<BazaModel>>() {
            @Override
            public void onChanged(List<BazaModel> bazaModels) {
                loadViewModel.insertBazaModel(bazaModels);
            }
        });

        loadViewModel.getListMainOptions().observe(MainActivity.this, new Observer<List<MainOptions>>() {
            @Override
            public void onChanged(List<MainOptions> mainOptions) {
                loadViewModel.insertMainOptions(mainOptions);

                sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
                SharedPreferences.Editor myEdit = sharedPreferences.edit();

                Optional<MainOptions> foundId = mainOptions.stream()
                        .filter(item -> 1 == item.getId())
                        .findFirst(); // Находим первый элемент, соответствующий условию

                if (foundId.isPresent()) {
                    // Если элемент найден, сохраняем его цену в SharedPreferences

                    myEdit.putString("нумпрайс", foundId.get().getName());
                    myEdit.apply(); // Не забываем подтвердить изменения
                    textViewPriceNumber.setText(foundId.get().getName());
                }
            }
        });

        loadViewModel.getListSpecialDarkOptions().observe(MainActivity.this, new Observer<List<SpecialDarkOptions>>() {
            @Override
            public void onChanged(List<SpecialDarkOptions> specialDarkOptions) {
                loadViewModel.insertSpecialDarkOptions(specialDarkOptions);
            }
        });

        loadViewModel.getListSpecialLightOptions().observe(MainActivity.this, new Observer<List<SpecialLightOptions>>() {
            @Override
            public void onChanged(List<SpecialLightOptions> specialLightOptions) {
                loadViewModel.insertSpecialLightOptions(specialLightOptions);
            }
        });

        loadViewModel.getListKo().observe(MainActivity.this, new Observer<List<Ko>>() {
            @Override
            public void onChanged(List<Ko> kos) {

                loadViewModel.insertKo(kos);
                //Получаем данные по окраске и записываем их в шаред преференс
                sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
                SharedPreferences.Editor myEdit = sharedPreferences.edit();
                // Получаем нужный элемент с помощью Stream API
                Optional<Ko> foundKoMkm = kos.stream()
                        .filter(item -> "180 мкм".equals(item.getName()))
                        .findFirst(); // Находим первый элемент, соответствующий условию

                if (foundKoMkm.isPresent()) {
                    // Если элемент найден, сохраняем его цену в SharedPreferences
                    myEdit.putFloat("180 мкм", foundKoMkm.get().getPrice());
                    myEdit.apply(); // Не забываем подтвердить изменения
                }

                Optional<Ko> foundKoColour = kos.stream()
                        .filter(item -> "1 цвет".equals(item.getName()))
                        .findFirst(); // Находим первый элемент, соответствующий условию

                if (foundKoColour.isPresent()) {
                    // Если элемент найден, сохраняем его цену в SharedPreferences
                    myEdit.putFloat("1 цвет", foundKoColour.get().getPrice());
                    myEdit.apply(); // Не забываем подтвердить изменения
                }

                Optional<Ko> foundKoKof = kos.stream()
                        .filter(item -> "коэф".equals(item.getName()))
                        .findFirst(); // Находим первый элемент, соответствующий условию

                if (foundKoKof.isPresent()) {
                    // Если элемент найден, сохраняем его цену в SharedPreferences
                    myEdit.putFloat("коэф", foundKoKof.get().getPrice());
                    myEdit.apply(); // Не забываем подтвердить изменения
                }



            }
        });

        buttonSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginViewModel.logout();
                finish();
            }
        });

        buttonUserName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ActivityUserInfo.class);
                startActivity(intent);
            }
        });

        buttonBd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ActivityViewBd.class));
            }
        });

    }

    private void exitAndSignOut(){

        validPathExit= validPath.concat("Exit");
        databaseReference.child(validPathExit).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {


                if (snapshot.exists()) {
                    // Получаем значение как String
                    String value = snapshot.getValue(String.class);

                    // Проверяем, равно ли значение "1"
                    assert value != null;
                    if(value.equals("1")){
                        loginViewModel.logout();
                        finish();
                    }

                }




            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Обработка ошибок, например, нет прав доступа

            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        resetAllValues();// приводим кэш к 0-состоянию при переходе на главный экран
    }
}

