package software.mys.guardaditoapp.data.network

interface CallBack<T> {
    fun onSuccess(result: T)
    fun onFailure(error: Exception)
}