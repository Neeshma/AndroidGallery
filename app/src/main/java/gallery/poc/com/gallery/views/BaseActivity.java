package gallery.poc.com.gallery.views;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import gallery.poc.com.gallery.R;
import gallery.poc.com.gallery.model.GalleryItem;

/**
 * Created by neeshmamaliakkal on 7/29/17.
 */
public class BaseActivity extends AppCompatActivity {

    protected RecyclerView galleryRecyclerView;
    protected RecyclerView.Adapter galleryAdapter;
    protected RecyclerView.LayoutManager galleryLayoutManager;
    protected List<GalleryItem> itemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        galleryRecyclerView = (RecyclerView) findViewById(R.id.galleryList);
        galleryRecyclerView.setHasFixedSize(true);

        galleryLayoutManager = new LinearLayoutManager(this);
        galleryRecyclerView.setLayoutManager(galleryLayoutManager);
    }

}
