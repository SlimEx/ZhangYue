package com.yuki.zhangyue.module.read

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.os.PowerManager
import android.support.design.widget.BottomSheetDialog
import android.support.v4.view.ViewPager
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.slim.annotation.LayoutId
import com.slim.base.XActivity
import com.yuki.zhangyue.R
import com.yuki.zhangyue.app.common.BOOK
import com.yuki.zhangyue.app.common.K_NIGHT_MODE
import com.yuki.zhangyue.app.common.SECTION_COUNT
import com.yuki.zhangyue.app.entity.Book
import com.yuki.zhangyue.app.entity.BookSectionContent
import com.yuki.zhangyue.app.entity.BookSectionItem
import com.yuki.zhangyue.app.utils.BrightnessUtils
import com.yuki.zhangyue.app.utils.DataHelper
import com.yuki.zhangyue.app.utils.SystemBarUtils
import com.yuki.zhangyue.app.widget.RecyclerViewItemDecoration
import com.yuki.zhangyue.app.widget.reader.OnPageChangeListener
import com.yuki.zhangyue.app.widget.reader.PageView
import kotlinx.android.synthetic.main.activity_read.*
import kotlinx.android.synthetic.main.layout_read_bottom.*
import kotlinx.android.synthetic.main.toolbar_read.*

@LayoutId(R.layout.activity_read)
class ReadActivity : XActivity<ReadPresenter>(), View.OnClickListener {


    private lateinit var mWakeLock: PowerManager.WakeLock

    private val mTopInAnim: Animation by lazy { AnimationUtils.loadAnimation(this, R.anim.slide_top_in) }
    private val mTopOutAnim: Animation by lazy { AnimationUtils.loadAnimation(this, R.anim.slide_top_out) }
    private val mBottomInAnim: Animation by lazy { AnimationUtils.loadAnimation(this, R.anim.slide_bottom_in) }
    private val mBottomOutAnim: Animation by lazy { AnimationUtils.loadAnimation(this, R.anim.slide_bottom_out) }
    private val mReadSettingDialog: BottomSheetDialog by lazy { ReaderSettingDialog(this, readView) }
    private val book by lazy { intent.getSerializableExtra(BOOK) as Book }
    private val bookSectionAdapter by lazy { BookSectionAdapter() }
    private val readViewAdapter by lazy { ReadAdapter() }

    private var currentIndex = 0
    override fun initData(savedInstanceState: Bundle?) {
        currentIndex = intent.getIntExtra(SECTION_COUNT, 0)
        ReaderSettingManager.init(this)
        //半透明化StatusBar
        hideSystemBar()
        SystemBarUtils.transparentStatusBar(this)
        //初始化常亮
        initWakLock()
        //设置当前Activity的Bright
        initBright()
        //初始化侧边栏recyclerView
        initDrawLayout()
        //初始化menu动画
        initMenuAnim()
        //填充readView
        initReadView()
        initOnClickLister(this,tvSections, tvNightMode, tvSetting)
        mPresenter?.initData(book.id, currentIndex,readViewAdapter)
    }

    private fun initReadView() {
        readView.run {
            setAdapter(readViewAdapter)
            setOnThemeChangeListener { textColor, backgroundColor, textSize ->
                rvSection.setBackgroundColor(backgroundColor)
            }
            textSize = ReaderSettingManager.getInstance().textSize
            textColor = ReaderSettingManager.getInstance().textColor
            pageBackground = ReaderSettingManager.getInstance().pageBackground
            setTouchListener(object : PageView.TouchListener {
                override fun center() {
                    toggleMenu()
                }

                override fun cancel() {
                }
            })
            setOnPageChangeListener(object : ViewPager.OnPageChangeListener {
                override fun onPageChange(pos: Int) {
                }

                override fun onChapterChange(pos: Int) {
                    mPresenter?.initSectionContent(book.id,pos)
                }

                override fun onPageCountChange(count: Int) {
                }
            })

        }
        val isNight = ReadThemes.checkReadTheme(readView.pageBackground, readView.textColor, ReadThemes.NIGHT)
        tvNightMode.run {
            isSelected = isNight
            text = if (isNight) getString(R.string.read_daytime) else getString(R.string.read_night)
        }

    }


    private fun initDrawLayout() {
        readDrawer.isEnabled=false
        rvSection.run {
            layoutManager = LinearLayoutManager(this@ReadActivity)
            addItemDecoration(RecyclerViewItemDecoration.Builder(context)
                .color(Color.argb(77, 97, 97, 97))
                .thickness(1)
                .create())
            adapter = bookSectionAdapter
        }
    }

    fun initSectionList(data: List<BookSectionItem>) {
        bookSectionAdapter.setNewData(data)
        readDrawer.isEnabled=true
    }


    fun openSection(index: Int) {
        readView.openSection(index)
        currentIndex = index
    }

    fun initSectionContent(data: BookSectionContent) {
        readViewAdapter.addData(data.sectionIndex-1, data)
    }

    private fun initBright() {
        if (ReaderSettingManager.getInstance().isBrightnessAuto) {
            BrightnessUtils.setUseSystemBrightness(this)
        } else {
            BrightnessUtils.setBrightness(this, ReaderSettingManager.getInstance().brightness)
        }
    }

    private fun initWakLock() {
        //初始化屏幕常亮类
        val pm = getSystemService(Context.POWER_SERVICE) as PowerManager
        mWakeLock = pm.newWakeLock(FLAG_KEEP_SCREEN_ON, "keep bright")
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.tvSections -> readDrawer.openDrawer(readSide)
            R.id.tvNightMode -> {
                val nightModeSelected = !tvNightMode.isSelected
                toggleNightMode(nightModeSelected)
                ReaderSettingManager.getInstance().setNightMode(nightModeSelected)
                saveNightMode(nightModeSelected)
            }
            R.id.tvSetting -> {
                toggleMenu()
                openReadSetting()
            }
        }
    }

    private fun saveNightMode(nightModeSelected: Boolean) {
        DataHelper.setBoolenSF(this, K_NIGHT_MODE, nightModeSelected)
    }

    private fun openReadSetting() {
        mReadSettingDialog.show()
    }

    private fun toggleNightMode(isOpen: Boolean) {
        if (isOpen) {
            tvNightMode.text = getString(R.string.read_daytime)
            tvNightMode.isSelected = true
            readView.pageBackground = ReadThemes.NIGHT.pageBackground
            readView.textColor = ReadThemes.NIGHT.textColor
            readView.refreshPage()
            ReaderSettingManager.getInstance().pageBackground = readView.pageBackground
            ReaderSettingManager.getInstance().textColor = readView.textColor
        } else {
            tvNightMode.text = getString(R.string.read_night)
            tvNightMode.isSelected = false
            readView.pageBackground = ReadThemes.DEFAULT.pageBackground
            readView.textColor = ReadThemes.DEFAULT.textColor
            readView.refreshPage()
            ReaderSettingManager.getInstance().pageBackground = readView.pageBackground
            ReaderSettingManager.getInstance().textColor = readView.textColor
        }
    }

    /**
     * 切换菜单栏的可视状态
     * 默认是隐藏的
     */
    private fun toggleMenu() {
        if (appbar.visibility == VISIBLE) {
            //关闭
            appbar.startAnimation(mTopOutAnim)
            readBottom.startAnimation(mBottomOutAnim)
            appbar.visibility = GONE
            readBottom.visibility = GONE
            hideSystemBar()

        } else {
            appbar.visibility = VISIBLE
            readBottom.visibility = VISIBLE
            appbar.startAnimation(mTopInAnim)
            readBottom.startAnimation(mBottomInAnim)
            showSystemBar()
        }
    }

    private fun showSystemBar() {
        //显示
        SystemBarUtils.showUnStableStatusBar(this)
    }

    private fun hideSystemBar() {
        SystemBarUtils.hideStableStatusBar(this)
    }

    //初始化菜单动画
    private fun initMenuAnim() {
        mBottomInAnim.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation) {
                readView.setCanTouch(false)
            }

            override fun onAnimationEnd(animation: Animation) {
                readView.setCanTouch(true)
            }

            override fun onAnimationRepeat(animation: Animation) {

            }
        })
        mBottomOutAnim.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation) {
                readView.setCanTouch(false)
            }

            override fun onAnimationEnd(animation: Animation) {
                readView.setCanTouch(true)

            }

            override fun onAnimationRepeat(animation: Animation) {

            }
        })
    }

    override fun onBackPressedSupport() {
        if (readDrawer.isDrawerOpen(readSide)) {
            readDrawer.closeDrawers()
        }
        if (appbar.visibility == View.VISIBLE) {
            toggleMenu()
            return
        }
        super.onBackPressedSupport()

    }

    override fun onResume() {
        super.onResume()
        mWakeLock.acquire(10 * 60 * 1000L /*10 minutes*/)
    }

    override fun onPause() {
        super.onPause()
        mWakeLock.release()
    }


}
