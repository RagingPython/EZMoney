package ragingpython.ezmoney;

import android.content.Context;
import android.database.Cursor;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class WalletsElement extends RelativeLayout implements CustomCursorAdapterElement, View.OnClickListener {
    TextView T1, T2;
    Button B;


    public WalletsElement(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }


    @Override
    public void refreshContent(Cursor cursor) {
        if (T1==null) {
            B = findViewById(R.id.button_showOperations);
            T1 = findViewById(R.id.textView_walletName);
            T2 = findViewById(R.id.textView_balance);
            B.setOnClickListener(this);
        }
        if (cursor!=null) {
            T1.setText(cursor.getString(cursor.getColumnIndex("name")));
            T2.setText(cursor.getString(cursor.getColumnIndex("balance")));
        } else {
            T1.setText("");
            T1.setText("");
        }
    }

    @Override
    public void onClick(View view) {

    }
}
