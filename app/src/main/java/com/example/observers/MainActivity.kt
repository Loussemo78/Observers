package com.example.observers

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.observers.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val viewModel : MyViewModel = MyViewModel()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = MyAdapter()
        binding.recyclerView.adapter = adapter


        /*on observe TOUJOURS  UN  RESULTAT d'une interaction CRUD  : ADD ou SET , GET , UPDATE ou PUT , DELETE
        On observe les mises à jour du stringListLiveData avant d'ajouter un élément dans addString
        pour s'assurer que l'observateur (dans notre cas, la RecyclerView) est prêt à recevoir les nouvelles données.
        Si l'on exécute d'abord addString avant de s'inscrire à l'observateur, il y a une possibilité que la liste ne soit pas correctement mise à jour dans la RecyclerView,
        car celle-ci n'était pas prête à recevoir les nouvelles données au moment de l'ajout.
        En observant d'abord, on garantit que la liste sera mise à jour dans la RecyclerView dès qu'elle sera prête à recevoir les nouvelles données.*/

        viewModel.stringListLiveData.observe(this) {
            list -> adapter.submitList(list)
        }

        /*Un exemple où cela pourrait arriver serait si la RecyclerView était encore en train de se charger ou que son adaptateur n'avait pas encore été configuré lorsque l'ajout de données a été effectué.
         Si l'ajout est effectué trop tôt, avant que la RecyclerView ne soit prête à recevoir les nouvelles données, alors la RecyclerView ne sera pas mise à jour correctement.
         Cela pourrait également arriver si les mises à jour sont effectuées sur un thread différent de celui de la RecyclerView. Dans tous les cas, il est important d'attendre que la RecyclerView soit prête à recevoir les nouvelles données avant de les ajouter.
         */

        binding.addButton.setOnClickListener {
            val input = binding.editText.text.toString()
            if (input.isNotBlank()) {
                viewModel.addString(input)
                binding.editText.setText("")
            }
        }

    }
}