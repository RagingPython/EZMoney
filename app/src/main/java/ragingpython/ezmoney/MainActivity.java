package ragingpython.ezmoney;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import RecycleScrollView.RecycleScrollView;

public class MainActivity extends Activity {

    private DbContainer db;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        db = DbContainer.getInstance(getApplicationContext());

    }





}
