package com.roostersoft.gallozafferano

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.roostersoft.gallozafferano.adapter.RecipeAdapter
import com.roostersoft.gallozafferano.databinding.FragmentListBinding
import com.roostersoft.gallozafferano.viewModel.RecipeViewModel

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
        var messaggioErrore:String
        viewModel.recipes.observe(viewLifecycleOwner) { list ->
            if(list == null) {
                binding.layoutError.visibility = View.VISIBLE
                binding.layoutList.visibility = View.GONE
                messaggioErrore = "Errore 404 \n :( \n riprovare più tardi"
                binding.txtListError.text = messaggioErrore
            } else if(list.isEmpty()) {
                binding.layoutError.visibility = View.VISIBLE
                binding.layoutList.visibility = View.GONE
                messaggioErrore = "Ricettario vuoto. \nNon avrai mica cancellato tutto?"
                binding.txtListError.text = messaggioErrore
            } else {
                binding.layoutError.visibility = View.GONE
                binding.layoutList.visibility = View.VISIBLE
                //We set the adapter to the recyclerview
                binding.listFragmentRecipesList.adapter = RecipeAdapter(
                    //The first string represents the title of the recycleview
                    getString(R.string.welcome_back, args.name),
                    //The second argument is the list of recipes to show
                    list) {
                    //This lambda is the element onclick listener, "it" is the recipe clicked
                    val action = ListFragmentDirections.actionListFragmentToDetailsFragment(it)
                    findNavController().navigate(action)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}