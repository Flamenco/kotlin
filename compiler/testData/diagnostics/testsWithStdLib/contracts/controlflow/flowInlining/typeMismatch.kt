// !LANGUAGE: +CallsInPlaceEffect
// !DIAGNOSTICS: -INVISIBLE_REFERENCE -INVISIBLE_MEMBER

import kotlin.internal.contracts.*

fun <T> myRun(block: () -> T): T {
    contract {
        callsInPlace(block, InvocationKind.EXACTLY_ONCE)
    }
    return block()
}

fun foo(x: Int): Int = x + 1

fun typeMismatchInLambda(y: String): Int {
    val x = myRun { foo(<!TYPE_MISMATCH!>y<!>) }
    return x
}