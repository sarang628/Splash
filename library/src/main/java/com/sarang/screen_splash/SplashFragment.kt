package com.sarang.screen_splash

import android.os.Bundle
import android.transition.ChangeBounds
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Observer

/**
 * 스플레시 프레그먼트
 * 최초 앱 실행 체크를 담당
 */
class SplashFragment : Fragment() {
    // 테그
    private val TAG = "SplashFragment"

    // 뷰모델
    lateinit var mViewModel: SplashViewModel

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
        // 뷰모델 초기화
        initViewModel()

        // 데이터 바인딩 초기화
        initBinding()

        // 장비 등
        mViewModel.registerDevice()

        return TextView(context)
    }


    private fun initBinding() {
        Log.v(TAG, "initBinding")

        mViewModel.isRegisteredDevice.observe(viewLifecycleOwner, Observer {
            if (it) {

            }
        })

        mViewModel.isForgery.observe(requireActivity(), Observer {
            if (it) {
            }
        })

        mViewModel.isReady.observe(requireActivity(), Observer {

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
        fun go() {
        }

    }
}