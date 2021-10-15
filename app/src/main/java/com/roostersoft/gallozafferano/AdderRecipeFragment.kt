package com.roostersoft.gallozafferano

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.roostersoft.gallozafferano.databinding.FragmentAdderRecipeBinding
import com.roostersoft.gallozafferano.databinding.FragmentListBinding
import com.roostersoft.gallozafferano.model.Recipe
import com.roostersoft.gallozafferano.viewModel.RecipeViewModel

class AdderRecipeFragment : Fragment() {

    private var _binding: FragmentAdderRecipeBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val args: ListFragmentArgs by navArgs()
    private val viewModel: RecipeViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAdderRecipeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.modificationResponse.observe(viewLifecycleOwner) {
            //If it is not null, a response was received.
            it?.let {
                //If the boolean is true, the request was successful, we navigate to the list
                if(it.first) {
                    Toast.makeText(activity, it.second, Toast.LENGTH_LONG).show()
                    val action = AdderRecipeFragmentDirections.actionAdderRecipeFragmentToWaitingFragment()
                    findNavController().navigate(action)
                    viewModel.modificationResponse.postValue(null)
                } else {
                    //The boolean is false, we make the user remain on the screen in case they want to retry
                    Toast.makeText(activity, it.second, Toast.LENGTH_LONG).show()
                    viewModel.modificationResponse.postValue(null)
                }
            }
        }


        binding.btnAdderRecipe.setOnClickListener {
            val title = binding.txtInputAdderRecipeTitle.text.toString()
            val body = binding.txtInputAdderRecipeComment.text.toString()
            val recipe = Recipe(title, body)
            viewModel.addRecipe(recipe)
        }
    }
}