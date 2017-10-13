package ragingpython.ezmoney;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import RecycleScrollView.OnUpdateContentListener;

public class MainFragment extends android.app.Fragment {

    private OnFragmentSpeaksListener activityCallback;
    private ListView walletsList;
    private WalletsAdapter walletsAdapter;

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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main, container, false);
        walletsList = v.findViewById(R.id._walletsList);
        walletsAdapter = new WalletsAdapter(container.getContext());
        walletsList.setAdapter(walletsAdapter);
        return v;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activityCallback = (OnFragmentSpeaksListener) getActivity();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


}
