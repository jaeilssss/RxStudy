package com.example.rxproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable

class MainActivity : AppCompatActivity() {

    private var greeting : String = "Hello From RxJava"

    private lateinit var observable : Observable<String>

    private lateinit var myObserver : Observer<String>

    companion object var TAG : String = "my APP"

    private lateinit var txt : TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        observable = Observable.just(greeting)

        txt = findViewById(R.id.textview)

        myObserver = object  : Observer<String> {
            override fun onSubscribe(d: Disposable) {

                Log.i(TAG , "onSubscribe invoked")

            }

            override fun onNext(t: String) {
                Log.i(TAG , "onNext invoked")
                txt.text = t


            }

            override fun onError(e: Throwable) {
                Log.i(TAG , "onError invoked")

            }

            override fun onComplete() {
                Log.i(TAG , "onComplete invoked")

            }

        }

        observable.subscribe(myObserver)


    }
}