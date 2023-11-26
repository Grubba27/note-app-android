package com.example.noteapp;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * A fragment representing a list of Items.
 */
public class NotaFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String id = "item_number";
    private static final String text = "content";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private String mValue = "";

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public NotaFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static NotaFragment newInstance(int columnCount, String txt) {
        NotaFragment fragment = new NotaFragment();
        Bundle args = new Bundle();
        args.putInt(id, columnCount);
        args.putString(text, txt);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(id);
            mValue = getArguments().getString(text);
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.nota_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            ArrayList<Nota> items = new ArrayList<Nota>();
            recyclerView.setAdapter(new NotaRecyclerViewAdapter(items));
        }

        return view;
    }

}
