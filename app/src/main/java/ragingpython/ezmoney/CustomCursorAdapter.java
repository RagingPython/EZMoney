package ragingpython.ezmoney;

import android.content.Context;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;


public class CustomCursorAdapter extends BaseAdapter {

    Context context;
    LayoutInflater inflater;
    int layoutId;
    Cursor cursor = null;




    public CustomCursorAdapter(Context ctx, int itemLayoutId) {
        context=ctx;
        layoutId=itemLayoutId;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        if (cursor== null) {return 0;}
        return cursor.getCount();
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        if (i<0) {return null;}
        View view = convertView;
        if (view==null) {
            view = inflater.inflate(layoutId, viewGroup,false);
        }
        cursor.moveToPosition(i);
        ((CustomCursorAdapterElement) view).refreshContent(cursor);

        return view;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public void setCursor(Cursor cur) {
        cursor=cur;
        this.notifyDataSetChanged();
    }
}

