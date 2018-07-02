package com.yuki.zhangyue.module.main.bookcase;

import android.widget.ImageView;
import com.chad.library.adapter.base.BaseItemDraggableAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.slim.imageloader.GlideApp;
import com.yuki.zhangyue.R;
import com.yuki.zhangyue.app.entity.BookDao;
import com.yuki.zhangyue.app.widget.MaskableImageView;

import java.util.ArrayList;

/**
 * author : zchu
 * date   : 2017/9/14
 * desc   :
 */

public class BookcaseAdapter extends BaseItemDraggableAdapter<BookDao,BaseViewHolder> {
    private boolean isEditing = false;
    private ArrayList<BookDao> selectedBookDaos = new ArrayList<>();
    private OnItemSelectedListener mListener;

    public BookcaseAdapter() {
        super(R.layout.item_bookcase_book,null);
    }

    @Override
    protected void convert(BaseViewHolder helper, BookDao item) {
        GlideApp
                .with(mContext)
                .load(item.coverUrl)
                .placeholder(R.drawable.ic_book_cover_default)
                .into((ImageView) helper.getView(R.id.iv_cover));
        helper.setText(R.id.tv_title, item.name);
        helper.addOnLongClickListener(R.id.iv_cover);
        helper.getView(R.id.iv_selected).setSelected(selectedBookDaos.contains(item));
        helper.setGone(R.id.iv_selected, isEditing);
        helper.setGone(R.id.tv_updated,item.hasUpdate);
        ((MaskableImageView) helper.getView(R.id.iv_cover)).setEnabledMaskable(!isEditing);
    }


    public boolean cancelEdit() {
        if (isEditing) {
            changeEditState(false);
            return true;
        }
        return false;
    }

    public boolean startEdit() {
        if (!isEditing) {
            changeEditState(true);
            return true;
        }
        return false;
    }

    /**
     * 开启编辑模式
     */
    private void changeEditState(boolean state) {
        isEditing = state;
        selectedBookDaos.clear();
        notifyDataSetChanged();
        if(mListener!=null){
            mListener.onSelectedItemsChange(selectedBookDaos);
        }
    }

    public void selectedItem(int position) {

        BookDao bookDao = mData.get(position);
        if (selectedBookDaos.contains(bookDao)) {
            selectedBookDaos.remove(bookDao);
        } else {
            selectedBookDaos.add(bookDao);
        }
        notifyItemChanged(position);
        if (mListener != null) {
            mListener.onSelectedItemsChange(selectedBookDaos);
        }
    }

    public void selectedAllItem() {
        selectedBookDaos = new ArrayList<>(mData);
        notifyDataSetChanged();
        if (mListener != null) {
            mListener.onSelectedItemsChange(selectedBookDaos);
        }
    }

    public void clearSelectedAllItem() {
        selectedBookDaos.clear();
        notifyDataSetChanged();
        if (mListener != null) {
            mListener.onSelectedItemsChange(selectedBookDaos);
        }
    }

    public boolean isEditing() {
        return isEditing;
    }

    public interface OnItemSelectedListener {
        void onSelectedItemsChange(ArrayList<BookDao> items);
    }

    public void setOnItemSelectedListener(OnItemSelectedListener listener) {
        this.mListener = listener;
        mListener.onSelectedItemsChange(selectedBookDaos);
    }

    public ArrayList<BookDao> getSelectedBookDaos() {
        return selectedBookDaos;
    }
}
