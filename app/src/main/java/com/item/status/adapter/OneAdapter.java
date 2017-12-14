package com.item.status.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.item.status.R;

/**
 * Created by Jie on 2017/12/14.
 * 适配器
 */

public class OneAdapter extends BaseQuickAdapter<String,BaseViewHolder>{

    public OneAdapter(){
        super(R.layout.item_one);
    }
    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.text,item);
    }
}
