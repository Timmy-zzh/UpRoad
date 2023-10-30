package com.timmy.libbase.ext

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import java.lang.reflect.InvocationTargetException
import java.lang.reflect.ParameterizedType

/**
 * 扩展函数
 */

fun <VB : ViewBinding> AppCompatActivity.inflateViewBindingWithGeneric(layoutInflater: LayoutInflater): VB =
    genericViewBindingClass(this) { clazz: Class<VB> ->
        clazz.getMethod("inflate", LayoutInflater::class.java).invoke(null, layoutInflater) as VB
    }

fun <VB : ViewBinding> Fragment.inflateViewBindingWithGeneric(
    layoutInflater: LayoutInflater,
    parent: ViewGroup?,
    attachToParent: Boolean
): VB =
    genericViewBindingClass(this) { clazz ->
        clazz.getMethod(
            "inflate",
            LayoutInflater::class.java,
            ViewGroup::class.java,
            Boolean::class.java
        )
            .invoke(null, layoutInflater, parent, attachToParent) as VB
    }

fun <VB> genericViewBindingClass(any: Any, block: (Class<VB>) -> VB): VB {
    var genericSuperclass = any.javaClass.genericSuperclass
    var superclass = any.javaClass.superclass
    while (superclass != null) {
        if (genericSuperclass is ParameterizedType) {
            // 获取该类的泛型参数
            try {
                return block.invoke(genericSuperclass.actualTypeArguments[0] as Class<VB>)
            } catch (e: NoSuchMethodError) {
            } catch (e: ClassCastException) {
            } catch (e: InvocationTargetException) {
                throw e.targetException
            }
        }

        genericSuperclass = superclass.genericSuperclass
        superclass = superclass.superclass
    }

    throw IllegalArgumentException("there is no generic of ViewBinding!")
}


/**
 * 通过获取泛型设置的ViewBinding得到xml文件
 * - 再通过反射获取泛型的值，获取ViewBinding的值
 * - 最后调用setContentView方法，设置Activity界面的布局
 */
private fun <VB : ViewBinding> inflateViewBinding(
    act: AppCompatActivity,
    layoutInflater: LayoutInflater
): VB {
    /**
     * 获取当前类及其父类上的泛型，通过递归获取到父类的数据
     */
    // 获取超类类型,可以获取到泛型的具体参数
    var genericSuperclass = act.javaClass.genericSuperclass
    var superclass = act.javaClass.superclass
    //  genericSuperclass = com.timmy.lib.base.BaseVBActivity<com.timmy.uproad.databinding.ActivityMainBinding>
    println("genericSuperclass = $genericSuperclass")
    //  获取的是当前类的类型 superclass = class com.timmy.lib.base.BaseVBActivity
    println("superclass = $superclass")

    while (superclass != null) {
        if (genericSuperclass is ParameterizedType) {

            // 获取该类的泛型参数
            try {
                val typeArguments = genericSuperclass.actualTypeArguments
                return (typeArguments[0] as Class<VB>).getMethod(
                    "inflate",
                    LayoutInflater::class.java
                )
                    .invoke(null, layoutInflater) as VB

            } catch (e: NoSuchMethodError) {
            } catch (e: ClassCastException) {
            } catch (e: InvocationTargetException) {
                throw e.targetException
            }

            genericSuperclass = superclass.genericSuperclass
            superclass = superclass.superclass

            //  genericSuperclass = com.timmy.lib.base.BaseVBActivity<com.timmy.uproad.databinding.ActivityMainBinding>
            println("genericSuperclass22 = $genericSuperclass")
            //  获取的是当前类的类型 superclass = class com.timmy.lib.base.BaseVBActivity
            println("superclass22 = $superclass")
        }
    }
    throw IllegalArgumentException("there is no generic of ViewBinding!")
}
