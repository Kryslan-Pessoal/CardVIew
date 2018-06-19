package com.aperam.kryslan.praticaspadrao.tools;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Handler;
import android.widget.Toast;

import java.util.List;

public class Utils {

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
                            Toast.makeText(c, "Screenshot captured!!", Toast.LENGTH_LONG).show();
                        }
                    }
                    h.postDelayed(this, delay);
                }
            }, delay);
        }
    }
}
