package com.bailemeng.app.view.home.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bailemeng.app.R;
import com.bailemeng.app.base.BaseAppFragment;
import com.bailemeng.app.utils.ToastUtil;
import com.lljjcoder.city_20170724.CityPickerView;
import com.lljjcoder.city_20170724.bean.CityBean;
import com.lljjcoder.city_20170724.bean.DistrictBean;
import com.lljjcoder.city_20170724.bean.ProvinceBean;

/**
 * 应用名称: BaiLeMeng
 * 包 名 称: com.bailemeng.app.view.home.fragment
 * 创 建 人: shenjinghao
 * 创建时间: 2017/10/9
 */
public class EventRegistrationItemFragment extends BaseAppFragment {

    public static EventRegistrationItemFragment newInstance() {
        return new EventRegistrationItemFragment();
    }

    private ImageView itemEvevtIv;
    private LinearLayout itemEvevtLl;
    private TextView selectDateBirthTv,selectLocationTv;

    @Override
    public void initialView(View view) {
        itemEvevtIv=view.findViewById(R.id.item_evevt_iv);
        itemEvevtLl=view.findViewById(R.id.item_evevt_ll);
        //获取Activity传递过来的参数
        Bundle mBundle = getArguments();
        int type = mBundle.getInt("type");
        String color = mBundle.getString("title");
        if (type==0){
            itemEvevtIv.setVisibility(View.VISIBLE);
            itemEvevtLl.setVisibility(View.GONE);
            itemEvevtIv.setBackgroundColor(Color.parseColor(color));
        } else if (type==1){
            itemEvevtIv.setVisibility(View.GONE);
            itemEvevtLl.setVisibility(View.VISIBLE);
        }
        selectDateBirthTv=view.findViewById(R.id.select_date_birth_tv);
        selectLocationTv=view.findViewById(R.id.select_location_tv);
    }

    @Override
    public void initialListenter() {
        selectDateBirthTv.setOnClickListener(this);
        selectLocationTv.setOnClickListener(this);
    }

    @Override
    public void initialData() {

    }

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_item_event_registration;
    }

    @Override
    public void viewClick(View v) {
        super.viewClick(v);
        switch (v.getId()){
            case R.id.select_date_birth_tv:
                break;
            case R.id.select_location_tv:
                CityPickerView cityPicker = new CityPickerView.Builder(mActivity)
                        .textSize(20)
                        .title("地址选择")
                        .backgroundPop(0xa0000000)
                        .titleBackgroundColor("#ffffff")
                        .titleTextColor("#000000")
                        .backgroundPop(0xa0000000)
                        .confirTextColor("#000000")
                        .cancelTextColor("#000000")
                        .province("江苏省")
                        .city("常州市")
                        .district("天宁区")
                        .textColor(Color.parseColor("#000000"))
                        .provinceCyclic(true)
                        .cityCyclic(false)
                        .districtCyclic(false)
                        .visibleItemsCount(7)
                        .itemPadding(10)
                        .onlyShowProvinceAndCity(false)
                        .build();
                cityPicker.show();

                //监听方法，获取选择结果
                cityPicker.setOnCityItemClickListener(new CityPickerView.OnCityItemClickListener() {
                    @Override
                    public void onSelected(ProvinceBean province, CityBean city, DistrictBean district) {
                        //返回结果
                        //ProvinceBean 省份信息
                        //CityBean     城市信息
                        //DistrictBean 区县信息
                        ToastUtil.showLongToast(mActivity,"ProvinceBean "+province+", CityBean "+city+", DistrictBean "+district);
                    }

                    @Override
                    public void onCancel() {

                    }
                });
                break;
        }
    }
}
