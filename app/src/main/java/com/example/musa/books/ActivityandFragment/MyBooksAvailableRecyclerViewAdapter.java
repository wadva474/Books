package com.example.musa.books;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.ShareCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.musa.books.Dummy.Item;
import com.example.musa.books.Dummy.VolumeInfo;
import com.squareup.picasso.Picasso;

import java.util.List;


public class MyBooksAvailableRecyclerViewAdapter extends RecyclerView.Adapter<MyBooksAvailableRecyclerViewAdapter.ViewHolder> {

    private  List<Item>mitems;
    OnclickLIstener lIstener
    private Context mcontext;


    public MyBooksAvailableRecyclerViewAdapter(List<Item> mitems, OnclickLIstener lIstener, Context mcontext) {
        this.mitems = mitems;
        this.lIstener = lIstener;
        this.mcontext = mcontext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_booksavailable, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.mTitleView.setText(mitems.get(position).getVolumeInfo().getTitle());
        holder.mAuthorView.setText(mitems.get(position).getVolumeInfo().getPublisher());
       Picasso.with(mcontext).load(Uri.parse( mitems.get(position).getVolumeInfo().getImageLinks().getSmallThumbnail())).fit().into(holder.imageView);
//
//        holder.mCardView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent=new Intent(Intent.ACTION_VIEW);
//                intent.setData(Uri.parse(mitems.get(position).getVolumeInfo().getPreviewLink()));
//                mcontext.startActivity(intent);
//            }
//
//        });
    }
    public void setMitems(List<Item> mitems) {
        this.mitems = mitems;
    }

    @Override
    public int getItemCount() {
        if (mitems!=null){
            return mitems.size();
        }
        else
        return 0;
    }
    public interface OnclickLIstener{
        void OncardClick(int Position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements AdapterView.OnItemClickListener {
        private final CardView mCardView;
        private final TextView mTitleView;
        private final TextView mAuthorView;
        private final ImageView imageView;


        private ViewHolder(View itemView) {
            super(itemView);
            mCardView = itemView.findViewById(R.id.CardView);
            mTitleView = itemView.findViewById(R.id.title);
            mAuthorView = itemView.findViewById(R.id.author);
            imageView = itemView.findViewById(R.id.image);
            itemView.setOnClickListener(this);
        }

    }
}