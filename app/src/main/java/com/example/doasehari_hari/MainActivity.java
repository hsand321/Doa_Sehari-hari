package com.example.doasehari_hari;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.doasehari_hari.database.Doa;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private DoaViewModel doaViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.rvListDoa);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        DoaAdapter adapter = new DoaAdapter();
        recyclerView.setAdapter(adapter);

        doaViewModel = ViewModelProviders.of(this).get(DoaViewModel.class);
        doaViewModel.getAllDoa().observe(this, new Observer<List<Doa>>() {
            @Override
            public void onChanged(List<Doa> doas) {
                adapter.setDoas(doas);
            }
        });
    }
}