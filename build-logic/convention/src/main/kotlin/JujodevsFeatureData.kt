import com.jujodevs.buildlogic.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class JujodevsFeatureData : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("jujodevs.android.library")
                apply("jujodevs.android.hilt")
            }

            dependencies {
                add("implementation", project(":core:common"))
                add("implementation", libs.findLibrary("inject").get())
            }
        }
    }
}