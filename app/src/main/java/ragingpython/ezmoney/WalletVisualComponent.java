package ragingpython.ezmoney;

import android.content.Context;
import android.database.Cursor;
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


    public WalletVisualComponent(Context context, Cursor walletCursor) {
        super(context);
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.wallet_item, this);
        btnShowOperations = (Button) findViewById(R.id.button_showOperations);
        viewName = (TextView) findViewById(R.id.textView_walletName);
        viewBalance = (TextView) findViewById(R.id.textView_balance);
        btnShowOperations.setOnClickListener(this);

        int columnId;
        viewName.setText(walletCursor.getString(walletCursor.getColumnIndex("name")));
        viewBalance.setText(walletCursor.getString(walletCursor.getColumnIndex("balance")));
    }

    public void onClick(View view) {

    }

}
