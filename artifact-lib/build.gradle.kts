plugins {
    `java-library`
    `maven-publish`
    id("ru.tutu.github.token") version "1.2.0"
}

gitHubToken {
    id = "jcenter-cache-publish"
    scope = "read:packages,write:packages"
    secretAES = "not-so-big-secret-key"
    storeTokenAtHomeGradleProperties()
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

group = "org.sample"
version = "1.0-SNAPSHOT"

publishing {
    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/avdim/jcenter-cache")
            credentials {
                username = "avdim"
                password = gitHubToken.getToken(project)
            }
        }
    }
    publications {
        create<MavenPublication>("my") {
            groupId = "org.sample"
            artifactId = "artifact-lib"
            version = "0.1"
            from(components["java"])
        }
    }
}
