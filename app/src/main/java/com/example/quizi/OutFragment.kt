package com.example.quizi

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.quizi.databinding.FragmentOutBinding

class OutFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = DataBindingUtil.inflate<FragmentOutBinding>(
        inflater, R.layout.fragment_out, container, false)

        binding.tryAgain.setOnClickListener{
            findNavController().navigate(R.id.title_fragment)
        }

        return binding.root

    }

}
