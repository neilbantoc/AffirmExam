package neilbantoc.affirm

enum class Config(val baseUrl: String) {
    PROD("https://api.flickr.com/services/");

    companion object {
        val currentConfig = PROD
    }
}