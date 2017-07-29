package gallery.poc.com.gallery.views;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.BounceInterpolator;

import java.util.List;

import gallery.poc.com.gallery.R;
import gallery.poc.com.gallery.adapters.AlbumPagerAdapter;
import gallery.poc.com.gallery.adapters.GalleryAdapter;
import gallery.poc.com.gallery.listeners.RecyclerTouchListener;
import gallery.poc.com.gallery.model.GalleryItem;
import gallery.poc.com.gallery.utils.GalleryConstants;
import gallery.poc.com.gallery.utils.ImageUtils;

public class MainActivity extends BaseActivity {

    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        itemList = ImageUtils.listAssetFolders(this, "photos");
        galleryAdapter = new GalleryAdapter(itemList);
        galleryRecyclerView.setAdapter(galleryAdapter);

        viewPager = (ViewPager) findViewById(R.id.viewpager);

        galleryRecyclerView.addOnItemTouchListener(
                new RecyclerTouchListener(this, galleryRecyclerView, new RecyclerTouchListener.ItemClickListener() {

                    @Override public void onItemClicked(View sharedView, int position) {
                        Intent intent = new Intent(MainActivity.this,AlbumActivity.class);
                        String transitionName = itemList.get(position).getItemName();
                        intent.putExtra(GalleryConstants.EXTRA_ALBUM_NAME, transitionName);

                        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(MainActivity.this, sharedView, transitionName);
                        startActivity(intent, options.toBundle());
                    }

                    @Override public void onItemLongClicked(View sharedView, int position) {
                        String albumName = itemList.get(position).getItemName();
                        List<GalleryItem> itemList = ImageUtils.listAssetFiles(sharedView.getContext(), "photos/" + albumName);
                        viewPager.setAdapter(new AlbumPagerAdapter(MainActivity.this,itemList));

                        viewPager.setVisibility(View.VISIBLE);
                        Animation animation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.bounce);
                        viewPager.startAnimation(animation);

                    }

                    @Override
                    public void onItemLongClickEnded() {
                        viewPager.setVisibility(View.GONE);
                    }

                    @Override
                    public void onItemMoved(int direction) {
                        if(RecyclerTouchListener.MOVE_LEFT == direction) {
                            viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
                        }else {
                            viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
                        }
                    }
                })
        );

    }

}
