package com.example.aziz_musaev_hw_14.auth

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.example.aziz_musaev_hw_14.R
import com.example.aziz_musaev_hw_14.databinding.FragmentAuthBinding
import com.example.aziz_musaev_hw_14.extenssion.showToast
import com.google.firebase.FirebaseException
import com.google.firebase.auth.*
import java.util.concurrent.TimeUnit


class AuthFragment : Fragment() {

    private lateinit var binding: FragmentAuthBinding
    private var auth = FirebaseAuth.getInstance()
    private var correctCode: String? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentAuthBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.countryCode.setOnCountryChangeListener {
            binding.etPhone.setText("+"+ binding.countryCode.selectedCountryCode)
        }
        initListener()
    }


    private fun initListener() {
        binding.btnSend.setOnClickListener {
            if (binding.etPhone.text!!.isNotEmpty()) {
                sendPhone()
                Toast.makeText(requireContext(), "Отправка", Toast.LENGTH_SHORT).show()
            } else {
                binding.etLayoutPhone.error = "Введите номер телефона"
            }
            binding.btnConfirm.setOnClickListener {
                if (binding.etCode.text.toString() == ""){
                    binding.etLayoutCode.error = "Введите код"
                }
                else{
                    sendCode()
                }

            }
        }
    }

    private fun sendPhone() {
        auth.setLanguageCode("RU")
        val options = PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber(binding.etPhone.text.toString())       // Phone number to verify
            .setTimeout(120L, TimeUnit.SECONDS) // Timeout and unit
            .setActivity(requireActivity())                 // Activity (for callback binding)
            .setCallbacks(object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                override fun onVerificationCompleted(p0: PhoneAuthCredential) {

                }

                override fun onVerificationFailed(exception: FirebaseException) {
                    showToast(exception.message.toString())
                }

                override fun onCodeSent(
                    verificationCode: String,
                    p1: PhoneAuthProvider.ForceResendingToken,
                ) {
                    correctCode = verificationCode
                    binding.countryCode.isVisible=false
                    binding.etLayoutPhone.isVisible = false
                    binding.btnSend.isVisible = false
                    binding.etLayoutCode.isVisible = true
                    binding.btnConfirm.isVisible = true
                    super.onCodeSent(verificationCode, p1)
                }

            })          // OnVerificationStateChangedCallbacks
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)
    }



    private fun sendCode() {

        val credential = correctCode?.let { it1 ->
            PhoneAuthProvider.getCredential(it1,
                binding.etCode.text.toString())
        }
        if (credential != null) {
            signInWithPhoneAuthCredential(credential)
        }
    }

    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        auth.signInWithCredential(credential)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("ololo", "signInWithCredential:success")

                    findNavController().navigate(R.id.navigation_home)
                } else {
                    // Sign in failed, display a message and update the UI
                    Log.w("ololo", "signInWithCredential:failure", task.exception)
                    if (task.exception is FirebaseAuthInvalidCredentialsException) {
                        Log.e("ololo",
                            "signInWithPhoneAuthCredential: " + task.exception.toString())
                    }
                    // Update UI
                }
            }
    }
}