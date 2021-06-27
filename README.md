# Deezer API implementation with coroutines and Android jetpack components

[![Kotlin](https://kotlin.link/awesome-kotlin.svg)](https://kotlinlang.org/)
[![Kotlin Version](https://img.shields.io/badge/kotlin-1.4.32-blue.svg)](http://kotlinlang.org/)


### Clean Architecture Description and Implementation

This project uses MVVM with coroutines, androidx and android lifecycle components, ViewModel , Navigation and Paging  to display a Searchable list of artists from [Deezer API](https://developers.deezer.com/) using Retrofit . Selecting Artist will display a List of his/her albums and can enter the album to see the list of tracks for this album .

After bring the artist list from the Api we will handle the response with the Paging source and hand it to the viewModel to help the fragment display the list using PagingDataAdapter
The navigation between the fragments and send the data between them it will happen with the help of Navigation component 

### Package Details
* **api:** Contain the Apiservices , custom caller adapter to help with the network handling and the list repository for all the lists that we have 
* **base:** Contain base file to be extended in the app and generic adapters to help reduce boilerplate code 
* **DI:** Contain the Dependency injection modules 
* **Utils:** Contain helpers for many purposes (like binding adapters , extensions) 
* **album, tracks, artist:** Contain the views , models and ViewModels for each fragment that we have 

 

### Frameworks and Libraries

* Architecture components 
* coroutines for async processing
* Gson for Json Parsing
* Koin for dependency injection
* Glide for Image Loading
* Coroutines event pipe to help send event to update the view

