package com.assignment.arch

/**
 * Author: Ankit Singh
 * Package: com.assignment.arch
 * Project: EITC du Assignment
 **/

interface ObserverContract {

    fun observeNetworkResponse() {}

    fun observeNavigationEvent() {}

    fun removeObservers() {}
}
