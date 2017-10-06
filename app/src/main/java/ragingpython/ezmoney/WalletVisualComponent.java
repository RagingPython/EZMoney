package ragingpython.ezmoney;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class WalletVisualComponent extends RelativeLayout implements View.OnClickListener {
    private Button btnShowOperations;
    private TextView viewName;
    private TextView viewBalance;
    private float bal;


    public WalletVisualComponent(Context context, String name, Float balance) {
        super(context);
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.wallet_item, this);
        btnShowOperations = (Button) findViewById(R.id.button_showOperations);
        viewName = (TextView) findViewById(R.id.textView_walletName);
        viewBalance = (TextView) findViewById(R.id.textView_balance);
        btnShowOperations.setOnClickListener(this);
        viewName.setText(name);
        viewBalance.setText(String.valueOf(balance));
        bal = balance;
    }

    public void onClick(View view) {
        bal=bal+1;
        viewBalance.setText(String.valueOf(bal));
    }

}
