package RecycleScrollView;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import ragingpython.ezmoney.R;

public class WalletVisualComponent extends RelativeLayout implements View.OnClickListener {
    private Button btnShowOperations;
    private TextView viewName;
    private TextView viewBalance;


    public WalletVisualComponent(Context context) {
        super(context);
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.wallet_item, this);
        btnShowOperations = (Button) findViewById(R.id.button_showOperations);
        viewName = (TextView) findViewById(R.id.textView_walletName);
        viewBalance = (TextView) findViewById(R.id.textView_balance);
        btnShowOperations.setOnClickListener(this);
    }

    public void setContent(Cursor cursor) {
        viewName.setText(cursor.getString(cursor.getColumnIndex("name")));
        viewBalance.setText(cursor.getString(cursor.getColumnIndex("balance")));
    }

    public void onClick(View view) {

    }

}
