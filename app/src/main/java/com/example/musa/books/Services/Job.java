package com.example.musa.books.Services;

import android.content.Context;
import android.support.annotation.NonNull;

import com.firebase.jobdispatcher.Driver;
import com.firebase.jobdispatcher.FirebaseJobDispatcher;
import com.firebase.jobdispatcher.GooglePlayDriver;
import com.firebase.jobdispatcher.JobService;
import com.firebase.jobdispatcher.Lifetime;
import com.firebase.jobdispatcher.Trigger;

import java.util.concurrent.TimeUnit;

public class Job {
    private static  String SyncTag="BooksSyn";
    private static boolean sInstance;
    private static final int SYNC_INTERVAL_HOURS = 3;
    private static final int SYNC_INTERVAL_SECONDS = (int) TimeUnit.HOURS.toSeconds(SYNC_INTERVAL_HOURS);
    private static final int SYNC_FLEXTIME_SECONDS = SYNC_INTERVAL_SECONDS / 3;

     public static void Jobs( @NonNull Context context) {
        if (sInstance) return;
        sInstance=true;
        Driver driver=new GooglePlayDriver(context);
        FirebaseJobDispatcher firebaseJobDispatcher=new FirebaseJobDispatcher(driver);
        com.firebase.jobdispatcher.Job bookSync=firebaseJobDispatcher.newJobBuilder()
                .setService(JobServer.class)
                .setTag(SyncTag)
                .setRecurring(true)
                .setLifetime(Lifetime.FOREVER)
                .setTrigger(Trigger.executionWindow(
                        SYNC_INTERVAL_SECONDS,
                        SYNC_INTERVAL_SECONDS + SYNC_FLEXTIME_SECONDS))
                .setReplaceCurrent(true)
                .build();
        firebaseJobDispatcher.schedule(bookSync);


    }
}
