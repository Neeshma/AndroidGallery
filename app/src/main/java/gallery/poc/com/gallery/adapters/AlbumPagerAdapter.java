package gallery.poc.com.gallery.adapters;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

import gallery.poc.com.gallery.R;
import gallery.poc.com.gallery.model.GalleryItem;

/**
 * Created by neeshmamaliakkal on 7/28/17.
 */
public class AlbumPagerAdapter extends PagerAdapter{

    private Context mContext;
    private List<GalleryItem> itemList;

    public AlbumPagerAdapter(Context context, List<GalleryItem> itemList) {
        mContext = context;
        this.itemList = itemList;
    }

    @Override
    public Object instantiateItem(ViewGroup collection, int position) {
        GalleryItem item = itemList.get(position);
        LayoutInflater inflater = LayoutInflater.from(mContext);
        ViewGroup layout = (ViewGroup) inflater.inflate(R.layout.photo_pager_layout, collection, false);
        ImageView imgView = (ImageView) layout.findViewById(R.id.itemImg);
        imgView.setImageBitmap(item.getItemImage());
        imgView.setScaleType(ImageView.ScaleType.FIT_XY);
        collection.addView(layout);
        return layout;
    }

    @Override
    public void destroyItem(ViewGroup collection, int position, Object view) {
        collection.removeView((View) view);
    }

    @Override
    public int getCount() {
        return itemList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

}
