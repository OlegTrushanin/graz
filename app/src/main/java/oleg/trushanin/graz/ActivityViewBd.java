package oleg.trushanin.graz;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ActivityViewBd extends AppCompatActivity {

    EditText inputSearchCompany;

    RecyclerView recyclerViewBdKp;

    AdapterViewBd adapterViewBd;

    ViewBdViewModel viewBdViewModel;

    //лист который будем фильтровать по компании
    List<TableDataBaseKp> sourceList;

    InitViewPosition initViewPosition;



    String search = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_bd);
        inputSearchCompany = findViewById(R.id.inputSearchCompany);
        recyclerViewBdKp = findViewById(R.id.recyclerViewBdKp);
        initViewPosition = InitViewPosition.getInstance();
        adapterViewBd = new AdapterViewBd();
        viewBdViewModel = new ViewModelProvider(this).get(ViewBdViewModel.class);

        recyclerViewBdKp.setAdapter(adapterViewBd);

        //выгружаем данные из БД
        viewBdViewModel.loadListKp();
//        //передаем коллекцию элементов БД в адаптер
        viewBdViewModel.getListKp().observe(this, new Observer<List<TableDataBaseKp>>() {
            @Override
            public void onChanged(List<TableDataBaseKp> tableDataBaseKps) {
                sourceList = tableDataBaseKps;
                adapterViewBd.setListElementBd(tableDataBaseKps);
            }
        });


        inputSearchCompany.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                search = editable.toString().trim();
                filterSearch(search);


            }
        });


        adapterViewBd.setOnTableDataBaseKpClickListener(new AdapterViewBd.OnTableDataBaseKpClickListener() {
            @Override
            public void onClickListener(TableDataBaseKp tableDataBaseKp) {

                Uri uri = Uri.parse(tableDataBaseKp.getUriPdf());
                initViewPosition.setUriPdfKp(uri);

                Intent intent = new Intent(ActivityViewBd.this, ActivityViewPdfKp.class);

                startActivity(intent);

            }
        });


        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(
                0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView,
                                  @NonNull RecyclerView.ViewHolder viewHolder,
                                  @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

                int position = viewHolder.getAdapterPosition();

                TableDataBaseKp tableDataBaseKp = adapterViewBd.getListElementBd().get(position);

                initViewPosition.setUriPdfKp(Uri.parse(tableDataBaseKp.getUriPdf()));

                Intent intent = new Intent(ActivityViewBd.this, ActivityMenegElementBd.class);

                intent.putExtra("obj",tableDataBaseKp);

                startActivity(intent);

                //уведомляем адаптер, что данные не удалились
                adapterViewBd.notifyItemChanged(position);




            }
        });


        itemTouchHelper.attachToRecyclerView(recyclerViewBdKp);



    }


    private void filterSearch(String comp){


        List<TableDataBaseKp> filterList = new ArrayList<>();

        for(TableDataBaseKp tabl: sourceList){


            if(tabl.getClient().toLowerCase().contains(comp)){

                filterList.add(tabl);

            }

            adapterViewBd.setListElementBd(filterList);

        }




    }

    @Override
    protected void onResume() {
        super.onResume();
        //Нужно для отображения списка по ключевому слову при возвращении в активити
        if(sourceList != null) {
            viewBdViewModel.loadListKp();
//        //передаем коллекцию элементов БД в адаптер
            viewBdViewModel.getListKp().observe(this, new Observer<List<TableDataBaseKp>>() {
                @Override
                public void onChanged(List<TableDataBaseKp> tableDataBaseKps) {
                    sourceList = tableDataBaseKps;
                    adapterViewBd.setListElementBd(tableDataBaseKps);
                    filterSearch(search);
                    adapterViewBd.notifyDataSetChanged();
                }
            });

        }
        adapterViewBd.notifyDataSetChanged();
    }
}