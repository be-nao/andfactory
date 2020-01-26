package com.example.andfactory.ui.readme

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import com.example.andfactory.R
import com.example.andfactory.databinding.ReadMeFragmemtBinding
import dagger.android.support.DaggerFragment

class ReadMeFragment : DaggerFragment() {

    companion object {
        const val ARGS_README_URL = "read_me_url"
        fun newInstance(readMeUrl: String): ReadMeFragment {
            val fragment = ReadMeFragment()
            fragment.arguments = bundleOf(ARGS_README_URL to readMeUrl)
            return fragment
        }
    }

    private lateinit var binding: ReadMeFragmemtBinding
    var url: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.read_me_fragmemt, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        url = arguments?.getString(ARGS_README_URL)
        setBinding()
    }

    private fun setBinding() {
        binding.apply {
            readMeWebView.apply {
                loadUrl(this@ReadMeFragment.url)
            }
        }
    }
}
