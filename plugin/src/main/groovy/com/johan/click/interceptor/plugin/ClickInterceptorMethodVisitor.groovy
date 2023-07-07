package com.johan.click.interceptor.plugin

import org.objectweb.asm.Label
import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.Opcodes


class ClickInterceptorMethodVisitor extends MethodVisitor {

    ClickInterceptorMethodVisitor(int api) {
        super(api)
    }

    ClickInterceptorMethodVisitor(int api, MethodVisitor methodVisitor) {
        super(api, methodVisitor)
    }

    @Override
    void visitCode() {
        super.visitCode()
        visitMethodInsn(Opcodes.INVOKESTATIC, "com/johan/click/interceptor/library/ClickInterceptorManager", "getInstance", "()Lcom/johan/click/interceptor/library/ClickInterceptorManager;", false)
        visitVarInsn(Opcodes.ALOAD, 1)
        visitMethodInsn(Opcodes.INVOKEVIRTUAL, "com/johan/click/interceptor/library/ClickInterceptorManager", "isIntercept", "(Landroid/view/View;)Z", false)
        Label label0 = new Label()
        visitJumpInsn(Opcodes.IFEQ, label0)
        visitInsn(Opcodes.RETURN)
        visitLabel(label0)
    }

}