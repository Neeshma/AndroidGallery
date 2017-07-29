package gallery.poc.com.gallery.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.List;

import gallery.poc.com.gallery.R;
import gallery.poc.com.gallery.model.GalleryItem;

/**
 * Created by neeshmamaliakkal on 7/28/17.
 */
public class AlbumAdapter extends BaseAdapter {

    public AlbumAdapter(List<GalleryItem> itemList) {
        super(itemList);
    }

    @Override
    public AlbumAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                      int viewType) {

        LinearLayout v = (LinearLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.photo_item_layout, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        final String itemName = itemList.get(position).getItemName();
        holder.mTextView.setText(itemName);
        holder.mImageView.setImageBitmap(itemList.get(position).getItemImage());

    }
}




