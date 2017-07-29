package gallery.poc.com.gallery.listeners;

import android.content.Context;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by neeshmamaliakkal on 7/28/17.
 */

public class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

    private GestureDetector gestureDetector;
    private ItemClickListener clickListener;
    private boolean isLongPressed = false;
    private float oldX, newX;
    public static final int MIN_DISTANCE = 20;
    public static final int MOVE_LEFT = 1;
    public static final int MOVE_RIGHT = 2;

    public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final ItemClickListener clickListener) {
        this.clickListener = clickListener;
        gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }

            @Override
            public void onLongPress(MotionEvent e) {
                View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                if (child != null && clickListener != null) {
                    clickListener.onItemLongClicked(child, recyclerView.getChildAdapterPosition(child));
                    isLongPressed = true;
                }
            }

        });
    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {

        final int action = MotionEventCompat.getActionMasked(e);
        View child = rv.findChildViewUnder(e.getX(), e.getY());
        if (child != null && clickListener != null && gestureDetector.onTouchEvent(e)) {
            clickListener.onItemClicked(child, rv.getChildAdapterPosition(child));
        }else{
            switch(action) {
                case (MotionEvent.ACTION_DOWN) :
                    oldX = e.getX();
                    break;
                case (MotionEvent.ACTION_MOVE) :
                    if(isLongPressed){
                        newX = e.getX();
                        float deltaX = newX - oldX;
                        if (Math.abs(deltaX) > MIN_DISTANCE)
                        {
                            // Left to Right swipe action
                            if (newX > oldX)
                            {
                                clickListener.onItemMoved(MOVE_RIGHT);
                                oldX = newX;
                            }
                            // Right to left swipe action
                            else
                            {
                                clickListener.onItemMoved(MOVE_LEFT);
                                oldX = newX;
                            }

                        }
                    }
                    break;
                case (MotionEvent.ACTION_UP) :
                    isLongPressed = false;
                    clickListener.onItemLongClickEnded();
                    break;
            }
        }
        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {
    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }

    public interface ItemClickListener {
        void onItemClicked(View sharedView, int position);
        void onItemLongClicked(View sharedView, int position);
        void onItemMoved(int direction);
        void onItemLongClickEnded();
    }
}
