package oleg.trushanin.graz;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;



public class LoginActivity extends AppCompatActivity {

    private LoginViewModel loginViewModel;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private Button buttonSign;


    private TextView textViewForgotPassword;

    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



        databaseReference = firebaseDatabase.getReference();



        initView();



        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        observe();

        buttonSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = editTextEmail.getText().toString().trim();
                String password = editTextPassword.getText().toString().trim();
                if(!email.equals("")&&!password.equals("")){
                    loginViewModel.logIn(email,password);

                }else{
                    Toast.makeText(LoginActivity.this, "Заполните поля логина и пароля", Toast.LENGTH_SHORT).show();

                }


            }
        });

        textViewForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(LoginActivity.this, ResetPasswordActivity.class);

                intent.putExtra("email", editTextEmail.getText().toString().trim());

                startActivity(intent);

            }
        });



    }

    private void initView(){

        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonSign = findViewById(R.id.buttonSign);


        textViewForgotPassword = findViewById(R.id.textViewForgotPassword);

    }

    private void observe(){

        loginViewModel.getUser().observe(this, new Observer<FirebaseUser>() {
            @Override
            public void onChanged(FirebaseUser firebaseUser) {
                if(firebaseUser != null){
                    getToken();
                    Toast.makeText(LoginActivity.this, "Вы авторизовались", Toast.LENGTH_SHORT).show();
//                    startActivity(new Intent(LoginActivity.this,MainActivity.class));
//
//                    finish();
                }

            }
        });

        loginViewModel.getError().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Toast.makeText(LoginActivity.this, s, Toast.LENGTH_SHORT).show();

            }
        });


    }

    public void getToken(){ // получаем токен из файрбейз и сохраняем в инкриптшаредпреференс

        databaseReference.child("data").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {

                    // Получаем токен
                    String token = snapshot.child("token").getValue(String.class);
                    // Получаем ссылку
                    String url = snapshot.child("url").getValue(String.class);


                    SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
                    SharedPreferences.Editor myEdit = sharedPreferences.edit();
                    myEdit.putString("token", token);
                    myEdit.putString("url", url);

                    myEdit.apply();

                    startActivity(new Intent(LoginActivity.this,MainActivity.class));

                    finish();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                startActivity(new Intent(LoginActivity.this,MainActivity.class));

                finish();
            }
        });

    }
}