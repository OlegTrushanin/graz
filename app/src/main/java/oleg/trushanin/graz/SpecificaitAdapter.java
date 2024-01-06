package oleg.trushanin.graz;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class SpecificaitAdapter extends RecyclerView.Adapter<SpecificaitAdapter.HolderSpecificationAdapter>{


    public void setSpecificaitList(List<DataVisualPair> specificaitList) {
        this.specificaitList = specificaitList;
        notifyDataSetChanged();
    }

    private List<DataVisualPair> specificaitList = new ArrayList<>();

    @NonNull
    @Override
    public HolderSpecificationAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.specification_item1,
                parent,
                false);


        return new HolderSpecificationAdapter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderSpecificationAdapter holder, int position) {

        DataVisualPair dataVisualPair = specificaitList.get(position);

        holder.textViewCellOption.setText(dataVisualPair.getName());
        holder.textViewCellValue.setText(dataVisualPair.getValue());




    }

    @Override
    public int getItemCount() {
        return specificaitList.size();
    }

    public class HolderSpecificationAdapter extends RecyclerView.ViewHolder{

        TextView textViewCellOption;
        TextView textViewCellValue;


        public HolderSpecificationAdapter(@NonNull View itemView) {
            super(itemView);
            textViewCellOption = itemView.findViewById(R.id.textViewTimePay);
            textViewCellValue = itemView.findViewById(R.id.textViewCellValue);

        }
    }


}
