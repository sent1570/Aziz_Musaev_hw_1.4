package com.example.aziz_musaev_hw_14.ui.profile

import android.R.attr.previewImage
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import com.example.aziz_musaev_hw_14.R
import com.example.aziz_musaev_hw_14.databinding.FragmentProfileBinding
import com.example.aziz_musaev_hw_14.extenssion.loadImage
import com.example.aziz_musaev_hw_14.utils.Preferences
import com.google.firebase.auth.FirebaseAuth


class ProfileFragment : Fragment() {
    private lateinit var auth: FirebaseAuth
    private lateinit var binding: FragmentProfileBinding
    private lateinit var preferences: Preferences
    private var mGetContent: ActivityResultLauncher<String> = registerForActivityResult(
        ActivityResultContracts.GetContent()

    ) { uri ->


        binding.ivProfile.loadImage(uri.toString())
        Preferences(requireContext()).saveImageUri(uri.toString())


    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        preferences = Preferences(requireContext())
        initListener();
        binding.ivProfile.loadImage(preferences.getImageUri())
        binding.etProfileUserName.setText(preferences.getUserName())
        binding.etProfileEmail.setText(preferences.getEmail())
        binding.etProfilePhone.setText(preferences.getPhone())
        binding.etProfileGender.setText(preferences.getGender())
        binding.etProfileDateOfBirth.setText(preferences.getDateOfBirth())
        binding.etProfileUserName.addTextChangedListener {
            preferences.setUserName(binding.etProfileUserName.text.toString())
        }
        binding.etProfileEmail.addTextChangedListener {
            preferences.setEmail(binding.etProfileEmail.text.toString())
        }
        binding.etProfilePhone.addTextChangedListener {
            preferences.setPhone(binding.etProfilePhone.text.toString())
        }
        binding.etProfileGender.addTextChangedListener {
            preferences.setGender(binding.etProfileGender.text.toString())
        }
        binding.etProfileDateOfBirth.addTextChangedListener {
            preferences.setDateOfBirth(binding.etProfileDateOfBirth.text.toString())
        }
        binding.btnExit.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            Toast.makeText(requireContext(), "Аккаунт был удален", Toast.LENGTH_SHORT).show()
        }


    }

    private fun initListener() {
        binding.ivProfile.setOnClickListener {
            mGetContent.launch("image/*");
        }
    }

}