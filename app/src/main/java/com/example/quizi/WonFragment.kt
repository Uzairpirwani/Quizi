package com.example.quizi

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.quizi.databinding.FragmentWonBinding


class WonFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = DataBindingUtil.inflate<FragmentWonBinding>(inflater, R.layout.fragment_won, container, false
        )

        binding.tryAgain.setOnClickListener {
            findNavController().navigate(R.id.title_fragment)
        }

        return binding.root
//    	return inflater.inflate
    }

}
