package net.spooncast.ext.string

fun String.isValidIpAddress(): Boolean {
    val regex = """^((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.){3}(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$"""
    return this.matches(Regex(regex))
}

fun String.isValidWebsocketUrl(): Boolean {
    val regex = "^(ws|wss)://[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*(:[1-9][0-9]{0,4})?(/\\S*)?$"
    return this.matches(Regex(regex))
}
