package com.yuki.zhangyue.module.read

import android.util.SparseArray
import com.yuki.zhangyue.app.entity.BookSectionContent
import com.yuki.zhangyue.app.widget.reader.StringAdapter



class ReadAdapter : StringAdapter() {

    private var bookArray: SparseArray<BookSectionContent> = SparseArray()


    override fun getPageSource(section: Int): String? {
        val sectionContent = bookArray.get(section)
        return if (sectionContent != null) bookArray.get(section).content else null
    }

    override fun getSectionCount(): Int {
        return bookArray.size()
    }

    override fun getSectionName(section: Int): String? {
        val sectionContent = bookArray.get(section)
        return if (sectionContent != null) bookArray.get(section).sectionName else null
    }

    override fun hasNextSection(currentSection: Int): Boolean {
        return bookArray.get(currentSection + 1) != null
    }

    override fun hasPreviousSection(currentSection: Int): Boolean {
        return bookArray.get(currentSection - 1) != null
    }

    fun addData(section: Int, content: BookSectionContent?) {
        bookArray.put(section, content)
    }


    fun hasSection(section: Int): Boolean {
        return bookArray.get(section) != null
    }

}
