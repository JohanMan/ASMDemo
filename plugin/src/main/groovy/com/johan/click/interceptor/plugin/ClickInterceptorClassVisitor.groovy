package com.johan.click.interceptor.plugin

import org.objectweb.asm.ClassVisitor
import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.Opcodes

class ClickInterceptorClassVisitor extends ClassVisitor {

    ClickInterceptorClassVisitor(int api) {
        super(api)
    }

    ClickInterceptorClassVisitor(int api, ClassVisitor classVisitor) {
        super(api, classVisitor)
    }

    @Override
    MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
        MethodVisitor visitor = cv.visitMethod(access, name, descriptor, signature, exceptions)
        if (((access & Opcodes.ACC_PUBLIC) != 0 && (access & Opcodes.ACC_STATIC) == 0) &&
                name == "onClick" &&
                descriptor == "(Landroid/view/View;)V") {
            return new ClickInterceptorMethodVisitor(Opcodes.ASM5, visitor)
        }
        return visitor
    }

}