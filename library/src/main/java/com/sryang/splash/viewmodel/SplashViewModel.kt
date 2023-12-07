package com.sryang.splash.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sryang.splash.uistate.LoginState
import com.sryang.splash.uistate.SplashUiState
import com.sryang.splash.usecase.SplashUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * 스플레시 뷰모델
 */
@HiltViewModel
class SplashViewModel @Inject constructor(
    private val splashService: SplashUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(SplashUiState())
    val uiState = _uiState.asStateFlow()

    init {
        //로그인 상태 체크
        checkLogin()
    }

    fun logout() {
        viewModelScope.launch {
            splashService.logout()
            _uiState.emit(
                uiState.value.copy(
                    loginState = LoginState.LOGOUT
                )
            )
        }
    }

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

    fun checkLogin() {
        _uiState.update { it.copy(loginState = null) }

        viewModelScope.launch {
            // 토큰이 없다면 로그아웃 상태
            if (!splashService.isLogin()) {
                _uiState.update { it.copy(loginState = LoginState.LOGOUT) }
                return@launch
            }

            try {
                if (!splashService.checkSession()) { //세션체크
                    _uiState.update { it.copy(loginState = LoginState.SESSION_EXPIRED) }
                    return@launch
                }
            } catch (e: Exception) {
                _uiState.update { it.copy(loginState = LoginState.NETWORK_ERROR) }
                return@launch
            }

            // 로그인
            _uiState.update { it.copy(loginState = LoginState.LOGIN) }

        }
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