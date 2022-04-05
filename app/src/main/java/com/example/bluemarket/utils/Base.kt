package com.example.bluemarket.utils

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bluemarket.R
import io.reactivex.SingleObserver
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable


abstract class MyViewModel : ViewModel() {

    val progressbarLiveData = MutableLiveData<Boolean>()
    val compositeDisposable = CompositeDisposable()

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}

abstract class MySingleObserver<T>(val compositeDisposable: CompositeDisposable) :
    SingleObserver<T> {
    override fun onSubscribe(d: Disposable) {
        compositeDisposable.add(d)
    }

    override fun onError(e: Throwable) {
        Log.i("LOG", e.toString())
    }
}

interface MyView {
    val root: ConstraintLayout?
    val myContext: Context?
    fun showProgressBar(show: Boolean) {
        root?.let { rootView ->
            myContext?.let { cntx ->
                var progressbarView = rootView.findViewById<View>(R.id.frame_progressbar)
                if (progressbarView == null && show) {
                    progressbarView =
                        LayoutInflater.from(cntx).inflate(R.layout.progressbar, rootView, false)
                    rootView.addView(progressbarView)
                }
                progressbarView?.visibility = if (show) View.VISIBLE else View.INVISIBLE
            }
        }
    }
}

abstract class MyFragment : Fragment(), MyView {
    override val root: ConstraintLayout?
        get() = view as ConstraintLayout
    override val myContext: Context?
        get() = context
}

