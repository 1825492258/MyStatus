package com.item.status.picker;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.bigkoo.pickerview.TimePickerView;
import com.bigkoo.pickerview.listener.CustomListener;
import com.item.status.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class PickerActivity extends AppCompatActivity implements View.OnClickListener {
    // 时间选择器
    private TimePickerView pvTime, pvCustomTime;
    // 条件选择器
    private OptionsPickerView pvOptions, pvCustomOptions;

    private OptionsPickerView mOptions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picker);
        findViewById(R.id.btn_pick_one).setOnClickListener(this);
        findViewById(R.id.btn_pick_two).setOnClickListener(this);
        findViewById(R.id.btn_pick_three).setOnClickListener(this);
        findViewById(R.id.btn_pick_four).setOnClickListener(this);
        findViewById(R.id.btn_pick_five).setOnClickListener(this);
        //  String JsonData = new GetJsonDataUtil().getJson(this, "province.json");//获取assets目录下的json文件数据
        //  Log.d("jiejie", JsonData);
        int hour = getCurrentHour();
        int min = getCurrentMin();
        Log.d("jiejie", "hour " + hour + "  min" + min + "    " + min %5  + "  " + min/5);
        Log.d("jiejie",4 % 5 + "  " + 4 /5);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_pick_one: // 弹出时间选择器
                if (pvTime == null) {
                    initTimePicker();
                }
                pvTime.show();
                break;
            case R.id.btn_pick_two: // 弹出自定义时间选择器
                if (pvCustomTime == null) {
                    initCustomTimePicker();
                }
                pvCustomTime.show();
                break;
            case R.id.btn_pick_three: // 弹出条件选择器
                if (pvOptions == null) {
                    initOptionPicker();
                }
                pvOptions.show();
                break;
            case R.id.btn_pick_four:
                if (pvCustomOptions == null) {
                    initCustomOptionPicker();
                }
                pvCustomOptions.show();
                break;
            case R.id.btn_pick_five:
                if (mOptions == null) {
                    initMyOptionPicker();
                }
                mOptions.show();
                break;
        }
    }

    private void initTimePicker() {
        // 控制时间范围(如果不设置范围，则使用默认时间1900-2100，此段代码可以注释)
        // 因为系统Calendar的月份是从0-11的,所以如果是调用Calendar的set方法来设置时间,月份的范围也要是从0-11
        Calendar selectedDate = Calendar.getInstance();
        Calendar startData = Calendar.getInstance();
        startData.set(2014, 0, 23);
        Calendar endDate = Calendar.getInstance();
        endDate.set(2019, 11, 28);
        // 时间选择器
        pvTime = new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                Log.d("jiejie", getTime(date));
            }
        })
                // 年月日时分秒 的显示与否，不设置则默认全部显示
                .setType(new boolean[]{true, true, true, false, false, false})
                // 默认设置为年月日时分秒
                .setLabel("", "", "", "", "", "")
                .setCancelText("Cancel") // 取消按钮文字
                .setSubmitText("Sure") // 确定按钮文字
                .setTitleText("Title") // 设置标题文字
                .setOutSideCancelable(false) // 点击屏幕，点在控件外部范围时，是否取消显示
                .isCenterLabel(false) // 是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .setDate(selectedDate)
                .setDividerColor(Color.DKGRAY)
                .setLineSpacingMultiplier(3f)
                // .setRangDate(startData, endDate) // 时间范围
                // .isDialog(true) // 是否显示为对话框样式
                .setDecorView(null)
                .build();

    }

    private void initCustomTimePicker() {
        /**
         * @description
         *
         * 注意事项：
         * 1.自定义布局中，id为 optionspicker 或者 timepicker 的布局以及其子控件必须要有，否则会报空指针.
         * 具体可参考demo 里面的两个自定义layout布局。
         * 2.因为系统Calendar的月份是从0-11的,所以如果是调用Calendar的set方法来设置时间,月份的范围也要是从0-11
         * setRangDate方法控制起始终止时间(如果不设置范围，则使用默认时间1900-2100年，此段代码可注释)
         */
        Calendar selectedDate = Calendar.getInstance();//系统当前时间
        Calendar startDate = Calendar.getInstance();
        startDate.set(2014, 1, 23);
        Calendar endDate = Calendar.getInstance();
        endDate.set(2027, 2, 28);

        pvCustomTime = new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                Log.d("jiejie", "自定义" + getTime(date));
            }
        })
                .setDate(selectedDate)
                //.setRangDate(startDate, endDate)
                .setLayoutRes(R.layout.pickerview_custom_time, new CustomListener() {
                    @Override
                    public void customLayout(View v) {
                        final TextView tvSubmit = (TextView) v.findViewById(R.id.tv_finish);
                        ImageView ivCancel = (ImageView) v.findViewById(R.id.iv_cancel);
                        tvSubmit.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                pvCustomTime.returnData();
                                pvCustomTime.dismiss();
                            }
                        });
                        ivCancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                pvCustomTime.dismiss();
                            }
                        });
                    }
                })
                .setContentSize(18) // 滚轮文字大小
                // 年月日时分秒 的显示与否，不设置则默认全部显示
                .setType(new boolean[]{true, true, true, false, false, false})
                .setLabel("年", "月", "日", "时", "分", "秒")
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .setDividerColor(0xFF24AD9D)
                .setLineSpacingMultiplier(2.5f)
                .setTextXOffset(0, 0, 0, 40, 0, -40)
                .build();
    }

    private ArrayList<ProvinceBean> options1Items = new ArrayList<>();
    private ArrayList<ArrayList<String>> options2Items = new ArrayList<>();

    // 条件选择器初始化
    private void initOptionPicker() {

        /**
         * 注意：如果是添加JavaBean实体数据，则实体类需要实现IPickerViewData接口
         * PickerView 会通过getPickerViewText 方法获取字符串显示出来
         */
        //选项1
        options1Items.add(new ProvinceBean(0, "广东", "描述部分", "其他数据"));
        options1Items.add(new ProvinceBean(1, "湖南", "描述部分", "其他数据"));
        options1Items.add(new ProvinceBean(2, "广西", "描述部分", "其他数据"));

        //选项2
        ArrayList<String> options2Items_01 = new ArrayList<>();
        options2Items_01.add("广州");
        options2Items_01.add("佛山");
        options2Items_01.add("东莞");
        options2Items_01.add("珠海");
        ArrayList<String> options2Items_02 = new ArrayList<>();
        options2Items_02.add("长沙");
        options2Items_02.add("岳阳");
        options2Items_02.add("株洲");
        options2Items_02.add("衡阳");
        ArrayList<String> options2Items_03 = new ArrayList<>();
        options2Items_03.add("桂林");
        options2Items_03.add("玉林");
        options2Items.add(options2Items_01);
        options2Items.add(options2Items_02);
        options2Items.add(options2Items_03);

        pvOptions = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                String tx = options1Items.get(options1).getPickerViewText() + options2Items.get(options1).get(options2);
                Log.d("jiejie", "---" + tx);
            }
        })
                .setTitleText("城市选择")
                .setContentTextSize(18) // 设置滚轮文字大小
                .setDividerColor(Color.RED) // 设置分割线的颜色
                .setSelectOptions(0, 1) // 默认选中项
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .setLabels("省", "市", "区")
                .setLineSpacingMultiplier(2.5f)
                .setBackgroundId(0x66000000) //设置外部遮罩颜色
                .build();
        pvOptions.setPicker(options1Items, options2Items); // 二级选择器
    }

    private ArrayList<String> cardItem = new ArrayList<>();

    // 自定义条件选择器
    private void initCustomOptionPicker() {
        for (int i = 0; i < 8; i++) {
            cardItem.add("----" + i);
        }
        pvCustomOptions = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {

            }
        })
                .setLayoutRes(R.layout.pickerview_custom_options, new CustomListener() {
                    @Override
                    public void customLayout(View v) {
                        final TextView tvSubmit = (TextView) v.findViewById(R.id.tv_finish);
                        //final TextView tvAdd = (TextView) v.findViewById(R.id.tv_add);
                        ImageView ivCancel = (ImageView) v.findViewById(R.id.iv_cancel);
                        tvSubmit.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                pvCustomOptions.returnData();
                                pvCustomOptions.dismiss();
                            }
                        });

                        ivCancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                pvCustomOptions.dismiss();
                            }
                        });
                    }
                })
                .setContentTextSize(18) // 设置滚轮文字大小
                .setDividerColor(Color.RED) // 设置分割线的颜色
                .setSelectOptions(2) // 默认选中项
                .setLineSpacingMultiplier(2.5f)
                .build();
        pvCustomOptions.setPicker(cardItem);
    }


    private ArrayList<TimeBean> myItem1 = new ArrayList<>(); // 一级数据源
    private ArrayList<ArrayList<String>> myItem2 = new ArrayList<>(); // 二级数据源
    private ArrayList<ArrayList<ArrayList<String>>> myItem3 = new ArrayList<>(); // 三级数据源

    private void initMyOptionPicker() {
        // 选项1
        myItem1.add(new TimeBean("今天"));
        myItem1.add(new TimeBean("明天"));
        myItem1.add(new TimeBean("后天"));
        myItem1.add(new TimeBean(getDay(3)));
        myItem1.add(new TimeBean(getDay(4)));

        // 选项2
        myItem2.add(getTodayHourData());
        myItem2.add(getHourData());
        myItem2.add(getHourData());
        myItem2.add(getHourData());
        myItem2.add(getHourData());

        // 选项3
        myItem3.add(getToDayMinuteData());
        myItem3.add(getMinuteData());
        myItem3.add(getMinuteData());
        myItem3.add(getMinuteData());
        myItem3.add(getMinuteData());

        mOptions = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {

            }
        })
                .setTitleText("出发时间") // 设置标题
                .setTitleSize(15) // 设置标题大小
                .setSubCalSize(12) // 设置 确定取消 的大小
                .setContentTextSize(15) // 设置滚轮文字大小
                .setLineSpacingMultiplier(2.8f) // 设置间距倍数
                .setSelectOptions(0, 0, 0) // 设置默认选项
                .setOutSideCancelable(true) // 点击外部dismiss default true
                .build();
        mOptions.setPicker(myItem1, myItem2, myItem3);
        //  mOptions.setPicker(myItem1,myItem2);
    }

    /**
     * 获取日期
     *
     * @param day 推迟的天数  如3 则表示3日后是的日期
     * @return 9月8日 周三
     */
    private String getDay(int day) {
        String[] weeks = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, day);
        return (calendar.get(Calendar.MONTH) + 1) + "月" + calendar.get(Calendar.DAY_OF_MONTH) + "日 " + weeks[calendar.get(Calendar.DAY_OF_WEEK) - 1];
    }

    private String getTime(Date date) {//可根据需要自行截取数据显示
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        return format.format(date);
    }

    private int getCurrentMin() {
        return Calendar.getInstance().get(Calendar.MINUTE);
    }

    /**
     * 获取当前的小时
     */
    private int getCurrentHour() {
        return Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
    }

    /**
     * 选项2 今天的点
     */
    private ArrayList<String> getTodayHourData() {
        ArrayList<String> lists = new ArrayList<>();
        int hour = getCurrentHour(); // 获取当前的小时
        int min = getCurrentMin(); // 获取当前的分钟
        if (min > 45) {
            hour = hour + 1; // 如果已经超过了45分钟，从下个小时开始算起
        }
        for (int i = hour; i < 24; i++) {
            lists.add(i + "点");
        }
        return lists;
    }

    /**
     * 选项2 明天的点
     */
    private ArrayList<String> getHourData() {
        ArrayList<String> lists = new ArrayList<>();
        for (int i = 0; i < 24; i++) {
            lists.add(i + "点");
        }
        return lists;
    }

    /**
     * 明天 后天  分
     */
    private ArrayList<String> getMinData() {
        ArrayList<String> dataArrayList = new ArrayList<>();
        for (int i = 0; i < 60; i = i +5) {
            dataArrayList.add(i + "分");
        }
        return dataArrayList;
    }


    private ArrayList<String> getTodayMinData() {
        ArrayList<String> dataArray = new ArrayList<>();
        int min = getCurrentMin();
        int jj;
        if(min > 0){
            jj = (min / 5) * 5 + 10;
        }else{
            jj = 15 ;
        }
        for (int i = jj; i < 60; i = i +5) {
            dataArray.add(i + "分");
        }


        return dataArray;
    }

    /**
     * 明后 天的
     */
    private ArrayList<ArrayList<String>> getMinuteData() {
        ArrayList<ArrayList<String>> Minutes = new ArrayList<>();
        for (int i = 0; i < 24; i++) {
            Minutes.add(getMinData());
        }
        return Minutes;
    }

    private ArrayList<ArrayList<String>> getToDayMinuteData() {
        int hour = getCurrentHour();
        int min = getCurrentMin();
        if (min > 45) {
            hour = hour + 1;
        }
        ArrayList<ArrayList<String>> Minutes = new ArrayList<>();
        //Minutes.add(getTodayMinData());
        for (int i = hour; i < 24; i++) {
            if(i == hour){
                Minutes.add(getTodayMinData());
            }else {
                Minutes.add(getMinData());
            }

        }
        return Minutes;
    }
}
