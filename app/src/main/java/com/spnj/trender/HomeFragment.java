package com.spnj.trender;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    private static final String TAG = "HomeFragment";

    private TextView mTextMessage;
    private Toolbar mToolbar;

    private ArrayList<String> mName = new ArrayList<>();

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        //Toolbar
        mToolbar = (Toolbar) getActivity().findViewById(R.id.tb);
        mToolbar.setTitle("Trender");
        setHasOptionsMenu(true);
        //

        initUsername();
        //RecyclerView
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_home);
        HomeFragAdapter homeAdapter = new HomeFragAdapter(getActivity(), mName);
        recyclerView.setAdapter(homeAdapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        return view;
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.home_option, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home_search:
                Toast.makeText(getActivity(), "Search", Toast.LENGTH_SHORT).show();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void initUsername() {
        Log.d(TAG, "initUsername: preparing username");

        mName.add("Alvin");
        mName.add("Azka");
        mName.add("Savira");
        mName.add("Hanas");
        mName.add("Raihan");
        mName.add("Budi");
        mName.add("Umam");

    }

}
