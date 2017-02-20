
class UrlHelper {
  static serverUrl(url) {
    if (!PRODUCTION) {
      return `http://localhost:8080${url}`
    }

    return url
  }
}

module.exports = UrlHelper
