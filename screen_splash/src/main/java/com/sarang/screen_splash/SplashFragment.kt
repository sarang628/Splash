package com.sarang.screen_splash

import android.os.Bundle
import android.transition.ChangeBounds
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Observer
import com.example.torang_core.navigation.SplashNavigation
import com.sarang.screen_splash.databinding.FragmentSplashBinding

/**
 * 스플레시 프레그먼트
 * 최초 앱 실행 체크를 담당
 */
class SplashFragment : Fragment() {
    // 테그
    private val TAG = "__SplashFragment"

    // 뷰모델
    lateinit var mViewModel: SplashViewModel

    // 바인딩
    lateinit var mBinding: FragmentSplashBinding

    //@Inject
    var splashNavigation: SplashNavigation? = null

    //@Inject
    //lateinit var deviceManager : TorangDeviceManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (activity?.application is SplashComponentProvider) {
            (activity?.application as SplashComponentProvider).getSplashCompoenet().inject(this)
        }
        sharedElementEnterTransition = ChangeBounds()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = FragmentSplashBinding.inflate(inflater, container, false)
        mBinding.lifecycleOwner = this

        // 뷰모델 초기화
        initViewModel()

        // 데이터 바인딩 초기화
        initBinding()

        // 장비 등
        mViewModel.registerDevice()

        return mBinding.root
    }


    private fun initBinding() {
        Log.v(TAG, "initBinding")

        mBinding.vm = mViewModel

        mViewModel.isRegisteredDevice.observe(viewLifecycleOwner, Observer {
            if (it) {

            }
        })

        mViewModel.isForgery.observe(requireActivity(), Observer {
            if (it) {
            }
        })

        mViewModel.isReady.observe(requireActivity(), Observer { aBoolean: Boolean? ->
            Log.v(TAG, "observe is ready : $aBoolean")
            if (splashNavigation == null) {
                Log.e(TAG, "splashNavigation is null")
                return@Observer
            }
            splashNavigation?.registerDeviceFingerPrint(object :
                SplashNavigation.OnRegisteredFingerPringListener {
                override fun onRegistered() {}
            })

            if (splashNavigation!!.isLogin) {
                Log.v(TAG, "is login goMain")
                splashNavigation?.goMain(fragmentManager)
            } else {
                Log.v(TAG, "isn't login goLogin")
                splashNavigation?.goLogin(fragmentManager)
            }
        })

        mViewModel.errorMsg.observe(viewLifecycleOwner, Observer { s: String? ->
            /*TorangDialog.Builder(context)
                .setMessage(s)
                .setPositiveButton(null) { _, _ -> activity?.finish() }
                .build()
                .show()*/
        })

        mViewModel.registerDevice()
    }

    private fun initViewModel() {
        mViewModel.splashNavigation = splashNavigation
        // mViewModel.deviceManager = deviceManager

    }

    override fun onPause() {
        Log.v(TAG, "onPuase")
        mViewModel.stopProgress()
        super.onPause()
    }

    override fun onResume() {
        Log.v(TAG, "onResume")
        mViewModel.startProgress()
        super.onResume()
    }

    companion object {
        /**
         * 해당 프레그먼트를 얻을 수 있습니다.
         *
         * @param supportFragmentManager
         * @return
         */
        operator fun get(supportFragmentManager: FragmentManager): SplashFragment? {
            return supportFragmentManager.findFragmentByTag(SplashFragment::class.java.simpleName) as SplashFragment?
        }

        /**
         * 해당 프래그먼트로 진입하는 기능입니다.
         *
         * @param appCompatActivity
         * @param containerId
         */
        fun go(
            appCompatActivity: AppCompatActivity,
            containerId: Int,
            splashNavigation: SplashNavigation?
        ) {
            val splashFragment = SplashFragment()
            if (splashNavigation != null) {
                splashFragment.splashNavigation = splashNavigation
            }
            appCompatActivity.supportFragmentManager.beginTransaction()
                .replace(containerId, splashFragment, SplashFragment::class.java.simpleName)
                .commit()
        }

        fun go(appCompatActivity: AppCompatActivity, containerId: Int) {
            val splashFragment = SplashFragment()
            appCompatActivity.supportFragmentManager.beginTransaction()
                .replace(containerId, splashFragment, SplashFragment::class.java.simpleName)
                .commit()
        }
    }
}