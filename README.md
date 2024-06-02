[![](https://jitpack.io/v/darkryh/SomosKudasai.svg)](https://jitpack.io/#darkryh/SomosKudasai)

Api for Anime news site Somos Kudasai + Builded with kotlin + Kotlin Coroutines :balloon:

# Installation
with Gradle
```groovy  
repositories {   
	maven { url 'https://jitpack.io' }  
}  
  
dependencies {  
	implementation("com.github.darkryh:SomosKudasai:$version")
}  
```  
# Example to get HomePage and search the especific new - Use Case
```kotlin
ViewModel() {

	fun getHome() = viewModelScope.launch(IO) {

            // Getting Home object
            val home = SomosKudasai.getHome()

            // Getting the recent news retun a list of NewsPreview
            val recentNews  = home?.recentNews ?: return@launch

            val firstNews = recentNews.first()

            //Getting Info from url from first recent new return a News Object
            val newsDetail = SomosKudasai.getNewsInfo(firstNews.url)
	}
}
```
# Example of different tags for binding
You can check example in the app in compose too.
```kotlin
ViewModel() {

  fun bindingComposeOrLayout() {
  //return a News Object
  val newsDetail = SomosKudasai.getNewsInfo("news url")

    newsDetail.structure.foreach {
      // do your binding proccess with every different tag about the new
      is P -> it.text
      is Image -> it.src
      is H1 -> it.text
      is H2 -> it.text
      is H3 -> it.text
      is H4 -> it.text
      is H5 -> it.text
      is Ul -> it.items
      is Iframe -> it.url
      is Video -> it.src
      else -> null
    }
  }
}
```

# Requests
- **Home** : the function getHome() return a Home object
- **News** : the function getNewsInfo() return a News Object

# Objects
```kotlin
data class Home(
    val trendsOfTheWeek : List<NewsPreview>,
    val recentNews : List<NewsPreview>,
    val japanNews : List<NewsPreview>,
    val otakuCulture : List<NewsPreview>,
    val reviews : List<NewsPreview>
)
```
```kotlin
data class NewsPreview(
    val title: String,
    val image: String,
    val type : String?,
    val date : String,
    val author : String?,
    val url : String
)
```
```kotlin
data class News(
    val title: String,
    val image: String,
    val type : String,
    val date : String,
    val author : String,
    val authorImage : String,
    val authorWords : String,
    val structure : List<Tag>
)
```
# Want to colaborate
f you want to help or collaborate, feel free to contact me on X account @Darkryh or just make a request.
