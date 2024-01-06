package oleg.trushanin.graz;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.slider.Slider;

import java.text.NumberFormat;
import java.util.Locale;

public class ActivityRequisitesKp extends AppCompatActivity {

    InitViewPosition initViewPosition;
    TextView textTotalPrice;
    EditText editPlusPrice;
    EditText editPrepaidPpc;
    CheckBox checkBoxTimePay;
    EditText editTimePayDay;
    CheckBox checkBoxDelivery;
    EditText editDeliveryAdress;
    EditText editDeliveryCost;
    EditText editDeliveryTime;
    EditText editClient;
    EditText editDayValidity;
    FloatingActionButton buttonRequisHome;
    FloatingActionButton  buttonRequisNext;

    TextView textSecondDeliveryCost;
    TextView textSecondDelivery;
    TextView textSecondTimePay;


    Slider sliderPrepaid;
    private boolean isUserTextChange = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requisites_kp);
        initView();
        loadFirstPosition();


        checkBoxTimePay.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    textSecondTimePay.setVisibility(View.VISIBLE);
                    editTimePayDay.setVisibility(View.VISIBLE);
                }else {
                    textSecondTimePay.setVisibility(View.GONE);
                    editTimePayDay.setVisibility(View.GONE);
                    editTimePayDay.setText("");
                }
            }
        });

        checkBoxDelivery.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    editDeliveryAdress.setVisibility(View.VISIBLE);
                    editDeliveryCost.setVisibility(View.VISIBLE);
                    textSecondDeliveryCost.setVisibility(View.VISIBLE);
                    textSecondDelivery.setVisibility(View.VISIBLE);
                }else{
                    editDeliveryAdress.setVisibility(View.GONE);
                    editDeliveryCost.setVisibility(View.GONE);
                    textSecondDeliveryCost.setVisibility(View.GONE);
                    textSecondDelivery.setVisibility(View.GONE);
                    editDeliveryAdress.setText("");
                    editDeliveryCost.setText("");

                }
            }
        });


        sliderPrepaid.addOnChangeListener(new Slider.OnChangeListener() {
            @Override
            public void onValueChange(@NonNull Slider slider, float value, boolean fromUser) {
                if(fromUser){
                    editPrepaidPpc.setText(String.valueOf((int)value));
                }
            }
        });

        editPrepaidPpc.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (!isUserTextChange) return;

                if (editable.toString().isEmpty()) {
                    // Если поле пустое, ничего не делаем
                    return;
                }

                int value;
                try {
                    value = Integer.parseInt(editable.toString());
                } catch (NumberFormatException e) {
                    // Если введенное значение не является числом, очищаем поле
                    isUserTextChange = false;
                    editPrepaidPpc.setText("");
                    isUserTextChange = true;
                    return;
                }

                if (value > sliderPrepaid.getValueTo()) {
                    value = (int) sliderPrepaid.getValueTo();
                    isUserTextChange = false;
                    editPrepaidPpc.setText(String.valueOf(value));
                    sliderPrepaid.setValue(value);
                    isUserTextChange = true;
                } else if (value < sliderPrepaid.getValueFrom()) {
                    isUserTextChange = false;
                    editPrepaidPpc.setText(String.valueOf((int) sliderPrepaid.getValueFrom()));
                    isUserTextChange = true;
                } else {
                    sliderPrepaid.setValue(value);
                }

                editPrepaidPpc.setSelection(editPrepaidPpc.getText().length());
            }

        });






        buttonRequisNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                writeVariableType();

                if(!checkBoxTimePay.isChecked() && !checkBoxDelivery.isChecked()){

                    Intent intent = new Intent(ActivityRequisitesKp.this, ActivityCreateAndSendPDF.class);
                    startActivity(intent);
                    return;

                }

                if (checkBoxTimePay.isChecked() && editTimePayDay.getText().toString().isEmpty()) {

                    Toast.makeText(ActivityRequisitesKp.this,
                            "Не заполнена отсрочка", Toast.LENGTH_SHORT).show();

                } else {
                    if (checkBoxDelivery.isChecked() & (editDeliveryAdress.getText().toString().isEmpty()
                            || editDeliveryCost.getText().toString().isEmpty())) {

                            Toast.makeText(ActivityRequisitesKp.this,
                                    "Заполните поля достваки", Toast.LENGTH_SHORT).show();
                        } else {
                            Intent intent = new Intent(ActivityRequisitesKp.this, ActivityCreateAndSendPDF.class);
                            startActivity(intent);
                        }
                    }
                }
            });

        buttonRequisHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivityRequisitesKp.this,MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });

        //переопределяем бэк
        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled */) { // при нажатии кнопки назад происходит сохранение выбранных значений
            @Override
            public void handleOnBackPressed() {
                // Вызовите здесь ваш метод
                writeVariableType();
                finish();
            }
        };
        getOnBackPressedDispatcher().addCallback(this, callback);



    }

    private void loadFirstPosition() {
        NumberFormat formatter = NumberFormat.getNumberInstance(Locale.FRANCE);
        formatter.setMinimumFractionDigits(2);
        formatter.setMaximumFractionDigits(2);
        String formattedCost = formatter.format(initViewPosition.getTotalPrice());
        formattedCost += " руб.";
        textTotalPrice.setText("Стоимость: ".concat(formattedCost));

        if(initViewPosition.getPlusPricePpc()!=0) {
            editPlusPrice.setText(String.valueOf(initViewPosition.getPlusPricePpc()));
        }

        editPrepaidPpc.setText(String.valueOf(initViewPosition.getPrepaidPpc()));
        sliderPrepaid.setValue(initViewPosition.getPrepaidPpc());

        checkBoxTimePay.setChecked(initViewPosition.isTimePay());

        if(initViewPosition.getTimePayDay()>0) {
            editTimePayDay.setText(String.valueOf(initViewPosition.getTimePayDay()));
        }

        checkBoxDelivery.setChecked(initViewPosition.isDeliveryPpc());

        editDeliveryAdress.setText(initViewPosition.getDeliveryAdress());

        if(initViewPosition.getDeliveryCost()>0) {
            editDeliveryCost.setText(String.valueOf(initViewPosition.getDeliveryCost()));
        }

        if(initViewPosition.getDeliveryTime()>0){

            editDeliveryTime.setText(String.valueOf(initViewPosition.getDeliveryTime()));
        }

        editClient.setText(initViewPosition.getClient());

        if(initViewPosition.getDayValidity()>0){
            editDayValidity.setText(String.valueOf(initViewPosition.getDayValidity()));
        }

        if(initViewPosition.isTimePay()){
            textSecondTimePay.setVisibility(View.VISIBLE);
            editTimePayDay.setVisibility(View.VISIBLE);
        }

        if(initViewPosition.isDeliveryPpc()){
            editDeliveryAdress.setVisibility(View.VISIBLE);
            editDeliveryCost.setVisibility(View.VISIBLE);
            textSecondDeliveryCost.setVisibility(View.VISIBLE);
            textSecondDelivery.setVisibility(View.VISIBLE);
        }


    }


    private void initView() {
        initViewPosition = InitViewPosition.getInstance();
        textTotalPrice = findViewById(R.id.textTotalPrice);
        editPlusPrice = findViewById(R.id.editPlusPrice);
        editPrepaidPpc = findViewById(R.id.editPrepaidPpc);
        checkBoxTimePay = findViewById(R.id.checkBoxTimePay);
        editTimePayDay = findViewById(R.id.editTimePayDay);
        checkBoxDelivery = findViewById(R.id.checkBoxDelivery);
        editDeliveryAdress = findViewById(R.id.editDeliveryAdress);
        editDeliveryCost = findViewById(R.id.editDeliveryCost);
        editDeliveryTime = findViewById(R.id.editDeliveryTime);
        editClient = findViewById(R.id.editClient);
        editDayValidity = findViewById(R.id.editDayValidity);
        buttonRequisHome = findViewById(R.id.buttonRequisHome);
        buttonRequisNext = findViewById(R.id.buttonRequisNext);

        textSecondDeliveryCost = findViewById(R.id.textSecondDeliveryCost);
        textSecondDelivery = findViewById(R.id.textSecondDelivery);
        textSecondTimePay = findViewById(R.id.textSecondTimePay);

        sliderPrepaid = findViewById(R.id.sliderPrepaid);

    }

    private void writeVariableType(){

        //записываем значение добавки к цене
        if(!editPlusPrice.getText().toString().isEmpty()) {
            initViewPosition.setPlusPricePpc(Float.parseFloat(editPlusPrice.getText().toString()));
        }else {
            initViewPosition.setPlusPricePpc(0);
        }
        // записываем размер аванса
        if(!editPrepaidPpc.getText().toString().isEmpty()) {
            initViewPosition.setPrepaidPpc(Integer.parseInt(editPrepaidPpc.getText().toString()));
        }else {
            initViewPosition.setPrepaidPpc(0);
        }
        //нужна ли отсрочка
        initViewPosition.setTimePay(checkBoxTimePay.isChecked());

        //размер отсрочки в днях
        if(!editTimePayDay.getText().toString().isEmpty()) {
            initViewPosition.setTimePayDay(Integer.parseInt(editTimePayDay.getText().toString()));
        }else{
            initViewPosition.setTimePayDay(0);
        }
        //нужна ли доставка
        initViewPosition.setDeliveryPpc(checkBoxDelivery.isChecked());

        //адрес доставки
        initViewPosition.setDeliveryAdress(editDeliveryAdress.getText().toString());

        //стоимость доставки
        if(!editDeliveryCost.getText().toString().isEmpty()) {
            initViewPosition.setDeliveryCost(Float.parseFloat(editDeliveryCost.getText().toString()));
        }else{
            initViewPosition.setDeliveryCost(0);
        }
        // срок поставки
        if(!editDeliveryTime.getText().toString().isEmpty()){
            initViewPosition.setDeliveryTime(Integer.parseInt(editDeliveryTime.getText().toString()));
        }else {
            initViewPosition.setDeliveryTime(0);
        }

        //срок действия КП
        if(!editDayValidity.getText().toString().isEmpty()){
            initViewPosition.setDayValidity(Integer.parseInt(editDayValidity.getText().toString()));
        }else {
            initViewPosition.setDayValidity(0);
        }

        //Клиент
        initViewPosition.setClient(editClient.getText().toString());





    }
}