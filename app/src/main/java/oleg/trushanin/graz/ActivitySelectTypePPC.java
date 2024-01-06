package oleg.trushanin.graz;

import static oleg.trushanin.graz.InitViewPosition.resetValuesOneScreen;
import static oleg.trushanin.graz.InitViewPosition.resetValuesTwoScreen;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class ActivitySelectTypePPC extends AppCompatActivity {


    private boolean isDataChanged = false; // для отслеживания изменений первого экрана при возврате на него из второго
    RadioGroup radioGroupFirstType;
    RadioButton radioButtonFirstCirclePPC;
    RadioButton radioButtonFirstCirclePPCA;
    RadioButton radioButtonFirstCasePPC;
    RadioButton radioButtonFirstChemPPC;
    RadioButton radioButtonFirstPPCB;

    RadioGroup radioGroupFirstAxes;
    RadioButton radioButtonFirstAxes3;
    RadioButton radioButtonFirstAxes4;
    RadioButton radioButtonFirstAxes3_1;

    RadioGroup radioGroupFirstAxesPPCcircle;

    RadioButton radioButtonFirstAxes3PPCcircle;
    RadioButton radioButtonFirstAxes4PPCcircle;

    RadioGroup radioGroupFirstAxesSpringPneum;

    RadioButton radioButtonFirstAxesPneum;
    RadioButton radioButtonFirstPneum2;
    RadioButton radioButtonFirstKomb;

    Spinner spinerFirst;

    FloatingActionButton buttonFirstHome;
    FloatingActionButton buttonFirstNext;

    String[] spinner_FirstCirclePPC_3;
    String[] spinner_FirstCirclePPC_4;
    String[] spinner_FirstCirclePPCA_3;
    String[] spinner_FirstCirclePPCA_4;
    String[] spinner_FirstCirclePPCA_3_1;
    String[] spinner_FirstCasePPC_pneum;
    String[] spinner_FirstCasePPC_pneum2;
    String[] spinner_FirstCasePPC_komb;
    String[] spinner_FirstChemPPC;
    String[] spinner_FirstPPCB_3;
    String[] spinner_FirstPPCB_4;
    String[] spinner_FirstPPCB_3_1;

    private ArrayAdapter<String> spinnerAdapter;

    private InitViewPosition initViewPosition;

    SelectTypePPCViewModel selectTypePPCViewModel;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_type_ppc);
        initView();
        inintArraySpiner();


        spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item); // создаем спинер адаптер
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinerFirst.setAdapter(spinnerAdapter);

        loadFirstPosition();

        buttonFirstNext.setOnClickListener(new View.OnClickListener() {

                        @Override
            public void onClick(View view) {
                            if (checkAxesGroup()) {// проверяем заполнены ли все необходимые поля

                                writeVariableType();
                                if(initViewPosition.isDataChanged){ //если изменения были то мы приводим все данные второго экрана к 0-значению
                                    resetValuesTwoScreen();
                                }

                                offDataChanged(); // ставим флаг изменений isDataChanged в false

                                selectTypePPCViewModel.loadCostNamePPC(initViewPosition.getVolumePPC(),
                                        initViewPosition.getTypeRatio(),initViewPosition.getTypeAxel(),
                                        initViewPosition.getTypeAxelCount(),initViewPosition.getTypeMaterial(),
                                        initViewPosition.getAxelWeight());



                                selectTypePPCViewModel.getListCostNamePPC().observe(ActivitySelectTypePPC.this, new Observer<List<BazaModel>>() {
                                    @Override
                                    public void onChanged(List<BazaModel> bazaModels) {
                                        if(bazaModels != null){

                                            if(!bazaModels.isEmpty()) {// проверяем есть ли в прайсе выбранная модель
                                                initViewPosition.setModelPPC(bazaModels.get(0).getNameModelPPC());
                                                initViewPosition.setCostPPC(bazaModels.get(0).getModelPPCCost());

                                                if (initViewPosition.getCostPPC() != -1.0) { // проверяем наличие цены в прайсе
                                                    Intent intent = new Intent(ActivitySelectTypePPC.this, ActivitySelectMainOptions.class);
                                                    startActivity(intent);
                                                } else {
                                                    Toast.makeText( // вызываем сообщение если у модели нет цены
                                                            ActivitySelectTypePPC.this,
                                                            "В прайсе отсутствует цена на выбранную ППЦ",
                                                            Toast.LENGTH_SHORT
                                                    ).show();
                                                }
                                            }else {// вызываем сообщение если модели нет
                                                Toast.makeText(
                                                        ActivitySelectTypePPC.this,
                                                        "В прайсе отсутствует выбранная модель",
                                                        Toast.LENGTH_SHORT
                                                ).show();
                                            }
                                        }
                                    }
                                });


                        }else{
                                Toast.makeText(//вызываем если поле подвески не заполнено
                                        ActivitySelectTypePPC.this,
                                        "Выберите тип подвески",
                                        Toast.LENGTH_SHORT
                                ).show();
                            }
            }
        });



        CompoundButton.OnCheckedChangeListener listener = new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                updateVisibility();
                updateSpiner();
                resetValuesOneScreen();// при изменении на первом экране приводим кэш к 0-состоянию

                   onDataChanged();//устанавливаем флаг изменений в true по изменениям на первом экране


            }
        };

         radioButtonFirstCirclePPC.setOnCheckedChangeListener(listener);
         radioButtonFirstCirclePPCA.setOnCheckedChangeListener(listener);
         radioButtonFirstCasePPC.setOnCheckedChangeListener(listener);
         radioButtonFirstChemPPC.setOnCheckedChangeListener(listener);
         radioButtonFirstPPCB.setOnCheckedChangeListener(listener);

         radioButtonFirstAxes3.setOnCheckedChangeListener(listener);
         radioButtonFirstAxes4.setOnCheckedChangeListener(listener);
         radioButtonFirstAxes3_1.setOnCheckedChangeListener(listener);

        radioButtonFirstAxes3PPCcircle.setOnCheckedChangeListener(listener);
        radioButtonFirstAxes4PPCcircle.setOnCheckedChangeListener(listener);



         radioButtonFirstPneum2.setOnCheckedChangeListener(listener);
         radioButtonFirstAxesPneum.setOnCheckedChangeListener(listener);
         radioButtonFirstKomb.setOnCheckedChangeListener(listener);


        buttonFirstHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(ActivitySelectTypePPC.this,MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });


    }


    private void initView(){
         radioGroupFirstType = findViewById(R.id.radioGroupFirstType);
         radioButtonFirstCirclePPC= findViewById(R.id.radioButtonFirstCirclePPC);
         radioButtonFirstCirclePPCA = findViewById(R.id.radioButtonFirstCirclePPCA);
         radioButtonFirstCasePPC = findViewById(R.id.radioButtonFirstCasePPC);
         radioButtonFirstChemPPC = findViewById(R.id.radioButtonFirstChemPPC);
         radioButtonFirstPPCB = findViewById(R.id.radioButtonFirstPPCB);

         radioGroupFirstAxes = findViewById(R.id.radioGroupFirstAxes);
         radioButtonFirstAxes3 = findViewById(R.id.radioButtonFirstAxes3);
         radioButtonFirstAxes4 = findViewById(R.id.radioButtonFirstAxes4);
         radioButtonFirstAxes3_1  = findViewById(R.id.radioButtonFirstAxes3_1);

        radioGroupFirstAxesPPCcircle = findViewById(R.id.radioGroupFirstAxesPPCcircle);
        radioButtonFirstAxes3PPCcircle = findViewById(R.id.radioButtonFirstAxes3PPCcircle);
        radioButtonFirstAxes4PPCcircle = findViewById(R.id.radioButtonFirstAxes4PPCcircle);

         radioGroupFirstAxesSpringPneum = findViewById(R.id.radioGroupFirstAxesSpringPneum);
         radioButtonFirstAxesPneum = findViewById(R.id.radioButtonFirstAxesPneum);
         radioButtonFirstPneum2 = findViewById(R.id.radioButtonFirstPneum2);
         radioButtonFirstKomb = findViewById(R.id.radioButtonFirstKomb);


         spinerFirst = findViewById(R.id.spinerFirst);

         buttonFirstNext = findViewById(R.id.buttonFirstNext);
         buttonFirstHome = findViewById(R.id.buttonFirstHome);
        initViewPosition = InitViewPosition.getInstance();
        selectTypePPCViewModel = new ViewModelProvider(this).get(SelectTypePPCViewModel.class);

    }


    private void inintArraySpiner(){

         spinner_FirstCirclePPC_3 = getResources().getStringArray(R.array.spinner_FirstCirclePPC_3);
         spinner_FirstCirclePPC_4 = getResources().getStringArray(R.array.spinner_FirstCirclePPC_4);
         spinner_FirstCirclePPCA_3 = getResources().getStringArray(R.array.spinner_FirstCirclePPCA_3);
         spinner_FirstCirclePPCA_4 = getResources().getStringArray(R.array.spinner_FirstCirclePPCA_4);
         spinner_FirstCirclePPCA_3_1 = getResources().getStringArray(R.array.spinner_FirstCirclePPCA_3_1);

         spinner_FirstCasePPC_pneum = getResources().getStringArray(R.array.spinner_FirstCasePPC_pneum);
         spinner_FirstCasePPC_pneum2 = getResources().getStringArray(R.array.spinner_FirstCasePPC_pneum2);
         spinner_FirstCasePPC_komb = getResources().getStringArray(R.array.spinner_FirstCasePPC_komb);

         spinner_FirstChemPPC = getResources().getStringArray(R.array.spinner_FirstChemPPC);
         spinner_FirstPPCB_3 = getResources().getStringArray(R.array.spinner_FirstPPCB_3);
         spinner_FirstPPCB_4 = getResources().getStringArray(R.array.spinner_FirstPPCB_4);
         spinner_FirstPPCB_3_1 = getResources().getStringArray(R.array.spinner_FirstPPCB_3_1);

    }

    private void updateVisibility(){

        if(radioButtonFirstCirclePPC.isChecked()){
            radioGroupFirstAxesPPCcircle.setVisibility(View.VISIBLE);
            radioGroupFirstAxesSpringPneum.setVisibility(View.INVISIBLE);
            radioGroupFirstAxes.setVisibility(View.INVISIBLE);
            clearButtonGroupForFirst();
        }else if(radioButtonFirstCirclePPCA.isChecked()
                ||radioButtonFirstPPCB.isChecked()){
            radioGroupFirstAxes.setVisibility(View.VISIBLE);
            radioGroupFirstAxesSpringPneum.setVisibility(View.INVISIBLE);
            radioGroupFirstAxesPPCcircle.setVisibility(View.INVISIBLE);
            clearButtonGroupForFirst();
        }else if(radioButtonFirstCasePPC.isChecked()){
            radioGroupFirstAxes.setVisibility(View.INVISIBLE);
            radioGroupFirstAxesPPCcircle.setVisibility(View.INVISIBLE);
            radioGroupFirstAxesSpringPneum.setVisibility(View.VISIBLE);
            clearButtonGroupForFirst();
        }else if(radioButtonFirstChemPPC.isChecked()){
            radioGroupFirstAxes.setVisibility(View.INVISIBLE);
            radioGroupFirstAxesSpringPneum.setVisibility(View.INVISIBLE);
            radioGroupFirstAxesPPCcircle.setVisibility(View.INVISIBLE);
            clearButtonGroupForFirst();

        }




    }

    private void updateSpiner(){

        if(radioButtonFirstCirclePPC.isChecked()
                &&radioButtonFirstAxes3PPCcircle.isChecked()){
            spinnerAdapter.clear();
            spinnerAdapter.addAll(spinner_FirstCirclePPC_3);
        }else if(radioButtonFirstCirclePPC.isChecked()
                &&radioButtonFirstAxes4PPCcircle.isChecked()){
            spinnerAdapter.clear();
            spinnerAdapter.addAll(spinner_FirstCirclePPC_4);
        }else if(radioButtonFirstCirclePPCA.isChecked()
                &&radioButtonFirstAxes3.isChecked()){
            spinnerAdapter.clear();
            spinnerAdapter.addAll(spinner_FirstCirclePPCA_3);
        }else if(radioButtonFirstCirclePPCA.isChecked()
                &&radioButtonFirstAxes4.isChecked()){
            spinnerAdapter.clear();
            spinnerAdapter.addAll(spinner_FirstCirclePPCA_4);
        }else if(radioButtonFirstCasePPC.isChecked()
                &&radioButtonFirstAxesPneum.isChecked()){
            spinnerAdapter.clear();
            spinnerAdapter.addAll(spinner_FirstCasePPC_pneum);
        } else if (radioButtonFirstCasePPC.isChecked()
                &&radioButtonFirstPneum2.isChecked()) {
            spinnerAdapter.clear();
            spinnerAdapter.addAll(spinner_FirstCasePPC_pneum2);
        } else if(radioButtonFirstCasePPC.isChecked()
                &&radioButtonFirstKomb.isChecked()){
            spinnerAdapter.clear();
            spinnerAdapter.addAll(spinner_FirstCasePPC_komb);
        }else if (radioButtonFirstChemPPC.isChecked()) {
            spinnerAdapter.clear();
            spinnerAdapter.addAll(spinner_FirstChemPPC);
        } else if (radioButtonFirstPPCB.isChecked()
                &&radioButtonFirstAxes3.isChecked()) {
            spinnerAdapter.clear();
            spinnerAdapter.addAll(spinner_FirstPPCB_3);
        }else if (radioButtonFirstPPCB.isChecked()
                &&radioButtonFirstAxes4.isChecked()) {
            spinnerAdapter.clear();
            spinnerAdapter.addAll(spinner_FirstPPCB_4);
        }else if(radioButtonFirstCirclePPCA.isChecked()
                &&radioButtonFirstAxes3_1.isChecked()){
            spinnerAdapter.clear();
            spinnerAdapter.addAll(spinner_FirstCirclePPCA_3_1);
        }else if (radioButtonFirstPPCB.isChecked()
                &&radioButtonFirstAxes3_1.isChecked()) {
            spinnerAdapter.clear();
            spinnerAdapter.addAll(spinner_FirstPPCB_3_1);
        }

        spinnerAdapter.notifyDataSetChanged();

    }

    private void loadFirstPosition(){
        radioButtonFirstCirclePPC.setChecked(true);

        if(radioButtonFirstCirclePPC.isChecked()){
            radioGroupFirstAxesPPCcircle.setVisibility(View.VISIBLE);
        }

        if(radioButtonFirstCirclePPC.isChecked()
                &&radioButtonFirstAxes3PPCcircle.isChecked()){
            spinnerAdapter.clear();
            spinnerAdapter.addAll(spinner_FirstCirclePPC_3);};
    }



    private void clearButtonGroupForFirst(){
        radioGroupFirstAxesPPCcircle.clearCheck();
        radioGroupFirstAxesSpringPneum.clearCheck();
        radioGroupFirstAxes.clearCheck();

    }

    public void initFirstPosition(RadioGroup radioGroup){

        RadioButton radioButtonChek = (RadioButton) radioGroup.getChildAt(0);

        radioButtonChek.setChecked(true);


    }

    private boolean checkAxesGroup(){//проверяем выбран ли определенный тип подвески

        if(radioGroupFirstAxes.getCheckedRadioButtonId()==-1
            &&radioGroupFirstAxesPPCcircle.getCheckedRadioButtonId()==-1
            &&radioGroupFirstAxesSpringPneum.getCheckedRadioButtonId()==-1
            &&!radioButtonFirstChemPPC.isChecked()){
            return false;

        }

        return true;
    }

    private void writeVariableType(){

        if (radioButtonFirstAxes4PPCcircle.isChecked()) {
            initViewPosition.setTypeAxelCount("4 оси");
            initViewPosition.setTypeAxel("Пневматическая");


        } else if (radioButtonFirstAxes4.isChecked()) {
            initViewPosition.setTypeAxelCount("4 оси");
            initViewPosition.setTypeAxel("Пневматическая");

        } else if (radioButtonFirstAxes3_1.isChecked()) {
            initViewPosition.setTypeAxelCount("3+1 ось");
            initViewPosition.setTypeAxel("Пневматическая");
        }else {
            initViewPosition.setTypeAxelCount("3 оси");
            if (radioButtonFirstPneum2.isChecked()) {
                initViewPosition.setTypeAxel("Пневматическая (2 ската)");
            } else if (radioButtonFirstKomb.isChecked()) {
                initViewPosition.setTypeAxel("Комбинированная (1 ось пневматическая, 2-3 рессора)");

            } else {
                initViewPosition.setTypeAxel("Пневматическая");
            }
        }
        if(spinerFirst.getSelectedItem().toString().charAt(2) == '.'){ // Берем объем из спинера, переводя стринг в дабл
            initViewPosition.setVolumePPC(Double.parseDouble(spinerFirst.getSelectedItem().toString().substring(0, 4)));
        }else{
            initViewPosition.setVolumePPC(Double.parseDouble(spinerFirst.getSelectedItem().toString().substring(0, 2)));
        }



        if(radioButtonFirstCirclePPC.isChecked()){
                initViewPosition.setTypeRatio("Круг с усеченной передней частью");
                initViewPosition.setTypeMaterial("Сталь 09Г2С (4 мм)");
                initViewPosition.setType("ППЦС круг");
                initViewPosition.setTermalChick("нет");

            } else if(radioButtonFirstCirclePPCA.isChecked()){
                initViewPosition.setTypeRatio("Круг с усеченной передней частью");
                initViewPosition.setTypeMaterial("Алюминиевый сплав");
                initViewPosition.setType("ППЦА круг");
                initViewPosition.setTermalChick("нет");

            }else if (radioButtonFirstCasePPC.isChecked()) {
                initViewPosition.setTypeRatio("Чемодан");
                if(initViewPosition.getVolumePPC()>=35){
                    initViewPosition.setTypeMaterial("Сталь 09Г2С (5 мм)");
                }else {
                    initViewPosition.setTypeMaterial("Сталь 09Г2С (4 мм)");
                }
                initViewPosition.setType("ППЦЧемодан");
                initViewPosition.setTermalChick("нет");
            if(initViewPosition.getVolumePPC()>=35){
                initViewPosition.setAxelWeight("12 тонн");
            }

            } else if (radioButtonFirstChemPPC.isChecked()) {
                initViewPosition.setTypeRatio("Чемотасс");
                initViewPosition.setTypeMaterial("Сталь 09Г2С (4 мм)");
                initViewPosition.setType("ППЦЧемотасс");
                initViewPosition.setTermalChick("нет");


            }else if (radioButtonFirstPPCB.isChecked()) {
                initViewPosition.setTypeRatio("Круг");
                initViewPosition.setTypeMaterial("Сталь 09Г2С (4 мм)");
                initViewPosition.setType("ППЦБ");
                initViewPosition.setTermalChick("150 мм");

            }




        }



    @Override
    protected void onResume() {
        super.onResume();

    }

    public void onDataChanged() {
        //isDataChanged = true;
        initViewPosition.setDataChanged(true);
    }

    public void offDataChanged() {
        //isDataChanged = false;
        initViewPosition.setDataChanged(false);
    }

}



