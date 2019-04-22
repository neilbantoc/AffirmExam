package neilbantoc.affirm

enum class Config(val baseUrl: String) {
    PROD("");

    companion object {
        val currentConfig = PROD
    }
}