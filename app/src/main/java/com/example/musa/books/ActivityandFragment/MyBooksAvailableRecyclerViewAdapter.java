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

import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.devs.readmoreoption.ReadMoreOption;
import com.example.musa.books.AppExecutors.AppExecutor;
import com.example.musa.books.Database.VolumeDatabase;
import com.example.musa.books.Dummy.Item;
import com.example.musa.books.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class MyBooksAvailableRecyclerViewAdapter extends RecyclerView.Adapter<MyBooksAvailableRecyclerViewAdapter.ViewHolder> implements Filterable {

   public static List<VolumeDatabase>mitems;
    private OnclickLIstener lIstener;
    private Context mcontext;


    public MyBooksAvailableRecyclerViewAdapter(  Context mcontext,OnclickLIstener lIstener) {
        this.lIstener = lIstener;
        this.mcontext = mcontext;
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
        holder.mTitleView.setText(mitems.get(position).getTitle());
        ReadMoreOption readMoreOption = new ReadMoreOption.Builder(mcontext)
                .textLength(300)
                .moreLabel("read more")
                .lessLabel("less")
                .moreLabelColor(Color.GREEN)
                .lessLabelColor(Color.BLUE)
                .labelUnderLine(true)
                .build();
        readMoreOption.addReadMoreTo(holder.mAuthorView,mitems.get(position).getDescription());

       Picasso.with(mcontext).load(Uri.parse( mitems.get(position).getImageUrl()).buildUpon().build()).fit().into(holder.imageView);
       holder.Popup.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               android.support.v7.widget.PopupMenu popupMenu=new android.support.v7.widget.PopupMenu(mcontext,v.findViewById(R.id.appCompatImageButton));
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
        return mitems;
    }

    public  void setMitems(List<VolumeDatabase> mitems) {
        MyBooksAvailableRecyclerViewAdapter.mitems = mitems;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (mitems!=null){
            return mitems.size();
        }
        else
        return 0;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String search =constraint.toString();
                if (search.isEmpty()){
                    mitems=getMitems();
                }
                else {
                    List<VolumeDatabase> booksvolume =new ArrayList<>();
                    for (VolumeDatabase row : mitems){
                        if (row.getTitle().toLowerCase().contains(search.toLowerCase())){
                            booksvolume.add(row);
                        }
                    }
                    setMitems(booksvolume);
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = mitems;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                setMitems((ArrayList<VolumeDatabase>)results.values);
                notifyDataSetChanged();

            }
        };

    }

    public interface OnclickLIstener{
        void OncardClick(String Uri);
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
                String Uri = mitems.get(getAdapterPosition()).getWebLink();
                lIstener.OncardClick(Uri);
            }

        }
    }
}