package com.me.rickmorty

import org.apache.maven.artifact.versioning.DefaultArtifactVersion

data class RemoteConfigVersionModel(
    val versionAndroid: DefaultArtifactVersion,
    val urlStoreAndroid: String
)
