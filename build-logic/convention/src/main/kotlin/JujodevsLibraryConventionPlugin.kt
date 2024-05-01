import com.jujodevs.buildlogic.configureKotlin
import com.jujodevs.buildlogic.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class JujodevsLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("java-library")
                apply("org.jetbrains.kotlin.jvm")
            }

            configureKotlin(false)

            dependencies {
                add("implementation", libs.findLibrary("kotlinx-coroutines-core").get())
            }
        }
    }
}