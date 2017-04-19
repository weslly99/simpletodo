package com.codepath.simpletodo.listeners;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by weslly on 19/04/17.
 */

public class ItemListener extends RecyclerView.SimpleOnItemTouchListener {
    private RecyclerView mRecyclerView;
    private GestureDetector mGestureDetector;
    private OnItemListener mItemListener;

    public ItemListener(RecyclerView rv, Context context,OnItemListener itemListener) {
        mRecyclerView = rv;
        mItemListener = itemListener;
        mGestureDetector = new GestureDetector(context, new SimpleOnGestureListener(){
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
               View view = getView(e);
                mItemListener.onSingleTouch(view);
                return true;
            }

            @Override
            public void onLongPress(MotionEvent e) {
                View view = getView(e);
                mItemListener.onLongTouch(view);
            }
        });
    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        mGestureDetector.onTouchEvent(e);
        return false;
    }

    private View getView(MotionEvent e) {
        return mRecyclerView.findChildViewUnder(e.getX(), e.getY());
    }

   public interface OnItemListener{
        void onSingleTouch(View view);
        void onLongTouch(View view);
    }
}
