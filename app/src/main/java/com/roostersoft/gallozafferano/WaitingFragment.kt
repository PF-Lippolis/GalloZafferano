package com.roostersoft.gallozafferano

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import com.roostersoft.gallozafferano.viewModel.RecipeViewModel


//fragment richiamato subito dopo il login, per mostrare una barra
//di attesa mentre il server restituisce i dati
class WaitingFragment : Fragment() {
    private val TAG = WaitingFragment::class.java.simpleName
    private val viewModel:RecipeViewModel by activityViewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_waiting, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.recipes.observe(viewLifecycleOwner){
            val action:NavDirections

            var pref = view.context.getSharedPreferences("user", Context.MODE_PRIVATE)
            val name = pref.getString("user", null)?:"utente"
            action = WaitingFragmentDirections.actionWaitingFragmentToListFragment(name)


            view.findNavController().navigate(action)
        }
    }
}