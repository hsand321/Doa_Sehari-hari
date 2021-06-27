package com.example.doasehari_hari;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.doasehari_hari.database.Doa;
import com.example.doasehari_hari.repository.DoaRepository;

import java.util.List;


public class DoaViewModel extends AndroidViewModel {

    private DoaRepository repository;
    private LiveData<List<Doa>>allDoas;

    public DoaViewModel(@NonNull Application application) {
        super(application);
        repository = new DoaRepository(application);
        allDoas = repository.getAllDoas();
    }

    public void insert(Doa doa){
        repository.insert(doa);
    }

    public void delete(Doa doa){
        repository.delete(doa);
    }

    public LiveData<List<Doa>>getAllDoa(){
        return allDoas;
    }
}
