package com.aperam.kryslan.praticaspadrao.tools;

import android.app.ActivityManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.widget.Toast;

import java.util.List;


public class OnClearFromRecentService extends Service {

    boolean appExit = false;
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        appExit = true;
        super.onDestroy();
    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {  //Quando removemos o app da lista de apps recentes na memória.
        appExit = true;
        stopSelf();
    }

    public void ScreenshotListener(final Context c) {
        final ActivityManager am = (ActivityManager) c.getSystemService(Context.ACTIVITY_SERVICE);

        if (am != null) {
            final Handler h = new Handler();
            final int delay = 3000; //milesegundos
            h.postDelayed(new Runnable() {
                public void run() {
                    List<ActivityManager.RunningServiceInfo> rs = am.getRunningServices(200);
                    for (ActivityManager.RunningServiceInfo ar : rs) {
                        if (ar.process.equals("com.android.systemui:screenshot")) {
                            Toast.makeText(c, "Screenshot tirado!!!", Toast.LENGTH_LONG).show();
                        }
                        if(appExit){  //Aqui, ele para o Listener de ScreenShot caso o app seja encerrado.
                            break;
                        }
                    }
                    h.postDelayed(this, delay);
                }
            }, delay);
        }
    }
}