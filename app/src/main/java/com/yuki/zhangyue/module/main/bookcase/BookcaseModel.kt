package com.yuki.zhangyue.module.main.bookcase

import com.slim.base.mvp.Model
import com.slim.respository.IRepository
import javax.inject.Inject

/**
 * 项目：ZhangYue
 * 作者：Yuki- 2018/6/22
 * 邮箱：125508663@qq.com
 */
class BookcaseModel@Inject
constructor(repository: IRepository): Model(repository)

