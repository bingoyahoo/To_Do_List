package com.larrylow.android.todolist;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    ArrayAdapter<String> mTaskAdapter;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.fragment_main, container, false);

        //Create the fake data
        String[] fakeData = {
                "Revise for exam",
                "Buy milk",
                "Do laundry",
                "Call Melissa",
                "Buy stamps",
        };
        //Create the ArrayAdapter by telling "how" (layout),"where" (textview), and " what" (data)
        List<String> tasks = new ArrayList<String>(Arrays.asList(fakeData));
        mTaskAdapter = new ArrayAdapter<String>(
                getActivity(),
                R.layout.list_item_task,
                R.id.list_item_task_textview,
                tasks);

        //Still need to bind adapter to the ListView
        ListView listView = (ListView) rootview.findViewById(R.id.listview_tasks);
        listView.setAdapter(mTaskAdapter);

        return rootview;
    }
}
