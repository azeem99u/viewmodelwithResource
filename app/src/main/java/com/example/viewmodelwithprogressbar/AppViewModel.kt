package com.example.viewmodelwithprogressbar

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*
import kotlinx.coroutines.GlobalScope.coroutineContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class AppViewModel : ViewModel() {

    val allFiles: MutableLiveData<Resource<NewsResponse>> = MutableLiveData()

    init {
        getAllFiles()
    }

    private fun getAllFiles() {
        allFiles.postValue(Resource.Loading())

        viewModelScope.launch {
            allFiles.postValue(handleSearchNewsResponse())
        }
    }

    private suspend fun handleSearchNewsResponse(): Resource<NewsResponse> {
            return withContext(Dispatchers.IO){
                var fileModeList: List<FileModel> = emptyList()
                val newsResponse = NewsResponse(fileModeList)
                return@withContext Resource.Success(newsResponse)
            }
    }

    data class NewsResponse(val fileModel: List<FileModel>)


}