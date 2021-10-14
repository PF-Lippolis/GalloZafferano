package com.roostersoft.gallozafferano

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.roostersoft.gallozafferano.databinding.FragmentDetailsBinding

class DetailsFragment : Fragment() {

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
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}