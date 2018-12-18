package com.example.sho.mvvm.rxkotlin

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.widget.TextView
import android.widget.Toast
import com.example.sho.mvvm.R
import com.jakewharton.rxbinding2.view.RxView
import com.jakewharton.rxbinding2.widget.RxTextView
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_login.*


/**
 * よくあるログイン時画面のアクティビティ
 *
 * Created by sho on 2018/11/19.
 */
class LoginActivity : AppCompatActivity() {

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val emailObservable: Observable<Boolean> = observeTextChange(input_password)
        val passwordObservable: Observable<Boolean> = observeTextChange(input_password)

        Observable.combineLatest<Boolean, Boolean, Boolean>(
                emailObservable, passwordObservable,
                BiFunction { t1, t2 -> t1 && t2 })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(RxView.enabled(sign_in))

        sign_in.setOnClickListener {
            Toast.makeText(applicationContext, "login処理", Toast.LENGTH_SHORT).show()
        }
    }

    private fun observeTextChange(textView: TextView): Observable<Boolean> {
        return RxTextView.textChangeEvents(textView).map { t -> !TextUtils.isEmpty(t?.text()) }
    }
}