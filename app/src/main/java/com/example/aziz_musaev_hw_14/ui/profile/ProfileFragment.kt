package com.example.aziz_musaev_hw_14.ui.profile

import android.R.attr.previewImage
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.example.aziz_musaev_hw_14.databinding.FragmentProfileBinding


class ProfileFragment : Fragment() {
private lateinit var binding: FragmentProfileBinding
    var mGetContent: ActivityResultLauncher<String> = registerForActivityResult(
        ActivityResultContracts.GetContent()
    ) { uri -> binding.ivProfile.setImageURI(uri) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(LayoutInflater.from(context),container,false)
        initListener();
        return binding.root
    }

    private fun initListener() {
        binding.ivProfile.setOnClickListener{
            mGetContent.launch("image/*");
        }
    }


}