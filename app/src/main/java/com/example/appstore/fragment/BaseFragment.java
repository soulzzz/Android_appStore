package com.example.appstore.fragment;

import android.os.Build;
import android.os.Bundle;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.appstore.R;
import com.example.appstore.adapter.ItemAdapter;
import com.example.appstore.appinfo.AppData;
import com.example.appstore.appinfo.DataList;
import com.example.appstore.retrofit.RetrofitUtil;
import com.example.appstore.service.ITvService;
import com.example.appstore.util.GridCenterDecoration;
import com.example.appstore.util.LogUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BaseFragment extends Fragment{
    private static String TAG = "BaseFragment";
    private  String section = "推荐";
    private RecyclerView recyclerView;
    View view;
    public LayoutInflater inflater;
    private List<AppData> mDataList;
    private TextView connectFail;
    private ItemAdapter itemAdapter;
    public BaseFragment(String tag,String section) {
        TAG = tag;
        this.section = section;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inflater = getLayoutInflater();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_base, container,false);
        initViews();
        initData();
        return view;
    }

    private void initViews() {
        LogUtil.d(TAG, "initViews: ");
        connectFail = view.findViewById(R.id.connect_fail);
        recyclerView= view.findViewById(R.id.recyclerView);
        mDataList = new ArrayList<>();
        itemAdapter =new ItemAdapter(getContext(),mDataList);
        recyclerView.setAdapter(itemAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),4));
        recyclerView.addItemDecoration(new GridCenterDecoration());
    }
    public void initData(){

        ITvService iTvService = RetrofitUtil.getInstance().getRetrofit().create(ITvService.class);
        Call<DataList> call = iTvService.get_apps(System.currentTimeMillis()/1000);
        LogUtil.d(TAG,"initData"+System.currentTimeMillis());
        call.enqueue(new Callback<DataList>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<DataList> call, Response<DataList> response) {
                if(response.body().getDatas()!=null) mDataList.addAll( response.body().getDatas().stream().filter(appData -> appData.getKind()!=null &&appData.getKind().contains(section)).collect(Collectors.toList()));
                itemAdapter.notifyDataSetChanged();
                if(connectFail.getVisibility()==View.VISIBLE){
                    connectFail.setVisibility(View.GONE);
                }

            }

            @Override
            public void onFailure(Call<DataList> call, Throwable t) {
                LogUtil.e(TAG,"initData request fail");
                connectFail.setVisibility(View.VISIBLE);
            }
        });
    }


}