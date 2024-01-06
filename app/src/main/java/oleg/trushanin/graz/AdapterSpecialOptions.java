package oleg.trushanin.graz;

import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class AdapterSpecialOptions extends RecyclerView.Adapter <AdapterSpecialOptions.SpecialOptionsVieHolder> {


    InitViewPosition initViewPosition = InitViewPosition.getInstance();

    public void setList(List<SpecialLightOptions> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    List<SpecialLightOptions> list = new ArrayList<SpecialLightOptions>();

    public void setOnClickItemListener(OnClickItemListener onClickItemListener) {
        this.onClickItemListener = onClickItemListener;
    }

    OnClickItemListener onClickItemListener;


    @NonNull
    @Override
    public SpecialOptionsVieHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.item_special_options,
                parent,
                false);

        return new SpecialOptionsVieHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SpecialOptionsVieHolder holder, int position) {

        SpecialLightOptions specialOptions = list.get(position);
        if(specialOptions.getPrice() != -1) {
            holder.textViewCellOption.setText(specialOptions.getName());

            float price = specialOptions.getPrice();

            //увеличиваем стоимоть опций в зависимости от кол-ва отсеков и их типа
            if(specialOptions.getMod() == 0) {
                holder.textViewCellOption.setText(specialOptions.getName());
                holder.textViewCellValue.setText(String.valueOf(price));
            }else if(specialOptions.getMod() == 1){
                String valueText = specialOptions.getName() + " " + initViewPosition.getCountCapsule() + " отс.";
                holder.textViewCellOption.setText(valueText);
                holder.textViewCellValue.setText(String.valueOf(price*(initViewPosition.getCountCapsule()-1)));
            }else  if(specialOptions.getMod() == 2){
                String valueText = specialOptions.getName() + " " + initViewPosition.getCountCapsule() + " отс.";
                holder.textViewCellOption.setText(valueText);
                holder.textViewCellValue.setText(String.valueOf(price*initViewPosition.getCountCapsule()));

            }


            holder.checkBox.setOnCheckedChangeListener(null); // убираем слушателя перед инициализацией
            holder.checkBox.setChecked(specialOptions.isIs());


        }else{
            holder.textViewCellOption.setText(specialOptions.getName());


            holder.textViewCellValue.setVisibility(View.GONE);

            holder.checkBox.setOnCheckedChangeListener(null); // убираем слушателя перед инициализацией
            holder.checkBox.setVisibility(View.GONE);

            holder.viewAfterCell.setBackground(new ColorDrawable(Color.parseColor("#E81010")));
            holder.textViewCellOption.setGravity(Gravity.CENTER);
            holder.textViewCellOption.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
            holder.textViewCellOption.setTypeface(null, Typeface.BOLD);
        }
        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                specialOptions.setIs(isChecked); // Отмечаем выбор опции в итоговой коллекции

                if (onClickItemListener != null) {
                    onClickItemListener.onClickItem(specialOptions, isChecked);
                }
            }

        });







    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    interface OnClickItemListener{
        void onClickItem(SpecialLightOptions specialOptions, boolean isChecked);
    }

    public class SpecialOptionsVieHolder extends RecyclerView.ViewHolder{


        TextView textViewCellOption;
        TextView textViewCellValue;

        View viewAfterCell;
        CheckBox checkBox;



        public SpecialOptionsVieHolder(@NonNull View itemView) {
            super(itemView);
            textViewCellOption = itemView.findViewById(R.id.textViewTimePay);
            textViewCellValue = itemView.findViewById(R.id.textViewCellValue);
            viewAfterCell = itemView.findViewById(R.id.viewAfterCell);
            checkBox = itemView.findViewById(R.id.checkBoxTimePay);


        }
    }
}
