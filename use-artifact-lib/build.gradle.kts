plugins {
    application
    java
    kotlin("jvm") version "1.4.30"
    id("ru.tutu.github.token") version "1.2.0"
}

gitHubToken {
    id = "jcenter-cache-publish"
    scope = "read:packages,write:packages"
    secretAES = "not-so-big-secret-key"
    storeTokenAtHomeGradleProperties()
}

dependencies {
    implementation("org.sample:artifact-lib:0.1")
}

application.mainClassName = "org.sample.MainKt"

repositories {
    mavenCentral()
    maven {
        name = "GitHubPackages"
        url = uri("https://maven.pkg.github.com/avdim/jcenter-cache")
        credentials {
            username = "avdim"
            password = gitHubToken.getToken(project)
        }
    }
}
