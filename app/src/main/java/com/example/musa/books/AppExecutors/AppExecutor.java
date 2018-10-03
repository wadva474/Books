package com.example.musa.books.AppExecutors;

import android.os.Looper;
import android.support.annotation.NonNull;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.logging.Handler;

public class AppExecutor {
    private  Executor DataIO;
    private  Executor NetworkIO;
    private  Executor MainThreadIO;
    private  static AppExecutor Sinstance;
    private  static Object Lock = new Object();

    private  AppExecutor(Executor dataIO, Executor networkIO, Executor mainThreadIO) {
        DataIO = dataIO;
        NetworkIO = networkIO;
        MainThreadIO = mainThreadIO;
    }

    public static AppExecutor getSinstance() {
        if (Sinstance == null) {
            synchronized (Lock) {
                Sinstance = new AppExecutor(Executors.newSingleThreadExecutor(), Executors.newFixedThreadPool(3), new MainExecutor());
            }
        }
        return Sinstance;
    }


    public Executor getDataIO() {
        return DataIO;
    }

    public Executor getNetworkIO() {
        return NetworkIO;
    }

    private static class MainExecutor implements Executor {
       private android.os.Handler handler=new android.os.Handler(Looper.getMainLooper());
        @Override
        public void execute(@NonNull Runnable command) {
            handler.post(command);
        }
    }
}
