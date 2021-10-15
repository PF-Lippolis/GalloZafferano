package com.roostersoft.gallozafferano

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.roostersoft.gallozafferano.databinding.FragmentDetailsBinding
import com.roostersoft.gallozafferano.viewModel.RecipeViewModel

class DetailsFragment : Fragment() {
    private val viewModel:RecipeViewModel by activityViewModels()
    private var _binding: FragmentDetailsBinding? = null
    // This property is only valid between onCreateView and
// onDestroyView.
    private val binding get() = _binding!!
    private val args:DetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun onClickButton() {
        binding.iconDelete.setOnClickListener{
            showCustomDialog()
        }
    }

    private fun showCustomDialog() {
        MaterialAlertDialogBuilder(requireActivity())
            .setTitle("Attenzione")
            .setMessage("Eliminare definitivamente la ricetta?")
            .setNegativeButton("No",null)
            .setPositiveButton("Si"){
                _, _ ->
                deleteRecipe()
            }.show()
    }

    private fun deleteRecipe() {
        viewModel.deleteRecipe(args.recipe)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recipeDetailsName.text = args.recipe.title
        binding.recipeDetailsDescription.text = args.recipe.body

        val circularProgressDrawable = CircularProgressDrawable(binding.recipeDetailsImage.context)
        circularProgressDrawable.strokeWidth = 5f
        circularProgressDrawable.centerRadius = 30f
        circularProgressDrawable.start()

        Glide.with(this)
            .load(args.recipe.imageUrl)
            .placeholder(circularProgressDrawable)
            .error(R.drawable.sad_face)
            .centerCrop()
            .into(binding.recipeDetailsImage)

        viewModel.modificationResponse.observe(viewLifecycleOwner) {
            //If it is not null, a response was received.
            it?.let {
                //If the boolean is true, the request was successful, we navigate to the list
                if (it.first) {
                    Toast.makeText(activity, it.second, Toast.LENGTH_LONG).show()
                    val action =
                        DetailsFragmentDirections.actionDetailsFragmentToWaitingFragment()
                    findNavController().navigate(action)
                    viewModel.modificationResponse.postValue(null)
                } else {
                    //The boolean is false, we make the user remain on the screen in case they want to retry
                    Toast.makeText(activity, it.second, Toast.LENGTH_LONG).show()
                    viewModel.modificationResponse.postValue(null)
                }
            }
            onClickButton()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}