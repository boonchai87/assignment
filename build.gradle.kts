import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.7.1"
	id("io.spring.dependency-management") version "1.0.11.RELEASE"
	kotlin("jvm") version "1.6.21"
	kotlin("plugin.spring") version "1.6.21"

	// add by neng
	kotlin("plugin.allopen") version "1.4.32"
	id("org.jetbrains.kotlin.plugin.jpa") version "1.6.21"// fixed no argument

}

group = "com.example"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_1_8

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	testImplementation("org.springframework.boot:spring-boot-starter-test")


	// add by neng
	runtimeOnly("org.springframework.boot:spring-boot-devtools")// for reload
	implementation("org.springframework.boot:spring-boot-starter-data-mongodb")// mongodb
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")// for jpa
	implementation("org.postgresql:postgresql:42.1.4")// postgresql driver
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	// https://mvnrepository.com/artifact/junit/junit
	testImplementation("junit:junit:4.13.2")
	// https://mvnrepository.com/artifact/org.mockito/mockito-core
	//testImplementation("org.mockito:mockito-core:2.1.0")
// https://mvnrepository.com/artifact/com.ninja-squad/springmockk
	implementation("com.ninja-squad:springmockk:3.1.1")
// https://mvnrepository.com/artifact/javax.validation/validation-api
	implementation("javax.validation:validation-api:2.0.1.Final")// validation
	// https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-thymeleaf
	implementation("org.springframework.boot:spring-boot-starter-thymeleaf:2.7.1")


}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "1.8"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
