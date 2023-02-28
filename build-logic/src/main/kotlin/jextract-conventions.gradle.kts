import io.github.krakowski.jextract.JextractPlugin

plugins {
    id("java-conventions")
    //id("io.github.krakowski.jextract")
}

// TODO: https://github.com/krakowski/gradle-jextract/pull/14
apply<JextractPlugin>()
