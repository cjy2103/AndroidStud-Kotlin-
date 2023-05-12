package com.example.protodatastore

import androidx.datastore.core.Serializer
import java.io.InputStream
import java.io.OutputStream

object UserProtoSerializer : Serializer<Data.User>{
    override val defaultValue: Data.User
        get() = Data.User.newBuilder().build()

    override suspend fun readFrom(input: InputStream): Data.User {
        return Data.User.parseFrom(input)
    }

    override suspend fun writeTo(t: Data.User, output: OutputStream) {
        t.writeTo(output)
    }

}