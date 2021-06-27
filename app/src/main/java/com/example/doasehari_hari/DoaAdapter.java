package com.example.doasehari_hari;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doasehari_hari.database.Doa;

import java.util.ArrayList;
import java.util.List;

public class DoaAdapter extends RecyclerView.Adapter<DoaAdapter.DoaHolder> {

    private List<Doa> doas = new ArrayList<>();

    @NonNull
    @Override
    public DoaHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item,viewGroup, false);
        return new DoaHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull DoaAdapter.DoaHolder doaHolder, int position) {
        Doa pengenDoa = doas.get(position);
        doaHolder.title.setText(pengenDoa.getTitle());
        doaHolder.arabic.setText(pengenDoa.getArabic());
        doaHolder.latin.setText(pengenDoa.getLatin());
        doaHolder.translation.setText(pengenDoa.getTranslation());

    }

    @Override
    public int getItemCount() {
        return doas.size();
    }

    public void setDoas(List<Doa>doas){
        this.doas = doas;
        notifyDataSetChanged();
    }

    class DoaHolder extends RecyclerView.ViewHolder {
        private TextView title;
        private TextView arabic;
        private TextView latin;
        private TextView translation;

        public DoaHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tvTitle);
            arabic = itemView.findViewById(R.id.tvArabic);
            latin = itemView.findViewById(R.id.tvLatin);
            translation = itemView.findViewById(R.id.tvTerjemahan);
        }
    }
}
