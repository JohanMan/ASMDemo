package com.johan.click.interceptor.plugin

import com.android.build.api.transform.*
import com.android.build.gradle.internal.pipeline.TransformManager
import com.android.utils.FileUtils

class ClickInterceptorTransform extends Transform {

    @Override
    String getName() {
        return "ClickInterceptorTransform"
    }

    @Override
    Set<QualifiedContent.ContentType> getInputTypes() {
        return TransformManager.CONTENT_CLASS
    }

    @Override
    Set<? super QualifiedContent.Scope> getScopes() {
        return TransformManager.SCOPE_FULL_PROJECT
    }

    @Override
    boolean isIncremental() {
        return false
    }

    @Override
    void transform(TransformInvocation transformInvocation) throws TransformException, InterruptedException, IOException {
        customTransform(transformInvocation.getContext(),
                transformInvocation.getInputs(),
                transformInvocation.getOutputProvider(),
                transformInvocation.incremental)
    }

    void customTransform(Context context, Collection<TransformInput> inputs, TransformOutputProvider outputProvider, boolean isIncremental) {
        // 遍历
        inputs.forEach { TransformInput input ->
            // 遍历目录
            input.directoryInputs.each { DirectoryInput directoryInput ->
                // 遍历文件
                directoryInput.file.eachFileRecurse { File file ->
                    // 处理文件
                    ClickInterceptorPluginUtil.handleDirectoryFile(file)
                }
                // 需要将所有输入文件copy到输出，以交给下一下transform处理
                File dest = outputProvider.getContentLocation(directoryInput.name, directoryInput.contentTypes, directoryInput.scopes, Format.DIRECTORY)
                FileUtils.copyDirectory(directoryInput.file, dest)
            }
            // 遍历 jar
            input.jarInputs.each { JarInput jarInput ->
                // 处理文件
                ClickInterceptorPluginUtil.handleJar(jarInput)
                // 需要将所有输入的jar里面的文件copy到输出目录，以交给下一下transform处理
                File dest = outputProvider.getContentLocation(jarInput.name, jarInput.contentTypes, jarInput.scopes, Format.JAR)
                FileUtils.copyFile(jarInput.file, dest)
            }
        }
    }

}