package com.example.aziz_musaev_hw_14.ui.onBoard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.aziz_musaev_hw_14.R
import com.example.aziz_musaev_hw_14.databinding.FragmentOnBoardBinding


class OnBoardFragment : Fragment() {

private lateinit var binding: FragmentOnBoardBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOnBoardBinding.inflate(LayoutInflater.from(context),container,false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = BoardAdapter(childFragmentManager,this::onSkipClick,this::onNextClick)
        binding.vpBoard.adapter = adapter
        binding.springDotsIndicator.attachTo(binding.vpBoard)
    }
private  fun onSkipClick()
{
    binding.vpBoard.currentItem = 2
}
    private fun onNextClick(){
        binding.vpBoard.currentItem += 1
    }
}