package com.example.musa.books.AppExecutors;

import android.os.Looper;
import android.support.annotation.NonNull;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.logging.Handler;

public class AppExecutor {
    public Executor DataIO;
    public Executor NetworkIO;
    public Executor MainThreadIO;
    public static AppExecutor Sinstance;
    public static Object Lock = new Object();

    public AppExecutor(Executor dataIO, Executor networkIO, Executor mainThreadIO) {
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
