package gallery.poc.com.gallery.adapters;

import android.support.v4.view.ViewCompat;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.List;

import gallery.poc.com.gallery.R;
import gallery.poc.com.gallery.model.GalleryItem;

/**
 * Created by neeshmamaliakkal on 7/28/17.
 */
public class GalleryAdapter extends BaseAdapter {


    public GalleryAdapter(List<GalleryItem> itemList) {
        super(itemList);
    }

    @Override
    public GalleryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {

        LinearLayout v = (LinearLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_layout, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        final String itemName = itemList.get(position).getItemName();
        holder.mTextView.setText(itemName);
        holder.mImageView.setImageBitmap(itemList.get(position).getItemImage());

        ViewCompat.setTransitionName(holder.mImageView, itemName);

    }

}




