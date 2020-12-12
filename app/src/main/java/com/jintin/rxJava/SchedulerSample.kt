package com.jintin.rxJava

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers

object SchedulerSample {
    @JvmStatic
    fun main(args: Array<String>) {
        subscribeOn()
        Thread.sleep(3000)
        observeOn()
        Thread.sleep(3000)
        mix()
        Thread.sleep(3000)
    }

    // subscribeOn only work first time
    private fun subscribeOn() {
        println("---subscribeOnTest")
        Observable.create<Int> {
            println("observable in: " + Thread.currentThread().name)
            for (i in 1..5) {
                it.onNext(i)
            }
            it.onComplete()
        }
            .subscribeOn(Schedulers.newThread())
            .map {
                println("map in: " + Thread.currentThread().name)
                it * 2
            }
            .subscribeOn(Schedulers.io())
            .subscribe {
                println("observe in: " + Thread.currentThread().name)
                println(it)
            }
    }

    // observeOn affect only all later operation
    private fun observeOn() {
        println("---observeOnTest")
        Observable.create<Int> {
            println("observable in: " + Thread.currentThread().name)
            for (i in 1..5) {
                it.onNext(i)
            }
            it.onComplete()
        }
            .observeOn(Schedulers.newThread())
            .map {
                println("map in: " + Thread.currentThread().name)
                it * 2
            }
            .observeOn(Schedulers.io())
            .subscribe {
                println("observe in: " + Thread.currentThread().name)
                println(it)
            }
    }

    private fun mix() {
        println("---mixTest")
        Observable.create<Int> {
            println("observable in: " + Thread.currentThread().name)
            for (i in 1..5) {
                it.onNext(i)
            }
            it.onComplete()
        }
            .observeOn(Schedulers.newThread())
            .map {
                println("map in: " + Thread.currentThread().name)
                it * 2
            }
            .observeOn(Schedulers.io())
            .subscribeOn(Schedulers.newThread())
            .subscribe {
                println("observe in: " + Thread.currentThread().name)
                println(it)
            }
    }

}