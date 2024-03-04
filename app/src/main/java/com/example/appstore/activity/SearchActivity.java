package com.example.appstore.activity;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.appstore.R;
import com.example.appstore.adapter.ItemAdapter;
import com.example.appstore.appinfo.AppData;
import com.example.appstore.appinfo.DataList;
import com.example.appstore.retrofit.RetrofitUtil;
import com.example.appstore.service.ITvService;
import com.example.appstore.util.Cn2Spell;
import com.example.appstore.util.GridCenterDecoration;
import com.example.appstore.util.InitialUtil;
import com.example.appstore.util.LogUtil;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SearchActivity extends AppCompatActivity {
    EditText searchEdit;
    Button searchButton;
    private RecyclerView recyclerView;
    private ItemAdapter itemAdapter;
    private List<AppData> mDataList ;
    private Context context;
    private static final String TAG = "SearchActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        context = this;
        initView();
        initData("s");
    }

    private void initView() {

        searchEdit= findViewById(R.id.edit_search);
        searchButton = findViewById(R.id.button_search);
        recyclerView= findViewById(R.id.recyclerView);
        mDataList = new ArrayList<>();
        itemAdapter =new ItemAdapter(this,mDataList);
        recyclerView.setAdapter(itemAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        recyclerView.addItemDecoration(new GridCenterDecoration());
    }

    public void Click(View view) throws IOException {
        String searchwords = "";
        switch (view.getId()){
            case R.id.search_0:
                searchwords  = searchEdit.getText().toString();
                searchEdit.setText(searchwords + "0");
                break;
            case R.id.search_1:
                searchwords  = searchEdit.getText().toString();
                searchEdit.setText(searchwords + "1");
                break;
            case R.id.search_2:
                searchwords  = searchEdit.getText().toString();
                searchEdit.setText(searchwords + "2");
                break;
            case R.id.search_3:
                searchwords  = searchEdit.getText().toString();
                searchEdit.setText(searchwords + "3");
                break;
            case R.id.search_4:
                searchwords  = searchEdit.getText().toString();
                searchEdit.setText(searchwords + "4");
                break;
            case R.id.search_5:
                searchwords  = searchEdit.getText().toString();
                searchEdit.setText(searchwords + "5");
                break;
            case R.id.search_6:
                searchwords  = searchEdit.getText().toString();
                searchEdit.setText(searchwords + "6");
                break;
            case R.id.search_7:
                searchwords  = searchEdit.getText().toString();
                searchEdit.setText(searchwords + "7");
                break;
            case R.id.search_8:
                searchwords  = searchEdit.getText().toString();
                searchEdit.setText(searchwords + "8");
                break;
            case R.id.search_9:
                searchwords  = searchEdit.getText().toString();
                searchEdit.setText(searchwords + "9");
                break;
            case R.id.search_A:
                searchwords  = searchEdit.getText().toString();
                searchEdit.setText(searchwords + "A");
                break;
            case R.id.search_B:
                searchwords  = searchEdit.getText().toString();
                searchEdit.setText(searchwords + "B");
                break;
            case R.id.search_C:
                searchwords  = searchEdit.getText().toString();
                searchEdit.setText(searchwords + "C");
                break;
            case R.id.search_D:
                searchwords  = searchEdit.getText().toString();
                searchEdit.setText(searchwords + "D");
                break;
            case R.id.search_E:
                searchwords  = searchEdit.getText().toString();
                searchEdit.setText(searchwords + "E");
                break;
            case R.id.search_F:
                searchwords  = searchEdit.getText().toString();
                searchEdit.setText(searchwords + "F");
                break;
            case R.id.search_G:
                searchwords  = searchEdit.getText().toString();
                searchEdit.setText(searchwords + "G");
                break;
            case R.id.search_H:
                searchwords  = searchEdit.getText().toString();
                searchEdit.setText(searchwords + "H");
                break;
            case R.id.search_I:
                searchwords  = searchEdit.getText().toString();
                searchEdit.setText(searchwords + "I");
                break;
            case R.id.search_J:
                searchwords  = searchEdit.getText().toString();
                searchEdit.setText(searchwords + "J");
                break;
            case R.id.search_K:
                searchwords  = searchEdit.getText().toString();
                searchEdit.setText(searchwords + "K");
                break;
            case R.id.search_L:
                searchwords  = searchEdit.getText().toString();
                searchEdit.setText(searchwords + "L");
                break;
            case R.id.search_M:
                searchwords  = searchEdit.getText().toString();
                searchEdit.setText(searchwords + "M");
                break;
            case R.id.search_N:
                searchwords  = searchEdit.getText().toString();
                searchEdit.setText(searchwords + "N");
                break;
            case R.id.search_O:
                searchwords  = searchEdit.getText().toString();
                searchEdit.setText(searchwords + "O");
                break;
            case R.id.search_P:
                searchwords  = searchEdit.getText().toString();
                searchEdit.setText(searchwords + "P");
                break;
            case R.id.search_Q:
                searchwords  = searchEdit.getText().toString();
                searchEdit.setText(searchwords + "Q");
                break;
            case R.id.search_R:
                searchwords  = searchEdit.getText().toString();
                searchEdit.setText(searchwords + "R");
                break;
            case R.id.search_S:
                searchwords  = searchEdit.getText().toString();
                searchEdit.setText(searchwords + "S");
                break;
            case R.id.search_T:
                searchwords  = searchEdit.getText().toString();
                searchEdit.setText(searchwords + "T");
                break;
            case R.id.search_U:
                searchwords  = searchEdit.getText().toString();
                searchEdit.setText(searchwords + "U");
                break;
            case R.id.search_V:
                searchwords  = searchEdit.getText().toString();
                searchEdit.setText(searchwords + "V");
                break;
            case R.id.search_W:
                searchwords  = searchEdit.getText().toString();
                searchEdit.setText(searchwords + "W");
                break;
            case R.id.search_X:
                searchwords  = searchEdit.getText().toString();
                searchEdit.setText(searchwords + "X");
                break;
            case R.id.search_Y:
                searchwords  = searchEdit.getText().toString();
                searchEdit.setText(searchwords + "Y");
                break;
            case R.id.search_Z:
                searchwords  = searchEdit.getText().toString();
                searchEdit.setText(searchwords + "Z");
                break;
            case R.id.search_back:
                searchwords = searchEdit.getText().toString().substring(0,searchEdit.getText().toString().length()-1);
                searchEdit.setText(searchwords);
                break;
            case R.id.search_clear:
                searchEdit.setText("");
                break;
            case R.id.button_search:
                searchwords = searchEdit.getText().toString();
                if (searchwords.length() == 0){
                    Toast.makeText(context, "输入内容为空", Toast.LENGTH_SHORT).show();
                } else {
                    initData(searchwords);
                }
                break;
            default:
                break;





        }

    }
    public void initData(String searchwords){
        ITvService iTvService = RetrofitUtil.getInstance().getRetrofit().create(ITvService.class);
        Call<DataList> call = iTvService.get_apps(System.currentTimeMillis()/1000);
        LogUtil.d(TAG,"initData"+System.currentTimeMillis());
        call.enqueue(new Callback<DataList>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<DataList> call, Response<DataList> response) {
                List<AppData> tmplist = response.body().getDatas();
               // if(response.body().getDatas()!=null) mDataList = response.body().getDatas().stream().filter(appData -> appData.getKind()!=null &&appData.getKind().contains("游戏")).collect(Collectors.toList());
                if(mDataList !=null){
                    mDataList.clear();
                }else{
                    mDataList = new ArrayList<AppData>();
                }

                for (int i = 0; i < tmplist.size(); i++) {
                    if (SearchCheck(searchwords, tmplist.get(i).getName())) {
                        mDataList.add(tmplist.get(i));
                    }
                }
                if (mDataList == null || mDataList.size() == 0) {
                    Toast.makeText(context, "没有搜索结果", Toast.LENGTH_SHORT).show();;
                }
                LogUtil.d(TAG,"Add App"+mDataList.size());
                itemAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<DataList> call, Throwable t) {
                LogUtil.e(TAG,"initData request fail");
            }
        });
    }

        public boolean SearchCheck(String searchText, String appName){
            if (appName.toLowerCase().contains(searchText.toLowerCase())){
                return true;
            }
            String pinyin = new Cn2Spell().getSelling(appName);
            if (pinyin.toLowerCase().contains(searchText.toLowerCase())){
                return true;
            }
            String inital = InitialUtil.getChineseInitial(appName);
            if (inital.toLowerCase().contains(searchText.toLowerCase())){
                return true;
            }
            return false;
        }

    }
