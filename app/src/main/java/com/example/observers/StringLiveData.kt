package com.example.observers

import androidx.lifecycle.MutableLiveData

class StringListLiveData : MutableLiveData<List<String>>() {

    /*La propriété value est une propriété publique de la classe LiveData de la bibliothèque d'architecture Android Jetpack.
   Elle représente la valeur actuelle stockée dans l'objet LiveData.
   Les objets LiveData sont utilisés pour stocker des données qui doivent être observées par des composants Android, tels que des activités, des fragments, ou des services.
   Lorsqu'une nouvelle valeur est affectée à value, tous les observateurs enregistrés pour cet objet LiveData sont notifiés de la mise à jour de la valeur,
   ce qui leur permet de réagir en conséquence.
   Les observateurs peuvent être enregistrés en appelant la méthode observe() de l'objet LiveData.*/

    init {
        value = listOf()  // setValue = rentrer une valeur
    }


    fun addString(string: String) {
        val list = value?.toMutableList() ?: mutableListOf()
        list.add(string)
        value = list
    }

    /*Ainsi, chaque fois que la fonction addString(string: String) est appelée,
    la liste stockée dans l'objet StringListLiveData est mise à jour avec la nouvelle chaîne de caractères
    et tous les observateurs de cet objet sont notifiés de la mise à jour.*/
}
