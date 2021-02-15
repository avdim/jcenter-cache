plugins {
    `java-library`
    `maven-publish`
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

group = "org.sample"
version = "1.0-SNAPSHOT"

publishing {
    publications {
        create<MavenPublication>("test") {
            groupId = "org.sample"
            artifactId = "artifact-lib"
            version = "0.1"
            from(components["java"])
        }
    }
}
