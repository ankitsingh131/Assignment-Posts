package com.assignment.presentation.listener

/**
 * Author: Ankit Singh
 * Package: com.assignment.presentation.listener
 * Project: EITC du Assignment
 **/

interface OnItemClickListener<in T> {

    fun onItemClicked(item: T)
}