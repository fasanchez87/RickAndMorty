package com.me.rickmorty


class UnsupportedVersionException(
    val remoteConfigVersionModel: RemoteConfigVersionModel?
) : Exception("Your version is under the min version, please update the application and try again!!")
