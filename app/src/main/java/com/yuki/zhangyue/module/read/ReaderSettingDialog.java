//package com.yuki.zhangyue.module.read;
//
//import android.app.Activity;
//import android.content.Context;
//import android.support.annotation.NonNull;
//import android.support.design.widget.BottomSheetDialog;
//import android.view.View;
//import android.widget.ImageView;
//import android.widget.SeekBar;
//import android.widget.TextView;
//import com.yuki.zhangyue.R;
//import com.yuki.zhangyue.app.utils.BrightnessUtils;
//import com.yuki.zhangyue.app.widget.ShapeView;
//import com.yuki.zhangyue.app.widget.reader.PageView;
//
//
//public class ReaderSettingDialog extends BottomSheetDialog implements View.OnClickListener, SeekBar.OnSeekBarChangeListener {
//
//    private PageView mPageView;
//    private ImageView readIvLightnessMinus;
//    private SeekBar readSbLightnessProgress;
//    private ImageView readIvLightnessPlus;
//    private TextView readTvLightnessSystem;
//
//    private TextView readTvFontSizeMinus;
//    private TextView readTvFontSize;
//    private TextView readTvFontSizePlus;
//    private TextView readTvFontSizeDefault;
//    private TextView readTvFontSetting;
//
//    private TextView readTvFlipOverCover;
//    private TextView readTvFlipOverSimulation;
//    private TextView readTvFlipOverSlide;
//    private TextView readTvFlipOverNone;
//    private View[] flipOverViews;
//
//    private ImageView readTvColorSetting;
//
//    private ShapeView readThemeWhite;
//    private ShapeView readThemeAmber;
//    private ShapeView readThemeGreen;
//    private ShapeView readThemeBrown;
//    private ShapeView readThemeBlack;
//    private View[] themeViews;
//
//
//    public ReaderSettingDialog(@NonNull Context context, @NonNull PageView pageView) {
//        super(context, R.style.Read_Setting_Dialog);
//        setOwnerActivity((Activity) context);
//        super.setContentView(R.layout.bottom_sheet_read_setting);
//        this.mPageView = pageView;
//        initView();
//        initListener();
//        initDisplay();
//    }
//
//    private void initView() {
//        readIvLightnessMinus = findViewById(R.id.read_iv_lightness_minus);
//        readSbLightnessProgress = findViewById(R.id.read_sb_lightness_progress);
//        readIvLightnessPlus = findViewById(R.id.read_iv_lightness_plus);
//        readTvLightnessSystem = findViewById(R.id.read_tv_lightness_system);
//
//        readTvFontSizeMinus = findViewById(R.id.read_tv_font_size_minus);
//        readTvFontSize = findViewById(R.id.read_tv_font_size);
//        readTvFontSizePlus = findViewById(R.id.read_tv_font_size_plus);
//        readTvFontSizeDefault = findViewById(R.id.read_tv_font_size_default);
//        readTvFontSetting = findViewById(R.id.read_tv_font_setting);
//
//        readTvFlipOverCover = findViewById(R.id.read_tv_flip_over_cover);
//        readTvFlipOverSimulation = findViewById(R.id.read_tv_flip_over_simulation);
//        readTvFlipOverSlide = findViewById(R.id.read_tv_flip_over_slide);
//        readTvFlipOverNone = findViewById(R.id.read_tv_flip_over_none);
//        readTvColorSetting = findViewById(R.id.read_tv_color_setting);
//        flipOverViews = new View[]{
//                readTvFlipOverSimulation,
//                readTvFlipOverCover,
//                readTvFlipOverSlide,
//                readTvFlipOverNone
//        };
//
//        readThemeWhite = findViewById(R.id.read_theme_white);
//        readThemeAmber = findViewById(R.id.read_theme_amber);
//        readThemeGreen = findViewById(R.id.read_theme_green);
//        readThemeBrown = findViewById(R.id.read_theme_brown);
//        readThemeBlack = findViewById(R.id.read_theme_black);
//        themeViews = new View[]{
//                readThemeWhite,
//                readThemeAmber,
//                readThemeGreen,
//                readThemeBrown,
//                readThemeBlack
//        };
//    }
//
//    private void initListener() {
//        readIvLightnessMinus.setOnClickListener(this);
//        readIvLightnessPlus.setOnClickListener(this);
//        readTvLightnessSystem.setOnClickListener(this);
//        readTvFontSizeMinus.setOnClickListener(this);
//        readTvFontSizePlus.setOnClickListener(this);
//        readTvFontSizeDefault.setOnClickListener(this);
//        readTvFontSetting.setOnClickListener(this);
//        readTvFlipOverCover.setOnClickListener(this);
//        readTvFlipOverSimulation.setOnClickListener(this);
//        readTvFlipOverSlide.setOnClickListener(this);
//        readTvFlipOverNone.setOnClickListener(this);
//        readTvColorSetting.setOnClickListener(this);
//
//        readThemeWhite.setOnClickListener(this);
//        readThemeAmber.setOnClickListener(this);
//        readThemeGreen.setOnClickListener(this);
//        readThemeBrown.setOnClickListener(this);
//        readThemeBlack.setOnClickListener(this);
//
//        readSbLightnessProgress.setOnSeekBarChangeListener(this);
//
//    }
//
//    private void initDisplay() {
//        readTvFontSize.setText(String.valueOf(mPageView.getTextSize()));
//        setPageMode(mPageView.getPageMode());
//        ReadThemes readTheme = ReadThemes.Companion.getReadTheme(mPageView.getPageBackground(),
//                mPageView.getTextColor());
//        if (readTheme != null) {
//            setReadTheme(readTheme);
//        }
//        setBrightness(ReaderSettingManager.getInstance().getBrightness(), readSbLightnessProgress);
//        setAutoBrightness(ReaderSettingManager.getInstance().isBrightnessAuto(), readTvLightnessSystem);
//    }
//
//    @Override
//    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.read_iv_lightness_minus://亮度减
//                setBrightness(readSbLightnessProgress.getProgress() - 1, readSbLightnessProgress);
//                break;
//            case R.id.read_iv_lightness_plus://亮度加
//                setBrightness(readSbLightnessProgress.getProgress() + 1, readSbLightnessProgress);
//                break;
//            case R.id.read_tv_lightness_system://亮度跟随系统
//                setAutoBrightness(!readTvLightnessSystem.isSelected(), readTvLightnessSystem);
//                break;
//            case R.id.read_tv_font_size_minus://字体大小减
//                ReaderSettingManager.getInstance().setTextSize(mPageView.getTextSize() - 1);
//                setTextSize(mPageView.getTextSize() - 1);
//                break;
//            case R.id.read_tv_font_size_plus://字体大小加
//                ReaderSettingManager.getInstance().setTextSize(mPageView.getTextSize() + 1);
//                setTextSize(mPageView.getTextSize() + 1);
//                break;
//            case R.id.read_tv_font_size_default://字体大小 默认
//                setTextSize(40);
//                ReaderSettingManager.getInstance().setTextSize(40);
//                break;
//            case R.id.read_tv_font_setting://字体设置
//
//                break;
//            case R.id.read_tv_flip_over_cover://翻页模式-仿真
//                setPageMode(PageView.PAGE_MODE_COVER);
//                break;
//            case R.id.read_tv_flip_over_simulation://翻页模式-覆盖
//                setPageMode(PageView.PAGE_MODE_SIMULATION);
//                break;
//            case R.id.read_tv_flip_over_slide://翻页模式-滑动
//                setPageMode(PageView.PAGE_MODE_SLIDE);
//                break;
//            case R.id.read_tv_flip_over_none://翻页模式-无
//                setPageMode(PageView.PAGE_MODE_NONE);
//                break;
//            case R.id.read_theme_white:
//                setReadTheme(ReadThemes.WHITE);
//                break;
//            case R.id.read_theme_amber:
//                setReadTheme(ReadThemes.AMBER);
//                break;
//            case R.id.read_theme_green:
//                setReadTheme(ReadThemes.GREEN);
//                break;
//            case R.id.read_theme_brown:
//                setReadTheme(ReadThemes.BROWN);
//                break;
//            case R.id.read_theme_black:
//                setReadTheme(ReadThemes.BLACK);
//                break;
//            case R.id.read_tv_color_setting://更多主题设置
//
//                break;
//        }
//
//    }
//
//    private void setBrightness(int progress, SeekBar seekBar) {
//        if (progress < 0 || progress > seekBar.getMax()) {
//            return;
//        }
//        if (readTvLightnessSystem.isSelected()) {
//            setAutoBrightness(false, readTvLightnessSystem);
//        }
//        seekBar.setProgress(progress);
//        BrightnessUtils.setBrightness(getOwnerActivity(), progress);
//    }
//
//    private void setAutoBrightness(boolean isEnabled, View view) {
//        if (isEnabled) {
//            BrightnessUtils.setUseSystemBrightness(getOwnerActivity());
//        } else {
//            BrightnessUtils.setBrightness(getOwnerActivity(), readSbLightnessProgress.getProgress());
//        }
//        ReaderSettingManager.getInstance().setAutoBrightness(isEnabled);
//        view.setSelected(isEnabled);
//    }
//
//    private void setReadTheme(ReadThemes readTheme) {
//        mPageView.setPageBackground(readTheme.getPageBackground());
//        mPageView.setTextColor(readTheme.getTextColor());
//        mPageView.refreshPage();
//        ReaderSettingManager.getInstance().setPageBackground(readTheme.getPageBackground());
//        ReaderSettingManager.getInstance().setTextColor(readTheme.getTextColor());
//        switch (readTheme) {
//            case WHITE:
//                selectedThemeView(readThemeWhite);
//                break;
//            case AMBER:
//                selectedThemeView(readThemeAmber);
//                break;
//            case GREEN:
//                selectedThemeView(readThemeGreen);
//                break;
//            case BROWN:
//                selectedThemeView(readThemeBrown);
//                break;
//            case BLACK:
//                selectedThemeView(readThemeBlack);
//                break;
//        }
//    }
//
//
//    private void selectedThemeView(@NonNull View selectedView) {
//        selectedView.setSelected(true);
//
//        for (View view : themeViews) {
//            if (view != selectedView && view.isSelected()) {
//                view.setSelected(false);
//            }
//        }
//
//    }
//
//    private void setTextSize(int size) {
//        mPageView.setTextSize(size);
//        readTvFontSize.setText(String.valueOf(size));
//    }
//
//    private void setPageMode(int pageMode) {
//        View selectedView = null;
//        switch (pageMode) {
//            case PageView.PAGE_MODE_COVER:
//                readTvFlipOverCover.setSelected(true);
//                selectedView = readTvFlipOverCover;
//                break;
//            case PageView.PAGE_MODE_SIMULATION:
//                readTvFlipOverSimulation.setSelected(true);
//                selectedView = readTvFlipOverSimulation;
//                break;
//            case PageView.PAGE_MODE_SLIDE:
//                readTvFlipOverSlide.setSelected(true);
//                selectedView = readTvFlipOverSlide;
//                break;
//            case PageView.PAGE_MODE_NONE:
//                readTvFlipOverNone.setSelected(true);
//                selectedView = readTvFlipOverNone;
//                break;
//        }
//        for (View view : flipOverViews) {
//            if (view != selectedView && view.isSelected()) {
//                view.setSelected(false);
//            }
//        }
//        if (pageMode != mPageView.getPageMode()) {
//            mPageView.setPageAnimMode(pageMode);
//            ReaderSettingManager
//                    .getInstance()
//                    .setPageMode(pageMode);
//        }
//
//    }
//
//    @Override
//    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
//        if (readTvLightnessSystem.isSelected()) {
//            setAutoBrightness(false, readTvLightnessSystem);
//        }
//        BrightnessUtils.setBrightness(getOwnerActivity(), progress);
//    }
//
//    @Override
//    public void onStartTrackingTouch(SeekBar seekBar) {
//
//    }
//
//    @Override
//    public void onStopTrackingTouch(SeekBar seekBar) {
//        //设置进度
//        ReaderSettingManager.getInstance().setBrightness(seekBar.getProgress());
//    }
//
//
//}
