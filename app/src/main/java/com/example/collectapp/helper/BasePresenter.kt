package com.example.collectapp.helper

abstract class BasePresenter<T>(val view: BaseFragment<T>, private val provider: BaseProvider<T>) {

    open fun getPresenterResponse() {
        view.showProgressBar()
        provider.getProviderResponse(object : PresenterCallback<T> {

            override fun onSuccess(responseModel: T) {
                view.hideProgressBar();
                view.loadResponse(responseModel);
            }
            override fun onFailure(message: String) {
                view show message
                view.hideProgressBar()
            }
        })
    }

}