package com.example.appstore.util;

import android.graphics.Rect;
import android.view.View;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appstore.R;

public class GridCenterDecoration extends RecyclerView.ItemDecoration{

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        GridLayoutManager manager = (GridLayoutManager) parent.getLayoutManager();
        int parentWidth = parent.getMeasuredWidth() - parent.getPaddingStart() - parent.getPaddingEnd() ;
        int spanCount = manager.getSpanCount();
        float itemWidth = view.getResources().getDimension(R.dimen.app_card_width);
        float spanMargin = (parentWidth - itemWidth * spanCount) / (spanCount);
        outRect.left = (int)spanMargin/2;
    }
}
