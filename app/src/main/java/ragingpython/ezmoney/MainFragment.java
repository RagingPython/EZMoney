package ragingpython.ezmoney;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

public class MainFragment extends android.app.Fragment {

    private OnFragmentSpeaksListener activityCallback;
    private ListView walletsList;
    private CustomCursorAdapter customCursorAdapter;
    private DbContainer db;
    private Button bb;

    public MainFragment() {
    }

    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();
        //Bundle args = new Bundle();
        //fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = DbContainer.getInstance(null);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main, container, false);
        walletsList = v.findViewById(R.id._walletsList);
        walletsList.setDividerHeight(0);
        customCursorAdapter = new CustomCursorAdapter(container.getContext(), R.layout.wallet_item);
        walletsList.setAdapter(customCursorAdapter);
        bb= v.findViewById(R.id.button3);
        return v;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activityCallback = (OnFragmentSpeaksListener) getActivity();
    }

    @Override
    public void onStart() {
        super.onStart();
        customCursorAdapter.setCursor(db.getWallets());
        bb.setText("123");
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


}
