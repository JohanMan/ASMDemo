package com.johan.click.interceptor.plugin

import com.android.build.api.transform.JarInput
import org.apache.commons.io.IOUtils
import org.objectweb.asm.ClassReader
import org.objectweb.asm.ClassWriter
import org.objectweb.asm.Opcodes

import java.util.jar.JarEntry
import java.util.jar.JarFile
import java.util.jar.JarOutputStream
import java.util.zip.ZipEntry

class ClickInterceptorPluginUtil {

    static void handleDirectoryFile(File file) {
        // 只处理Class文件
        if (file.absolutePath.endsWith(".class")) {
            // ClassReader读取class文件的内容
            def classReader = new ClassReader(file.bytes)
            // ClassWriter用于回写修改后的class内容
            def classWriter = new ClassWriter(classReader, ClassWriter.COMPUTE_MAXS)
            // ClassWriter其实也是ClassVisitor的子类，这样就可构造出一个自定义的ClassVisitor
            def classVisitor = new ClickInterceptorClassVisitor(Opcodes.ASM5, classWriter)
            // ClassVisitor处理class
            classReader.accept(classVisitor, ClassReader.EXPAND_FRAMES)
            // 处理后的数据写回文件
            def bytes = classWriter.toByteArray()
            def outputStream = new FileOutputStream(file.path)
            outputStream.write(bytes)
            outputStream.close()
        }
    }

    static void handleJar(JarInput jarInput) {
        def jarFile = jarInput.file
        // 临时文件
        def optJar = new File(jarFile.getParent(), jarFile.name + ".opt")
        if (optJar.exists()) {
            optJar.delete()
        }
        def file = new JarFile(jarFile)
        Enumeration enumeration = file.entries()
        JarOutputStream jarOutputStream = new JarOutputStream(new FileOutputStream(optJar))
        while (enumeration.hasMoreElements()) {
            JarEntry jarEntry = (JarEntry) enumeration.nextElement()
            String entryName = jarEntry.getName()
            ZipEntry zipEntry = new ZipEntry(entryName)
            InputStream inputStream = file.getInputStream(jarEntry)
            jarOutputStream.putNextEntry(zipEntry)
            // 处理Class文件
            if (entryName.endsWith(".class")) {
                // ClassReader读取class文件的内容
                def classReader = new ClassReader(inputStream)
                // ClassWriter用于回写修改后的class内容
                def classWriter = new ClassWriter(classReader, ClassWriter.COMPUTE_MAXS)
                // ClassWriter其实也是ClassVisitor的子类，这样就可构造出一个自定义的ClassVisitor
                def classVisitor = new ClickInterceptorClassVisitor(Opcodes.ASM5, classWriter)
                // ClassVisitor处理class
                classReader.accept(classVisitor, ClassReader.EXPAND_FRAMES)
                // 处理后的数据写回文件
                def bytes = classWriter.toByteArray()
                jarOutputStream.write(bytes)
            } else {
                jarOutputStream.write(IOUtils.toByteArray(inputStream))
            }
            inputStream.close()
            jarOutputStream.closeEntry()
        }
        jarOutputStream.close()
        file.close()
        // 删除临时文件
        if (jarFile.exists()) {
            jarFile.delete()
        }
        // 临时文件重名为原来jar文件
        optJar.renameTo(jarFile)
    }

}