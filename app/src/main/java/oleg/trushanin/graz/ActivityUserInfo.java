package oleg.trushanin.graz;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

public class ActivityUserInfo extends AppCompatActivity {

    EditText editSurname;
    EditText editName;
    EditText editNameName;
    EditText editTitle;
    EditText editPhoneNumber;
    EditText editEmail;

    Button buttonSaveUserInfo;
    Button buttonLoadPricePdf;

    LoadPriceViewModel loadPriceViewModel;

    ProgressBar progressBarDownLoadPrice;

    String urlDownload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);

        loadPriceViewModel = new ViewModelProvider(this).get(LoadPriceViewModel.class);



        initView();


        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        SharedPreferences.Editor myEdit = sharedPreferences.edit();

        editSurname.setText(sharedPreferences.getString("фамилия",""));
        editName.setText(sharedPreferences.getString("имя",""));
        editNameName.setText(sharedPreferences.getString("отчество",""));
        editTitle.setText(sharedPreferences.getString("должность",""));
        editPhoneNumber.setText(sharedPreferences.getString("телефон",""));
        editEmail.setText(sharedPreferences.getString("почта",""));


        urlDownload = sharedPreferences.getString("url", "");

        loadPriceViewModel.getLoadBool().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if(aBoolean){
                    progressBarDownLoadPrice.setVisibility(View.VISIBLE);

                }else{
                    progressBarDownLoadPrice.setVisibility(View.GONE);

                }
            }
        });

        loadPriceViewModel.getLoadBoolError().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                Toast.makeText(ActivityUserInfo.this, "Не удалось загрузить прайс", Toast.LENGTH_SHORT).show();

            }
        });

        loadPriceViewModel.getLoadBoolToast().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if(aBoolean){

                    Toast.makeText(ActivityUserInfo.this, "Прайс загружен", Toast.LENGTH_SHORT).show();
                }

            }
        });



        editSurname.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                myEdit.putString("фамилия", editable.toString());
                myEdit.apply();

            }
        });

        editName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                myEdit.putString("имя", editable.toString());
                myEdit.apply();

            }
        });

        editNameName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                myEdit.putString("отчество", editable.toString());
                myEdit.apply();

            }
        });

        editTitle.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                myEdit.putString("должность", editable.toString());
                myEdit.apply();

            }
        });

        editPhoneNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                myEdit.putString("телефон", editable.toString());
                myEdit.apply();
            }
        });

        editEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                myEdit.putString("почта", editable.toString());
                myEdit.apply();
            }
        });


        buttonSaveUserInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        buttonLoadPricePdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                loadPriceViewModel.loadPrice(urlDownload);
            }
        });


    }

    public void initView(){

        editSurname = findViewById(R.id.editSurname);
        editName = findViewById(R.id.editName);
        editNameName = findViewById(R.id.editNameName);
        editTitle = findViewById(R.id.editTitle);
        editPhoneNumber = findViewById(R.id.editPhoneNumber);
        editEmail = findViewById(R.id.editEmail);
        buttonLoadPricePdf = findViewById(R.id.buttonLoadPricePdf);
        progressBarDownLoadPrice = findViewById(R.id.progressBarDownLoadPrice);


        buttonSaveUserInfo = findViewById(R.id.buttonSaveUserInfo);




    }
}