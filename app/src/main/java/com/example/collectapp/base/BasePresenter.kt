package com.example.collectapp.base

import com.example.collectapp.session.alltransactions.view.AllTransactionsListView
import io.reactivex.disposables.CompositeDisposable

abstract class BasePresenter {

    val compositeDisposable = CompositeDisposable()

    open fun onCleared() {
        compositeDisposable.dispose()
    }

}