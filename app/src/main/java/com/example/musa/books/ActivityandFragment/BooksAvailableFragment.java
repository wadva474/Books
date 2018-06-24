package com.example.musa.books;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.musa.books.Dummy.AvailableBooks;
import com.example.musa.books.Dummy.Item;
import com.example.musa.books.Retrofit.RetrofitBuilder;
import com.example.musa.books.Retrofit.UrlCall;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class BooksAvailableFragment extends Fragment{

    // TODO: Customize parameters
   RecyclerView recyclerView;
    List<Item> itemList=new ArrayList<>();
    private String BookSearch;



    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public BooksAvailableFragment() {
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_booksavailable_list, container, false);
        recyclerView=view.findViewById(R.id.recyclerview);
        if (getArguments()!=null) {
             BookSearch = getArguments().getString("Query");
        }
        LinearLayoutManager layoutManager=new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        Retrofit retrofit= new RetrofitBuilder().Builder();
        UrlCall volume =retrofit.create(UrlCall.class);
        Call<AvailableBooks> Vbooks = volume.volumes("Programming");
        Vbooks.enqueue(new Callback<AvailableBooks>() {
                @Override
                public void onResponse(Call<AvailableBooks> call, Response<AvailableBooks> response) {

                    try {
                        itemList.clear();
                        itemList.addAll(response.body().getItems());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<AvailableBooks> call, Throwable t) {
                    Toast.makeText(getContext(), "CHECK YOUR INTERNET CONNECTION", Toast.LENGTH_LONG).show();
                }
            });

        MyBooksAvailableRecyclerViewAdapter adapter=new MyBooksAvailableRecyclerViewAdapter(getContext(),itemList);
        recyclerView.setAdapter(adapter);
        DividerItemDecoration decoration=new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(decoration);
        recyclerView.hasFixedSize();
        adapter.notifyDataSetChanged();
           return view;
        }






}
