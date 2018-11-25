package com.example.sho.mvvm.rxkotlin

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.widget.TextView
import android.widget.Toast
import com.example.sho.mvvm.R
import com.jakewharton.rxbinding.view.RxView
import com.jakewharton.rxbinding.widget.RxTextView
import kotlinx.android.synthetic.main.activity_login.*
import rx.Observable
import rx.android.schedulers.AndroidSchedulers

/**
 * よくあるログイン時画面のアクティビティ
 *
 * Created by sho on 2018/11/19.
 */
class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val emailObservable: Observable<Boolean> = observeTextChange(input_email)
        val passwordObservable: Observable<Boolean> = observeTextChange(input_password)

        Observable.combineLatest(emailObservable, passwordObservable
        ) { validEmail, validPassword -> validEmail && validPassword }
                .subscribeOn(AndroidSchedulers.mainThread())
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