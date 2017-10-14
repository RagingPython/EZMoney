package ragingpython.ezmoney;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentController;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;

public class MainActivity extends Activity implements OnFragmentSpeaksListener{

    private DbContainer db;
    private FragmentManager fragmentManager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = DbContainer.getInstance(getApplicationContext());

        fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id._fragmentContainer, new MainFragment(), "main");
        transaction.commit();
    }

    @Override
    public void onFragmentSpeaks(Fragment fragment, Bundle speech) {

    }
}
