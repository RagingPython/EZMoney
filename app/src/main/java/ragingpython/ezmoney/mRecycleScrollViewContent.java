package ragingpython.ezmoney;

import android.content.Context;
import android.database.Cursor;
import android.view.View;

import RecycleScrollView.RecycleScrollViewContent;

/**
 * Created by Admin on 09.10.2017.
 */

public class mRecycleScrollViewContent extends RecycleScrollViewContent {
    Cursor cursor;
    @Override
    public View createInstance(Context context) {
        return new WalletVisualComponent(context);
    }

    public void setCursor(Cursor c) {
        cursor=c;
        update();
    }

    @Override
    public int getCount() {
        if (cursor!= null) {
            return cursor.getCount();
        } else {
            return 0;
        }
    }

    @Override
    public void updateElement(View view, int index) {
        cursor.move(index);
        ((WalletVisualComponent) view).setContent(cursor);
    }
}
