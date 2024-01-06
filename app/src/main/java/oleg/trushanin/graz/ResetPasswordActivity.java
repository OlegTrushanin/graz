package oleg.trushanin.graz;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

public class ResetPasswordActivity extends AppCompatActivity {


    EditText editTextEmail;
    Button buttonResetPassword;

    ResetPasswordViewModel resetPasswordViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        initView();
        resetPasswordViewModel = new ViewModelProvider(this).get(ResetPasswordViewModel.class);
        observeResetPassword();
        String email = getIntent().getStringExtra("email");
        editTextEmail.setText(email);

        buttonResetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = editTextEmail.getText().toString().trim();
                if(!email.equals("")){
                    resetPasswordViewModel.resetPassword(email);
                }else{
                    Toast.makeText(ResetPasswordActivity.this, "Заполните поле email", Toast.LENGTH_SHORT).show();
                }
            }
        });






    }


    private void initView(){

        editTextEmail = findViewById(R.id.editTextEmail);
        buttonResetPassword = findViewById(R.id.buttonResetPassword);
    }

    private void observeResetPassword(){

        resetPasswordViewModel.getSuccess().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {

                Toast.makeText(ResetPasswordActivity.this, "Ссылка для изменения пароля отправлена на e-mail", Toast.LENGTH_SHORT).show();
            }
        });

        resetPasswordViewModel.getError().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Toast.makeText(ResetPasswordActivity.this, s, Toast.LENGTH_SHORT).show();
            }
        });




    }
}