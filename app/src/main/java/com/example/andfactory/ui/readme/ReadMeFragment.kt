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
        const val ARGS_HTTP_URL = "http_url"
        const val ARGS_DEFAULT_BRANCH = "default_branch"
        fun newInstance(readMeUrl: String, defaultBranch: String): ReadMeFragment {
            val fragment = ReadMeFragment()
            fragment.arguments =
                bundleOf(ARGS_HTTP_URL to readMeUrl, ARGS_DEFAULT_BRANCH to defaultBranch)
            return fragment
        }
    }

    private lateinit var binding: ReadMeFragmemtBinding
    var url: String? = null
    var branch: String? = null

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
        url = arguments?.getString(ARGS_HTTP_URL)
        branch = arguments?.getString(ARGS_DEFAULT_BRANCH)
        setBinding()
    }

    private fun setBinding() {
        binding.apply {
            readMeWebView.apply {
                loadUrl(createReadMeUrl())
            }
        }
    }

    private fun createReadMeUrl(): String {
        return "$url/blob/$branch/README.md"
    }
}
