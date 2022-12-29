package com.example.rxproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.observers.DisposableObserver
import io.reactivex.rxjava3.schedulers.Schedulers

class MainActivity : AppCompatActivity() {

    private var greeting : String = "Hello From RxJava"

    private lateinit var observable : Observable<String>

    private lateinit var myObserver : DisposableObserver<String>


    private lateinit var myObserver2 : DisposableObserver<String>


    companion object var TAG : String = "my APP"

    private lateinit var txt : TextView

//    private lateinit var disposable : Disposable

    private var compositeDisposable = CompositeDisposable()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        observable = Observable.just(greeting)

        observable.subscribeOn(Schedulers.io())

        observable.observeOn(AndroidSchedulers.mainThread())


        txt = findViewById(R.id.textview)

//        myObserver = object  : Observer<String> {
//            override fun onSubscribe(d: Disposable) {
//
//                Log.i(TAG , "onSubscribe invoked")
//
////                disposable = d
//
//            }
//
//            override fun onNext(t: String) {
//                Log.i(TAG , "onNext invoked")
//                txt.text = t
//
//
//            }
//
//            override fun onError(e: Throwable) {
//                Log.i(TAG , "onError invoked")
//
//            }
//
//            override fun onComplete() {
//                Log.i(TAG , "onComplete invoked")
//
//            }
//
//        }


        myObserver = object : DisposableObserver<String>() {
            override fun onNext(t: String) {
                Log.i(TAG , "onComplete invoked")

            Toast.makeText(applicationContext,t.toString(),Toast.LENGTH_SHORT).show()
            }

            override fun onError(e: Throwable) {
                Log.i(TAG , "onComplete invoked")
            }

            override fun onComplete() {
                Log.i(TAG , "onComplete invoked")
            }

        }

        compositeDisposable.add(myObserver)

        observable.subscribe(myObserver)

        myObserver2 = object : DisposableObserver<String>() {
            override fun onNext(t: String) {
                Log.i(TAG , "onComplete invoked")

                txt.text = t
            }

            override fun onError(e: Throwable) {
                Log.i(TAG , "onComplete invoked")
            }

            override fun onComplete() {
                Log.i(TAG , "onComplete invoked")
            }

        }

        compositeDisposable.add(myObserver2)
        observable.subscribe(myObserver2)



    }

    override fun onDestroy() {
        super.onDestroy()

//        myObserver.dispose()
//        myObserver2.dispose()

        compositeDisposable.clear()

    }
}