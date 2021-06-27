package com.example.doasehari_hari.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.doasehari_hari.database.Doa;
import com.example.doasehari_hari.database.DoaDao;
import com.example.doasehari_hari.database.DoaDatabase;

import java.util.List;

public class DoaRepository {

    private DoaDao doaDao;
    private LiveData<List<Doa>> allDoa;

    public DoaRepository(Application application){
        DoaDatabase database = DoaDatabase.getInstance(application);
        doaDao = database.doaDao();
        allDoa = doaDao.getAllDoa();
    }

    public void insert(Doa doa){
        new InsertDoaAsyncTask(doaDao).execute(doa);
    }

    public void delete(Doa doa){
        new DeleteDoaAsyncTask(doaDao).execute(doa);
    }

    public  LiveData<List<Doa>>getAllDoas(){
        return allDoa;
    }

    private  static class InsertDoaAsyncTask extends AsyncTask<Doa, Void, Void> {

        private DoaDao doaDao;
        private InsertDoaAsyncTask(DoaDao doaDao){
            this.doaDao = doaDao;
        }
        @Override
        protected Void doInBackground(Doa... doas) {
            doaDao.insert(doas[0]);
            return null;
        }
    }
    private  static class DeleteDoaAsyncTask extends AsyncTask<Doa, Void, Void> {

        private DoaDao doaDao;
        private DeleteDoaAsyncTask(DoaDao doaDao){
            this.doaDao = doaDao;
        }
        @Override
        protected Void doInBackground(Doa... doas) {
            doaDao.delete(doas[0]);
            return null;
        }
    }
}
