package oleg.trushanin.graz;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import java.text.NumberFormat;
import java.util.Locale;

public class ActivityKartaOkraski extends AppCompatActivity {

    TextView textViewPriceMkm;
    TextView textViewPriceColorCount;
    TextView textViewPriceLevelDifficult;
    TextView textViewSummPrices;

    SwitchCompat switchMkm;
    Spinner spinnerColorCount;
    Spinner spinnerLevelDifficult;

    Button buttonSaveKO;

    InitViewPosition initViewPosition;

    float priceMkm;
    float priceColor;
    float priceDiff;

    float priceMkmShared;
    float priceColorShared;
    float priceDiffShared;

    private String [] spiner_KOLevelDifficult;
    private String [] spiner_KOLevelDifficultNull;

    private ArrayAdapter<String> adapterLevelDifficult;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_karta_okraski);

        SharedPreferences sh = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        priceMkmShared = sh.getFloat("180 мкм", 0);
        priceColorShared = sh.getFloat("1 цвет", 0);
        priceDiffShared = sh.getFloat("коэф", 0);


        initViewsAndAdapter();

        loadFirstPosition();

        switchMkm.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if(b){
                    initViewPosition.setThicknessLkpKo("180 мкм");
                    textViewPriceMkm.setVisibility(View.VISIBLE);
                    priceMkm += priceMkmShared;
                    String s = "Итого: " + formatedPrice(priceMkm);
                    textViewSummPrices.setText(s);
                    totalPrice();
                }else{
                    initViewPosition.setThicknessLkpKo("");
                    textViewPriceMkm.setVisibility(View.INVISIBLE);
                    priceMkm -= priceMkmShared;
                    String s = "Итого: " + formatedPrice(priceMkm);
                    textViewSummPrices.setText(s);
                    totalPrice();
                }

            }
        });

        spinnerColorCount.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                priceColor = priceColorShared*l;
                String s = formatedPrice(priceColor);
                textViewPriceColorCount.setText(s);

                initViewPosition.setColorCountKo((int)(l+1));

                if(l>=1){ // меняем адаптеры сложности в зависимости от кол-ва цветов

                    adapterLevelDifficult.clear();
                    adapterLevelDifficult.addAll(spiner_KOLevelDifficult);
                    if(initViewPosition.getLevelDifficultKo()==0) {// добавляем стоимость за сложность при более 2 цветах и коэф 1 (позиция в спинере 0)

                        priceDiff = priceDiffShared;
                        String s1 = formatedPrice(priceDiff);
                        textViewPriceLevelDifficult.setText(s1);


                    }else{
                        spinnerLevelDifficult.setSelection(initViewPosition.getLevelDifficultKo());
                    }


                }else {// если 1 цвет то зануляем сложность
                    adapterLevelDifficult.clear();
                    adapterLevelDifficult.addAll(spiner_KOLevelDifficultNull);
                    initViewPosition.setLevelDifficultKo(0);
                    priceDiff = 0;
                    String s1 = formatedPrice(priceDiff);
                    textViewPriceLevelDifficult.setText(s1);
                }

                totalPrice();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {



            }
        });

        spinnerLevelDifficult.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(initViewPosition.getColorCountKo()>1) { //прибавляем стоимость по сложности в зависимости от цветов
                    priceDiff = priceDiffShared * (l+1);
                }
                String s = formatedPrice(priceDiff);
                textViewPriceLevelDifficult.setText(s);
                totalPrice();
                initViewPosition.setLevelDifficultKo((int)(l));

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        buttonSaveKO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });



    }


    public String formatedPrice(float price) { // формат стоимости с пробелом между разрядами

        NumberFormat formatter = NumberFormat.getNumberInstance(Locale.FRANCE);
        formatter.setMinimumFractionDigits(2);
        formatter.setMaximumFractionDigits(2);
        return formatter.format(price);
    }
    public void initViewsAndAdapter(){

         textViewPriceMkm = findViewById(R.id.textViewPriceMkm);
         textViewPriceColorCount = findViewById(R.id.textViewPriceColorCount);
         textViewPriceLevelDifficult = findViewById(R.id.textViewPriceLevelDifficult);
         textViewSummPrices = findViewById(R.id.textViewSummPrices);

         switchMkm = findViewById(R.id.switchMkm);
         spinnerColorCount = findViewById(R.id.spinnerColorCount);
         spinnerLevelDifficult = findViewById(R.id.spinnerLevelDifficult);

         spiner_KOLevelDifficult = getResources().getStringArray(R.array.spiner_KOLevelDifficult);
         spiner_KOLevelDifficultNull = getResources().getStringArray(R.array.spiner_KOLevelDifficultNull);

         buttonSaveKO = findViewById(R.id.buttonSaveKO);

         initViewPosition = InitViewPosition.getInstance();
         priceMkm = 0;
         priceColor = 0;
         priceDiff = 0;

        adapterLevelDifficult = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item); // создаем спинер адаптер
        adapterLevelDifficult.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerLevelDifficult.setAdapter(adapterLevelDifficult);






    }

    public void loadFirstPosition(){

        if(initViewPosition.getThicknessLkpKo().equals("180 мкм")){
            switchMkm.setChecked(true);
            textViewPriceMkm.setVisibility(View.VISIBLE);
            priceMkm = priceMkmShared;
        }



        spinnerColorCount.setSelection(initViewPosition.getColorCountKo()-1);

        if(initViewPosition.getLevelDifficultKo() == 0){

            adapterLevelDifficult.clear();
            adapterLevelDifficult.addAll(spiner_KOLevelDifficultNull);



        }else{
            adapterLevelDifficult.clear();
            adapterLevelDifficult.addAll(spiner_KOLevelDifficult);

            spinnerLevelDifficult.setSelection(initViewPosition.getLevelDifficultKo()-1);


        }



        String s = "Итого :" + formatedPrice(initViewPosition.getCostKoPPC());

        textViewSummPrices.setText(s);


    }

    public void totalPrice(){

        float total = priceMkm + priceColor + priceDiff;

        String result = "Итого: " + formatedPrice(total);

        textViewSummPrices.setText(result);

        initViewPosition.setCostKoPPC(total);

    }




}