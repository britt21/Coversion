package com.example.conversion

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.conversion.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {

    lateinit var binding : FragmentHomeBinding
    lateinit var viewModel : HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
     binding = FragmentHomeBinding.inflate(inflater)
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)


        binding.button.setOnClickListener {
            val editText = binding.editText.text.toString()
            if (editText.isEmpty()) {
                Toast.makeText(requireContext(), "Input some text", Toast.LENGTH_SHORT).show()
            } else {
             getRate()
            }

        }


        val tobalance = HomeFragmentDirections.actionHomeFragment2ToBalanceFragment()
        binding.walletImg.setOnClickListener {
            findNavController().navigate(tobalance)
        }


        return binding.root
    }

    private fun ReadatafromDatabase() {
        lifecycleScope.launch {
            viewModel.readData.ObserveOnce(viewLifecycleOwner, Observer { database ->
                if (database.isNullOrEmpty()) {
                    getRate()

                } else {

                }
            })
        }
    }


    private fun getRate() {
        val spinone = binding.spinner.selectedItem.toString()
        val spintwo = binding.spinner2.selectedItem.toString()
        val editText = binding.editText.text.toString()

        viewModel.getCurrency(editText, spinone, spintwo)
        viewModel.livecurr.observe(this, Observer { result ->
            if (result != null) {
                val resulttxt = binding.rateit
                resulttxt.text = result.data
            }
        })
    }
}

