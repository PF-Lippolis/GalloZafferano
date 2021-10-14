package com.roostersoft.gallozafferano

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.roostersoft.gallozafferano.adapter.RecipeAdapter
import com.roostersoft.gallozafferano.databinding.FragmentDetailsBinding
import com.roostersoft.gallozafferano.databinding.FragmentListBinding

class ListFragment : Fragment() {

    private var _binding: FragmentListBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val args: ListFragmentArgs by navArgs()
    private val viewModel: RecipeViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.recipes.observe(viewLifecycleOwner) {
            binding.listFragmentRecipesList.adapter = RecipeAdapter(
                getString(R.string.welcome_back, args.name),
                it,
                {})
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}