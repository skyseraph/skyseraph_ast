
package skyseraph.android.ui;

import skyseraph.android.common.IpMessageConst;
import skyseraph.android.con_service.ConObj;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;

public class MainActivity extends Activity {
    private Context mContext = null;

    private MainHandler mMainHandler = null;

    private ConObj mConObj = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
        mMainHandler = new MainHandler();
        mConObj = new ConObj(mContext);
        mConObj.InitConObj(mMainHandler);
    }

    @Override
    protected void onStop() {
        // TODO Auto-generated method stub
        super.onStop();
        mConObj.DeInitConObj();
    }

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    private void setThreadStart() {
        // changeStateTo(STARTING_STATE);
        mConObj.SetThreadStart();
    }
    
    private class MainHandler extends Handler {
        public MainHandler() {

        }

        @Override
        public void handleMessage(Message msg) {
            // TODO Auto-generated method stub
            // super.handleMessage(msg);
            switch (msg.what) {
                case IpMessageConst.IPMSG_SEVICE_BIND_EVENT:
                    // add here
                    break;
                case IpMessageConst.IPMSG_SEVICE_UNBIND_EVENT:
                    // add here
                    break;

                default:
                    break;
            }
        }
    }

}
