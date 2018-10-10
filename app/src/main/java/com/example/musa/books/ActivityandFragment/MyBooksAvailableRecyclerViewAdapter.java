package com.example.musa.books.ActivityandFragment;

import android.content.Context;

import android.graphics.Color;
import android.net.Uri;
import android.support.annotation.NonNull;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.TextView;

import com.devs.readmoreoption.ReadMoreOption;
import com.example.musa.books.AppExecutors.AppExecutor;
import com.example.musa.books.Database.BooksDatabase;
import com.example.musa.books.Database.FavouriteDatabase;
import com.example.musa.books.Database.VolumeDatabase;
import com.example.musa.books.R;
import com.squareup.picasso.Picasso;

import java.util.List;


public class MyBooksAvailableRecyclerViewAdapter extends RecyclerView.Adapter<MyBooksAvailableRecyclerViewAdapter.ViewHolder>{

   public   static List<VolumeDatabase> mItems;
    private OnclickListener listener;
    private Context mContext;


    public MyBooksAvailableRecyclerViewAdapter(Context mContext, OnclickListener listener) {
        this.listener = listener;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_booksavailable, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        holder.mTitleView.setText(mItems.get(position).getTitle());
        ReadMoreOption readMoreOption = new ReadMoreOption.Builder(mContext)
                .textLength(150)
                .moreLabel("read more")
                .lessLabel("less")
                .moreLabelColor(Color.RED)
                .lessLabelColor(Color.BLUE)
                .labelUnderLine(true)
                .build();
        readMoreOption.addReadMoreTo(holder.mAuthorView, mItems.get(position).getDescription());

       Picasso.with(mContext).load(Uri.parse( mItems.get(position).getImageUrl()).buildUpon().build()).fit().into(holder.imageView);
        holder.Popup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                android.support.v7.widget.PopupMenu popupMenu=new android.support.v7.widget.PopupMenu(mContext,holder.Popup);
                popupMenu.getMenuInflater().inflate(R.menu.popupmenu,popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new android.support.v7.widget.PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        int id =item.getItemId();
                        switch (id){
                            case R.id.addToFavourite:
                                AppExecutor.getSinstance().getDataIO().execute(new Runnable() {
                                    @Override
                                    public void run() {
                                        BooksDatabase.getsInstance(mContext).favouriteDao().insertFavourite
                                                (new FavouriteDatabase(mItems.get(holder.getAdapterPosition()).getImageUrl()
                                                        , mItems.get(holder.getAdapterPosition()).getTitle()
                                                        , mItems.get(holder.getAdapterPosition()).getWebLink(), mItems.get(holder.getAdapterPosition()).getDescription()));
                                    }
                                });
                        }
                        return true;
                    }
                });

            }
        });


           }

    public static List<VolumeDatabase> getMitems() {
        return mItems;
    }

    public  void setMitems(List<VolumeDatabase> mitems) {
        MyBooksAvailableRecyclerViewAdapter.mItems = mitems;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (mItems !=null){
            return mItems.size();
        }
        else
        return 0;
    }



    public interface OnclickListener {
        void onCardClick(String Uri);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private  TextView mTitleView;
        private  TextView mAuthorView;
        private ImageView imageView;
        private android.support.v7.widget.AppCompatImageButton Popup;


        private ViewHolder(View itemView) {
            super(itemView);
            mTitleView = itemView.findViewById(R.id.title);
            mAuthorView = itemView.findViewById(R.id.author);
            imageView = itemView.findViewById(R.id.image);
            Popup=itemView.findViewById(R.id.appCompatImageButton);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (v==itemView) {
                String Uri = mItems.get(getAdapterPosition()).getWebLink();
                listener.onCardClick(Uri);
            }

        }
    }
}