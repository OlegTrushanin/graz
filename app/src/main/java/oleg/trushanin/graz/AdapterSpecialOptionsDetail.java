package oleg.trushanin.graz;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class AdapterSpecialOptionsDetail extends RecyclerView.Adapter <AdapterSpecialOptionsDetail.SpecialOptionsDetailVieHolder>{




    public void setList(List<SpecialLightOptions> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    List<SpecialLightOptions> list = new ArrayList<>();



    @NonNull
    @Override
    public SpecialOptionsDetailVieHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.special_options_item,
                parent,
                false);


        return new SpecialOptionsDetailVieHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SpecialOptionsDetailVieHolder holder, int position) {


        holder.textViewDopOption.setText(list.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class SpecialOptionsDetailVieHolder extends RecyclerView.ViewHolder{


        TextView textViewDopOption;



        public SpecialOptionsDetailVieHolder(@NonNull View itemView) {
            super(itemView);
            textViewDopOption = itemView.findViewById(R.id.textViewDopOption);



        }
    }
}
