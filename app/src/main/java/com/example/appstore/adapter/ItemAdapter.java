package com.example.appstore.adapter;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.appstore.R;
import com.example.appstore.activity.DetailActivity;
import com.example.appstore.appinfo.AppData;
import com.example.appstore.util.LogUtil;
import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.Myholder> {
    private final String TAG = "ItemAdapter";
    private List<AppData> mDataList;
    private Context context;
    public ItemAdapter(Context context, List<AppData> dataList) {
        this.context = context;
        this.mDataList = dataList;
    }

    @Override
    public Myholder onCreateViewHolder( ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.app_card, parent,false);
        return new Myholder(inflate);
    }

    @Override
    public void onBindViewHolder( Myholder holder, int position) {
        LogUtil.d(TAG,"onBindViewHolder "+position+ " start");
        holder.appCardName.setText(mDataList.get(position).getName() );
        holder.appCardRecommendation.setText(mDataList.get(position).getDetail());
        holder.appCardId.setText(Integer.toString(mDataList.get(position).getId()));
        Glide.with(context)
                .load(mDataList.get(position).geticon())
                .into(holder.appCardIcon);
        Glide.with(context)
                .load(mDataList.get(position).getBackground())
                .into(holder.appCardShow);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("appid",mDataList.get(position) );
                context.startActivity(intent);
            }
        });
        holder.itemView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    AnimatorSet animatorSet = new AnimatorSet();
                    holder.shimmerFrameLayout.startShimmer();
                    holder.shimmerFrameLayout.showShimmer(true);
                    animatorSet.playTogether(ObjectAnimator.ofFloat(v, "scaleX", 1.0f, 1.15f)
                            , ObjectAnimator.ofFloat(v, "scaleY", 1.0f, 1.15f));
                    animatorSet.setDuration(100);
                    animatorSet.start();
                    animatorSet = null;
                    v.setZ(v.getZ()+1);
                }else{
                    AnimatorSet animatorSet = new AnimatorSet();
                    holder.shimmerFrameLayout.stopShimmer();
                    holder.shimmerFrameLayout.hideShimmer();
                    animatorSet.playTogether(ObjectAnimator.ofFloat(v, "scaleX", 1.15f, 1.0f)
                            , ObjectAnimator.ofFloat(v, "scaleY", 1.15f, 1.0f));
                    animatorSet.setDuration(100);
                    animatorSet.start();
                    animatorSet = null;
                    v.setZ(v.getZ()-1);
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataList!=null?mDataList.size():0;
    }

    class Myholder extends RecyclerView.ViewHolder{
        private TextView appCardName,appCardRecommendation,appCardId ;
        private ImageView appCardIcon,appCardShow;
        private ShimmerFrameLayout shimmerFrameLayout;
        AnimatorSet animatorSet = new AnimatorSet();
        public Myholder(View view) {
            super(view);
            shimmerFrameLayout = view.findViewById(R.id.shimmer_view_container);
            appCardName = view.findViewById(R.id.app_card_name);
            appCardRecommendation = view.findViewById(R.id.app_card_recommendation);
            appCardId = view.findViewById(R.id.app_card_id);
            appCardIcon = view.findViewById(R.id.app_card_icon);
            appCardShow = view.findViewById(R.id.app_card_show);
        }
    }
}
