package com.tonicartos.superslimexample;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tonicartos.superslim.LayoutManager;

/**
 * Fragment that displays a list of country names.
 */
public class CountriesFragment extends Fragment {


    private CountryNamesAdapter mAdapter;

    private int mHeaderDisplay;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


            mHeaderDisplay = getResources().getInteger(R.integer.default_header_display);

        RecyclerView     mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        final LayoutManager layout = new LayoutManager(getActivity());
        mRecyclerView.setLayoutManager(layout);


        mAdapter = new CountryNamesAdapter(getActivity(), mHeaderDisplay);


        mRecyclerView.setAdapter(mAdapter);

    }

}
