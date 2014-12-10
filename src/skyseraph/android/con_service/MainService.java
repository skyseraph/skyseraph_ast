
package skyseraph.android.con_service;

import skyseraph.android.common.Constant;
import skyseraph.android.common.IpMessageConst;
import skyseraph.android.lib.utils.LibLogUtils2;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;

public class MainService extends Service {

    private Context mContext = null;

    public Handler mHandler = null;

    private IBinder binder = new MainService.LocalBinder();

    @Override
    public IBinder onBind(Intent intent) {
        // TODO Auto-generated method stub
        LibLogUtils2.d(Constant.TAG, "onBind");
        return binder;
    }

    public class LocalBinder extends Binder {
        public MainService getService() {
            return MainService.this;
        }
    }

    @Override
    public void onCreate() {
        // TODO Auto-generated method stub
        LibLogUtils2.d(Constant.TAG, "onCreate");
        super.onCreate();

        myInit();
        myWork();
    }

    @Override
    @Deprecated
    public void onStart(Intent intent, int startId) {
        // TODO Auto-generated method stub
        LibLogUtils2.d(Constant.TAG, "onStart");
        super.onStart(intent, startId);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // TODO Auto-generated method stub
        LibLogUtils2.d(Constant.TAG, "onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        // TODO Auto-generated method stub
        LibLogUtils2.d(Constant.TAG, "onUnbind");
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        // TODO Auto-generated method stub
        LibLogUtils2.d(Constant.TAG, "onDestroy");
        super.onDestroy();
        myDeInit();
    }

    private void sendMsg2Act(int what) {
        LibLogUtils2.d(Constant.TAG, "sendMsg:" + what);
        if (mHandler != null)
            mHandler.sendEmptyMessage(what);
    }

    private void sendMsg2Act(int what, Object obj) {
        if (mHandler != null) {
            Message msg = mHandler.obtainMessage(what, (String)obj);
            msg.sendToTarget();
        }
    }

    private void sendMsg2ActDelay(int what, int ms) {
        if (mHandler != null)
            mHandler.sendEmptyMessageDelayed(what, ms);
    }

    private void myInit() {
        // TODO Auto-generated method stub
        mContext = this;
        // start Thread
        StartThreadFromS();
        // add your code here

    }

    private void myDeInit() {
        // TODO Auto-generated method stub
        // add your code here

    }

    public void StartThreadFromA() {
        // TODO Auto-generated method stub
        new Thread(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                // add your code here

                // send msg to Activity
                sendMsg2Act(IpMessageConst.IPMSG_SEVICE_TEST_EVENT);
            }
        }).start();
    }

    public void StartThreadFromS() {
        // TODO Auto-generated method stub
        new Thread(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                // add your code here

                // send msg to Activity
                sendMsg2Act(IpMessageConst.IPMSG_SEVICE_TEST_EVENT);
            }
        }).start();
    }

    private void myWork() {
        // TODO Auto-generated method stub
        // add your code here
    }

}
