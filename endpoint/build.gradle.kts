plugins {
    id("org.springframework.boot") apply false
    id("io.spring.dependency-management")
    `java-library`

    id("org.owasp.dependencycheck")
}

apply(from = "$rootDir/gradle/configJavaModule.gradle.kts")

// https://spring.io/blog/2016/12/16/dependency-management-plugin-1-0-0-rc1
dependencyManagement {
    imports {
        mavenBom(org.springframework.boot.gradle.plugin.SpringBootPlugin.BOM_COORDINATES)
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:${project.properties["springCloudVersion"]}")
    }
}

configurations {
    all {
        exclude(group = "commons-logging", module = "commons-logging")
        exclude(group = "com.zaxxer", module = "HikariCP-java7")
        exclude(group = "junit", module = "junit")
        exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
    }
}

dependencies {
    api(project(":service"))

    implementation("org.slf4j:slf4j-api")

    implementation("org.springframework.boot:spring-boot")

    implementation("org.hibernate.validator:hibernate-validator")

    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springdoc:springdoc-openapi-starter-common:${project.properties["springdocVersion"]}")

    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude(group = "junit", module = "junit")
    }
}

// *********************************************************************************************************************
// TEST


// *********************************************************************************************************************
// BUILD

tasks.getByName<Jar>("jar") {
    val projectName: String by project
    val name: String by project
    val archiveExtension: String by archiveExtension
    archiveFileName.set("${projectName}-${name}.${archiveExtension}")
}