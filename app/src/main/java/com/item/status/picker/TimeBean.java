package com.item.status.picker;

import com.bigkoo.pickerview.model.IPickerViewData;

/**
 * Created by Jie on 2017/12/28.
 * 时间
 */

public class TimeBean implements IPickerViewData{
    private String time;

    public TimeBean(String time) {
        this.time = time;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String getPickerViewText() {
        return time;
    }
}
