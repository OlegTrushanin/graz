package oleg.trushanin.graz;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

public class ActivitySelectMainOptions extends AppCompatActivity {

    private boolean isUpdatingRGET = false;

    Spinner spinerSecondCountCapsule;
    Spinner spinerSecondTypeGorlCovers;
    Spinner spinerSecondBottomValve;
    Spinner spinerSecondMarkTire;
    Spinner spinerSecondNNandRec;
    Spinner spinerSecondPump;
    Spinner spinerSecondTypeCombSliv;
    Spinner spinerSecondMestoZapKol;


    RadioGroup radioGroupSecondHeightSSU;
    RadioButton radioButtonSecondHeightSSU_1150;
    RadioButton radioButtonSecondHeightSSU_1250;
    RadioButton radioButtonSecondHeightSSU_1350;



    RadioGroup radioGroupTypeGorl;
    RadioButton radioButtonSecondGorlGOST;
    RadioButton radioButtonSecondGorlEVRO;

    RadioGroup radioGroupSecondMarkaAxel;
    RadioButton radioButtonSecondAxelBPW;
    RadioButton radioButtonSecondAxelSAF;

    RadioGroup radioGroupSecondTypeBreakMeh;

    RadioButton radioButtonSecondBreakBar;

    RadioButton radioButtonSecondBreakDisk;

    RadioGroup radioGroupSecondliftAxel_3;
    RadioButton radioButtonSecondLiftAxel_3_Not;
    RadioButton radioButtonSecondLiftAxel_3_1;

    RadioGroup radioGroupSecondliftAxel_4;
    RadioButton radioButtonSecondLiftAxel_4_12;
    RadioButton radioButtonSecondLiftAxel_4_14;

    RadioGroup radioGroupSecondTypeBreakSyst;
    RadioButton radioButtonSecondBreakSyst_Wabco;
    RadioButton radioButtonSecondBreakSyst_Knorr;



    RadioGroup radioGroupSecondSystTEBS;
    RadioButton radioButtonSecondTEBS_Yes;
    RadioButton radioButtonSecondTEBS_Not;

    RadioGroup radioGroupSystemInformPPC;
    RadioButton radioButtonSystemInformPPC_Not;
    RadioButton radioButtonSystemInformPPC_Opt;
    RadioButton radioButtonSystemInformPPC_Smrt;

    RadioGroup radioGroupSecondNNandRec;
    RadioButton radioButtonNNandRec_Not;
    RadioButton radioButtonNNandRec_Yes;
    RadioButton radioButtonNNandRec_Podg;

    RadioGroup radioGroupSecondStoronaSliva;
    RadioButton radioButtonStorSliv_L;
    RadioButton radioButtonStorSliv_R;
    RadioButton radioButtonStorSliv_LR;

    RadioGroup radioGroupCountZapKol;
    RadioButton radioButtonCountZapKol_0;
    RadioButton radioButtonCountZapKol_1;
    RadioButton radioButtonCountZapKol_2;

    RadioGroup radioGroupOkraska;
    RadioButton radioButtonOkraska_TU;
    RadioButton radioButtonOkraska_KO;



    Button buttonSecondDopOtions;
    FloatingActionButton buttonFirstHome;
    FloatingActionButton buttonFirstNext;
    Button buttonSecondCalcutionKo;

    FloatingActionButton fabDetailAktViewPDF;

    TextView textViewTEBS;
    TextView textViewTypeBreakSystem;
    TextView textViewSystemInformPPC;

    TextView textViewModelPPC;

    TextView textViewCountCapsule;
    TextView textViewTypeGorlovin;
    TextView textViewTypeGorlCovers;
    TextView textViewBottomValve;
    TextView textViewNNandRec;
    TextView textViewPump;
    TextView textViewStoronaSliva;
    TextView textViewMestoZapKol;
    TextView textViewOkraska;


    EditText editTextInputSSU;

    ProgressBar progressBar;

    String[] spiner_SecondCountCapsule;
    String[] spiner_SecondTypeGorlCovers;
    String[] spiner_SecondBottomValve;
    String[] spiner_SecondBottomValvePpcb;
    String[] spiner_SecondMarkTire;
    String[] spiner_SecondNNandRec;
    String[] spiner_SecondNNandRec_Podg;
    String[] spiner_SecondNNandRec_Podg_Two;
    String[] spinner_Second_Pump;
    String[] spinner_Second_PumpPpcb;
    String[] spinner_Second_StoronaSliv;
    String[] spinner_Second_MestoZapKol_1sl;
    String[] spinner_Second_StoronaSlivNotNN;
    String[] spinner_Second_MestoZapKol_2sl;

    private InitViewPosition initViewPosition;


    private ArrayAdapter<String> adapterGorlCovers;
    private ArrayAdapter<String> adapterBottomValve;
    private ArrayAdapter<String> adapterMarkTire;
    private ArrayAdapter<String> adapterNNandRec;
    private ArrayAdapter<String> adapterPump;
    private ArrayAdapter<String> adapterStorCliv;
    private ArrayAdapter<String> adapterMestoZapKol;

    LoadViewModel loadViewModel;

    SharedPreferences sharedPreferences;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_main_options);
        loadViewModel = new ViewModelProvider(this).get(LoadViewModel.class);

        //отображаем кнопку просмотра прайса
        sharedPreferences =  getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
        boolean isPdfPrice = sharedPreferences.getBoolean("urlIs", false);
        //загружаем ссылку на прайс в кэш класс
        String urlPrice = sharedPreferences.getString("uriPrice","");


        initView();
        initViewPosition.setUriPdfPrice(Uri.parse(urlPrice));

        if(isPdfPrice){
            fabDetailAktViewPDF.setVisibility(View.VISIBLE);
        }

        informOfPPC();
        inintArraySpiner();
        loadSpinnerAdapter();
        if(initViewPosition.getType().equals("ППЦБ")){
            loadFirstPositionPpcb();
        }else{
            loadFirstPosition();
        }



        //Добавдяем в КЭШ список специальных опций
        if(initViewPosition.getType().equals("ППЦБ")){

            loadViewModel.loadSpecialDarkCashe();

            loadViewModel.getListSpecialDarkCashe().observe(this, new Observer<List<SpecialDarkOptions>>() {
                @Override
                public void onChanged(List<SpecialDarkOptions> specialDarkOptions) {
                    List<SpecialLightOptions> listCast = new ArrayList<>();
                    for(SpecialDarkOptions s: specialDarkOptions){
                        listCast.add(new SpecialLightOptions(s));
                    }
                    initViewPosition.setSpecialLightOptionsList(listCast);
                }
            });


        }else{
            loadViewModel.loadSpecialLightCashe();

            loadViewModel.getListSpecialLightCashe().observe(this, new Observer<List<SpecialLightOptions>>() {
                @Override
                public void onChanged(List<SpecialLightOptions> specialLightOptions) {
                    initViewPosition.setSpecialLightOptionsList(specialLightOptions);
                }
            });
        }



        //Выбираем высоту ССУ кнопками
        radioGroupSecondHeightSSU.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() { // радиогруппа высоту ССУ
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                String selectedText="";
                // checkedId - это идентификатор выбранной радио-кнопки
                if (checkedId != -1) {

                    RadioButton selectedRadioButton = (RadioButton) findViewById(checkedId);
                    if (selectedRadioButton != null) {
                        selectedText = selectedRadioButton.getText().toString();
                        initViewPosition.setHighSSU(selectedText);

                    }
                }
                if(!isUpdatingRGET) { //чтобы не уйти в цикл при изменении полей
                    isUpdatingRGET = true;
                    editTextInputSSU.setText(selectedText);
                    isUpdatingRGET = false;
                }

            }
        });

        // установка высоту ССУ в ручную
        editTextInputSSU.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // Этот метод вызывается перед тем, как текст будет изменен
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Этот метод вызывается для уведомления о том, что в тексте произошли изменения
            }

            @Override
            public void afterTextChanged(Editable s) {
                String str = editTextInputSSU.getText().toString();

                if(!isUpdatingRGET) { //чтобы не уйти в цикл при изменении полей
                    isUpdatingRGET = true;
                    radioGroupSecondHeightSSU.clearCheck();
                    isUpdatingRGET = false;
                }
                initViewPosition.setHighSSU(str);

            }

        });

        //устанавливаем видимость дискового тормоза в зависимости от марки осей
        radioGroupSecondMarkaAxel.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(i == R.id.radioButtonSecondAxelBPW){
                    radioButtonSecondBreakDisk.setVisibility(View.INVISIBLE);
                    radioButtonSecondBreakBar.setChecked(true);
                } else if (i == R.id.radioButtonSecondAxelSAF) {
                    radioButtonSecondBreakDisk.setVisibility(View.VISIBLE);

                }
            }
        });


        //Выбираем марку тормозной системы и в зависимости от этого скрываем системы управления подвеской
        radioGroupSecondTypeBreakSyst.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(i == R.id.radioButtonSecondBreakSyst_Knorr ){
                    radioButtonSystemInformPPC_Smrt.setVisibility(View.INVISIBLE);
                    radioButtonSystemInformPPC_Not.setChecked(true);
                } else if (i == R.id.radioButtonSecondBreakSyst_Wabco) {
                    radioButtonSystemInformPPC_Smrt.setVisibility(View.VISIBLE);
                    radioButtonSystemInformPPC_Not.setChecked(true);
                }
            }
        });

        //Выбираем наличие T-EBS и в зависимости от выбора управляем видимостью системой упр. подвеской
        radioGroupSecondSystTEBS.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(i == R.id.radioButtonSecondTEBS_Yes){
                    radioGroupSystemInformPPC.setVisibility(View.VISIBLE);
                    textViewSystemInformPPC.setVisibility(View.VISIBLE);
                    radioButtonSystemInformPPC_Not.setChecked(true);
                } else if (i == R.id.radioButtonSecondTEBS_Not) {
                    radioGroupSystemInformPPC.setVisibility(View.GONE);
                    textViewSystemInformPPC.setVisibility(View.GONE);
                    radioButtonSystemInformPPC_Not.setChecked(true);
                }
            }
        });

        //выбираем есть ли нижний налив и отображаем спинер с марками НН или подготовки
        radioGroupSecondNNandRec.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(i == R.id.radioButtonNNandRec_Not){
                    adapterNNandRec.clear();
                    spinerSecondNNandRec.setVisibility(View.GONE);
                    if (radioButtonStorSliv_LR.isChecked()) { // изменяем комбинацию слива если выбираем нижний налив
                        spinerSecondTypeCombSliv.setVisibility(View.VISIBLE);
                        adapterStorCliv.clear();
                        adapterStorCliv.addAll(spinner_Second_StoronaSlivNotNN);
                    }
                } else if (i == R.id.radioButtonNNandRec_Yes) {
                    spinerSecondNNandRec.setVisibility(View.VISIBLE);
                    adapterNNandRec.clear();
                    adapterNNandRec.addAll(spiner_SecondNNandRec);
                    if (radioButtonStorSliv_LR.isChecked()) { // изменяем комбинацию слива если выбираем нижний налив
                        spinerSecondTypeCombSliv.setVisibility(View.VISIBLE);
                        adapterStorCliv.clear();
                        adapterStorCliv.addAll(spinner_Second_StoronaSliv);
                    }
                } else if (i == R.id.radioButtonNNandRec_Podg) {
                    if(radioButtonStorSliv_LR.isChecked()) {
                        spinerSecondNNandRec.setVisibility(View.VISIBLE);
                        adapterNNandRec.clear();
                        adapterNNandRec.addAll(spiner_SecondNNandRec_Podg_Two);
                    }else{
                        spinerSecondNNandRec.setVisibility(View.VISIBLE);
                        adapterNNandRec.clear();
                        adapterNNandRec.addAll(spiner_SecondNNandRec_Podg);
                    }
                    if (radioButtonStorSliv_LR.isChecked()) { // изменяем комбинацию слива если выбираем нижний налив
                        spinerSecondTypeCombSliv.setVisibility(View.VISIBLE);
                        adapterStorCliv.clear();
                        adapterStorCliv.addAll(spinner_Second_StoronaSlivNotNN);
                    }

                }
            }
        });

        //выбираем сторону слива
        radioGroupSecondStoronaSliva.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                if(i == R.id.radioButtonStorSliv_LR&&radioButtonNNandRec_Yes.isChecked()){
                    spinerSecondTypeCombSliv.setVisibility(View.VISIBLE);
                    adapterStorCliv.clear();
                    adapterStorCliv.addAll(spinner_Second_StoronaSliv);
                    adapterMestoZapKol.clear();//устанавливаем расположение запаски
                    adapterMestoZapKol.addAll(spinner_Second_MestoZapKol_2sl);
                } else if (i == R.id.radioButtonStorSliv_LR) {
                    spinerSecondTypeCombSliv.setVisibility(View.VISIBLE);
                    adapterStorCliv.clear();
                    adapterStorCliv.addAll(spinner_Second_StoronaSlivNotNN);
                    adapterMestoZapKol.clear();//устанавливаем расположение запаски
                    adapterMestoZapKol.addAll(spinner_Second_MestoZapKol_2sl);
                    adapterNNandRec.clear();
                    adapterNNandRec.addAll(spiner_SecondNNandRec_Podg_Two);
                }else {
                    spinerSecondTypeCombSliv.setVisibility(View.GONE);
                    adapterStorCliv.clear();
                    adapterMestoZapKol.clear();
                    adapterMestoZapKol.addAll(spinner_Second_MestoZapKol_1sl);
                    adapterNNandRec.clear();
                    adapterNNandRec.addAll(spiner_SecondNNandRec_Podg);

                }
            }
        });

        //расположение запаски
        spinerSecondMestoZapKol.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (view instanceof TextView) {
                    String selectedText = ((TextView) view).getText().toString();

                    if(selectedText.equals("В тех. шкафу и заднем свесе")){
                        radioButtonCountZapKol_2.setVisibility(View.VISIBLE);
                    } else {
                        radioButtonCountZapKol_2.setVisibility(View.INVISIBLE);
                        radioButtonCountZapKol_1.setChecked(true);
                    }
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //выбор карты окраски
        radioGroupOkraska.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(i == R.id.radioButtonOkraska_TU){
                    buttonSecondCalcutionKo.setVisibility(View.GONE);
                    initViewPosition.setThicknessLkpKo("");
                    initViewPosition.setColorCountKo(1);
                    initViewPosition.setLevelDifficultKo(1);

                }else{
                    buttonSecondCalcutionKo.setVisibility(View.VISIBLE);
                }
            }
        });

        buttonSecondCalcutionKo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivitySelectMainOptions.this, ActivityKartaOkraski.class);
                startActivity(intent);
            }
        });

        buttonFirstNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                writeVariableType();
                Intent intent = new Intent(ActivitySelectMainOptions.this, ActivityDetailKP.class);
                startActivity(intent);
            }
        });


        buttonFirstHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(ActivitySelectMainOptions.this,MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                
            }
        });

        // изменяем стоимость спец. опций в зависимости от количества отсеков (при их выборе)
        spinerSecondCountCapsule.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                // Параметр 'position' указывает на выбранный элемент.
                // Параметр 'id' - это значение, возвращаемое адаптером элемента.




                //Находим все поля у которых mod == 1
                initViewPosition.getSpecialLightOptionsList().stream()
                        .filter(item -> 1 == item.getMod() || 2 == item.getMod())
                        .forEach(item -> {
                            //высчитвыаем стоимость опции в зависимости от кол-ва отсеков
                            if (item.getMod() == 1) {
                                float price = item.getPrice()*(position);
                                int identifier = item.getIdentifier();
                                // меняем значения в уже выбранной коллекции (choos)
                                Optional<SpecialLightOptions> foundIdentifaier =
                                        initViewPosition.getChoosSpecialLightOptionsList().stream()
                                                .filter(item1 -> identifier == item1.getIdentifier())
                                                .findFirst();


                                foundIdentifaier.ifPresent(item1 -> {

                                    item1.setPrice(price);
                                });
                            } else if(item.getMod() == 2) {
                                float price = item.getPrice() * (position+1);
                                int identifier = item.getIdentifier();
                                // меняем значения в уже выбранной коллекции (choos)
                                Optional<SpecialLightOptions> foundIdentifaier =
                                        initViewPosition.getChoosSpecialLightOptionsList().stream()
                                                .filter(item1 -> identifier == item1.getIdentifier())
                                                .findFirst();


                                foundIdentifaier.ifPresent(item1 -> {

                                    item1.setPrice(price);
                                });
                            }

                        });

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // Вызывается когда никакой элемент не выбран.
            }
        });


        //переопределяем бэк
        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled */) { // при нажатии кнопки назад происходит сохранение выбранных значений
            @Override
            public void handleOnBackPressed() {
                writeVariableType();
                finish();
            }
        };
        getOnBackPressedDispatcher().addCallback(this, callback);

        buttonSecondDopOtions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                writeVariableType();
                Intent intent = new Intent(ActivitySelectMainOptions.this, ActivitySpecialOptions.class);
                startActivity(intent);
                progressBar.setVisibility(View.INVISIBLE);
            }
        });

        fabDetailAktViewPDF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ActivitySelectMainOptions.this, ActivityViewPdfPrice.class));
            }
        });

    }

    private void writeVariableType(){

        // Передаем количество отсеков
        int selectedItemPosition = spinerSecondCountCapsule.getSelectedItemPosition();
        initViewPosition.setCountCapsule(selectedItemPosition+1); // сохраняем значение в кэше

        //Тип горловин ЕВРО/ГОСТ
        int selectedIdTypeGorl = radioGroupTypeGorl.getCheckedRadioButtonId(); // Находим активную кнопку
        if (selectedIdTypeGorl != -1) {
            RadioButton selectedRadioButton = (RadioButton) findViewById(selectedIdTypeGorl);
           initViewPosition.setTypeGorlovin(selectedRadioButton.getText().toString()); // передаем текст из нажатой кнопки в кэш
        }

        //Марки крышек горловин
        Object selectedIdGorlCovers = spinerSecondTypeGorlCovers.getSelectedItem(); // получаем значение стоящее в спинере
        initViewPosition.setTypeGorlCovers(selectedIdGorlCovers.toString());// устанавливаем его в переменную

        //Марки донных клапанов
        Object selectedIdBottomValve = spinerSecondBottomValve.getSelectedItem(); // получаем значение стоящее в спинере
        initViewPosition.setTypeBottomValve(selectedIdBottomValve.toString());// устанавливаем его в переменную

        //Марка осей
        int selectedIdMarkaAxel = radioGroupSecondMarkaAxel.getCheckedRadioButtonId(); // Находим активную кнопку
        if (selectedIdTypeGorl != -1) {
            RadioButton selectedRadioButton = (RadioButton) findViewById(selectedIdMarkaAxel);
            initViewPosition.setTypeMarkAxel(selectedRadioButton.getText().toString()); // передаем текст из нажатой кнопки в кэш
        }

        //Тип тормозных механизмов
        int selectedIdBreakMeh = radioGroupSecondTypeBreakMeh.getCheckedRadioButtonId(); // Находим активную кнопку
        if (selectedIdTypeGorl != -1) {
            RadioButton selectedRadioButton = (RadioButton) findViewById(selectedIdBreakMeh);
            initViewPosition.setTypeBreakMeh(selectedRadioButton.getText().toString()); // передаем текст из нажатой кнопки в кэш
        }

        //Подъемные оси, когда 3
        int selectedIdliftAxel_3 = radioGroupSecondliftAxel_3.getCheckedRadioButtonId(); // Находим активную кнопку
        if (selectedIdliftAxel_3 != -1) {
            RadioButton selectedRadioButton = (RadioButton) findViewById(selectedIdliftAxel_3);
            initViewPosition.setLiftAxel(selectedRadioButton.getText().toString()); // передаем текст из нажатой кнопки в кэш
        }

        //Подъемные оси, когда 4
        int selectedIdliftAxel_4 = radioGroupSecondliftAxel_4.getCheckedRadioButtonId(); // Находим активную кнопку
        if (selectedIdliftAxel_4 != -1) {
            RadioButton selectedRadioButton = (RadioButton) findViewById(selectedIdliftAxel_4);
            initViewPosition.setLiftAxel(selectedRadioButton.getText().toString()); // передаем текст из нажатой кнопки в кэш
        }

        //Марки шин
        Object selectedIdMarkTire = spinerSecondMarkTire.getSelectedItem(); // получаем значение стоящее в спинере
        initViewPosition.setTypeMarkTire(selectedIdMarkTire.toString());// устанавливаем его в переменную

        // Марка тормозной системы
        int selectedIdBreakSyst = radioGroupSecondTypeBreakSyst.getCheckedRadioButtonId(); // Находим активную кнопку
        if (selectedIdBreakSyst != -1) {
            RadioButton selectedRadioButton = (RadioButton) findViewById(selectedIdBreakSyst);
            initViewPosition.setTypeBreakSyst(selectedRadioButton.getText().toString()); // передаем текст из нажатой кнопки в кэш
        }
        // Система управления подвеской
        int selectedIdInformPPC = radioGroupSystemInformPPC.getCheckedRadioButtonId(); // Находим активную кнопку
        if (selectedIdInformPPC != -1) {
            RadioButton selectedRadioButton = (RadioButton) findViewById(selectedIdInformPPC);
            initViewPosition.setTypeSystemInformPPC(selectedRadioButton.getText().toString()); // передаем текст из нажатой кнопки в кэш
        }

        // Выбор T-EBS
        int selectedIdSystTEBS = radioGroupSecondSystTEBS.getCheckedRadioButtonId(); // Находим активную кнопку
        if (selectedIdSystTEBS != -1) {
            RadioButton selectedRadioButton = (RadioButton) findViewById(selectedIdSystTEBS);
            initViewPosition.setT_EBS(selectedRadioButton.getText().toString().equals("Да") ? "T-EBS" : "Нет");  // передаем текст из нажатой кнопки в кэш

        }

        // Выбор нижнего налива подготовки и его марок и вариантов
        int selectedIdNNandRec = radioGroupSecondNNandRec.getCheckedRadioButtonId(); // Находим активную кнопку
        if (selectedIdNNandRec != -1) {
            RadioButton selectedRadioButton = (RadioButton) findViewById(selectedIdNNandRec);
            initViewPosition.setIsNNandRec(selectedRadioButton.getText().toString());  // передаем текст из нажатой кнопки в кэш
            if(selectedIdNNandRec == R.id.radioButtonNNandRec_Yes){ // если НН да, устанавливаем значение марки/типа из спинера
                initViewPosition.setTypeNNandRec(spinerSecondNNandRec.getSelectedItem().toString());
            } else if (selectedIdNNandRec == R.id.radioButtonNNandRec_Podg) {
                initViewPosition.setTypeNNandRec(spinerSecondNNandRec.getSelectedItem().toString());
            }else {
                initViewPosition.setTypeNNandRec("");
            }
        }

        //Насос
        Object selectedIdPump = spinerSecondPump.getSelectedItem(); // получаем значение стоящее в спинере
        initViewPosition.setTypePump(selectedIdPump.toString());// устанавливаем его в переменную

        //Стороны слива
        int selectedIdStoronaSliva = radioGroupSecondStoronaSliva.getCheckedRadioButtonId(); // Находим активную кнопку
        if (selectedIdStoronaSliva != -1) {
            RadioButton selectedRadioButton = (RadioButton) findViewById(selectedIdStoronaSliva);
            initViewPosition.setTypeStorSliv(selectedRadioButton.getText().toString().equals("2 стороны") ? "На 2 стороны" : selectedRadioButton.getText().toString());  // передаем текст из нажатой кнопки в кэш

        }

        //комбинация слива
        if(radioButtonStorSliv_LR.isChecked()) {//записываем знач. если только выбрано 2 стороны слива
            Object selectedIdCombSliv = spinerSecondTypeCombSliv.getSelectedItem(); // получаем значение стоящее в спинере
            initViewPosition.setTypeCombSliv(selectedIdCombSliv.toString());// устанавливаем его в переменную
        }else{
            initViewPosition.setTypeCombSliv("");
        }

        //расположение запаски
        Object selectedIdMestoZapKol = spinerSecondMestoZapKol.getSelectedItem(); // получаем значение стоящее в спинере
        initViewPosition.setTypePlaceZap(selectedIdMestoZapKol.toString());// устанавливаем его в переменную

        //Количество запасок
        int selectedIdZapKol = radioGroupCountZapKol.getCheckedRadioButtonId(); // Находим активную кнопку
        if (selectedIdZapKol != -1) {
            RadioButton selectedRadioButton = (RadioButton) findViewById(selectedIdZapKol);

            switch (selectedRadioButton.getText().toString()){
                case "Нет":
                    initViewPosition.setCountZapKol(0);
                    break;
                case "1 колесо":
                    initViewPosition.setCountZapKol(1);
                    break;
                case "2 колеса":
                    initViewPosition.setCountZapKol(2);
                    break;
            }

        }

        //Окраска ППЦ
        int selectedIdOkraska = radioGroupOkraska.getCheckedRadioButtonId(); // Находим активную кнопку
        if (selectedIdOkraska != -1) {
            RadioButton selectedRadioButton = (RadioButton) findViewById(selectedIdOkraska);
            initViewPosition.setTypeOkraska(selectedRadioButton.getText().toString());  // передаем текст из нажатой кнопки в кэш

        }

    }



    private void initView(){
         spinerSecondCountCapsule = findViewById(R.id.spinerSecondCountCapsule);
         spinerSecondTypeGorlCovers = findViewById(R.id.spinerSecondTypeGorlCovers);
         spinerSecondBottomValve = findViewById(R.id.spinerSecondBottomValve);
         spinerSecondMarkTire = findViewById(R.id.spinerMarkTire);
         spinerSecondNNandRec= findViewById(R.id.spinerSecondNNandRec);
         spinerSecondPump= findViewById(R.id.spinerSecondPump);
         spinerSecondTypeCombSliv = findViewById(R.id.spinerSecondTypeCombSliv);
         spinerSecondMestoZapKol= findViewById(R.id.spinerSecondMestoZapKol);

         radioGroupSecondHeightSSU= findViewById(R.id.radioGroupSecondHeightSSU);
         radioButtonSecondHeightSSU_1150= findViewById(R.id.radioButtonSecondHeightSSU_1150);
         radioButtonSecondHeightSSU_1250= findViewById(R.id.radioButtonSecondHeightSSU_1250);
         radioButtonSecondHeightSSU_1350= findViewById(R.id.radioButtonSecondHeightSSU_1350);



         radioGroupTypeGorl= findViewById(R.id.radioGroupTypeGorl);
         radioButtonSecondGorlGOST= findViewById(R.id.radioButtonSecondGorlGOST);
         radioButtonSecondGorlEVRO= findViewById(R.id.radioButtonSecondGorlEVRO);

         radioGroupSecondMarkaAxel= findViewById(R.id.radioGroupSecondMarkaAxel);
         radioButtonSecondAxelBPW= findViewById(R.id.radioButtonSecondAxelBPW);
         radioButtonSecondAxelSAF= findViewById(R.id.radioButtonSecondAxelSAF);

        radioGroupSecondTypeBreakMeh= findViewById(R.id.radioGroupSecondTypeBreakMeh);
        radioButtonSecondBreakBar= findViewById(R.id.radioButtonSecondBreakBar);
        radioButtonSecondBreakDisk= findViewById(R.id.radioButtonSecondBreakDisk);

         radioGroupSecondliftAxel_3= findViewById(R.id.radioGroupSecondliftAxel_3);
         radioButtonSecondLiftAxel_3_Not= findViewById(R.id.radioButtonSecondLiftAxel_3_Not);
         radioButtonSecondLiftAxel_3_1= findViewById(R.id.radioButtonSecondLiftAxel_3_1);

         radioGroupSecondliftAxel_4= findViewById(R.id.radioGroupSecondliftAxel_4);
         radioButtonSecondLiftAxel_4_12 = findViewById(R.id.radioButtonSecondLiftAxel_4_12);
         radioButtonSecondLiftAxel_4_14 = findViewById(R.id.radioButtonSecondLiftAxel_4_14);

         radioGroupSecondTypeBreakSyst= findViewById(R.id.radioGroupSecondTypeBreakSyst);
         radioButtonSecondBreakSyst_Wabco= findViewById(R.id.radioButtonSecondBreakSyst_Wabco);
         radioButtonSecondBreakSyst_Knorr= findViewById(R.id.radioButtonSecondBreakSyst_Knorr);



         radioGroupSecondSystTEBS= findViewById(R.id.radioGroupSecondSystTEBS);
         radioButtonSecondTEBS_Yes= findViewById(R.id.radioButtonSecondTEBS_Yes);
         radioButtonSecondTEBS_Not= findViewById(R.id.radioButtonSecondTEBS_Not);

        radioGroupSystemInformPPC = findViewById(R.id.radioGroupSystemInformPPC);
        radioButtonSystemInformPPC_Not = findViewById(R.id.radioButtonSystemInformPPC_Not);
        radioButtonSystemInformPPC_Opt = findViewById(R.id.radioButtonSystemInformPPC_Opt);
        radioButtonSystemInformPPC_Smrt = findViewById(R.id.radioButtonSystemInformPPC_Smrt);


         radioGroupSecondNNandRec= findViewById(R.id.radioGroupSecondNNandRec);
         radioButtonNNandRec_Not= findViewById(R.id.radioButtonNNandRec_Not);
         radioButtonNNandRec_Yes= findViewById(R.id.radioButtonNNandRec_Yes);
         radioButtonNNandRec_Podg= findViewById(R.id.radioButtonNNandRec_Podg);

         radioGroupSecondStoronaSliva= findViewById(R.id.radioGroupSecondStoronaSliva);
         radioButtonStorSliv_L= findViewById(R.id.radioButtonStorSliv_L);
         radioButtonStorSliv_R= findViewById(R.id.radioButtonStorSliv_R);
         radioButtonStorSliv_LR= findViewById(R.id.radioButtonStorSliv_LR);

         radioGroupCountZapKol= findViewById(R.id.radioGroupCountZapKol);
         radioButtonCountZapKol_0= findViewById(R.id.radioButtonCountZapKol_0);
         radioButtonCountZapKol_1= findViewById(R.id.radioButtonCountZapKol_1);
         radioButtonCountZapKol_2= findViewById(R.id.radioButtonCountZapKol_2);

         radioGroupOkraska= findViewById(R.id.radioGroupOkraska);
         radioButtonOkraska_TU= findViewById(R.id.radioButtonOkraska_TU);
         radioButtonOkraska_KO= findViewById(R.id.radioButtonOkraska_KO);

         buttonSecondDopOtions= findViewById(R.id.buttonSecondDopOtions);
         buttonFirstHome= findViewById(R.id.buttonFirstHome);
         buttonFirstNext= findViewById(R.id.buttonFirstNext);
        buttonSecondCalcutionKo= findViewById(R.id.buttonSecondCalcutionKo);
        fabDetailAktViewPDF= findViewById(R.id.fabDetailAktViewPDF);

        textViewTEBS= findViewById(R.id.textViewTEBS);
        textViewTypeBreakSystem= findViewById(R.id.textViewTypeBreakSystem);
        editTextInputSSU= findViewById(R.id.editTextInputSSU);
        initViewPosition = InitViewPosition.getInstance();
        textViewSystemInformPPC = findViewById(R.id.textViewSystemInformPPC);
        textViewModelPPC = findViewById(R.id.textViewModelPPC);

        progressBar =findViewById(R.id.progressBar);

        textViewCountCapsule = findViewById(R.id.textViewCountCapsule);
        textViewTypeGorlovin = findViewById(R.id.textViewTypeGorlovin);
        textViewTypeGorlCovers = findViewById(R.id.textViewTypeGorlCovers);
        textViewBottomValve = findViewById(R.id.textViewBottomValve);
        textViewNNandRec = findViewById(R.id.textViewNNandRec);
        textViewPump = findViewById(R.id.textViewPump);
        textViewStoronaSliva = findViewById(R.id.textViewStoronaSliva);
        textViewMestoZapKol = findViewById(R.id.textViewMestoZapKol);
        textViewOkraska = findViewById(R.id.textViewOkraska);
    }

    private void inintArraySpiner(){
        spiner_SecondCountCapsule = getResources().getStringArray(R.array.spiner_SecondCountCapsule);
        spiner_SecondTypeGorlCovers = getResources().getStringArray(R.array.spiner_SecondTypeGorlCovers);
        spiner_SecondBottomValve = getResources().getStringArray(R.array.spiner_SecondBottomValve);
        spiner_SecondBottomValvePpcb = getResources().getStringArray(R.array.spiner_SecondBottomValvePpcb);
        spiner_SecondMarkTire = getResources().getStringArray(R.array.spiner_MarkTire);
        spiner_SecondNNandRec = getResources().getStringArray(R.array.spiner_SecondNNandRec);
        spiner_SecondNNandRec_Podg = getResources().getStringArray(R.array.spiner_SecondNNandRec_Podg);
        spiner_SecondNNandRec_Podg_Two = getResources().getStringArray(R.array.spiner_SecondNNandRec_Podg_Two);
        spinner_Second_Pump = getResources().getStringArray(R.array.spinner_Second_Pump);
        spinner_Second_PumpPpcb = getResources().getStringArray(R.array.spinner_Second_PumpPpcb);
        spinner_Second_StoronaSliv = getResources().getStringArray(R.array.spinner_Second_StoronaSliv);
        spinner_Second_StoronaSlivNotNN = getResources().getStringArray(R.array.spinner_Second_StoronaSlivNotNN);
        spinner_Second_MestoZapKol_1sl = getResources().getStringArray(R.array.spinner_Second_MestoZapKol_1sl);
        spinner_Second_MestoZapKol_2sl = getResources().getStringArray(R.array.spinner_Second_MestoZapKol_2sl);

    }

    private void loadFirstPosition(){


        if(initViewPosition.getTypeAxelCount().equals("4 оси")){
            textViewTEBS.setVisibility(View.VISIBLE);
            radioGroupSecondSystTEBS.setVisibility(View.VISIBLE);
            radioGroupSecondliftAxel_3.setVisibility(View.INVISIBLE);
            radioGroupSecondliftAxel_4.setVisibility(View.VISIBLE);
            radioButtonSecondLiftAxel_4_14.setVisibility(View.VISIBLE);
        } else if (initViewPosition.getTypeAxelCount().equals("3+1 ось")) {
            textViewTEBS.setVisibility(View.GONE);
            radioGroupSecondSystTEBS.setVisibility(View.GONE);
            radioGroupSecondliftAxel_3.setVisibility(View.INVISIBLE);
            radioGroupSecondliftAxel_4.setVisibility(View.VISIBLE);
            radioButtonSecondLiftAxel_4_14.setVisibility(View.INVISIBLE);

        }else{
            textViewTEBS.setVisibility(View.GONE);
            radioGroupSecondSystTEBS.setVisibility(View.GONE);
        }

        if(initViewPosition.getTypeMarkAxel().equals("BPW")){
            radioButtonSecondBreakDisk.setVisibility(View.INVISIBLE);

        }


        spinerSecondCountCapsule.setSelection(initViewPosition.getCountCapsule()-1); // устанавливаем спинер на кол-во отсеков

        switch (initViewPosition.getHighSSU()) { // устанавливаем кнопки высоты или произв значение
            case "1150-1200":
                radioButtonSecondHeightSSU_1150.setChecked(true);
                break;
            case "1250-1300":
                radioButtonSecondHeightSSU_1250.setChecked(true);
                break;
            case "1350-1400":
                radioButtonSecondHeightSSU_1350.setChecked(true);
                break;
            default:
                editTextInputSSU.setText(initViewPosition.getHighSSU());

        }

        switch (initViewPosition.getTypeGorlovin()){ // устанавливаем тип горловин

            case "ГОСТ":
                radioButtonSecondGorlGOST.setChecked(true);
                break;
            case "ЕВРО":
                radioButtonSecondGorlEVRO.setChecked(true);
                break;
        }

        adapterGorlCovers.addAll(spiner_SecondTypeGorlCovers); // добавляем массив в адаптер нужно для поиска по значению
        int index = adapterGorlCovers.getPosition(initViewPosition.getTypeGorlCovers());
        if (index >= 0) {
            spinerSecondTypeGorlCovers.setSelection(index); // Устанавливает выбранный элемент в Spinner
        }


        adapterBottomValve.addAll(spiner_SecondBottomValve); // добавляем массив в адаптер нужно для поиска по значению
        int index1 = adapterBottomValve.getPosition(initViewPosition.getTypeBottomValve());
        if (index1 >= 0) {
            spinerSecondBottomValve.setSelection(index1); // Устанавливает выбранный элемент в Spinner
        }


        switch (initViewPosition.getTypeMarkAxel()){ // устанавливаем марку осей

            case "BPW":
                radioButtonSecondAxelBPW.setChecked(true);
                break;
            case "SAF":
                radioButtonSecondAxelSAF.setChecked(true);
                break;
        }

        // устанавливаем видимость марки осей в зависимости от грузоподъемности
        if (initViewPosition.getAxelWeight().equals("12 тонн")) {
            radioButtonSecondAxelSAF.setVisibility(View.GONE);
        }


        switch (initViewPosition.getTypeBreakMeh()){ // устанавливаем тип тормозов

            case "Барабанные":
                radioButtonSecondBreakBar.setChecked(true);
                break;
            case "Дисковые":
                radioButtonSecondBreakDisk.setChecked(true);
                break;
        }

        if(initViewPosition.getLiftAxel().isEmpty()) {// устанавливаем значение подъемных осей

            switch (initViewPosition.getTypeAxelCount()) {
                case "3 оси":
                    radioButtonSecondLiftAxel_3_Not.setChecked(true);
                    initViewPosition.setLiftAxel("Нет");
                    break;
                case "4 оси":
                    radioButtonSecondLiftAxel_4_12.setChecked(true);
                    initViewPosition.setLiftAxel("1-2 оси");
                    break;
                case "3+1 ось":
                    radioButtonSecondLiftAxel_4_12.setChecked(true);
                    initViewPosition.setLiftAxel("1-2 оси");
                    break;
            }


        } else if (initViewPosition.getLiftAxel().equals("Передняя")) {
            radioButtonSecondLiftAxel_3_1.setChecked(true);
        }else if (initViewPosition.getLiftAxel().equals("Нет")) {
            radioButtonSecondLiftAxel_3_Not.setChecked(true);
        }
        else if (initViewPosition.getLiftAxel().equals("1-4 оси")) {
            radioButtonSecondLiftAxel_4_14.setChecked(true);
        }else if (initViewPosition.getLiftAxel().equals("1-2 оси")) {
            radioButtonSecondLiftAxel_4_12.setChecked(true);
        }

        adapterMarkTire.addAll(spiner_SecondMarkTire); // добавляем массив в адаптер нужно для поиска по значению
        int index2 = adapterMarkTire.getPosition(initViewPosition.getTypeMarkTire());
        if (index1 >= 0) {
            spinerSecondMarkTire.setSelection(index2); // Устанавливает выбранный элемент в Spinner
        }

        if(initViewPosition.getTypeAxelCount().equals("3 оси")) {
            radioGroupSecondTypeBreakSyst.setVisibility(View.VISIBLE);
            textViewTypeBreakSystem.setVisibility(View.VISIBLE);
            switch (initViewPosition.getTypeBreakSyst()) { // устанавливаем марку тормозной системы

                case "Wabco":
                    radioButtonSecondBreakSyst_Wabco.setChecked(true);
                 //   radioButtonSystemInformPPC_Smrt.setVisibility(View.VISIBLE);
                    break;
                case "Knorr-Bremse":
                    radioButtonSecondBreakSyst_Knorr.setChecked(true);
                    radioButtonSystemInformPPC_Smrt.setVisibility(View.INVISIBLE);
                    break;
            }
        }

        if(initViewPosition.getT_EBS().equals("Нет") // утснавливаем выбор в T-EBS в зависимости от того что в классе InitView и смотря какой ППЦ
                &&initViewPosition.getTypeAxelCount().equals("4 оси")){
            radioButtonSecondTEBS_Not.setChecked(true);
        }else if(initViewPosition.getT_EBS().equals("T-EBS")
                &&initViewPosition.getTypeAxelCount().equals("4 оси")){
            radioButtonSecondTEBS_Yes.setChecked(true);
        }

        if(initViewPosition.getT_EBS().equals("T-EBS")) { // проверяем есть ли T-EBS и если есть предлагаем выбрать информ. систему
            switch (initViewPosition.getTypeSystemInformPPC()) {

                case "Нет":
                    radioButtonSystemInformPPC_Not.setChecked(true);
                    break;
                case "Optilink":
                    radioButtonSystemInformPPC_Opt.setChecked(true);
                    break;
                case "SmurtBoard":
                    radioButtonSystemInformPPC_Smrt.setChecked(true);
                    break;
            }
        }else {// если ТЕБС нет то выбор системы не возможен

            textViewSystemInformPPC.setVisibility(View.GONE);
            radioGroupSystemInformPPC.setVisibility(View.GONE);

        }

        switch (initViewPosition.getIsNNandRec()){ // устанавливаем наличие нижнего налива

            case "Нет":
                radioButtonNNandRec_Not.setChecked(true);
                spinerSecondNNandRec.setVisibility(View.GONE);
                adapterStorCliv.clear();
                adapterStorCliv.addAll(spinner_Second_StoronaSlivNotNN);
                break;
            case "Нижний \nналив":
                radioButtonNNandRec_Yes.setChecked(true);
                spinerSecondNNandRec.setVisibility(View.VISIBLE);
                adapterStorCliv.clear();
                adapterStorCliv.addAll(spinner_Second_StoronaSliv);// устанавливаем выбор комбинации слива
                adapterNNandRec.clear();
                adapterNNandRec.addAll(spiner_SecondNNandRec); // добавляем массив в адаптер нужно для поиска по значению
                if(initViewPosition.getTypeNNandRec().isEmpty()){
                    spinerSecondNNandRec.setSelection(0);
                    initViewPosition.setTypeNNandRec(spinerSecondNNandRec.getSelectedItem().toString()); // устанавливаем в спинер значение 0 элемента
                } else  {
                    int index3 = adapterNNandRec.getPosition(initViewPosition.getTypeNNandRec());
                    if (index3 >= 0) {
                        spinerSecondNNandRec.setSelection(index3); // Устанавливает выбранный элемент в Spinner
                    }
                }
                break;
            case "Подготовка":
                if(initViewPosition.getTypeStorSliv().equals("На 2 стороны")){
                    radioButtonNNandRec_Podg.setChecked(true);
                    spinerSecondNNandRec.setVisibility(View.VISIBLE);
                    adapterNNandRec.clear();
                    adapterNNandRec.addAll(spiner_SecondNNandRec_Podg_Two); // добавляем массив в адаптер нужно для поиска по значению
                }else {
                    radioButtonNNandRec_Podg.setChecked(true);
                    spinerSecondNNandRec.setVisibility(View.VISIBLE);
                    adapterNNandRec.clear();
                    adapterNNandRec.addAll(spiner_SecondNNandRec_Podg); // добавляем массив в адаптер нужно для поиска по значению
                }
                    if(initViewPosition.getTypeNNandRec().isEmpty()){
                    spinerSecondNNandRec.setSelection(0);
                    initViewPosition.setTypeNNandRec(spinerSecondNNandRec.getSelectedItem().toString()); // устанавливаем в спинер значение 0 элемента
                } else  {
                    int index3 = adapterNNandRec.getPosition(initViewPosition.getTypeNNandRec());
                    if (index3 >= 0) {
                        spinerSecondNNandRec.setSelection(index3); // Устанавливает выбранный элемент в Spinner
                    }
                }
                break;
        }

        adapterPump.addAll(spinner_Second_Pump); // добавляем массив в адаптер нужно для поиска по значению
        int index4 = adapterPump.getPosition(initViewPosition.getTypePump());
        if (index4 >= 0) {
            spinerSecondPump.setSelection(index4); // Устанавливает выбранный элемент в Spinner
        }


        switch (initViewPosition.getTypeStorSliv()){ // устанавливаем стороны слива

            case "Слева":
                radioButtonStorSliv_L.setChecked(true);
                adapterMestoZapKol.clear();
                adapterMestoZapKol.addAll(spinner_Second_MestoZapKol_1sl);
                break;
            case "Справа":
                radioButtonStorSliv_R.setChecked(true);
                adapterMestoZapKol.clear();
                adapterMestoZapKol.addAll(spinner_Second_MestoZapKol_1sl);
                break;
            case "На 2 стороны":
                radioButtonStorSliv_LR.setChecked(true);
                spinerSecondTypeCombSliv.setVisibility(View.VISIBLE);
                adapterMestoZapKol.clear();
                adapterMestoZapKol.addAll(spinner_Second_MestoZapKol_2sl);
                //radioButtonCountZapKol_2.setVisibility(View.INVISIBLE); // убираем возможность поставить 2 колеса
                break;
        }

        //adapterStorCliv.addAll(spinner_Second_StoronaSliv); // добавляем массив в адаптер нужно для поиска по значению
        int index5 = adapterStorCliv.getPosition(initViewPosition.getTypeCombSliv()); // устанавливаем значение комбинации слива в спинере
        if (index5 >= 0) {
            spinerSecondTypeCombSliv.setSelection(index5); // Устанавливает выбранный элемент в Spinner
        }

        int index6 = adapterMestoZapKol.getPosition(initViewPosition.getTypePlaceZap()); // устанавливаем место запаски в спинере
        if (index6 >= 0) {
            spinerSecondMestoZapKol.setSelection(index6); // Устанавливает выбранный элемент в Spinner
        }

        if(initViewPosition.getTypePlaceZap().equals("В тех. шкафу и заднем свесе")){ //делаем видимой кнопку выбора 2 колес
            radioButtonCountZapKol_2.setVisibility(View.VISIBLE);
        }

        switch (initViewPosition.getCountZapKol()){ // значения кол-ва колес

            case 0:
                radioButtonCountZapKol_0.setChecked(true);
                break;
            case 1:
                radioButtonCountZapKol_1.setChecked(true);
                break;
            case 2:
                radioButtonCountZapKol_2.setChecked(true);
                break;
        }

        switch (initViewPosition.getTypeOkraska()){ // значения карты окраски

            case "ТУ завода":
                radioButtonOkraska_TU.setChecked(true);
                buttonSecondCalcutionKo.setVisibility(View.GONE);
                break;
            case "Карта окраски":
                radioButtonOkraska_KO.setChecked(true);
                buttonSecondCalcutionKo.setVisibility(View.VISIBLE);
                break;

        }










    }


    private void loadFirstPositionPpcb(){


        if(initViewPosition.getTypeAxelCount().equals("4 оси")){
            textViewTEBS.setVisibility(View.VISIBLE);
            radioGroupSecondSystTEBS.setVisibility(View.VISIBLE);
            radioGroupSecondliftAxel_3.setVisibility(View.INVISIBLE);
            radioGroupSecondliftAxel_4.setVisibility(View.VISIBLE);
            radioButtonSecondLiftAxel_4_14.setVisibility(View.VISIBLE);
        } else if (initViewPosition.getTypeAxelCount().equals("3+1 ось")) {
            textViewTEBS.setVisibility(View.GONE);
            radioGroupSecondSystTEBS.setVisibility(View.GONE);
            radioGroupSecondliftAxel_3.setVisibility(View.INVISIBLE);
            radioGroupSecondliftAxel_4.setVisibility(View.VISIBLE);
            radioButtonSecondLiftAxel_4_14.setVisibility(View.INVISIBLE);

        }else{
            textViewTEBS.setVisibility(View.GONE);
            radioGroupSecondSystTEBS.setVisibility(View.GONE);
        }

        if(initViewPosition.getTypeMarkAxel().equals("BPW")){
            radioButtonSecondBreakDisk.setVisibility(View.INVISIBLE);

        }

        // спинер кол-ва отсеков
        textViewCountCapsule.setVisibility(View.GONE);
        spinerSecondCountCapsule.setVisibility(View.GONE); // спинер кол-ва отсеков

        //тип горловин
        textViewTypeGorlovin.setVisibility(View.GONE);
        radioGroupTypeGorl.setVisibility(View.GONE);

        //крышки горловин
        textViewTypeGorlCovers.setVisibility(View.GONE);
        spinerSecondTypeGorlCovers.setVisibility(View.GONE);

        //Марка ДК
            textViewBottomValve.setText("Марка донного клапана");
//        spinerSecondBottomValve.setVisibility(View.GONE);

        //Нижний Налив
        textViewNNandRec.setVisibility(View.GONE);
        radioGroupSecondNNandRec.setVisibility(View.GONE);

        //Сторона слива
        textViewStoronaSliva.setVisibility(View.GONE);
        radioGroupSecondStoronaSliva.setVisibility(View.GONE);

        //Расположение запаски
        textViewMestoZapKol.setVisibility(View.GONE);
        spinerSecondMestoZapKol.setVisibility(View.INVISIBLE);

        //Окраска
        textViewOkraska.setVisibility(View.GONE);
        radioGroupOkraska.setVisibility(View.GONE);




        spinerSecondCountCapsule.setSelection(initViewPosition.getCountCapsule()-1); // устанавливаем спинер на кол-во отсеков

        switch (initViewPosition.getHighSSU()) { // устанавливаем кнопки высоты или произв значение
            case "1150-1200":
                radioButtonSecondHeightSSU_1150.setChecked(true);
                break;
            case "1250-1300":
                radioButtonSecondHeightSSU_1250.setChecked(true);
                break;
            case "1350-1400":
                radioButtonSecondHeightSSU_1350.setChecked(true);
                break;
            default:
                editTextInputSSU.setText(initViewPosition.getHighSSU());

        }


        switch (initViewPosition.getTypeGorlovin()){ // устанавливаем тип горловин

            case "ГОСТ":
                radioButtonSecondGorlGOST.setChecked(true);
                break;
            case "ЕВРО":
                radioButtonSecondGorlEVRO.setChecked(true);
                break;
        }

        adapterGorlCovers.addAll(spiner_SecondTypeGorlCovers); // добавляем массив в адаптер нужно для поиска по значению
        int index = adapterGorlCovers.getPosition(initViewPosition.getTypeGorlCovers());
        if (index >= 0) {
            spinerSecondTypeGorlCovers.setSelection(index); // Устанавливает выбранный элемент в Spinner
        }


        adapterBottomValve.addAll(spiner_SecondBottomValvePpcb); // добавляем массив в адаптер нужно для поиска по значению
        int index1 = adapterBottomValve.getPosition(initViewPosition.getTypeBottomValve());
        if (index1 >= 0) {
            spinerSecondBottomValve.setSelection(index1); // Устанавливает выбранный элемент в Spinner
        }


        switch (initViewPosition.getTypeMarkAxel()){ // устанавливаем марку осей

            case "BPW":
                radioButtonSecondAxelBPW.setChecked(true);
                break;
            case "SAF":
                radioButtonSecondAxelSAF.setChecked(true);
                break;
        }

        // устанавливаем видимость марки осей в зависимости от грузоподъемности
        if (initViewPosition.getAxelWeight().equals("12 тонн")) {
            radioButtonSecondAxelSAF.setVisibility(View.GONE);
        }


        switch (initViewPosition.getTypeBreakMeh()){ // устанавливаем тип тормозов

            case "Барабанные":
                radioButtonSecondBreakBar.setChecked(true);
                break;
            case "Дисковые":
                radioButtonSecondBreakDisk.setChecked(true);
                break;
        }

        if(initViewPosition.getLiftAxel().isEmpty()) {// устанавливаем значение подъемных осей

            switch (initViewPosition.getTypeAxelCount()) {
                case "3 оси":
                    radioButtonSecondLiftAxel_3_Not.setChecked(true);
                    initViewPosition.setLiftAxel("Нет");
                    break;
                case "4 оси":
                    radioButtonSecondLiftAxel_4_12.setChecked(true);
                    initViewPosition.setLiftAxel("1-2 оси");
                    break;
                case "3+1 ось":
                    radioButtonSecondLiftAxel_4_12.setChecked(true);
                    initViewPosition.setLiftAxel("1-2 оси");
                    break;
            }


        } else if (initViewPosition.getLiftAxel().equals("Передняя")) {
            radioButtonSecondLiftAxel_3_1.setChecked(true);
        }else if (initViewPosition.getLiftAxel().equals("Нет")) {
            radioButtonSecondLiftAxel_3_Not.setChecked(true);
        }
        else if (initViewPosition.getLiftAxel().equals("1-4 оси")) {
            radioButtonSecondLiftAxel_4_14.setChecked(true);
        }else if (initViewPosition.getLiftAxel().equals("1-2 оси")) {
            radioButtonSecondLiftAxel_4_12.setChecked(true);
        }

        adapterMarkTire.addAll(spiner_SecondMarkTire); // добавляем массив в адаптер нужно для поиска по значению
        int index2 = adapterMarkTire.getPosition(initViewPosition.getTypeMarkTire());
        if (index1 >= 0) {
            spinerSecondMarkTire.setSelection(index2); // Устанавливает выбранный элемент в Spinner
        }

        if(initViewPosition.getTypeAxelCount().equals("3 оси")) {
            radioGroupSecondTypeBreakSyst.setVisibility(View.VISIBLE);
            textViewTypeBreakSystem.setVisibility(View.VISIBLE);
            switch (initViewPosition.getTypeBreakSyst()) { // устанавливаем марку тормозной системы

                case "Wabco":
                    radioButtonSecondBreakSyst_Wabco.setChecked(true);
               //     radioButtonSystemInformPPC_Smrt.setVisibility(View.VISIBLE);
                    break;
                case "Knorr-Bremse":
                    radioButtonSecondBreakSyst_Knorr.setChecked(true);
                    radioButtonSystemInformPPC_Smrt.setVisibility(View.INVISIBLE);
                    break;
            }
        }

        if(initViewPosition.getT_EBS().equals("Нет") // утснавливаем выбор в T-EBS в зависимости от того что в классе InitView и смотря какой ППЦ
                &&initViewPosition.getTypeAxelCount().equals("4 оси")){
            radioButtonSecondTEBS_Not.setChecked(true);
        }else if(initViewPosition.getT_EBS().equals("T-EBS")
                &&initViewPosition.getTypeAxelCount().equals("4 оси")){
            radioButtonSecondTEBS_Yes.setChecked(true);
        }

        if(initViewPosition.getT_EBS().equals("T-EBS")) { // проверяем есть ли T-EBS и если есть предлагаем выбрать информ. систему
            switch (initViewPosition.getTypeSystemInformPPC()) {

                case "Нет":
                    radioButtonSystemInformPPC_Not.setChecked(true);
                    break;
                case "Optilink":
                    radioButtonSystemInformPPC_Opt.setChecked(true);
                    break;
                case "SmurtBoard":
                    radioButtonSystemInformPPC_Smrt.setChecked(true);
                    break;
            }
        }else {// если ТЕБС нет то выбор системы не возможен

            textViewSystemInformPPC.setVisibility(View.GONE);
            radioGroupSystemInformPPC.setVisibility(View.GONE);

        }

        switch (initViewPosition.getIsNNandRec()){ // устанавливаем наличие нижнего налива

            case "Нет":
                radioButtonNNandRec_Not.setChecked(true);
                spinerSecondNNandRec.setVisibility(View.GONE);
                adapterStorCliv.clear();
                adapterStorCliv.addAll(spinner_Second_StoronaSlivNotNN);
                break;
            case "Нижний \nналив":
                radioButtonNNandRec_Yes.setChecked(true);
                spinerSecondNNandRec.setVisibility(View.VISIBLE);
                adapterStorCliv.clear();
                adapterStorCliv.addAll(spinner_Second_StoronaSliv);// устанавливаем выбор комбинации слива
                adapterNNandRec.clear();
                adapterNNandRec.addAll(spiner_SecondNNandRec); // добавляем массив в адаптер нужно для поиска по значению
                if(initViewPosition.getTypeNNandRec().isEmpty()){
                    spinerSecondNNandRec.setSelection(0);
                    initViewPosition.setTypeNNandRec(spinerSecondNNandRec.getSelectedItem().toString()); // устанавливаем в спинер значение 0 элемента
                } else  {
                    int index3 = adapterNNandRec.getPosition(initViewPosition.getTypeNNandRec());
                    if (index3 >= 0) {
                        spinerSecondNNandRec.setSelection(index3); // Устанавливает выбранный элемент в Spinner
                    }
                }
                break;
            case "Подготовка":
                radioButtonNNandRec_Podg.setChecked(true);
                spinerSecondNNandRec.setVisibility(View.VISIBLE);
                adapterNNandRec.clear();
                adapterNNandRec.addAll(spiner_SecondNNandRec_Podg); // добавляем массив в адаптер нужно для поиска по значению
                if(initViewPosition.getTypeNNandRec().isEmpty()){
                    spinerSecondNNandRec.setSelection(0);
                    initViewPosition.setTypeNNandRec(spinerSecondNNandRec.getSelectedItem().toString()); // устанавливаем в спинер значение 0 элемента
                } else  {
                    int index3 = adapterNNandRec.getPosition(initViewPosition.getTypeNNandRec());
                    if (index3 >= 0) {
                        spinerSecondNNandRec.setSelection(index3); // Устанавливает выбранный элемент в Spinner
                    }
                }
                break;
        }

        adapterPump.addAll(spinner_Second_PumpPpcb); // добавляем массив в адаптер нужно для поиска по значению
        int index4 = adapterPump.getPosition(initViewPosition.getTypePump());
        if (index4 >= 0) {
            spinerSecondPump.setSelection(index4); // Устанавливает выбранный элемент в Spinner
        }


        switch (initViewPosition.getTypeStorSliv()){ // устанавливаем стороны слива

            case "Слева":
                radioButtonStorSliv_L.setChecked(true);
                adapterMestoZapKol.clear();
                adapterMestoZapKol.addAll(spinner_Second_MestoZapKol_1sl);
                break;
            case "Справа":
                radioButtonStorSliv_R.setChecked(true);
                adapterMestoZapKol.clear();
                adapterMestoZapKol.addAll(spinner_Second_MestoZapKol_1sl);
                break;
            case "На 2 стороны":
                radioButtonStorSliv_LR.setChecked(true);
                spinerSecondTypeCombSliv.setVisibility(View.VISIBLE);
                adapterMestoZapKol.clear();
                adapterMestoZapKol.addAll(spinner_Second_MestoZapKol_2sl);
                //radioButtonCountZapKol_2.setVisibility(View.INVISIBLE); // убираем возможность поставить 2 колеса
                break;
        }

        //adapterStorCliv.addAll(spinner_Second_StoronaSliv); // добавляем массив в адаптер нужно для поиска по значению
        int index5 = adapterStorCliv.getPosition(initViewPosition.getTypeCombSliv()); // устанавливаем значение комбинации слива в спинере
        if (index5 >= 0) {
            spinerSecondTypeCombSliv.setSelection(index5); // Устанавливает выбранный элемент в Spinner
        }


        spinerSecondMestoZapKol.setSelection(2);

        if(initViewPosition.getTypePlaceZap().equals("В тех. шкафу и заднем свесе")){ //делаем видимой кнопку выбора 2 колес
            radioButtonCountZapKol_2.setVisibility(View.VISIBLE);
        }

        switch (initViewPosition.getCountZapKol()){ // значения кол-ва колес

            case 0:
                radioButtonCountZapKol_0.setChecked(true);
                break;
            case 1:
                radioButtonCountZapKol_1.setChecked(true);
                break;
            case 2:
                radioButtonCountZapKol_2.setChecked(true);
                break;
        }

        switch (initViewPosition.getTypeOkraska()){ // значения карты окраски

            case "ТУ завода":
                radioButtonOkraska_TU.setChecked(true);
                buttonSecondCalcutionKo.setVisibility(View.GONE);
                break;
            case "Карта окраски":
                radioButtonOkraska_KO.setChecked(true);
                buttonSecondCalcutionKo.setVisibility(View.VISIBLE);
                break;

        }


    }




    private void loadSpinnerAdapter(){

        adapterGorlCovers = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item); // создаем спинер адаптер
        adapterGorlCovers.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinerSecondTypeGorlCovers.setAdapter(adapterGorlCovers);

        adapterBottomValve = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item); // создаем спинер адаптер
        adapterBottomValve.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinerSecondBottomValve.setAdapter(adapterBottomValve);

        adapterMarkTire = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item); // создаем спинер адаптер
        adapterMarkTire.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinerSecondMarkTire.setAdapter(adapterMarkTire);

        adapterNNandRec = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item); // создаем спинер адаптер
        adapterNNandRec.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinerSecondNNandRec.setAdapter(adapterNNandRec);

        adapterPump = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item); // создаем спинер адаптер
        adapterPump.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinerSecondPump.setAdapter(adapterPump);

        adapterStorCliv = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item); // создаем спинер адаптер
        adapterStorCliv.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinerSecondTypeCombSliv.setAdapter(adapterStorCliv);

        adapterMestoZapKol = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item); // создаем спинер адаптер
        adapterMestoZapKol.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinerSecondMestoZapKol.setAdapter(adapterMestoZapKol);



    }
    // выводим основные сведения о ППЦ на второй экран сборки
    private void informOfPPC(){

        double cost = initViewPosition.getCostPPC(); // переводим отображение double в формат разделения разрядов пробелом
        NumberFormat formatter = NumberFormat.getNumberInstance(Locale.FRANCE);
        formatter.setMinimumFractionDigits(2);
        formatter.setMaximumFractionDigits(2);
        String formattedCost = formatter.format(cost);

        String inform =initViewPosition.getModelPPC()+ ", "+'\n';
        inform +="Стоимость: " + formattedCost+ ", "+'\n'+'\n';
        inform +=initViewPosition.getType()+ ", "+'\n';
        inform += initViewPosition.getVolumePPC() + " м3, ";
        inform +=initViewPosition.getTypeAxelCount()+ ", "+'\n';
        inform +=initViewPosition.getTypeAxel();
        textViewModelPPC.setText(inform);


    }
    @Override
    protected void onResume() {
        super.onResume();
    }


}