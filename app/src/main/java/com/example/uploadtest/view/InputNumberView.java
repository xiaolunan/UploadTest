package com.example.uploadtest.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.example.uploadtest.R;
import com.example.uploadtest.utils.dpToPxUtils;

public class InputNumberView extends RelativeLayout {

    private static final String TAG = "InputNumberView";
    private Button leftBtn, rightBtn;
    private EditText editNum;
    private int num = 0;
    private NumListener numListener;
    private int minInt;
    private int maxInt;
    private int stepInt;
    private boolean disable;
    private int valueSizeInt;
    private int defaultNum;
    private int resourceId;
    private float viewHeight;
    private LinearLayout viewLayout;

    public int getMinInt() {
        return minInt;
    }

    public void setMinInt(int minInt) {
        this.minInt = minInt;
    }

    public int getMaxInt() {
        return maxInt;
    }

    public void setMaxInt(int maxInt) {
        this.maxInt = maxInt;
    }

    public int getStepInt() {
        return stepInt;
    }

    public void setStepInt(int stepInt) {
        this.stepInt = stepInt;
    }

    public boolean isDisable() {
        return disable;
    }

    public void setDisable(boolean disable) {
        this.disable = disable;
    }

    public int getDefaultNum() {
        return defaultNum;
    }

    public void setDefaultNum(int defaultNum) {
        this.defaultNum = defaultNum;
    }

    public int getResourceId() {
        return resourceId;
    }

    public void setResourceId(int resourceId) {
        this.resourceId = resourceId;
    }

    public InputNumberView(Context context) {
        this(context, null);
    }

    public InputNumberView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public InputNumberView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
        initAttr(context, attrs);
        initClick();
    }

    private void initAttr(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.InputNumberView);
        minInt = typedArray.getInt(R.styleable.InputNumberView_min, 0);
        maxInt = typedArray.getInt(R.styleable.InputNumberView_max, 0);
        stepInt = typedArray.getInt(R.styleable.InputNumberView_step, 1);
        disable = typedArray.getBoolean(R.styleable.InputNumberView_disable, false);
        defaultNum = typedArray.getInt(R.styleable.InputNumberView_defaultNum, 0);

        //设置默认值
        this.num = defaultNum;
        upNum();

        viewHeight = typedArray.getDimension(R.styleable.InputNumberView_viewHeight, dpToPxUtils.dpToPx(50));
        ViewGroup.LayoutParams lp = viewLayout.getLayoutParams();
        lp.height = (int) viewHeight;
        viewLayout.setLayoutParams(lp);
        viewLayout.requestLayout();

        resourceId = typedArray.getResourceId(R.styleable.InputNumberView_BtnBackground, -1);

        float editTextWidth = typedArray.getDimension(R.styleable.InputNumberView_editTextWidth, dpToPxUtils.dpToPx(50));
        LinearLayout.LayoutParams edit_lp=(LinearLayout.LayoutParams)editNum.getLayoutParams();
        edit_lp.width=(int)editTextWidth;
        editNum.setLayoutParams(edit_lp);
        editNum.requestLayout();
    }

    private void initView(Context context) {
        //LayoutInflater.from(context).inflate(R.layout.layout_input_number, this, true);
        //LayoutInflater.from(context).inflate(R.layout.layout_input_number, this);
        View view = LayoutInflater.from(context).inflate(R.layout.layout_input_number, this, false);
        addView(view);

        viewLayout = this.findViewById(R.id.mLl_package);
        leftBtn = this.findViewById(R.id.mBtn_jian);
        editNum = this.findViewById(R.id.mEdit_num);
        rightBtn = this.findViewById(R.id.mBtn_jia);
    }

    private void initClick() {
        leftBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                num -= stepInt;
                //设置最小值
                if (num < minInt) {
                    num = minInt;
                }
                setNum(num);
            }
        });

        rightBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                num += stepInt;
                //设置最大值
                if (num > maxInt) {
                    num = maxInt;
                }
                setNum(num);
            }
        });
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
        upNum();
    }

    public void upNum() {
        editNum.setText(String.valueOf(num));
        if (numListener != null) {
            numListener.getNum(num);
        }
    }

    public void setNumListener(NumListener numListener) {
        this.numListener = numListener;
    }

    public interface NumListener {
        void getNum(int value);
    }
}
