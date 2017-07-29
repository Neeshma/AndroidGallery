package gallery.poc.com.gallery.model;

import android.graphics.Bitmap;

/**
 * Created by neeshmamaliakkal on 7/28/17.
 */
public class GalleryItem {

    String itemName;

    Bitmap itemImage;

    public Bitmap getItemImage() {
        return itemImage;
    }

    public void setItemImage(Bitmap itemImage) {
        this.itemImage = itemImage;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
}
