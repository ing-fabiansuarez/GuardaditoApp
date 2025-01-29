package software.mys.guardaditoapp.data.network

interface CallBackApp<T> {
    fun onSuccess(result: T)
    fun onFailure(error: Exception)
}