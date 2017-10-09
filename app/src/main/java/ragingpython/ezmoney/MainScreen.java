package ragingpython.ezmoney;

import android.database.Cursor;

import android.os.Build;
import android.support.annotation.StringDef;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import RecycleScrollView.RecycleScrollView;

public class MainScreen extends AppCompatActivity implements View.OnClickListener{

    private EZDbContainer db;

    private Button btnTest;
    private Button btnTest2;
    private RecycleScrollView walletsContainer;
    private WalletVisualComponent c;
    private mRecycleScrollViewContent mRSVC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        db = EZDbContainer.getInstance(getApplicationContext());

        btnTest=(Button) findViewById(R.id.btnTest);
        btnTest2=(Button) findViewById(R.id.buttonTst2);
        walletsContainer = (RecycleScrollView) findViewById(R.id.walletsContainer);
        btnTest.setOnClickListener(this);
        btnTest2.setOnClickListener(this);

        mRSVC = new mRecycleScrollViewContent();
        walletsContainer.setContent(mRSVC);
    }


    public void onClick(View view) {
        if (view==btnTest) {
            //db.createWallet("wallet1", 50);
            mRSVC.setCursor(db.getWallets());
        }
        if (view==btnTest2) {
            btnTest2.setText(walletsContainer.debug());
        }
    }

}
