package oleg.trushanin.graz;


import android.graphics.Typeface;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class AdapterViewBd extends RecyclerView.Adapter<AdapterViewBd.ElementBdViewHolder> {

    public void setListElementBd(List<TableDataBaseKp> listElementBd) {
        //разворачиваем лист, сначала последние добавления
        List<TableDataBaseKp> listReverce = new ArrayList<>(listElementBd);
        Collections.reverse(listReverce);
        this.listElementBd = listReverce;
        notifyDataSetChanged();
    }

    public List<TableDataBaseKp> getListElementBd() {
        return listElementBd;
    }

    List<TableDataBaseKp> listElementBd = new ArrayList<>();

    public void setOnTableDataBaseKpClickListener(OnTableDataBaseKpClickListener onTableDataBaseKpClickListener) {
        this.onTableDataBaseKpClickListener = onTableDataBaseKpClickListener;
    }

    OnTableDataBaseKpClickListener onTableDataBaseKpClickListener;





    @NonNull
    @Override
    public ElementBdViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.item_element_bd,
                parent,
                false);

        return new ElementBdViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ElementBdViewHolder holder, int position) {

        TableDataBaseKp elementBd = listElementBd.get(position);

        String s1 = elementBd.getClient() + " - " + elementBd.getDateKp() + " - " + elementBd.getNumberPrice();
        holder.textViewCompDate.setText(s1);

        String s2 = elementBd.getModelPPC() + " " + elementBd.getVolumePPC() + " "
                + elementBd.getCountCapsule() + " отс. " + elementBd.getTypeMaterial();
        holder.textViewModVolumCapsMater.setText(s2);

        String s3 = elementBd.getTypeAxelCount()+ " " + elementBd.getTypeMarkAxel() + " "
                + elementBd.getTypeAxel();
        holder.textViewAxelMarkAxelWeightTypeAxel.setText(s3);

        NumberFormat formatter = NumberFormat.getNumberInstance(Locale.FRANCE);
        formatter.setMinimumFractionDigits(2);
        formatter.setMaximumFractionDigits(2);
        String formattedCost = formatter.format(elementBd.getTotalPrice());
        String s4 = "Стоимость прайс: " + formattedCost + " руб.";
        holder.textViewPriceCost.setText(s4);

        formattedCost = formatter.format(elementBd.getPlusPricePpc());
        String s5 = "Надбавка: " + formattedCost+ " руб.";
        holder.textViewAddCost.setText(s5);

        if(elementBd.getDeliveryCost() != 0) {
            formattedCost = formatter.format(elementBd.getDeliveryCost());
            String s6 = "Стоимость доставки: " + formattedCost + " руб.";
            holder.textViewDeliveryCost.setText(s6);
        }else{
            holder.textViewDeliveryCost.setVisibility(View.GONE);
        }

        formattedCost = formatter.format(elementBd.getTotalPriceKp());
        String s7 = "Итого цена в КП: " + formattedCost+ " руб.";
        SpannableString spannableString = new SpannableString(s7);
        StyleSpan boldSpan = new StyleSpan(Typeface.BOLD);
        int start = s7.indexOf(":")+1;
        int end = s7.indexOf("руб.");
        spannableString.setSpan(boldSpan, start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        holder.textViewTotalPriceKp.setText(spannableString);

        //Вешаем слушателя на элемент ресайклера
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(onTableDataBaseKpClickListener != null){

                    onTableDataBaseKpClickListener.onClickListener(elementBd);

                }



            }
        });







    }

    @Override
    public int getItemCount() {
        return listElementBd.size();
    }

interface OnTableDataBaseKpClickListener{

        void onClickListener(TableDataBaseKp tableDataBaseKp);

}

    public class ElementBdViewHolder extends RecyclerView.ViewHolder{

        TextView textViewCompDate;
        TextView textViewModVolumCapsMater;
        TextView textViewAxelMarkAxelWeightTypeAxel;
        TextView textViewPriceCost;
        TextView textViewAddCost;
        TextView textViewDeliveryCost;
        TextView textViewTotalPriceKp;



        public ElementBdViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewCompDate = itemView.findViewById(R.id.textViewCompDate);
            textViewModVolumCapsMater = itemView.findViewById(R.id.textViewModVolumCapsMater);
            textViewAxelMarkAxelWeightTypeAxel = itemView.findViewById(R.id.textViewAxelMarkAxelWeightTypeAxel);
            textViewPriceCost = itemView.findViewById(R.id.textViewPriceCost);
            textViewAddCost = itemView.findViewById(R.id.textViewAddCost);
            textViewDeliveryCost = itemView.findViewById(R.id.textViewDeliveryCost);
            textViewTotalPriceKp = itemView.findViewById(R.id.textViewTotalPriceKp);
        }
    }


}
