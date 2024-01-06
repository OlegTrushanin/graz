package oleg.trushanin.graz;

import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ActivitySpecialOptions extends AppCompatActivity {

    InitViewPosition initViewPosition;
    AdapterSpecialOptions adapterSpecialOptions;
    RecyclerView recyclerViewSpecialOptions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_special_options);

    recyclerViewSpecialOptions = findViewById(R.id.recyclerViewSpecialOptions);
    recyclerViewSpecialOptions.setLayoutManager(new LinearLayoutManager(this));

    adapterSpecialOptions = new AdapterSpecialOptions();

    initViewPosition = InitViewPosition.getInstance();

    recyclerViewSpecialOptions.setAdapter(adapterSpecialOptions);

    adapterSpecialOptions.setList(initViewPosition.getSpecialLightOptionsList());


    adapterSpecialOptions.setOnClickItemListener(new AdapterSpecialOptions.OnClickItemListener() {
        @Override
        public void onClickItem(SpecialLightOptions specialOptions, boolean isChecked) {
            if(isChecked){
                initViewPosition.addChoosSpecialLightOptionsList(specialOptions);
            }else{
                initViewPosition.removeChoosSpecialLightOptionsList(specialOptions);
            }
        }


    });

        //Переопределяем бэк. При выходе из спец опций пересчитываем цены на опции зависимые от отсеков
        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled */) { // при нажатии кнопки назад происходит сохранение выбранных значений
            @Override
            public void handleOnBackPressed() {

                initViewPosition.getSpecialLightOptionsList().stream()
                        .filter(item -> 1 == item.getMod() || 2 == item.getMod())
                        .forEach(item -> {
                            //высчитвыаем стоимость опции в зависимости от кол-ва отсеков
                            if (item.getMod() == 1) {
                                float price = item.getPrice()*(initViewPosition.getCountCapsule()-1);
                                int identifier = item.getIdentifier();
                                // меняем значения в уже выбранной коллекции (choos)
                                Optional<SpecialLightOptions> foundIdentifaier =
                                        initViewPosition.getChoosSpecialLightOptionsList().stream()
                                                .filter(item1 -> identifier == item1.getIdentifier())
                                                .findFirst();


                                foundIdentifaier.ifPresent(item1 -> {

                                    item1.setPrice(price);
                                });
                            } else {
                                float price = item.getPrice() * (initViewPosition.getCountCapsule());
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



                finish();
            }
        };
        getOnBackPressedDispatcher().addCallback(this, callback);


    }

    private void getListOptions() {

        if (initViewPosition.getType().equals("ППЦБ")) {
            List<SpecialDarkOptions> optionsList = initViewPosition.getSpecialDarkOptionsList();
            // Приведение списка SpecialDarkOptions к типу SpecialLightOptions
            List<SpecialLightOptions> castedList = new ArrayList<>();
            for (SpecialDarkOptions item : optionsList) {
                castedList.add(new SpecialLightOptions(item));
            }
            adapterSpecialOptions.setList(castedList);
        } else {
            adapterSpecialOptions.setList(initViewPosition.getSpecialLightOptionsList());
        }
    }
}