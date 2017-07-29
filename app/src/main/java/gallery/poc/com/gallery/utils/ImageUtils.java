package gallery.poc.com.gallery.utils;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import gallery.poc.com.gallery.model.GalleryItem;

/**
 * Created by neeshmamaliakkal on 7/28/17.
 */
public class ImageUtils {

    public static List<GalleryItem> listAssetFolders(Context context, String path) {
        AssetManager assetManager = context.getAssets();
        List<GalleryItem> itemList = new ArrayList<>();
        try {
            String[] assetList = assetManager.list(path);
            for (String file : assetList) {
                GalleryItem galleryItem = new GalleryItem();
                String[] imgPath = assetManager.list(path + "/" +file);
                galleryItem.setItemName(file);
                if(imgPath.length>0) {
                    InputStream is = assetManager.open(path + "/" + file + "/" + imgPath[0]);
                    Bitmap bitmap = BitmapFactory.decodeStream(is);
                    galleryItem.setItemImage(bitmap);
                }
                itemList.add(galleryItem);
            }
        } catch (IOException e) {
            return itemList;
        }

        return itemList;
    }

    public static List<GalleryItem> listAssetFiles(Context context, String path) {
        AssetManager assetManager = context.getAssets();
        List<GalleryItem> itemList = new ArrayList<>();
        try {
            String[] assetList = assetManager.list(path);
            for (String file : assetList) {
                GalleryItem galleryItem = new GalleryItem();
                galleryItem.setItemName(file);
                InputStream is = assetManager.open(path + "/" + file);
                Bitmap bitmap = BitmapFactory.decodeStream(is);
                galleryItem.setItemImage(bitmap);
                itemList.add(galleryItem);
            }
        } catch (IOException e) {
            return itemList;
        }

        return itemList;
    }

}
