
package skyseraph.android.con_service;

import skyseraph.android.common.Constant;
import skyseraph.android.common.IpMessageConst;
import skyseraph.android.lib.utils.LibLogUtils2;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;

public class ConObj {
    private Context mContext = null;

    private Handler mHandle = null;

    public MainService mMainService = null;

    private boolean m_bMainService = false;

    public ConObj(Context ct) {
        mContext = ct;
        LibLogUtils2.d(Constant.TAG, "ConObj");
    }

    public Boolean InitConObj(Handler hnd) {
        LibLogUtils2.d(Constant.TAG, "InitConObj");
        if (mContext == null)
            return false;
        mHandle = hnd;
        bind2Service();
        return true;
    }

    public Boolean DeInitConObj() {
        LibLogUtils2.d(Constant.TAG, "DeInitConObj");
        unBind2Service();
        return true;
    }

    private void unBind2Service() {
        // TODO Auto-generated method stub
        LibLogUtils2.d(Constant.TAG, "unBind2Service");
        if (m_bMainService) {
            mContext.unbindService(conn);
            m_bMainService = false;
        }
    }

    private void bind2Service() {
        // TODO Auto-generated method stub
        Intent intent = new Intent(mContext, MainService.class);
        mContext.startService(intent);
        mContext.bindService(intent, conn, Context.BIND_AUTO_CREATE);
    }

    private ServiceConnection conn = new ServiceConnection() {

        @Override
        public void onServiceDisconnected(ComponentName name) {
            // TODO Auto-generated method stub
            LibLogUtils2.d(Constant.TAG, "onServiceDisconnected");
            mMainService = null;
            m_bMainService = false;
            if (mHandle != null)
                mHandle.sendEmptyMessage(IpMessageConst.IPMSG_SEVICE_UNBIND_EVENT);
        }

        @Override
        public void onServiceConnected(ComponentName componentName, IBinder binder) {
            // TODO Auto-generated method stub
            LibLogUtils2.d(Constant.TAG, "onServiceConnected");
            mMainService = ((MainService.LocalBinder)binder).getService();
            m_bMainService = true;
            if (mHandle != null)
                mHandle.sendEmptyMessage(IpMessageConst.IPMSG_SEVICE_BIND_EVENT);
            mMainService.mHandler = mHandle;
        }
    };

    public void SetThreadStart() {
        // TODO Auto-generated method stub
        if (mMainService == null) {
            LibLogUtils2.e(Constant.TAG, "SetThreadStart==null");
            return;
        } else {
            LibLogUtils2.d(Constant.TAG, "SetThreadStart");
            mMainService.StartThreadFromA();
        }
    }
}
