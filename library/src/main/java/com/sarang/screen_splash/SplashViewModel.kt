package com.sarang.screen_splash

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

/**
 * 스플레시 뷰모델
 */
class SplashViewModel(context: Application) : AndroidViewModel(context) {
    // 프로그레스 점
    @JvmField
    var progress = MutableLiveData<String>()

    // 준비완료 상태
    val isReady = MutableLiveData<Boolean>()

    // 네비게이션 인터페이스

    // 장비 등록 여부
    val isRegisteredDevice = MutableLiveData<Boolean>()

    // 위변조 체크 여부
    val isForgery = MutableLiveData<Boolean>()

    // 에러메시지
    private val _errorMsg = MutableLiveData<String>()

    @JvmField
    var errorMsg: LiveData<String> = _errorMsg

    // 장비 관지라


    // 저장소


    // 프로그레스바 런어블
    private var runnable: Runnable? = null
    fun setIsReady(isReady: Boolean) {
        this.isReady.value = isReady
    }

    /**
     * 프로그레스바 시작
     */
    fun startProgress() {
    }

    /**
     * 프로그레스바 멈춤
     */
    fun stopProgress() {
    }

    /**
     * 장비등록
     */
    fun registerDevice() {

    }

    /**
     * 생성자
     *
     * @param context
     */
    init {
        progress.value = ""
    }
}