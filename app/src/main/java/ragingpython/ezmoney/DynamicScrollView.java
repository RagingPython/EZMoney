package ragingpython.ezmoney;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Canvas;
import android.text.Layout;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Space;

import java.text.AttributedCharacterIterator;
import java.util.LinkedList;

public class DynamicScrollView extends ScrollView {
    private LinearLayout walletsLayout;
    private WalletVisualComponent wallet;
    private Cursor walletsData;
    private Space topSpacing;
    private Space bottomSpacing;
    private LinkedList<WalletVisualComponent> walletsList;
    private int childHeight;
    private int parentHeigth;
    private int visibleWalletsNumber;


    public DynamicScrollView(Context context, AttributeSet attr) {
        super(context,attr);
    }


    public void initialize(Context context) {
        walletsLayout=new LinearLayout(context);
        walletsLayout.setOrientation(LinearLayout.VERTICAL);
        walletsLayout.setLayoutParams(new LinearLayout.MarginLayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        topSpacing = new Space(context);
        topSpacing.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0));


        bottomSpacing = new Space(context);
        bottomSpacing.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0));

        this.addView(walletsLayout);

        WalletVisualComponent w = new WalletVisualComponent(context);

        childHeight =  66;
        parentHeigth = 0;

        walletsLayout.addView(topSpacing);

        walletsList= new LinkedList<WalletVisualComponent>();

        visibleWalletsNumber =((int) (Math.ceil(this.getHeight()/childHeight)))+1;

        for(int i=0; i<visibleWalletsNumber; i++) {
            w = new WalletVisualComponent(context);

            walletsList.addLast(new WalletVisualComponent(context));

        }

        walletsLayout.addView(bottomSpacing);
    }

    public void updateWallets(Cursor cursor){
        walletsData = cursor;
        cursor.moveToFirst();
        int a;
        if (walletsData.getCount()<visibleWalletsNumber) {
            a=walletsData.getCount();
        } else {
            a= visibleWalletsNumber;
        }
        walletsLayout.removeAllViews();
        walletsLayout.addView(topSpacing);
        for (int i=0; i<a; i++) {
            walletsLayout.addView(walletsList.get(i));
            walletsList.get(i).setContent(cursor);
            cursor.moveToNext();
        }
        walletsLayout.addView(bottomSpacing);
        ViewGroup.LayoutParams par = topSpacing.getLayoutParams();
        par.height=0;
        topSpacing.setLayoutParams(par);

        par = bottomSpacing.getLayoutParams();
        par.height=66*(walletsData.getCount()-a);
        bottomSpacing.setLayoutParams(par);

        this.scrollTo(0,0);
    }

    public void updateVisual(int l, int t, int oldl, int oldt) {
        if (Math.floor(oldt/66)!=Math.floor(t/66)) {
            int s = (int) Math.floor(t/66);

            ViewGroup.LayoutParams par = topSpacing.getLayoutParams();
            par.height=s*66;
            topSpacing.setLayoutParams(par);

            par = bottomSpacing.getLayoutParams();
            par.height=66*(walletsData.getCount()-visibleWalletsNumber-s);
            bottomSpacing.setLayoutParams(par);
        }
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        updateVisual(l, t, oldl, oldt);
        super.onScrollChanged(l, t, oldl, oldt);
    }
}
