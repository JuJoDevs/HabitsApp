import com.jujodevs.buildlogic.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class JujodevsFeaturePresentation : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("jujodevs.android.library")
                apply("jujodevs.android.library.compose")
                apply("jujodevs.android.hilt")
            }

            dependencies {
                add("implementation", project(":core:presentation"))
                add("implementation", libs.findLibrary("androidx.core.ktx").get())
                add("implementation", libs.findLibrary("hilt.navigation.compose").get())
            }
        }
    }
}