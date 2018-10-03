package com.example.musa.books.Services;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;

import com.example.musa.books.Dummy.AvailableBooks;
import com.example.musa.books.Retrofit.RetrofitBuilder;
import com.example.musa.books.Retrofit.UrlCall;
import com.firebase.jobdispatcher.JobParameters;
import com.firebase.jobdispatcher.JobService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class JobServer extends JobService {
    private AsyncTask<Void, Void, Void> mFetchWeatherTask;
    @SuppressLint("StaticFieldLeak")
    @Override
    public boolean onStartJob(final JobParameters job) {
        mFetchWeatherTask = new AsyncTask<Void, Void, Void>(){
            @Override
            protected Void doInBackground(Void... voids) {
                Context context = getApplicationContext();
                BooksSyncing.BookSyncing(context);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                //  COMPLETED (6) Once the weather data is sync'd, call jobFinished with the appropriate arguements
                jobFinished(job, false);
            }
        };

        mFetchWeatherTask.execute();
        return true;
    }



    @Override
    public boolean onStopJob(JobParameters job) {

        if (mFetchWeatherTask != null) {
            mFetchWeatherTask.cancel(true);
        }
        return true;
    }
}
