package com.example.musa.books.ActivityandFragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.musa.books.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavouriteBooks extends Fragment {


    public FavouriteBooks() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favourite_books, container, false);
    }

}
