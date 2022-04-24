package com.example.oauthtest

import co.infinum.retromock.BodyFactory
import java.io.FileInputStream
import java.io.IOException
import java.io.InputStream


internal class ResourceBodyFactory : BodyFactory {
    @Throws(IOException::class)
    override fun create(input: String): InputStream {
        return ResourceBodyFactory::class.java.classLoader.getResource(input).openStream()
    }
}