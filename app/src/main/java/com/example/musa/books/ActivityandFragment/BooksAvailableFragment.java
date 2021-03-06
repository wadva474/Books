package com.example.musa.books.ActivityandFragment;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.musa.books.Database.BooksDatabase;
import com.example.musa.books.Database.VolumeDatabase;
import com.example.musa.books.R;
import com.example.musa.books.Services.BooksService;
import com.example.musa.books.Services.Job;
import com.example.musa.books.ViewModels.MainViewModel;
import com.example.musa.books.ViewModels.SearchViewModel;
import com.example.musa.books.ViewModels.SearchViewModelFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class BooksAvailableFragment extends Fragment implements MyBooksAvailableRecyclerViewAdapter.OnclickListener {

    // TODO: Customize parameters
   RecyclerView recyclerView;
    public MyBooksAvailableRecyclerViewAdapter adapter;
    private BooksDatabase booksDatabase;




    public BooksAvailableFragment() {
    }



    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_booksavailable_list, container, false);
        recyclerView=view.findViewById(R.id.recyclerview);
        booksDatabase=BooksDatabase.getsInstance(getContext());
        setHasOptionsMenu(true);
        LinearLayoutManager layoutManager=new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        adapter=new MyBooksAvailableRecyclerViewAdapter(getContext(),this);
        recyclerView.setAdapter(adapter);
        DividerItemDecoration decoration=new DividerItemDecoration(Objects.requireNonNull(getContext()),DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(decoration);
        recyclerView.hasFixedSize();
        setupViewModel();
        if (adapter.getItemCount()==0){
            startImmediateSync(getContext());
        }
        Job.Jobs(getContext());
           return view;
        }


    @Override
    public void onCardClick(String Uri) {
        Intent intent=new Intent(Intent.ACTION_VIEW);
        intent.setData(android.net.Uri.parse(Uri));
        startActivity(intent);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_main,menu);
        MenuItem item=menu.findItem(R.id.action_search);
        SearchView sv = new SearchView(((MainActivity) getActivity()).getSupportActionBar().getThemedContext());
        MenuItemCompat.setShowAsAction(item, MenuItemCompat.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW | MenuItemCompat.SHOW_AS_ACTION_IF_ROOM);
        MenuItemCompat.setActionView(item, sv);
        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                setupSearchViewModel(query,booksDatabase);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
               setupSearchViewModel(newText,booksDatabase);
                return true;
            }
        });
    }


    public void setupViewModel(){
        MainViewModel mainViewModel= ViewModelProviders.of(this).get(MainViewModel.class);
        mainViewModel.getBooksLivedata().observe(this, new Observer<List<VolumeDatabase>>() {
            @Override
            public void onChanged(@Nullable List<VolumeDatabase> volumeDatabases) {
               adapter.setMitems(volumeDatabases);
            }
        });
    }
    public void setupSearchViewModel(String searched,BooksDatabase booksDatabase){
        SearchViewModelFactory searchViewModelFactory=new SearchViewModelFactory(booksDatabase,searched);
        SearchViewModel searchViewModel=searchViewModelFactory.create(SearchViewModel.class);
        searchViewModel.getSearchLiveData().observe(this, new Observer<ArrayList<VolumeDatabase>>() {
            @Override
            public void onChanged(@Nullable ArrayList<VolumeDatabase> volumeDatabases) {
                adapter.setMitems(volumeDatabases);
            }
        });

    }

    public static void startImmediateSync(@NonNull final Context context) {
        Intent intentToSyncImmediately = new Intent(context,BooksService.class);
        context.startService(intentToSyncImmediately);
    }
}
