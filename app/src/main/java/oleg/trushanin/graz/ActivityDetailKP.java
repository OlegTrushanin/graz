package oleg.trushanin.graz;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ActivityDetailKP extends AppCompatActivity {

    InitViewPosition initViewPosition;
    TextView textViewThirdModelPPC;
    TextView textViewThirdTotalPrice;

    RecyclerView recyclerViewSpecification;
    RecyclerView recyclerViewDopOptions;

    SpecificaitAdapter specificaitAdapter;

    AdapterSpecialOptionsDetail adapterSpecialOptionsDetail;

    FloatingActionButton fabDetailAktHome;
    FloatingActionButton fabDetailAktCreatePDF;

    SelectMainOptionsViewModel selectMainOptionsViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_kp2);
        initView();


        textViewThirdModelPPC.setText(initViewPosition.getModelPPC());

        DataVisualManager dataVisualManager = DataVisualManager.getInstance();

        if(initViewPosition.getType().equals("ППЦБ")){
            dataVisualManager.loadVisualPairPpcb();
        }else{
            dataVisualManager.loadVisualPair();
        }



        initViewPosition.setListKP(dataVisualManager.getVisualPairArrayList());



        selectMainOptionsViewModel = new ViewModelProvider(this).get(SelectMainOptionsViewModel.class);

        selectMainOptionsViewModel.loadMainOptionsPrice();

        ArrayList<String> list = selectMainOptionsViewModel.getMainOptionsPriceList();

        final float[] priceOptions = {0}; // нужно для работы с анонимным классом

        specificaitAdapter = new SpecificaitAdapter();



        recyclerViewSpecification.setLayoutManager(new LinearLayoutManager(this));

        recyclerViewSpecification.setAdapter(specificaitAdapter);

        specificaitAdapter.setSpecificaitList(initViewPosition.getListKP());



        adapterSpecialOptionsDetail = new AdapterSpecialOptionsDetail();

        recyclerViewDopOptions.setLayoutManager(new LinearLayoutManager(this));

        recyclerViewDopOptions.setAdapter(adapterSpecialOptionsDetail);

        adapterSpecialOptionsDetail.setList(new ArrayList<>(initViewPosition.getChoosSpecialLightOptionsList()));






        selectMainOptionsViewModel.getPriceMainOptions(list).observe(this, new Observer<List<Float>>() {
            @Override
            public void onChanged(List<Float> floats) {

                for (Float f: floats){
                    priceOptions[0] += f;

                }

                float costSo = 0;

                for(SpecialLightOptions s: initViewPosition.getChoosSpecialLightOptionsList()){
                    costSo +=s.getPrice();
                }

                initViewPosition.setCostSpecialOption(costSo);
                initViewPosition.setCostMainOPtion(priceOptions[0]);
                initViewPosition.setTotalPrice(initViewPosition.getCostKoPPC() +
                        initViewPosition.getCostPPC()+ initViewPosition.getCostMainOPtion()
                        + initViewPosition.getCostSpecialOption());
                NumberFormat formatter = NumberFormat.getNumberInstance(Locale.FRANCE);
                formatter.setMinimumFractionDigits(2);
                formatter.setMaximumFractionDigits(2);
                String formattedCost = formatter.format(initViewPosition.getTotalPrice());
                formattedCost += " руб.";
                textViewThirdTotalPrice.setText(formattedCost);
            }




        });

        fabDetailAktHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivityDetailKP.this,MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });


        fabDetailAktCreatePDF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivityDetailKP.this, ActivityRequisitesKp.class);
                startActivity(intent);
            }
        });

    }

    public void initView(){
        initViewPosition = InitViewPosition.getInstance();
        fabDetailAktHome = findViewById(R.id.fabDetailAktHome);
        fabDetailAktCreatePDF = findViewById(R.id.fabDetailAktCreatePDF);
        recyclerViewSpecification = findViewById(R.id.recyclerViewSpecialOptions);
        textViewThirdModelPPC = findViewById(R.id.textViewThirdModelPPC);
        textViewThirdTotalPrice = findViewById(R.id.textViewThirdTotalPrice);
        recyclerViewDopOptions = findViewById(R.id.recyclerViewDopOptions);

    }
}