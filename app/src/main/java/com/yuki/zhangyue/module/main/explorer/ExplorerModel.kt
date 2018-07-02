package com.yuki.zhangyue.module.main.explorer

import com.slim.base.mvp.Model
import com.slim.respository.IRepository
import com.yuki.zhangyue.app.Api
import com.yuki.zhangyue.app.entity.Channel
import com.yuki.zhangyue.app.entity.HttpResult
import io.reactivex.Observable
import javax.inject.Inject

class ExplorerModel @Inject
constructor(repository: IRepository): Model(repository){
    fun getChannel(): Observable<MutableList<Channel>> {
        return repository.http(Api::class.java).channels
            .map { return@map it.data }
    }
}
