package gallery.poc.com.gallery.views;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import gallery.poc.com.gallery.R;
import gallery.poc.com.gallery.adapters.AlbumAdapter;
import gallery.poc.com.gallery.utils.GalleryConstants;
import gallery.poc.com.gallery.utils.ImageUtils;

public class AlbumActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String albumName = getIntent().getStringExtra(GalleryConstants.EXTRA_ALBUM_NAME);

        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().show();
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        itemList = ImageUtils.listAssetFiles(this, "photos/" + albumName);
        galleryAdapter = new AlbumAdapter(itemList);
        galleryRecyclerView.setAdapter(galleryAdapter);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            galleryRecyclerView.setTransitionName(albumName);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}
