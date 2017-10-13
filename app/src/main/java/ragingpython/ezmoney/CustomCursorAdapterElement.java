package ragingpython.ezmoney;

import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.view.ViewGroup;

public interface CustomCursorAdapterElement {
    public void refreshContent(Cursor cursor, int index);

}
