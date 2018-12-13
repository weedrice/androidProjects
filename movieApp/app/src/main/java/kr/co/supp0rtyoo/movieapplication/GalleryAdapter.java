package kr.co.supp0rtyoo.movieapplication;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.GalleryViewHolder> {
    Context context;
    ArrayList<GalleryItem> items = new ArrayList<GalleryItem>();

    OnItemClickListener listener;

    public GalleryAdapter(Context context) {
        this.context = context;
    }

    public interface OnItemClickListener {
        public void onItemClick(GalleryViewHolder holder, View view, int position);
    }

    @NonNull
    @Override
    public GalleryViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.gallery_contents, viewGroup, false);

        return new GalleryViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull GalleryViewHolder viewHolder, int i) {
        GalleryItem item = items.get(i);
        viewHolder.setItem(item);

        viewHolder.setOnItemClickListener(listener);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void addItem(GalleryItem item) {
        items.add(item);
    }

    public GalleryItem getItem(int position) {
        return items.get(position);
    }

    public ArrayList<GalleryItem> getItems() {
        return items;
    }

    public void addItems(ArrayList<GalleryItem> items) {
        this.items = items;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    static public class GalleryViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        String url;
        int type;
        OnItemClickListener listener;

        public GalleryViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.galleryImageView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();

                    if(listener != null) {
                        listener.onItemClick(GalleryViewHolder.this, view, position);
                    }
                }
            });
        }

        public void setItem(GalleryItem item) {
            type = item.getType();
            url = item.getUrl();
            String thumbUrl;
            if(type == 0) {
                thumbUrl = parseUrl(url);
                ImageLoadTask imageLoadTask = new ImageLoadTask(thumbUrl, imageView);
                imageLoadTask.execute();
            } else {
                ImageLoadTask imageLoadTask = new ImageLoadTask(url, imageView);
                imageLoadTask.execute();
            }
        }

        public String parseUrl(String url) {
            String[] parsed = url.split("/");

            //Log.d("parsed: ", parsed[parsed.length-1]);
            return "http://img.youtube.com/vi/"+parsed[parsed.length-1]+"/0.jpg";
        }

        public void setOnItemClickListener(OnItemClickListener listener) {
            this.listener = listener;
        }
    }

}
