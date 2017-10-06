package ragingpython.ezmoney;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainScreen extends AppCompatActivity implements View.OnClickListener{

    private EZDbContainer dbConnector;

    private Button btnTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
        
        btnTest=(Button) findViewById(R.id.btnTest);
        btnTest.setOnClickListener(this);
    }

    public void onClick(View view) {
        if (view==btnTest) {
            EZDbContainer db = EZDbContainer.getInstance(getApplicationContext());
            Toast.makeText(this, String.valueOf(db.createWallet("test1",0)), Toast.LENGTH_SHORT).show();
        }
    }
}
