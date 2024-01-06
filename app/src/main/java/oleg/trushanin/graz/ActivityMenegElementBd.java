package oleg.trushanin.graz;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

public class ActivityMenegElementBd extends AppCompatActivity {

    Button buttonSendKp;
    Button buttonDeleteKp;
    Uri uri;
    InitViewPosition initViewPosition;
    ViewBdViewModel viewBdViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meneg_element_bd);
        buttonSendKp = findViewById(R.id.buttonSendKp);
        buttonDeleteKp = findViewById(R.id.buttonDeleteKp);
        initViewPosition = InitViewPosition.getInstance();
        uri = initViewPosition.getUriPdfKp();
        viewBdViewModel = new ViewModelProvider(this).get(ViewBdViewModel.class);

        buttonSendKp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sharePdf();
            }
        });


        buttonDeleteKp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ContentResolver contentResolver = getContentResolver();

                //проверяем есть ли файл по этому uri
                try (Cursor cursor = contentResolver.query(uri, null, null, null, null)) {

                    //Если файл есть, то сначала удаляем файл и в случае успеха удаляем запись в БД
                    if (cursor != null && cursor.moveToFirst()) {
                        // Файл существует, можно пытаться удалить
                        int deletedRows = contentResolver.delete(uri, null, null);
                        if (deletedRows > 0) {
                            // Удаление прошло успешно
                            viewBdViewModel.deleteKp(uri.toString());
                        } else {
                            // Файл не был найден или произошла ошибка
                            Toast.makeText(ActivityMenegElementBd.this, "Не удалось удалить КП", Toast.LENGTH_SHORT).show();
                        }
                    }else{//если файл не найден то удаляем запись в БД
                        viewBdViewModel.deleteKp(uri.toString());

                    }

                } catch (Exception e) {
                    // Обработка исключений
                }

                finish();
        }});

        viewBdViewModel.getDeleteEvent().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if(aBoolean){
                    Toast.makeText(ActivityMenegElementBd.this, "Ком. предложение удалено", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void sharePdf(){

        Intent share = new Intent(Intent.ACTION_SEND);
        share.setType("application/pdf");
        share.putExtra(Intent.EXTRA_STREAM, uri);
        startActivity(Intent.createChooser(share, "Share PDF"));

    }
}