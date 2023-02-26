package com.yoni.mysportfeed.data.feed.util

import android.content.res.Resources
import com.squareup.moshi.Moshi
import com.yoni.mysportfeed.R
import com.yoni.mysportfeed.data.feed.model.ServerResponseDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DefaultLocalFeedReader(
    private val resources: Resources,
    private val moshi: Moshi
): ILocalFeedReader {

    override suspend fun read(): ServerResponseDto? {
        val fileContent = resources.openRawResource(R.raw.default_feed)
        val feedJson = fileContent.bufferedReader().readText()

        withContext(Dispatchers.IO) {
            fileContent.close()
        }

        return moshi
            .adapter(ServerResponseDto::class.java)
            .fromJson(feedJson)
    }

}