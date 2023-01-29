import org.gradle.internal.jvm.Jvm

plugins {
    id("cpp-library")
}

library {
    binaries.configureEach {
        val compileTask = compileTask.get()
        val javaHome = Jvm.current().javaHome
        compileTask.includes.from("$javaHome/include") // requires JDK

        val osFamily = targetPlatform.targetMachine.operatingSystemFamily
        when {
            osFamily.isLinux -> compileTask.includes.from("$javaHome/include/linux")
            osFamily.isWindows -> compileTask.includes.from("$javaHome/include/win32")
            osFamily.isMacOs -> compileTask.includes.from("$javaHome/include/darwin")
        }

        compileTask.source.from(fileTree( "src/main/cpp") {
            include("**/*.cpp")
        })

        @Suppress("UnstableApiUsage")
        when (toolChain) {
            is VisualCpp -> compileTask.compilerArgs.addAll("/TC")
            is GccCompatibleToolChain -> compileTask.compilerArgs.addAll("-x", "c", "-std=c11")
        }
    }
}
