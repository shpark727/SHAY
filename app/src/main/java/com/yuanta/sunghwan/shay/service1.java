package com.yuanta.sunghwan.shay;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

/**
 * Created by hwannis on 13/04/2017.
 */

public class service1  extends Service{

    private final IBinder mBinder = new LocalBinder();

    public class LocalBinder extends Binder {
        service1 getService(){
            return service1.this;
        }
    }


    public int mCurNum = 0;
    public Thread mCntThread = null;

    @Override
    public void onCreate(){
        super.onCreate();
    }


    @Override
    public int onStartCommand(Intent intent, int flags ,  int startId ){
        super.onStartCommand( intent, flags , startId) ; // 중간 intent flag값 설정필요

        if ( mCntThread == null ){
            mCntThread = new Thread("Cnt Thead"){
                public void run(){
                    try{
                        while(!Thread.currentThread().isInterrupted()){
                            mCurNum ++ ;
                            Thread.sleep(1000);
                        }
                    }

                    catch(InterruptedException ex){
                        return ;
                    }
                }
            };
            mCntThread.start();
        }
        return START_STICKY;
    }

    @Override
    public void onDestroy(){

        if(mCntThread !=null ){
            mCntThread.interrupt();
            mCntThread = null;
            mCurNum = 0 ;
        }
    }

    public IBinder onBind(Intent arg0){
        return mBinder;
    }

}
