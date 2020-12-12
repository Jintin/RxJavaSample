package com.jintin.rxJava

import io.reactivex.rxjava3.core.Observable

object OperatorSample {
    @JvmStatic
    fun main(args: Array<String>) {
        map()
        filter()
    }

    private fun map() {
        Observable.just(1, 2, 3, 4, 5)
            .map { it * 2 }
            .subscribe {
                println(it)
            }
    }

    private fun filter() {
        Observable.just(1, 2, 3, 4, 5)
            .filter { it % 2 == 0 }
            .subscribe {
                println(it)
            }
    }
}