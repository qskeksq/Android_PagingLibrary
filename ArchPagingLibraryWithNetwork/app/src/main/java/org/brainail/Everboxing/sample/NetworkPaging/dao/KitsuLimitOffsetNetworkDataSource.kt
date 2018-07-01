package org.brainail.Everboxing.sample.NetworkPaging.dao

import android.arch.paging.DataSource
import android.arch.paging.TiledDataSource
import org.brainail.Everboxing.sample.NetworkPaging.KitsuResponse
import org.brainail.Everboxing.sample.NetworkPaging.KitsuRestApi

abstract class KitsuLimitOffsetNetworkDataSource<T> protected constructor(val dataProvider: KitsuRestApi) : TiledDataSource<T>() {

    var queryFilter: String = ""

    override fun countItems(): Int = DataSource.COUNT_UNDEFINED

    protected abstract fun convertToItems(items: KitsuResponse, size: Int): List<T>

    override fun loadRange(startPosition: Int, loadCount: Int): List<T>? {
        val response = KitsuRestApi.getKitsu(queryFilter, startPosition, loadCount).execute().body()
        return convertToItems(response, response.data.size)
    }

}