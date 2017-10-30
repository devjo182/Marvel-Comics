package com.app.devjo.marvelcomics.adapter;

/**
 * Created by kjoha on 25/3/2017.
 */
import android.content.Context;
import android.media.Image;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import java.util.List;
import com.app.devjo.marvelcomics.R;
import com.app.devjo.marvelcomics.models.Comic;

public class ComicsAdapter extends RecyclerView.Adapter<ComicsAdapter.ViewHolder> {

    private List<Comic> items;
    private Context mContext;

    public ComicsAdapter(List<Comic> listComics, Context context) {
        this.items = listComics;
        this.mContext = context;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        private TextView mInfotext;
        private CardView cardView;
        private ImageView mImageView;

        public ViewHolder(View view) {
            super(view);
            cardView = (CardView) view.findViewById(R.id.card_view);
            mInfotext = (TextView) view.findViewById(R.id.info_text);
            mImageView = (ImageView) view.findViewById(R.id.image_view_comic);
        }
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ComicsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_row, parent, false);
        // set the view's size, margins, paddings and layout parameters
        //...
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        String path;
        String extension;
        try {
            List<Comic.Image> image = items.get(position).getImages();
            if(! image.get(0).equals(null) ){
                path = image.get(0).getPath();
                extension = image.get(0).getExtension();
                Glide.with(mContext).load(path + "/portrait_medium." + extension ).into( holder.mImageView);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        holder.mInfotext.setText(  String.valueOf(items.get(position).getId()) );
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

}

