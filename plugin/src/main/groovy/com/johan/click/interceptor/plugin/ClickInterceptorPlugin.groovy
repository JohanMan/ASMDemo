package com.johan.click.interceptor.plugin

import com.android.build.gradle.AppExtension
import org.gradle.api.Plugin
import org.gradle.api.Project

class ClickInterceptorPlugin implements Plugin<Project> {

    @Override
    void apply(Project project) {
        println("---------- use click interceptor plugin ----------")
        def appExtension = project.extensions.findByType(AppExtension.class)
        appExtension.registerTransform(new ClickInterceptorTransform())
    }

}