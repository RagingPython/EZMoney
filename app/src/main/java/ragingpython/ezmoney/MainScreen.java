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

public class MainScreen extends AppCompatActivity implements View.OnClickListener, View.OnTouchListener{

    private EZDbContainer db;

    private Button btnTest;
    private Button btnTest2;
    private DynamicScrollView walletsContainer;
    private WalletVisualComponent c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        db = EZDbContainer.getInstance(getApplicationContext());

        btnTest=(Button) findViewById(R.id.btnTest);
        btnTest2=(Button) findViewById(R.id.buttonTst2);
        walletsContainer = (DynamicScrollView) findViewById(R.id.walletsContainer);
        btnTest.setOnClickListener(this);
        btnTest2.setOnClickListener(this);


        final ViewTreeObserver vto = walletsContainer.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                walletsContainer.initialize(getApplicationContext());
                ViewTreeObserver obs = walletsContainer.getViewTreeObserver();
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    obs.removeOnGlobalLayoutListener(this);
                } else {
                    obs.removeGlobalOnLayoutListener(this);
                }

            }
        });
    }


    public void onClick(View view) {
        if (view==btnTest) {
            //db.createWallet("wallet1", 50);
            refreshWallets();
        }
        if (view==btnTest2) {
            ((Button) view).setText(String.valueOf(c.getHeight())+" "+String.valueOf((getApplicationContext().getResources().getDisplayMetrics().ydpi/72.0f)));
            btnTest.setText(String.valueOf(walletsContainer.getHeight()));
        }
    }

    public boolean onTouch(View view, MotionEvent motion) {
        return false;
    }

    public void refreshWallets() {
        Cursor cursor = db.getWallets();
        cursor.moveToFirst();

        walletsContainer.updateWallets(cursor);

    }
}
